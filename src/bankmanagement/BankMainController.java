/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Chandan Das
 */
public class BankMainController implements Initializable {
    
    private Label label;
    @FXML
    private ToggleButton acntHoldSceneBtn;
    @FXML
    private ToggleGroup group1;
    @FXML
    private ToggleButton brchEmpSceneBtn;
    @FXML
    private ToggleButton tranSceneBtn;
    @FXML
    private AnchorPane mainAnchor;
    @FXML
    private BorderPane rootLayout;
    @FXML
    private ToggleButton seviceBtn;
    @FXML
    private ToggleGroup group11;

   
  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         changeScene("AccountHolderScene.fxml");
        // TODO
    }    

    @FXML
    private void setAcntHoldScene(ActionEvent event) {
         changeScene("AccountHolderScene.fxml");
    }

    @FXML
    private void setBrchEmpScene(ActionEvent event) {
         changeScene("BranchEmployeeScene.fxml");
    }

    @FXML
    private void setTranScene(ActionEvent event) {
         changeScene("TransactoinScene.fxml");
    }
    
     public  void changeScene(String scenePath){
        
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource(scenePath));
        AnchorPane pane = new AnchorPane();
    try{
            pane = (AnchorPane) loader.load();
            rootLayout.setCenter(pane);
        }
        catch(Exception e){
        }
     
    }

    @FXML
    private void setServiceScene(ActionEvent event) {
         changeScene("ServiceScene.fxml");
        
    }
    
}
