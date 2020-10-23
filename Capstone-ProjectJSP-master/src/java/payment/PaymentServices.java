/*
 * Class Description: This class is for Paypal interaction and necessary methods to interact.
*/
package payment;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

/**
 * Class Description: This class is for Paypal interaction and necessary methods to interact.
 * 
 * @author Chris
 */
public class PaymentServices 
{
	private static final String CLIENT_ID ="AROphmm5JFSQV-ceHhfSjl7KvBSpPRnkJejgpKc9fiOEcwKup1Z8hZqNIm0xkx4OSdjeLvUsYtRl90FP";		//these are from the paypal account I set up.
	private static final String CLIENT_SECRET = "ECwIbYajKRXwCkaLfYcllws17EgbI6k2lPmbuJCzeipmS0JWk575J-b-UuHIaoMWvMVxgIsh6A4OQYT1";
	private static final String MODE = "sandbox";
	String fname, lname, email;
	
	
	/**
	 * We need to send 3 things to PayPal.
	 *1. Payer information
	 *2. Redirect URLs.
	 *3. Transaction information.
	 * @param orderDetail
	 * @return
	 * @throws PayPalRESTException 
	 */
	public String authorizePayment(OrderDetail orderDetail, String fname, String lname, String email) throws PayPalRESTException 
	{	
                this.fname = fname;
                this.lname = lname;
                this.email = email;
                
		//#1
		Payer payer = getPayerInformation(fname, lname, email);	// Payer is from sdk. We call this method to populate the payer object
		
		//#2
		RedirectUrls redirectUrls = getRedirectURLs();	//RedirectUrls is imported from sdk. We call the method I made to populate it. 
		
		//#3
		List<Transaction> listTransaction = getTransactionInformation(orderDetail);
		
		//Now tie it all together into a Payment object. .
		Payment requestPayment = new Payment();
		requestPayment.setTransactions(listTransaction);
		requestPayment.setRedirectUrls(redirectUrls);
		requestPayment.setPayer(payer);
		requestPayment.setIntent("authorize");
		
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);		//This line here hits up PayPals servers. 
		Payment approvedPayment = requestPayment.create(apiContext);
		
		System.out.println(approvedPayment);
		return getApprovalLink(approvedPayment);
	}
	

	/**
	 * This method gets payer object. It uses a bunch of other methods that are provided to us in the paypal SDK's.
	 * I hardcoded in values here for now.
	 * In our Capstone, we will populate these via the servlet (session object stored on login, or whatever they fill into the jsp. idk.) 
	 */
	private Payer getPayerInformation(String fname, String lname, String email) {
                
                
		Payer payer = new Payer();						//This is from the sdk.
		payer.setPaymentMethod("paypal");
		
		PayerInfo payerInfo = new PayerInfo();			//also from the sdk.
		payerInfo.setFirstName(fname);
		payerInfo.setLastName(lname);
		payerInfo.setEmail(email);
		
		payer.setPayerInfo(payerInfo);					//We now have a payer object, with payer info.
		return payer;
	}
	
	/**
	 * Method sets all the redirectURLs then returns them as an object.
	 * @return
	 */
	private RedirectUrls getRedirectURLs() 
	{
		RedirectUrls redirects = new RedirectUrls();				
		redirects.setCancelUrl("http://localhost:8080/CAPSTONE/menu");
		redirects.setReturnUrl("http://localhost:8080/CAPSTONE/Paypal");		
		return redirects;
	}
	
	private List<Transaction> getTransactionInformation(OrderDetail orderDetail)
	{
		Details details = new Details();
		details.setShipping(orderDetail.getShipping());
		details.setSubtotal(orderDetail.getSubtotal());
		details.setTax(orderDetail.getTax());
		
		Amount amount = new Amount();
		amount.setCurrency("CAD");
		amount.setTotal(orderDetail.getTotal());
		amount.setDetails(details);
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription(orderDetail.getProductName());
		
		ItemList itemList = new ItemList();			//ItemList and Item from sdk. 
		List<Item> items = new ArrayList<Item>();
		Item item = new Item();
		item.setCurrency("CAD");
		item.setName(orderDetail.getProductName());
		item.setPrice(orderDetail.getSubtotal());
		item.setTax(orderDetail.getTax());
		item.setQuantity("1");
		
		items.add(item);
		itemList.setItems(items);
		transaction.setItemList(itemList);
		
		List<Transaction> listTransaction = new ArrayList<Transaction>();
		listTransaction.add(transaction);
		
		return listTransaction;
		
		
	}
	
	
	/**
	 * Payment object has a method that returns a list of predefined links. 
	 * We need to scan through that list to return the approved one. 
	 */
	private String getApprovalLink(Payment approvedPayment)
	{
		List<Links> links = approvedPayment.getLinks();
		String approvalLink = null;
		
		for (Links link : links)
		{
			if(link.getRel().equalsIgnoreCase("approval_url"))
			{
				approvalLink = link.getHref();     //returns the URL of the approval link.        
			}
		}
		
		return approvalLink;
	}
}
