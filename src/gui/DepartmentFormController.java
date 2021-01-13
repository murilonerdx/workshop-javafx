package gui;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

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
        System.out.println("Salvando");
    }

    @FXML
    public void onBtCancelAction(){
        System.out.println("Cancelando");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
    }

    private void initializeNodes(){
        util.Constraints.setTextFieldInteger(txtId);
        util.Constraints.setTextFieldMaxLength(txtName, 30);
    }
}
