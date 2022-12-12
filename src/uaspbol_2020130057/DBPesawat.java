/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uaspbol_2020130057;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author A409
 */
public class DBPesawat {
    private PesawatModel dt=new PesawatModel(); 
    
    public PesawatModel getPesawatModel(){ 
        return(dt);
    }
    public void setPesawatModel(PesawatModel s){ 
        dt=s;
    } 

    public ObservableList<PesawatModel>  Load() {
        try {   
            ObservableList<PesawatModel> TableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select kode, nama from pesawat");
            int i = 1;
            while (rs.next()) {
                PesawatModel d=new PesawatModel();
                d.setKode(rs.getString("kode")); 
                d.setNama(rs.getString("nama"));
                d.setKapasitas(rs.getString("kapasitas"));
                TableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return TableData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int validasi(String nomor) {
        int val = 0;
        try {  
            Koneksi con = new Koneksi();     
            con.bukaKoneksi();   
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from pesawat where kode = '" + nomor + "'");
            while (rs.next()) {   
                val = rs.getInt("jml");            
            }
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        } return val;
    }        

    public boolean insert() {
        boolean berhasil = false;    
        Koneksi con = new Koneksi();
        try {         
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into pesawat (Kode, Nama) values (?,?,?)");
            con.preparedStatement.setString(1, getPesawatModel().getKode());
            con.preparedStatement.setString(2, getPesawatModel().getNama());
            con.preparedStatement.setString(3, getPesawatModel().getKapasitas());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    }
    
    public boolean delete(String nomor) {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from pesawat where Kode  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }

    public boolean update() {
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update pesawat set Nama = ? where  Kode = ? ; ");
            con.preparedStatement.setString(1, getPesawatModel().getKode());
            con.preparedStatement.setString(2, getPesawatModel().getNama());
            con.preparedStatement.setString(3, getPesawatModel().getKapasitas());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
}
