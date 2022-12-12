/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package uaspbol_2020130057;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author A409
 */
public class FXMLDocumentController implements Initializable {
    public static DBPesawat dtpesawat = new DBPesawat();
    public static DBRute dtrute = new DBRute();
    
    @FXML
    private MenuItem masterpesawat;
    @FXML
    private MenuItem masterrute;
    @FXML
    private MenuItem transaksipenjadwalan;
    @FXML
    private MenuItem displaypesawat;
    @FXML
    private MenuItem displayrute;
    @FXML
    private MenuItem displaypenjadwalan;
    @FXML
    private MenuItem keluar;
    
    private void handleButtonAction(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void masterpesawatklik(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputPesawat.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void masterruteklik(ActionEvent event) {
        try{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_InputRute.fxml"));    
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg=new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();        
        } catch (IOException e){   
            e.printStackTrace();   
        }
    }

    @FXML
    private void transaksipenjadwalanklik(ActionEvent event) {
    }

    @FXML
    private void displaypesawatklik(ActionEvent event) {
        try{  FXMLLoader loader=new FXMLLoader(getClass().getResource("FXML_DisplayPesawat.fxml"));    
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();        
        } catch (IOException e){   
            e.printStackTrace();
        }
    }

    @FXML
    private void displayruteklik(ActionEvent event) {
    }

    @FXML
    private void displaypenjadwalanklik(ActionEvent event) {
    }

    @FXML
    private void keluarklik(ActionEvent event) {
        System.exit(0);
    }
    
}
