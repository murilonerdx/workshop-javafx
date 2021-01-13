package gui;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

    private Department entity;


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
    public void onBtSaveAction(){
        if(entity == null){
            throw new IllegalStateException("Entity is null");
        }
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
