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
import models.Barang;
import models.Satuan;
import models.koneksi;


/**
 *
 * @author anang
 */
public class BarangController {
    private Barang barang = new Barang();
    private Satuan satuan = new Satuan();
    
    public String insertBarang(Barang barang){
        return barang.insert(barang.kode, barang.nama, barang.satuan, barang.jumlah, barang.harga);
    }
    
    public String updateBarang(Barang barang){
        return barang.update(barang.kode, barang.nama, barang.satuan, barang.jumlah, barang.harga);
    }
    
    public String deleteBarang(String kdBarang){
        return barang.delete(kdBarang);
    }
    /**
     * @param args the command line arguments
     */
    
    
}
