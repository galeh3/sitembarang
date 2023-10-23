/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author galeh.tri
 */
public class Barang {
    private final String nama_tabel = "tbl_barang";
    private final koneksi koneksi = new koneksi();
    
    private Object[][] list;
    private String pesan;
    
    public String kode, nama, nama_satuan, satuan, jumlah, harga;
    
    public Object[][] getList(){
        return list;
    }
    
    public boolean bacaData(){
        boolean adaKesalahan = false;
        Connection connection;
        list = new Object[0][0];
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select kd_barang, nama_barang, nama_satuan as satuan, jumlah, harga from " + nama_tabel + " inner join tbl_satuan on " + nama_tabel + ".satuan = " + Satuan.class.newInstance().nama_tabel + ".kd_satuan ORDER BY kd_barang ASC";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list = new Object[rset.getRow()][5];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        list[i] = new Object[]{rset.getString("kd_barang"), rset.getString("nama_barang"), rset.getString("satuan"), rset.getString("jumlah"), rset.getString("harga")};
                        i++;
                    } while (rset.next());
                }
                
                preparedStatement.close();
                rset.close();
                connection.close();
                adaKesalahan = false;
            } catch (SQLException ex){
                adaKesalahan = true;
                pesan = "Tidak dapat membaca data\n"+ex.getMessage();
            } catch (Exception e){
                adaKesalahan = true;
                pesan = "Error\n"+e.getMessage();
            }
        } else {
            adaKesalahan = true;
            pesan = "Tidak dapat melakukan koneksi ke server\n"+koneksi.getPesanKesalahan();
        }
        
        return !adaKesalahan;
    }
    
    public String insert(String kdbarang, String nama, String satuan, String jumlah, String harga){
        try {
            String sql = "INSERT INTO " + nama_tabel + " VALUES ('" + kdbarang + "','" + nama + "','" + satuan + "','" + jumlah + "','" + harga + "')";
            java.sql.Connection conn = koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            return  "Penyimpanan Data Berhasil";
        } catch (Exception e) {
            return  "Something went wrong";
        }
    }
    
    public String update(String kdbarang, String nama, String satuan, String jumlah, String harga){
        try {
            String sql = "UPDATE " + nama_tabel + " SET nama_barang = '" + nama + "',satuan= '" + satuan + "',jumlah= '" + jumlah + "',harga= '" + harga + "' WHERE kd_barang = '" + kdbarang + "'";
            java.sql.Connection conn = (Connection) koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            return "data berhasil di Update";
        } catch (Exception e) {
            return "Perubahan Data Gagal" + e.getMessage();
        }
    }
    
    public String delete(String kdbarang){
        try {
            String sql = "delete from " + nama_tabel + " where kd_barang='" + kdbarang + "'";
            java.sql.Connection conn = (Connection) koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            return "berhasil di Delete";
        } catch (Exception e) {
            return "Hapus Data Gagal" + e.getMessage();
        }
    }
}
