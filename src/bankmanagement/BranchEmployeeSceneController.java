/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import DB.DBConnection;
import DB.DisplayDatabase;
import DB.QueryDatabase;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chandan Das
 */
public class BranchEmployeeSceneController implements Initializable {

    @FXML
    private AnchorPane branchEmpAnchor;
    @FXML
    private TextField bCode;
    @FXML
    private TextField bName;
    @FXML
    private TextField bLoc;
    @FXML
    private TextField eName;
    @FXML
    private ComboBox<String> cBranch;
    @FXML
    private TableView<?> eTableView;

    /**
     * Initializes the controller class.
     */
     ObservableList<String> branchList = FXCollections.observableArrayList();
     DisplayDatabase empData = new DisplayDatabase();
    @FXML
    private TextField eSName;
    @FXML
    private Label warnMsg;
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ResultSet rs = QueryDatabase.query("Select BCode from branchtable;");
        if(rs!=null){
            try {
                while(rs.next()){
                    branchList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(BranchEmployeeSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        cBranch.setItems(branchList);
        
        empData.buildData(eTableView, "Select * from EmployeeTable Order By (Id) desc;");
        
    }    
 
    String branchCode="";
    String branchName="";
    String branchLoc="";
    
    @FXML
    private void addBranch(ActionEvent event) {
        branchCode = bCode.getText();
        branchName = bName.getText();
        branchLoc = bLoc.getText();
        
        if(branchCode==null || branchCode.isEmpty()){
           warnMsg.setText("Enter Branch Code.");
           bCode.requestFocus();
            return;
        }
           if(branchName==null || branchName.isEmpty()){
           warnMsg.setText("Enter Branch Name.");
           bName.requestFocus();
            return;
        }
           if(branchLoc==null || branchLoc.isEmpty()){
           warnMsg.setText("Enter Branch Location.");
           bLoc.requestFocus();
            return;
        }
           
            Connection c;
            try{
            c = DBConnection.connect();
            String query = "INSERT INTO BranchTable (Name,BCode,Address) VALUES("+
            "'"+branchName+"',\n" +
            "'"+branchCode+"',\n" +
            "'"+branchLoc+"');";                    
          
            c.createStatement().execute(query);
            
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BranchEmployeeSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           bName.clear();
           bCode.clear();
           bLoc.clear();
          
           branchList.add(branchCode);
           
           
            
        
    }

    String empName="";
    String empBranch="";
    
    @FXML
    private void addEmployee(ActionEvent event) {
        
        empName = eName.getText();
        branchCode = cBranch.getValue();
        
        if(branchCode==null || branchCode.isEmpty()){
           warnMsg.setText("Select Branch Code.");
           cBranch.requestFocus();
            return;
        }
           if(empName==null || empName.isEmpty()){
           warnMsg.setText("Enter Employee's Name.");
           eName.requestFocus();
            return;
        }
         
           
            Connection c;
            try{
            c = DBConnection.connect();
            String query = "INSERT INTO EmployeeTable (Name,Branch) VALUES("+
            "'"+empName+"',\n" +
            "'"+branchCode+"');";                    
          
            c.createStatement().execute(query);
            
            c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(BranchEmployeeSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           eName.clear();
           
          
          empData.buildData(eTableView, "Select * from EmployeeTable Order By (Id) desc;");
           
           
    }

    @FXML
    private void searchEmployee(ActionEvent event) {
        
        String query="";
        String name = eSName.getText();
        if(name!=null && !name.isEmpty()){
        query = "Select * from EmployeeTable where Name Like '%"+name+"%';";
        
        }else{
        query = "Select * from EmployeeTable;";
        
        }
        
        
          empData.buildData(eTableView,query);
    }

   
    
}
