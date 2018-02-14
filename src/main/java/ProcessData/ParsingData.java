/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProcessData;

import Beans.Destination;
import Beans.Hotel;
import Beans.HotelInfo;
import Beans.HotelPricingInfo;
import Beans.HotelUrls;
import Beans.HotelOffer;
import Beans.OfferDateRange;
import Beans.OfferInfo;
import Beans.Persona;
import Beans.UserInfo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.LoggingFilter;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mohammad
 */
public class ParsingData {

    private final String url = "https://offersvc.expedia.com/offers/v2/getOffers?scenario=deal-finder&page=foo&uid=foo&productType=Hotel";
    private WebResource webResource = null;

    public HotelOffer readingData() throws Exception {
        ByteArrayOutputStream baos = null;
        PrintStream ps = null;
        HotelOffer hotelOffer = new HotelOffer();
        List<Hotel> lstHotels = new ArrayList();
        ClientResponse resp = null;
        try {

            Client restClient = new Client();
            baos = new ByteArrayOutputStream();
            ps = new PrintStream(baos);
            restClient.addFilter(new LoggingFilter(ps));
            webResource = restClient.resource(url);
            resp = webResource.accept(MediaType.APPLICATION_JSON_TYPE)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .get(ClientResponse.class);
            if (resp.getStatus() == 200) {
                JSONObject jsonObject = new JSONObject(resp.getEntity(String.class));
                JSONObject tsmresponse = (JSONObject) jsonObject.getJSONObject("offerInfo");

                if (tsmresponse != null) {
                    OfferInfo offerInfo = new OfferInfo();
                    offerInfo.setSiteID(tsmresponse.getString("siteID"));
                    offerInfo.setLanguage(tsmresponse.getString("language"));
                    offerInfo.setCurrency(tsmresponse.getString("currency"));
                    offerInfo.setUserSelectedCurrency(tsmresponse.getString("userSelectedCurrency"));
                    hotelOffer.setOfferInfo(offerInfo);
                    JSONObject userInfoObject = jsonObject.getJSONObject("userInfo");
                    if (jsonObject.getJSONObject("userInfo") != null) {
                        UserInfo userInfo = new UserInfo();
                        userInfo.setUserId(userInfoObject.getString("userId"));
                        if (userInfoObject.getJSONObject("persona") != null) {
                            Persona persona = new Persona();
                            persona.setPersonaType(userInfoObject.getJSONObject("persona").getString("personaType"));
                            userInfo.setPersona(persona);
                        }
                        hotelOffer.setUserInfo(userInfo);
                    }

                    JSONObject offersObject = jsonObject.getJSONObject("offers");
                    if (offersObject.getJSONArray("Hotel") != null) {
                        for (int i = 0; i < offersObject.getJSONArray("Hotel").length(); i++) {
                            JSONObject obj = (JSONObject) offersObject.getJSONArray("Hotel").get(i);
                            String resStartDate = convertArrayToDate(obj.getJSONObject("offerDateRange").getJSONArray("travelStartDate"));
                            String resEndDate = convertArrayToDate(obj.getJSONObject("offerDateRange").getJSONArray("travelEndDate"));
                            OfferDateRange offerDateRange = new OfferDateRange();
                            offerDateRange.setTravelStartDate(resStartDate);
                            offerDateRange.setTravelEndDate(resEndDate);
                            offerDateRange.setLengthOfStay(obj.getJSONObject("offerDateRange").getInt("lengthOfStay") + "");

                            HotelUrls hotelUrls = new HotelUrls();
                            hotelUrls.setHotelSearchResultUrl(obj.getJSONObject("hotelUrls").getString("hotelSearchResultUrl"));
                            hotelUrls.setHotelInfositeUrl(obj.getJSONObject("hotelUrls").getString("hotelInfositeUrl"));

                            HotelInfo hotelInfo = new HotelInfo();
                            hotelInfo.setHotelStreetAddress(obj.getJSONObject("hotelInfo").getString("hotelStreetAddress"));
                            hotelInfo.setHotelImageUrl(obj.getJSONObject("hotelInfo").getString("hotelImageUrl"));
                            hotelInfo.setLocalizedHotelName(obj.getJSONObject("hotelInfo").getString("localizedHotelName"));
                            hotelInfo.setVipAccess(obj.getJSONObject("hotelInfo").getBoolean("vipAccess"));
                            hotelInfo.setHotelStarRating(obj.getJSONObject("hotelInfo").getString("hotelStarRating"));
                            hotelInfo.setHotelDestination(obj.getJSONObject("hotelInfo").getString("hotelDestination"));
                            hotelInfo.setHotelLatitude(obj.getJSONObject("hotelInfo").getDouble("hotelLatitude"));
                            hotelInfo.setIsOfficialRating(obj.getJSONObject("hotelInfo").getBoolean("isOfficialRating"));
                            hotelInfo.setHotelProvince(obj.getJSONObject("hotelInfo").getString("hotelProvince"));
                            hotelInfo.setHotelDestinationRegionID(obj.getJSONObject("hotelInfo").getString("hotelDestinationRegionID"));
                            hotelInfo.setHotelId(obj.getJSONObject("hotelInfo").getString("hotelId"));
                            hotelInfo.setHotelLongitude(obj.getJSONObject("hotelInfo").getDouble("hotelLongitude"));
                            hotelInfo.setHotelReviewTotal(obj.getJSONObject("hotelInfo").getInt("hotelReviewTotal"));
                            hotelInfo.setHotelName(obj.getJSONObject("hotelInfo").getString("hotelName"));
                            hotelInfo.setHotelGuestReviewRating(obj.getJSONObject("hotelInfo").getInt("hotelGuestReviewRating"));
                            hotelInfo.setHotelCountryCode(obj.getJSONObject("hotelInfo").getString("hotelCountryCode"));
                            hotelInfo.setHotelLongDestination(obj.getJSONObject("hotelInfo").getString("hotelLongDestination"));

                            HotelPricingInfo hotelPricingInfo = new HotelPricingInfo();

                            hotelPricingInfo.setTotalPriceValue(obj.getJSONObject("hotelPricingInfo").getDouble("totalPriceValue"));
                            hotelPricingInfo.setAveragePriceValue(obj.getJSONObject("hotelPricingInfo").getDouble("averagePriceValue"));
                            hotelPricingInfo.setOriginalPricePerNight(obj.getJSONObject("hotelPricingInfo").getDouble("originalPricePerNight"));
                            hotelPricingInfo.setDrr(obj.getJSONObject("hotelPricingInfo").getBoolean("drr"));
                            hotelPricingInfo.setPercentSavings(obj.getJSONObject("hotelPricingInfo").getDouble("percentSavings"));
                            hotelPricingInfo.setCurrency(obj.getJSONObject("hotelPricingInfo").getString("currency"));
                            hotelPricingInfo.setCrossOutPriceValue(obj.getJSONObject("hotelPricingInfo").getDouble("crossOutPriceValue"));

                            Destination destination = new Destination();
                            destination.setProvince(obj.getJSONObject("destination").getString("province"));
                            destination.setLongName(obj.getJSONObject("destination").getString("longName"));
                            destination.setNonLocalizedCity(obj.getJSONObject("destination").getString("nonLocalizedCity"));
                            destination.setTla(obj.getJSONObject("destination").getString("tla"));
                            destination.setShortName(obj.getJSONObject("destination").getString("shortName"));
                            destination.setAssociatedMultiCityRegionId(obj.getJSONObject("destination").getInt("associatedMultiCityRegionId"));
                            destination.setRegionID(obj.getJSONObject("destination").getString("regionID"));
                            destination.setCity(obj.getJSONObject("destination").getString("city"));
                            destination.setCountry(obj.getJSONObject("destination").getString("country"));

                            Hotel hotel = new Hotel();
                            hotel.setDestination(destination);
                            hotel.setHotelInfo(hotelInfo);
                            hotel.setHotelPricingInfo(hotelPricingInfo);
                            hotel.setHotelUrls(hotelUrls);
                            hotel.setOfferDateRange(offerDateRange);

                            lstHotels.add(hotel);

                        }
                        hotelOffer.setLstHotels(lstHotels);
                    }
                }
            } else {
                if (resp != null) {
                    resp.close();
                }
            }
        } finally {
            if (resp != null) {
                resp.close();
            }
        }
        return hotelOffer;
    }

    public static void main(String[] args) {
        try {
            new ParsingData().readingData();
        } catch (Exception ex) {
            Logger.getLogger(ParsingData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String convertArrayToDate(JSONArray arr) throws JSONException {
        String result = "";
        if (arr instanceof JSONArray) {

            result = arr.get(2) + "//" + arr.get(1) + "//" + arr.get(0);

        }
        return result;
    }

}
