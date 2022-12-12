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
public class FXML_DisplayPesawatController implements Initializable {

    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnakhir;
    @FXML
    private Button btnubah;
    @FXML
    private Button btnsesudah;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnsebelum;
    @FXML
    private Button btntambah;
    @FXML
    private Button btnawal;
    @FXML
    private TableView<PesawatModel> tbvpesawat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    private void showdata(){
        ObservableList<PesawatModel> data=FXMLDocumentController.dtpesawat.Load();
        if(data!=null){            
            tbvpesawat.getColumns().clear();
            tbvpesawat.getItems().clear();
            TableColumn col=new TableColumn("kode");
            col.setCellValueFactory(new PropertyValueFactory<PesawatModel, String>("kode"));
            tbvpesawat.getColumns().addAll(col);
            col=new TableColumn("nama");
            col.setCellValueFactory(new PropertyValueFactory<PesawatModel, String>("nama"));
            tbvpesawat.getColumns().addAll(col);
            tbvpesawat.setItems(data);
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvpesawat.getScene().getWindow().hide();
        }    
    }    

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
        tbvpesawat.getSelectionModel().selectLast();        
        tbvpesawat.requestFocus();   
    }

    @FXML
    private void ubahklik(ActionEvent event) {
        PesawatModel s= new PesawatModel();
        s = tbvpesawat.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputPesawat.fxml"));    
        Parent root = (Parent)loader.load();
        FXML_InputPesawatController isidt=(FXML_InputPesawatController)loader.getController();
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
    private void sesudahklik(ActionEvent event) {
        tbvpesawat.getSelectionModel().selectBelowCell();        
        tbvpesawat.requestFocus();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
        PesawatModel s = new PesawatModel();       
        s = tbvpesawat.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
            if(FXMLDocumentController.dtpesawat.delete(s.getKode())){
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
    private void sebelumklik(ActionEvent event) {
        tbvpesawat.getSelectionModel().selectAboveCell();        
        tbvpesawat.requestFocus();       
    }

    @FXML
    private void tambahklik(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputPesawat.fxml"));    
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
    private void awalklik(ActionEvent event) {
        tbvpesawat.getSelectionModel().selectFirst();        
        tbvpesawat.requestFocus(); 
    }
    
}
