/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.FormBarang;
import views.FormSatuan;

/**
 *
 * @author anang
 */
public class mainController {
    
//    public static FormBarang pendataan;
    public static FormSatuan pendataan;
    
    public static void main(String[] args){
        
        // testing crud barang 

        pendataan = new FormSatuan();
                
        pendataan.setVisible(true);
                
    }
}
