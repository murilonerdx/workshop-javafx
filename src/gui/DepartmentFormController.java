package gui;


import db.DbException;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;
import model.services.DepartmentService;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

    private Department entity;

    private DepartmentService service;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Button btSave;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btCancel;

    @FXML
    public void onBtSaveAction(ActionEvent event){
        if(entity == null){
            throw new IllegalStateException("Entity is null");
        }if(service == null){
            throw new IllegalStateException("Service is null");
        }
        try{
            entity = getFormData();
            service.saveOrUpdate(entity);
            Utils.currentStage(event).close();
        }catch(DbException e){
            util.Alerts.showAlert("Error saving obj", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private Department getFormData() {
        Department obj = new Department();
        obj.setId(Utils.tryParseToInt(txtId.getText()));
        obj.setName(txtName.getText());
        return obj;
    }

    @FXML
    public void onBtCancelAction(){
        System.out.println("Cancelando");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
    }

    public void setDepartment(Department entity){
        this.entity = entity;
    }

    public void setDepartmentService(DepartmentService service){
        this.service = service;
    }

    public void updateFormData(){
        if(entity == null) {
            throw new IllegalStateException("Entity is null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(entity.getName());
        System.out.println(entity.getName());
        System.out.println(entity.getId());
    }

    private void initializeNodes(){
        util.Constraints.setTextFieldInteger(txtId);
        util.Constraints.setTextFieldMaxLength(txtName, 30);
    }
}
