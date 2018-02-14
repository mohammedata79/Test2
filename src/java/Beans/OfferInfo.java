/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

/**
 *
 * @author mohammad
 */
public class OfferInfo {
     private String siteID;

    private String userSelectedCurrency;

    private String language;

    private String currency;

    public String getSiteID ()
    {
        return siteID;
    }

    public void setSiteID (String siteID)
    {
        this.siteID = siteID;
    }

    public String getUserSelectedCurrency ()
    {
        return userSelectedCurrency;
    }

    public void setUserSelectedCurrency (String userSelectedCurrency)
    {
        this.userSelectedCurrency = userSelectedCurrency;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }
}
