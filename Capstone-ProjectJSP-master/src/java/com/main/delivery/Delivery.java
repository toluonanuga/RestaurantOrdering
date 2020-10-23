/*
 * Class Description: This class is the Delivery model class.
*/
package com.main.delivery;

/**
 * Class Description: This class is the Delivery model class.
 *
 * @author Chris
 */
public class Delivery {
    private int deliveryID;
    private String recieverFirstname; 
    private String recieverLastname;
    private String recieverPhonenumber;
    private String recieverEmail;             
    private String building;
    private String unit;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryPostalCode;
    private String deliveryProvince;
    private String deliveryNote;
    private String deliveryMethod;
    
    public Delivery(){}
    
    
    /**
     * Delivery Object. We are *NOT* using delivery ID here because it is not needed at all to send or recieve
     *                  the order. We will still autoincrement the ID when we are posting to database though. 
     * @param recieverFirstname
     * @param recieverLastname
     * @param recieverPhonenumber
     * @param recieverEmail
     * @param building
     * @param unit
     * @param deliveryStreet
     * @param deliveryCity
     * @param deliveryPostalCode
     * @param deliveryProvince
     * @param deliveryNote
     * @param deliveryMethod 
     */
    public Delivery(String recieverFirstname,String recieverLastname, String recieverPhonenumber,
            String recieverEmail, String building, String unit, String deliveryStreet, String deliveryCity, 
            String deliveryPostalCode, String deliveryProvince, String deliveryNote, String deliveryMethod)
    {
        this.recieverFirstname = recieverFirstname;
        this.recieverLastname = recieverLastname;
        this.recieverEmail = recieverEmail;
        this.building = building;
        this.unit = unit;
        this.deliveryStreet = deliveryStreet;
        this.deliveryCity = deliveryCity;
        this.deliveryPostalCode = deliveryPostalCode;
        this.deliveryProvince = deliveryProvince;
        this.deliveryNote = deliveryNote;
        this.deliveryMethod = deliveryMethod;
    }

    /**
     * @return the recieverFirstname
     */
    public String getRecieverFirstname() {
        return recieverFirstname;
    }

    /**
     * @return the recieverLastname
     */
    public String getRecieverLastname() {
        return recieverLastname;
    }

    /**
     * @return the recieverPhonenumber
     */
    public String getRecieverPhonenumber() {
        return recieverPhonenumber;
    }

    /**
     * @return the recieverEmail
     */
    public String getRecieverEmail() {
        return recieverEmail;
    }

    /**
     * @return the building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @return the deliveryStreet
     */
    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    /**
     * @return the deliveryCity
     */
    public String getDeliveryCity() {
        return deliveryCity;
    }

    /**
     * @return the deliveryPostalCode
     */
    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    /**
     * @return the deliveryProvince
     */
    public String getDeliveryProvince() {
        return deliveryProvince;
    }

    /**
     * @return the deliveryNote
     */
    public String getDeliveryNote() {
        return deliveryNote;
    }

    /**
     * @return the deliveryMethod
     */
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    /**
     * @param recieverFirstname the recieverFirstname to set
     */
    public void setRecieverFirstname(String recieverFirstname) {
        this.recieverFirstname = recieverFirstname;
    }

    /**
     * @param recieverLastname the recieverLastname to set
     */
    public void setRecieverLastname(String recieverLastname) {
        this.recieverLastname = recieverLastname;
    }

    /**
     * @param recieverPhonenumber the recieverPhonenumber to set
     */
    public void setRecieverPhonenumber(String recieverPhonenumber) {
        this.recieverPhonenumber = recieverPhonenumber;
    }

    /**
     * @param recieverEmail the recieverEmail to set
     */
    public void setRecieverEmail(String recieverEmail) {
        this.recieverEmail = recieverEmail;
    }

    /**
     * @param building the building to set
     */
    public void setBuilding(String building) {
        this.building = building;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @param deliveryStreet the deliveryStreet to set
     */
    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    /**
     * @param deliveryCity the deliveryCity to set
     */
    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    /**
     * @param deliveryPostalCode the deliveryPostalCode to set
     */
    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    /**
     * @param deliveryProvince the deliveryProvince to set
     */
    public void setDeliveryProvince(String deliveryProvince) {
        this.deliveryProvince = deliveryProvince;
    }

    /**
     * @param deliveryNote the deliveryNote to set
     */
    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    /**
     * @param deliveryMethod the deliveryMethod to set
     */
    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }
    /**
     * 
     * @param deliveryID 
     */
    public void setDeliveryID(int deliveryID)
    {
        this.deliveryID = deliveryID; 
    }
    /**
     * 
     * @return 
     */
    public int getDeliveryID()
    {
        return this.deliveryID;
    }
    
    
       
}
