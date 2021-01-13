package gui;


import db.DbException;
import gui.gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.entities.Department;
import model.exceptions.ValidationException;
import model.services.DepartmentService;

import java.net.URL;
import java.util.*;

public class DepartmentFormController implements Initializable {

    @FXML
    private Department entity;
    @FXML
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
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

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
            notifyDataSetChangeListeners();
            Utils.currentStage(event).close();

        }catch(ValidationException e){
            setErrorMessager(e.getErrors());
        } catch(DbException e){
            Alerts.showAlert("Error saving obj", null, e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private void notifyDataSetChangeListeners() {
        for(DataChangeListener listener : dataChangeListeners){
            listener.onDataChanged();
        }
    }

    public void subscribeDataChangeListener(DataChangeListener listener){
        dataChangeListeners.add(listener);
    }

    private Department getFormData() {
        Department obj = new Department();
        ValidationException exception = new ValidationException("Validation exception");

        obj.setId(Utils.tryParseToInt(txtId.getText()));
        if(txtName.getText() == null || txtName.getText().trim().equals("")){
            exception.addError("name", "Field can't be empty");
        }
        obj.setName(txtName.getText());

        if(exception.getErrors().size() > 0){
            throw exception;
        }

        return obj;
    }

    @FXML
    public void onBtCancelAction(ActionEvent event){
        Utils.currentStage(event).close();
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
    }

    private void initializeNodes(){
        util.Constraints.setTextFieldInteger(txtId);
        util.Constraints.setTextFieldMaxLength(txtName, 30);
    }

    private void setErrorMessager(Map<String, String> errors){
        Set<String> fields = errors.keySet();

        if(fields.contains("name")){
            labelErrorName.setText(errors.get("name"));
        }
    }
}
