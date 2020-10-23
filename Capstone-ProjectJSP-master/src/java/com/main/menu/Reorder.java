/*
 * Class Description: This class is the Reorder model class.
*/
package com.main.menu;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Class Description: This class is the Reorder model class.
 *
 * @author Gabriel Erhman
 */
public class Reorder {

    private int orderID;
    private List<Item> itemList;
    private double totalPrice;

    /**
     * 
     * @return 
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * 
     * @param orderID 
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * 
     * @return 
     */
    public List<Item> getItemList() {
        return itemList;
    }

    /**
     * 
     * @param itemList 
     */
    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    /**
     * 
     * @return 
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * 
     * @param totalPrice 
     */
    public void setTotalPrice(double totalPrice) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        this.totalPrice = Double.parseDouble(df.format(totalPrice));
    }

}
