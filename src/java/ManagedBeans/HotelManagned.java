/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import Beans.HotelOffer;
import ProcessData.ParsingData;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author mohammad
 */
@ManagedBean
@RequestScoped
public class HotelManagned implements Serializable{

  
    public HotelManagned() {
    }
   
    public HotelOffer getHotelOffer() throws Exception{
        return  new ParsingData().readingData();
    }    
    
}
