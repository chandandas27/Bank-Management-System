/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankmanagement;

import DB.DBConnection;
import DB.DeleteDatabase;
import DB.DisplayDatabase;
import DB.QueryDatabase;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;   
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Chandan Das
 */
public class AccountHolderSceneController implements Initializable {

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
   
    @FXML
    private TextField cName;
    @FXML
    private TextField cAddress;
    @FXML
    private TextField cAdhaarId;
    @FXML
    private ComboBox<String> cGenderCombo;
    @FXML
    private ComboBox<String> cBranch;
    @FXML
    private Button addCBtn;
    @FXML
    private ComboBox<String> cType;
    @FXML
    private TableView<?> acntHoldTable;
    @FXML
    private DatePicker cDOB;
   
    String accountNumber = "";
    String type = ("");
    String name = ("");
    String gender = ("");
    String address = ("");
    String branch = ("");
    LocalDate dob = null;
    double balance = 0;
    String aadhar = "";
    
     ObservableList<String> branchList = FXCollections.observableArrayList();
    DisplayDatabase accountData = new DisplayDatabase();
    
    @FXML
    private TextField accnum;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> genderList = FXCollections.observableArrayList();
        genderList.add("M");
        genderList.add("F");
        genderList.add("O");
        cGenderCombo.setItems( genderList);
        
          ObservableList<String> typeList = FXCollections.observableArrayList();
        typeList.add("Savings");
        typeList.add("Current");
        typeList.add("Demat");
        cType.setItems( typeList);
        cType.setValue("Savings");
        
        ResultSet rs = QueryDatabase.query("Select BCode from branchtable;");
        if(rs!=null){
            try {
                while(rs.next()){
                    branchList.add(rs.getString(1));
                }
            } catch (SQLException ex) {
                Logger.getLogger(AccountHolderSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        cBranch.setItems(branchList);
        
        accountData.buildData(acntHoldTable, "Select * from accountTable;");
        
         cDOB.setValue(LocalDate.now());
    }    



    
    @FXML
    private void addCustomer(ActionEvent event) {
       
         getFields();
         try {
         
         Connection c;
            c = DBConnection.connect();
            
          if(!update){
         
         
           
            ResultSet rs= QueryDatabase.query("Select Count(Id) from accounttable where BCode ='"+branch+"';");
            if(rs==null){
            accountNumber = branch+"10001";
            }else{
                if(rs.next()){
                    int id = Integer.parseInt(rs.getString(1));
                    id++;
                    id +=10000;
                    accountNumber = branch+id;
                }else{
                     accountNumber = branch+"10001";
                     }
           
            }
                   
            
            String query = "INSERT INTO accounttable (Account_Number,Account_Type,BCode,Name,Gender,Dob,Address,Aadhar,Balance)VALUES("+
           
            "'"+accountNumber+"',\n" +
            "'"+type+"',\n" +
                     "'"+branch+"',\n" +
                     "'"+name+"',\n" +
            "'"+gender+"',\n" +
            "'"+dob+"',\n" +
            "'"+address+"',\n" +
            "'"+aadhar+"',\n" +
           
            "'"+balance+"');";                    
                   
            
        
            c.createStatement().execute(query);
            
           
         }else{
              
           String query = "Update accounttable set Account_Type='"+type+"',BCode='"+branch+"',Name='"+name+"',"
                   + "Gender='"+gender+"',Dob='"+dob+"',Address='"+address+"',Aadhar='"+aadhar+"' Where Id='"+id+"';";
                  System.out.println(query);
           c.createStatement().execute(query);
          } 
          
           c.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccountHolderSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
             clearFields();
            
         accountData.buildData(acntHoldTable, "Select * from accountTable;");  
    }


    private void getFields() {
        
        name = cName.getText();
        type=  cType.getValue();
        gender =  cGenderCombo.getValue();
        address = cAddress.getText();
        branch =  cBranch.getValue();
        dob = cDOB.getValue();
        balance=  0;
        aadhar = cAdhaarId.getText();
       
    }

    private void clearFields() {
         cName.clear();
       cType.setValue("Savings");
        cGenderCombo.setValue("");
        cAddress.clear();
       cBranch.setValue("");
       cDOB.setValue(LocalDate.now());
        balance=  0;
      cAdhaarId.clear();
      update = false;
      addCBtn.setText("Create Account");
    }
 String query="Select * from accountTable;";
    @FXML
    private void srcAcntNum(ActionEvent event) {
        
        String acc = accnum.getText();
        if(acc!=null && !acc.isEmpty()){
        query = "Select * from accountTable where Account_Number='"+acc+"';";
        
        }else{
        query = "Select * from accountTable;";
        
        }
        accountData.buildData(acntHoldTable, query);
    }

    @FXML
    private void mDeleteAccount(ActionEvent event) {
        
        int index = acntHoldTable.getSelectionModel().getFocusedIndex();
        ObservableList<ObservableList> data = accountData.getData();
       ObservableList<String> itemData = data.get(index);
       
       int id = Integer.parseInt(itemData.get(0));
      
       DeleteDatabase.deleteRecord(id, "accountTable");
      accountData.buildData(acntHoldTable, query);
    }
    boolean update = false;
    int id=1;
    @FXML
    private void mUpdateAccount(ActionEvent event) {
        
      int index = acntHoldTable.getSelectionModel().getFocusedIndex();
      ObservableList<ObservableList> data = accountData.getData();
      ObservableList<String> itemData = data.get(index);
      
      
      id = Integer.parseInt(itemData.get(0));
        cName.setText(itemData.get(4));
        cType.setValue(itemData.get(2));
        cGenderCombo.setValue(itemData.get(5));
        cAddress.setText(itemData.get(7));
        cBranch.setValue(itemData.get(3));
        cDOB.setValue(LocalDate.parse(itemData.get(6)));
        cAdhaarId.setText(itemData.get(8));
        update=true;
        addCBtn.setText("Update Account");
        
    }
    
}
