/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import models.Satuan;
import models.koneksi;


/**
 *
 * @author anang
 */
public class SatuanController {
    private Satuan satuan = new Satuan();

    public String insertSatuan(Satuan satuan){
        return satuan.insert(satuan.kode, satuan.nama);
    }
    
    public String updateSatuan(Satuan satuan){
        return satuan.update(satuan.kode, satuan.nama);
    }
    
    public String deleteSatuan(Integer kdSatuan){
        return satuan.delete(kdSatuan);
    }
    /**
     * @param args the command line arguments
     */
    
    
}
