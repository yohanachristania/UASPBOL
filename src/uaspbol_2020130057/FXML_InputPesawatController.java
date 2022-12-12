/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package uaspbol_2020130057;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author A409
 */
public class FXML_InputPesawatController implements Initializable {

    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtnama;
    @FXML
    private TextField txtkode;
    @FXML
    private TextField txtkapasitas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    boolean editdata=false;
    public void execute(PesawatModel d){
        if(!d.getKode().isEmpty()){
          editdata=true;
          txtkode.setText(d.getKode());
          txtnama.setText(d.getNama());
          txtkapasitas.setText(d.getKapasitas());
          txtkode.setEditable(false);
          txtnama.requestFocus();
          txtkapasitas.requestFocus();
        }
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtkode.setText("");
        txtnama.setText("");
        txtkapasitas.setText("");
        btnsimpan.setText("");
        btnbatal.setText("");
        btnkeluar.setText("");
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        PesawatModel s=new PesawatModel();
        s.setKode(txtkode.getText());
        s.setNama(txtnama.getText());
        s.setKapasitas(txtkapasitas.getText());
        FXMLDocumentController.dtpesawat.setPesawatModel(s);
        if(editdata){
            if(FXMLDocumentController.dtpesawat.update()){
                Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
                a.showAndWait();   
                txtkode.setEditable(true);          
                batalklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXMLDocumentController.dtpesawat.validasi(s.getKode())<=0){
            if(FXMLDocumentController.dtpesawat.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
               a.showAndWait();            
            }
        }else{
            Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtkode.requestFocus();
        }
    }
    
}
