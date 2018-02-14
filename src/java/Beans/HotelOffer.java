/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mohammad
 */
public class HotelOffer implements Serializable{
    private OfferInfo offerInfo;
    private UserInfo  userInfo;
    private List<Hotel> lstHotels = new ArrayList();

    public OfferInfo getOfferInfo() {
        return offerInfo;
    }

    public void setOfferInfo(OfferInfo offerInfo) {
        this.offerInfo = offerInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Hotel> getLstHotels() {
        return lstHotels;
    }

    public void setLstHotels(List<Hotel> lstHotels) {
        this.lstHotels = lstHotels;
    }
    
    
}
