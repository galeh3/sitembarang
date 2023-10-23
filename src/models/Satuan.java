/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author galeh.tri
 */
public class Satuan {
    public Integer id;
    public String nama;
    
    public final String nama_tabel = "tbl_satuan";
    private final koneksi koneksi = new koneksi();
    
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
}
