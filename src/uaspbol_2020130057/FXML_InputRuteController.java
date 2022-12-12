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
public class FXML_InputRuteController implements Initializable {

    @FXML
    private Button btnkeluar;
    @FXML
    private Button btnbatal;
    @FXML
    private Button btnsimpan;
    @FXML
    private TextField txtkotaasal;
    @FXML
    private TextField txtkotatujuan;
    @FXML
    private TextField txtkoderute;
    @FXML
    private TextField txtdurasi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    boolean editdata=false;
    public void execute(RuteModel d){
        if(!d.getKode().isEmpty()){
          editdata=true;
          txtkoderute.setText(d.getKode());
          txtkotaasal.setText(d.getKotaasal());
          txtkotatujuan.setText(d.getKotatujuan());
          txtdurasi.setText(d.getDurasi());
          txtkoderute.setEditable(false);
          txtkotaasal.requestFocus();
          txtkotatujuan.requestFocus();
          txtdurasi.requestFocus();
        }
    }
    
    @FXML
    private void keluarklik(ActionEvent event) {
         btnkeluar.getScene().getWindow().hide();
    }

    @FXML
    private void batalklik(ActionEvent event) {
        txtkoderute.setText("");
        txtkotaasal.setText("");
        txtkotatujuan.setText("");
        txtdurasi.setText("");
        btnsimpan.setText("");
        btnbatal.setText("");
        btnkeluar.setText("");
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        RuteModel s=new RuteModel();
        s.setKode(txtkoderute.getText());
        s.setKotaasal(txtkotaasal.getText());
        s.setKotatujuan(txtkotatujuan.getText());
        s.setDurasi(txtdurasi.getText());
        FXMLDocumentController.dtrute.setRuteModel(s);
        if(editdata){
            if(FXMLDocumentController.dtrute.update()){
                Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
                a.showAndWait();   
                txtkoderute.setEditable(true);          
                batalklik(event);                
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
               a.showAndWait();                    
            }
        }else if(FXMLDocumentController.dtrute.validasi(s.getKode())<=0){
            if(FXMLDocumentController.dtrute.insert()){
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
            txtkoderute.requestFocus();
        }
    }
    
}
