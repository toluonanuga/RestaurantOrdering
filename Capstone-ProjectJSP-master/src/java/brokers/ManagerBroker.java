/*
 * Class Description: This class acts as the intermediary between the database and Manager services.
 */
package brokers;

import com.main.databaseConnection.ConnectionPool;
import com.main.menu.Order;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class Description: This class acts as the intermediary between the database and Manager services.
 * @author Nguyen Khanh Duy Phan
 * @version 1.1
 */
public class ManagerBroker {
    
        /**
         * Sets an Orders status in the database to void
         * @param order
         * @return boolean
         */
        public boolean voidOrderStatus(Order order)  {
        ConnectionPool pool = ConnectionPool.createConnection();
        Connection con = pool.getConnection();
        String query = "UPDATE orders SET status = ? WHERE orderID = ?";
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, "VOID");
            ps.setInt(2, order.getOrderID());
            int row = ps.executeUpdate();
            System.out.println("row number " + row);

            if(row != 1) 
            {
                return false;
            } 
            
            return true; //if row == 1
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            pool.freeConnection(con);
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return false;
    }
    
    /**
     * Generates report of all sales since the first Order and creates a file based off of the parameter fileName.
     * @param fileName
     * @return boolean
     */
    public boolean printReportOfAll(String fileName)
    {
        PrintWriter pw = null;
        try {
            AdminBroker ab = new AdminBroker();
            List<Order> orderList = ab.getAllPastOrder();
            Path reportFilePath = Paths.get("");
            String reportString = reportFilePath.toAbsolutePath().toString();
            //String reportFilePath = "C:\\Users\\cuong\\OneDrive\\Desktop\\TestCSV\\" + fileName;
            File file = new File(reportString + "\\" + fileName);
            pw = new PrintWriter (file);
            StringBuilder sb = new StringBuilder();
            double totalIncome = 0;
            
            if(orderList != null)
            {
                for(int i = 0; i < orderList.size(); i++)
                {
                    totalIncome += orderList.get(i).getTotalPrice();
                }
                
            }   
            sb.append("Report from " + orderList.get(0).getOrderDate().toString() + " to " + orderList.get(orderList.size()-1).getDueDate().toString());
             sb.append("\r\n");
            sb.append("Order ID");
            sb.append(",");
            sb.append("Customer Email");
            sb.append(",");
            sb.append("Status");
            sb.append(",");
            sb.append("Start Date");
            sb.append(",");
            sb.append("End Date");
            sb.append(",");
            sb.append("Item Id");
            sb.append(",");
            sb.append("Item name");
            sb.append(",");
            sb.append("Item's Price");
            sb.append(",");
            sb.append("Item's Quantity");
            sb.append("\r\n");
            for(int i =0; i < orderList.size(); i++)
            {
                sb.append(orderList.get(i).getOrderID()); 
                sb.append(",");
                sb.append(orderList.get(i).getCustomerEmail());
                sb.append(",");
                sb.append(orderList.get(i).getOrderStatus());
                sb.append(",");
                sb.append(orderList.get(i).getOrderDate().toString()); 
                sb.append(",");
                sb.append(orderList.get(i).getDueDate().toString());
                sb.append(",");
                sb.append(Integer.toString(orderList.get(i).getItemList().get(0).getItemId()));
                sb.append(",");
                sb.append(orderList.get(i).getItemList().get(0).getItemName());
                sb.append(",");
                sb.append(Double.toString(orderList.get(i).getItemList().get(0).getPrice()));
                sb.append(",");
                sb.append(Integer.toString(orderList.get(i).getItemList().get(0).getQuantity()));
                sb.append("\r\n");
                for(int k =1; k < orderList.get(i).getItemList().size(); k++)
                {
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append(Integer.toString(orderList.get(i).getItemList().get(k).getItemId()));
                    sb.append(",");
                    sb.append(orderList.get(i).getItemList().get(k).getItemName());
                    sb.append(",");
                    sb.append(Double.toString(orderList.get(i).getItemList().get(k).getPrice()));
                    sb.append(",");
                    sb.append(Integer.toString(orderList.get(i).getItemList().get(k).getQuantity()));
                    sb.append("\r\n");
                }
            sb.append("Order Price: " +  Double.toString(orderList.get(i).getTotalPrice()));
            sb.append("\r\n");
            }
            sb.append("Total Income:  " +  Double.toString(totalIncome));
            pw.write(sb.toString());
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pw.close();
        }
        return true;
    }
    
    /**
     * Generates report of all sales since the first of the month to the current date and creates a file based off of the parameter fileName.
     * 
     * @param fileName
     * @return 
     */
    public boolean printReportOfLastMonth(String fileName)
    {
        PrintWriter pw = null;
        try {
            AdminBroker ab = new AdminBroker();
            List<Order> orderList = ab.getAllPastOrder();
            List<Order> monthOrderList = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            //current date
            java.util.Date date1 = calendar.getTime();
            java.sql.Date currentDate = new Date(date1.getTime());
            //first date of last moth from current date
            calendar.add(Calendar.MONTH, -1);
            calendar.set(Calendar.DATE, 1);
            java.util.Date date2 = calendar.getTime();
            java.sql.Date firstDateLastMonth = new Date(date2.getTime());
            
            for(int f = 0; f < orderList.size(); f++)
            {
                if(currentDate.compareTo(orderList.get(f).getOrderDate()) > 0 && firstDateLastMonth.compareTo(orderList.get(f).getOrderDate()) < 0)
                {
                    monthOrderList.add(orderList.get(f));
                }
            }
            
            Path reportFilePath = Paths.get("");
            String reportString = reportFilePath.toAbsolutePath().toString();
            //String reportFilePath = "C:\\Users\\cuong\\OneDrive\\Desktop\\TestCSV\\" + fileName;
            File file = new File(reportString + "\\" + fileName);
            pw = new PrintWriter (file);
            StringBuilder sb = new StringBuilder();
            double totalIncome = 0;
            
            if(monthOrderList != null)
            {
                for(int i = 0; i < monthOrderList.size(); i++)
                {
                    totalIncome += monthOrderList.get(i).getTotalPrice();
                }
                
            }   
            sb.append("Report from " + firstDateLastMonth.toString() + " to " + currentDate.toString());
             sb.append("\r\n");
            sb.append("Order ID");
            sb.append(",");
            sb.append("Customer Email");
            sb.append(",");
            sb.append("Status");
            sb.append(",");
            sb.append("Start Date");
            sb.append(",");
            sb.append("End Date");
            sb.append(",");
            sb.append("Item Id");
            sb.append(",");
            sb.append("Item name");
            sb.append(",");
            sb.append("Item's Price");
            sb.append(",");
            sb.append("Item's Quantity");
            sb.append("\r\n");
            for(int i =0; i < monthOrderList.size(); i++)
            {
                sb.append(monthOrderList.get(i).getOrderID()); 
                sb.append(",");
                sb.append(monthOrderList.get(i).getCustomerEmail());
                sb.append(",");
                sb.append(monthOrderList.get(i).getOrderStatus());
                sb.append(",");
                sb.append(monthOrderList.get(i).getOrderDate().toString()); 
                sb.append(",");
                sb.append(monthOrderList.get(i).getDueDate().toString());
                sb.append(",");
                sb.append(Integer.toString(monthOrderList.get(i).getItemList().get(0).getItemId()));
                sb.append(",");
                sb.append(monthOrderList.get(i).getItemList().get(0).getItemName());
                sb.append(",");
                sb.append(Double.toString(monthOrderList.get(i).getItemList().get(0).getPrice()));
                sb.append(",");
                sb.append(Integer.toString(monthOrderList.get(i).getItemList().get(0).getQuantity()));
                sb.append("\r\n");
                for(int k =1; k < monthOrderList.get(i).getItemList().size(); k++)
                {
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append(Integer.toString(monthOrderList.get(i).getItemList().get(k).getItemId()));
                    sb.append(",");
                    sb.append(monthOrderList.get(i).getItemList().get(k).getItemName());
                    sb.append(",");
                    sb.append(Double.toString(monthOrderList.get(i).getItemList().get(k).getPrice()));
                    sb.append(",");
                    sb.append(Integer.toString(monthOrderList.get(i).getItemList().get(k).getQuantity()));
                    sb.append("\r\n");
                }
            sb.append("Order Price: " +  Double.toString(monthOrderList.get(i).getTotalPrice()));
            sb.append("\r\n");
            }
            sb.append("Total Income:  " +  Double.toString(totalIncome));
            pw.write(sb.toString());
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pw.close();
        }
        return true;
    }
    
    /**
     * Generates report of all sales since last Monday to the current date and creates a file based off of the parameter fileName.
     * 
     * @param fileName
     * @return 
     */
    public boolean printReportOflastWeek(String fileName)
    {
        PrintWriter pw = null;
        try {
            AdminBroker ab = new AdminBroker();
            List<Order> orderList = ab.getAllPastOrder();
            List<Order> weeklyOrderList = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            //current date
            java.util.Date date1 = calendar.getTime();
            java.sql.Date currentDate = new Date(date1.getTime());
            //first date of last moth from current date
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            java.util.Date date2 = calendar.getTime();
            java.sql.Date firstDateLastWeek = new Date(date2.getTime());
            
            for(int f = 0; f < orderList.size(); f++)
            {
                if(currentDate.compareTo(orderList.get(f).getOrderDate()) > 0 && firstDateLastWeek.compareTo(orderList.get(f).getOrderDate()) < 0)
                {
                    weeklyOrderList.add(orderList.get(f));
                }
            }
            
            Path reportFilePath = Paths.get("");
            String reportString = reportFilePath.toAbsolutePath().toString();
            //String reportFilePath = "C:\\Users\\cuong\\OneDrive\\Desktop\\TestCSV\\" + fileName;
            File file = new File(reportFilePath + "\\" + fileName);
            pw = new PrintWriter (file);
            StringBuilder sb = new StringBuilder();
            double totalIncome = 0;
            
            if(weeklyOrderList != null)
            {
                for(int i = 0; i < weeklyOrderList.size(); i++)
                {
                    totalIncome += weeklyOrderList.get(i).getTotalPrice();
                }
                
            }   
            sb.append("Report from " + firstDateLastWeek.toString() + " to " + currentDate.toString());
             sb.append("\r\n");
            sb.append("Order ID");
            sb.append(",");
            sb.append("Customer Email");
            sb.append(",");
            sb.append("Status");
            sb.append(",");
            sb.append("Start Date");
            sb.append(",");
            sb.append("End Date");
            sb.append(",");
            sb.append("Item Id");
            sb.append(",");
            sb.append("Item name");
            sb.append(",");
            sb.append("Item's Price");
            sb.append(",");
            sb.append("Item's Quantity");
            sb.append("\r\n");
            for(int i =0; i < weeklyOrderList.size(); i++)
            {
                sb.append(weeklyOrderList.get(i).getOrderID()); 
                sb.append(",");
                sb.append(weeklyOrderList.get(i).getCustomerEmail());
                sb.append(",");
                sb.append(weeklyOrderList.get(i).getOrderStatus());
                sb.append(",");
                sb.append(weeklyOrderList.get(i).getOrderDate().toString()); 
                sb.append(",");
                sb.append(weeklyOrderList.get(i).getDueDate().toString());
                sb.append(",");
                sb.append(Integer.toString(weeklyOrderList.get(i).getItemList().get(0).getItemId()));
                sb.append(",");
                sb.append(weeklyOrderList.get(i).getItemList().get(0).getItemName());
                sb.append(",");
                sb.append(Double.toString(weeklyOrderList.get(i).getItemList().get(0).getPrice()));
                sb.append(",");
                sb.append(Integer.toString(weeklyOrderList.get(i).getItemList().get(0).getQuantity()));
                sb.append("\r\n");
                for(int k =1; k < weeklyOrderList.get(i).getItemList().size(); k++)
                {
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append("\t\t\t");
                    sb.append(",");
                    sb.append(Integer.toString(weeklyOrderList.get(i).getItemList().get(k).getItemId()));
                    sb.append(",");
                    sb.append(weeklyOrderList.get(i).getItemList().get(k).getItemName());
                    sb.append(",");
                    sb.append(Double.toString(weeklyOrderList.get(i).getItemList().get(k).getPrice()));
                    sb.append(",");
                    sb.append(Integer.toString(weeklyOrderList.get(i).getItemList().get(k).getQuantity()));
                    sb.append("\r\n");
                }
            sb.append("Order Price: " +  Double.toString(weeklyOrderList.get(i).getTotalPrice()));
            sb.append("\r\n");
            }
            sb.append("Total Income:  " +  Double.toString(totalIncome));
            pw.write(sb.toString());
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ManagerBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            pw.close();
        }
        return true;
    }
        
}
