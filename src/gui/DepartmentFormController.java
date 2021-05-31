
package gui;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentServices;


public class DepartmentFormController implements Initializable {
    
    private Department entity;
    
    private DepartmentServices service;
    
    @FXML
    private TextField txtId;
    
    @FXML
    private TextField txtName;
    
    @FXML 
    private Label labelErrorName;
    
    @FXML
    private Button btSave;
    
    @FXML
    private Button btCancel;
    
    public void setDepartment(Department entity){
        this.entity = entity;
    }
    
    public void setDepartmentService(DepartmentServices service){
        this.service = service;
    }
    
    @FXML
    public void onBTSaveAction(ActionEvent event){
        //cria um objeto Department e insere ou faz update através dos serviços
        //do DepartmentService criado, utilizando a depêndêcia service.
        if(entity == null){
            throw new IllegalStateException("Entity was null");
        }
        if(service == null){
            throw new IllegalStateException("Service was null");
        }
        try{
        entity = getFormData();
        service.saveOrUpdate(entity);
        Utils.currentStage(event).close();
        }
        catch(DbException e){
            Alerts.showAlert("Erro ao salvar o departamento", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    public void onBtCancelAction(ActionEvent event){
        Utils.currentStage(event).close();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }
    
    private void initializeNodes(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }
    
    public void updateFormData(){
        if(entity == null){
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
    }

    //método para pegar os dados do formulário (departmentForm) e instanciar e retornar um
    //novo objeto do tipo Department.
    private Department getFormData() {
        Department obj = new Department();
        
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        obj.setName(txtName.getText());
        
        return obj;
    }
    
}