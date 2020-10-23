/*
 * Class Description: This class is the Item model class.
 */
package com.main.menu;

/**
 * Class Description: This class is the Item model class.
 * 
 * @author cuong
 *
 */
public class Item
{
	private int itemId; 
        private String itemName; 
        private double price; 
        private String description; 
        private String category;
        private int quantity;
        private String imagePath;

        
     public Item()
    {
        
    }
    
    public Item(int itemId, String itemName, double price, String description, String category, String filepath)
    {
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
        this.description = description;
        this.category = category;
        this.imagePath = filepath;
    }
    
    
    /**
     * @return the itemId
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public void setQuantity(int q)
    {
        this.quantity = q;
    }
    public int getQuantity()
    {
        return this.quantity;
    }
        
        
    
    
    
}
