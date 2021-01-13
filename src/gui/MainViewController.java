package gui;

import application.Main;
import gui.util.Alerts;
import model.services.DepartmentService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public class MainViewController implements Initializable {
    @FXML
    private MenuItem menuItemSeller;
    @FXML
    private MenuItem menuItemDepartament;
    @FXML
    private MenuItem menuItemAbout;


    @FXML
    public void onMenuItemSellerAction(){
        System.out.println("Vendedor");
    }

    @FXML
    public void onMenuItemDepartmentAction(){
        loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
            controller.setTableViewDepartment(new DepartmentService());
            controller.updateTableView();
        });
    }

    @FXML
    public void onMenuItemAboutAction() {
        loadView("/gui/About.fxml", x ->{});
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private <T> void loadView(String absoluteName, Consumer<T> initializingAction){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVbox = loader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVbox = (VBox)((ScrollPane)mainScene.getRoot()).getContent();


            Node mainMenu = mainVbox.getChildren().get(0);
            mainVbox.getChildren().clear();
            mainVbox.getChildren().add(mainMenu);
            mainVbox.getChildren().addAll(newVbox.getChildren());

            T controller = loader.getController();
            initializingAction.accept(controller);

        }catch(IOException e){
            Alerts.showAlert("IOException", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
