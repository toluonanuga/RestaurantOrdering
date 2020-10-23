/*
 * Class Description: This class acts as the intermediary between the database broker and Customer Servlets.
 */
package services;
import brokers.CustomerBroker;
import com.main.delivery.Delivery;
import com.main.menu.Item;
import com.main.menu.Order;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Class Description: This class acts as the intermediary between the database broker and Customer Servlets.
 * 
 * @author cuong
 */
public class CustomerService {

    /**
    * Creates a new Order object based on the Actor information, Delivery information, Items purchased
    * and Order information and verifies that they are all correct.
    * @param actorID 
    * @param house_number
    * @param unit_number
    * @param street
    * @param postal_code
    * @param city
    * @param province
    * @param note
    * @param contact_email
    * @param order_time 
    * @param duedate   
    * @param total_price
    * @param status
    * @param payment_type
    * @param delivery_method
    * @param itemList please set the quantity of item before add it to the itemList 
    * @return 
    */ 
    public boolean createOrder(int actorID,String house_number, String unit_number,String street,
            String postal_code,String city,String province,String note,String contact_email, String phoneNumber,
            /*Date order_time,*/ Date duedate, double total_price,String status,String payment_type,String delivery_method, List<Item>itemList)
    {
        CustomerBroker cb = new CustomerBroker();
        Order order = new Order();
        Delivery delivery = new Delivery();
        order.setCustomerID(actorID);
        delivery.setBuilding(house_number);
        delivery.setUnit(unit_number);
        delivery.setDeliveryStreet(street);
        delivery.setDeliveryPostalCode(postal_code);
        delivery.setDeliveryCity(city);
        delivery.setDeliveryProvince(province);
        delivery.setDeliveryNote(note);
        delivery.setRecieverEmail(contact_email);
        delivery.setRecieverPhonenumber(phoneNumber);
       // order.setOrderDate((java.sql.Date) order_time);
        order.setDueDate((java.sql.Date) duedate);
        order.setTotalPrice(total_price);
        order.setOrderStatus(status);
        order.setPaymentMethod(payment_type);
        delivery.setDeliveryMethod(delivery_method);
        //add delivery info to order
        order.setDeliveryInfo(delivery);
        //add itemList to order
        order.setItemList(itemList);
        // add to the database 
      return cb.insertOrder(order);
        
    }
    
    	/**
	 * Gets a list of all Orders for a particular Customer.
	 * @param customerId
	 * @return
	 * @throws NullPointerException
	 */
	public List<Order> viewHistoryOrder (int customerId) throws NullPointerException
	{
		List<Order> historyOrderList = null;
                CustomerBroker cb = new CustomerBroker();
                historyOrderList = cb.getAllOrderByCustomerID(customerId);
		return historyOrderList;
		
	}
        /**
         * Adds a favourite Item to a particular Customer and verifies the result of the process.
         * @param customerId
         * @param item
         * @return 
         */
        public boolean addFavoriteItem(int customerId, Item item)
        {
            CustomerBroker  cb = new CustomerBroker();
            return cb.insertFavItem(customerId, item);
        }
        /**
         * Gets all favourite Items attached to a particular Customer using their ID.
         * @param customerId
         * @return 
         */
        public List<Item> getCusFavoriteItem(int customerId)
        {
            CustomerBroker  cb = new CustomerBroker();
            return cb.getFavoriteItems(customerId);
        }
        /**
         * Deletes a Favourite Item for a particular Customer using their ID and verifies the process results.
         * @param item
         * @return 
         */
        public boolean deleteCusFavoriteItem(int customerId, Item item)
        {
            CustomerBroker  cb = new CustomerBroker();
            return cb.removeFavItem(customerId, item);
        }
	
    
}
