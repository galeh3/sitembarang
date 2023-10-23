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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author galeh.tri
 */
public class Satuan {
    public Integer id;
    public Integer kode;
    public String nama;
    
    public final String nama_tabel = "tbl_satuan";
    private final koneksi koneksi = new koneksi();
    
    private Object[][] list2;
    private String pesan;
    
    public Object[][] getList2(){
        return list2;
    }
    private List<Satuan> list;
    
    public List<Satuan> getList(){
        Connection connection;
        list = new ArrayList();
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select * from " + nama_tabel;
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        Satuan satuan = new Satuan();
                        satuan.id = rset.getInt("kd_satuan");
                        satuan.nama = rset.getString("nama_satuan");
                        list.add(satuan);
                        i++;
                    } while (rset.next());
                }
            } catch(Exception e){
                
            }
        }
        return list;
    }
    
    public Integer findIndex(String cari){
        for(Integer i = 0; i < list.size(); i++){
            if(list.get(i).nama == cari) return i;
        }
        return 0;
    }
    
    
    
    public Integer findId(String cari){
        for(Integer i = 0; i < list.size(); i++){
            if(list.get(i).nama == cari) return list.get(i).id;
        }
        return 0;
    }
    public boolean bacaData(){
        boolean adaKesalahan = false;
        Connection connection;
        list2 = new Object[0][0];
        
        if ((connection = koneksi.getConnection()) != null){
            String SQLStatemen;
            PreparedStatement preparedStatement;
            ResultSet rset;
            
            try {
                SQLStatemen = "select kd_satuan, nama_satuan from " + nama_tabel + " ORDER BY kd_satuan ASC";
                preparedStatement = connection.prepareStatement(SQLStatemen);
                rset = preparedStatement.executeQuery();
                
                rset.next();
                rset.last();
                list2 = new Object[rset.getRow()][5];
                
                if (rset.getRow()>0){
                    rset.first();
                    int i=0;
                    do {
                        list2[i] = new Object[]{rset.getString("kd_satuan"), rset.getString("nama_satuan")};
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
    
    public String insert(Integer kdsatuan, String nama){
        try {
            String sql = "INSERT INTO " + nama_tabel + " VALUES ('" + kdsatuan + "','" + nama + "')";
            java.sql.Connection conn = koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            return  "Penyimpanan Data Berhasil";
        } catch (Exception e) {
            return  "Something went wrong";
        }
    }
    
    public String update(Integer kdsatuan, String nama){
        try {
            String sql = "UPDATE " + nama_tabel + " SET nama_satuan = '" + nama + "' WHERE kd_satuan = '" + kdsatuan + "'";
            java.sql.Connection conn = (Connection) koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            return "data berhasil di Update";
        } catch (Exception e) {
            return "Perubahan Data Gagal" + e.getMessage();
        }
    }
    
    public String delete(Integer kdsatuan){
        try {
            String sql = "delete from " + nama_tabel + " where kd_satuan='" + kdsatuan + "'";
            java.sql.Connection conn = (Connection) koneksi.getConnection();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            return "berhasil di Delete";
        } catch (Exception e) {
            return "Hapus Data Gagal" + e.getMessage();
        }
    }
}
