/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uaspbol_2020130057;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author A409
 */
public class FXML_DisplayRuteController implements Initializable {

    @FXML
    private Button btnawal;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnkeluar;
    @FXML
    private TableView<RuteModel> tbvrute;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    private void showdata(){
        ObservableList<RuteModel> data=FXMLDocumentController.dtrute.Load();
        if(data!=null){            
            tbvrute.getColumns().clear();
            tbvrute.getItems().clear();
            
            TableColumn col=new TableColumn("koderute");
            col.setCellValueFactory(new PropertyValueFactory<RuteModel, String>("koderute"));
            tbvrute.getColumns().addAll(col);
            
            col=new TableColumn("kotaasal");
            col.setCellValueFactory(new PropertyValueFactory<RuteModel, String>("kotaasal"));
            tbvrute.getColumns().addAll(col);
            
            col=new TableColumn("kotatujuan");
            col.setCellValueFactory(new PropertyValueFactory<RuteModel, String>("kotatujuan"));
            tbvrute.getColumns().addAll(col);
            
            col=new TableColumn("durasi");
            col.setCellValueFactory(new PropertyValueFactory<RuteModel, String>("durasi"));
            tbvrute.getColumns().addAll(col);
            
            tbvrute.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvrute.getScene().getWindow().hide();
        }
    }

    @FXML
    private void awalklik(ActionEvent event) {
        tbvrute.getSelectionModel().selectFirst();        
        tbvrute.requestFocus(); 
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputRute.fxml"));    
            Parent root = (Parent)loader.load();        
            Scene scene = new Scene(root);        
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);        
            stg.setIconified(false);        
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   
        }
        showdata();        
        awalklik(event);
    }

    @FXML
    private void sebelumklik(ActionEvent event) {
        tbvrute.getSelectionModel().selectAboveCell();        
        tbvrute.requestFocus(); 
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        RuteModel s = new RuteModel();       
        s = tbvrute.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
            if(FXMLDocumentController.dtrute.delete(s.getKode())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
            } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           awalklik(event);}
    }

    @FXML
    private void sesudahklik(ActionEvent event) {
        tbvrute.getSelectionModel().selectBelowCell();        
        tbvrute.requestFocus();
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        RuteModel s= new RuteModel();
        s = tbvrute.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputRute.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_InputRuteController isidt=(FXML_InputRuteController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   
        }
        showdata();  
        awalklik(event);
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvrute.getSelectionModel().selectLast();        
        tbvrute.requestFocus();
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }
    
}
