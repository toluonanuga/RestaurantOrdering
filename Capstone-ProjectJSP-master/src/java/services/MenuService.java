/*
 * Class Description: This class acts as the intermediary between the database broker and Menu Servlets.
 */
package services;

import brokers.MenuBroker;
import com.main.menu.Item;
import java.awt.Image;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Description: This class acts as the intermediary between the database broker and Menu Servlets.
 * @author cuong
 */
public class MenuService
{
	private List<Item> itemList;
        private MenuBroker mb = new MenuBroker();
        
        
        /**
         * 
	 * Adds a new Item to the MenuService and saves it to the database
         * Takes in an Item, sends it to the MenuBroker and if successful, adds that item to the itemList.
         * @param itemName
         * @param price
         * @param description
         * @param category
         * @param imagePath
         * @return boolean success
         */
	public boolean addToItemList(String itemName, double price, String description, String category, String imagePath ) 
	{
		Item addItem = new Item();
                addItem.setItemName(itemName);
                addItem.setPrice(price);
                addItem.setDescription(description);
                addItem.setCategory(category);
                addItem.setImagePath(imagePath);
                return mb.insertItem(addItem);
                
	}
	
	/**
	 * Modifies a live Item from the MenuService object and saves all changes to all sources.
	 * Takes in an Item, finds its itemID in the list, then sends the item to the MenuBroker.
	 * If that is successful update switch the Item with the parameter Item.
	 * @param itemId
	 * @param itemName
	 * @param price
	 * @param description
	 * @param category
	 * @param imagePath
	 * @throws NullPointerException
	 */
	public boolean modifyItemFromList(int itemId, String itemName, double price, 
		 String description, String category, String imagePath)
	{
            Item i = mb.getItemById(itemId);
            i.setItemName(itemName);
            i.setPrice(price);
            i.setDescription(description);
            i.setDescription(description);
            i.setCategory(category);
            i.setImagePath(imagePath);
            
           return  mb.updateItem(i);
		
	}
	
	/**
	 * Removes a particular Item from the MenuService object and saves all changes to all sources.
	 * Takes in an Item, finds its itemID in the list, then sends the item to the MenuBroker.
	 * If that is successful remove that Item from the itemList.
	 * @param ItemId
	 * @return
	 * @throws NullPointerException
	 */
	public boolean deleteItemFromList (int ItemId) 
	{
                return mb.deleteItem(mb.getItemById(ItemId));
	}
	
	/**
	 * Parses through the itemList, and if the items name contains the parameter name,
	 * it adds it to a temporary Item list, then returns that List of Items.
	 * @param name
	 * @return
	 * @throws NullPointerException
	 */
	public List<Item> displayItemList (String name) 
	{
            itemList = mb.getAllItem();
            List<Item> containList = new ArrayList<>();
            for(int i = 0; i < itemList.size(); i++) 
            {
                if(itemList.get(i).getItemName().contains(name) == true) 
                {
                    containList.add(itemList.get(i));
                }
            }
		return containList;
		
	}
	
	/**
	 * Takes the parameter itemID and goes through the itemList, and gets all items that are
	 * the parameter category. Then, returns the List.
	 * @param category
	 * @return
	 * @throws NullPointerException
	 */
	public List<Item> displayCategory(String category)
	{
            itemList = mb.getAllItem();
            List<Item> containList = new ArrayList<>();
            for(int i = 0; i < itemList.size(); i++) 
            {
                if(itemList.get(i).getCategory().equalsIgnoreCase(category)) 
                {
                    containList.add(itemList.get(i));
                }
            }
		return containList; // <========= HOPEFULLY, THIS WILL WORK! :D
		
	}
	/**
	 * Gets the itemList and returns all current Items in it.
	 * @return
	 * @throws NullPointerException
	 */
	public List<Item> displayAllItem()
	{
            itemList = mb.getAllItem();
		return itemList;
		
	}
	
	
	/**
	 * Goes through the itemList and, when it finds the itemID that matches the parameter, returns it.
	 * @param itemId
	 * @return
	 * @throws NullPointerException
	 */
	public Item getItemById(int itemId)
	{
		return mb.getItemById(itemId);
		
	}
        
        /**
         * Goes through the itemList and, when it finds the itemName that matches the parameter, returns it.
         * @param itemName
         * @return 
         */
        public Item getItemByName(String itemName) 
        {
            
            return mb.getItemByName(itemName);
        }
	
}
