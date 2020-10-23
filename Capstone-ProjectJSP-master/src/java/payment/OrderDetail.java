/*
 * Class Description: This class is the OrderDetail class that deals with prices and restaurant name.
 */
package payment;

/**
 *
 * Class Description: This class is the OrderDetail class that deals with prices and restaurant name.
 * @author Chris
 */
public class OrderDetail {
	private String productName;
	private float subtotal;
	private float shipping;
	private float tax;
	private float total;
	
	public OrderDetail(String productName, String subtotal, String shipping, String tax, String total) 
	{
		this.productName = "Tandoori Grill Order";
		this.subtotal = Float.parseFloat(subtotal);
		this.shipping = 0;
		this.tax = Float.parseFloat(tax);
		this.total = Float.parseFloat(total);
	}

	public String getProductName() {
		return productName;
	}

	public String getSubtotal() {
		return String.format("%.2f", subtotal);
	}


	public String getShipping() {
		return String.format("%.2f", shipping);
	}

	public String getTax() {
		return String.format("%.2f", tax);
	}

	public String getTotal() {
		return String.format("%.2f", total);
	}
}
