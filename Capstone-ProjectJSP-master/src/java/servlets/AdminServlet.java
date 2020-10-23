/*
 * Class Description: A bridge between JSPs and the Services: This Servlet works on options available for Administrators.
 */
package servlets;

import brokers.AdminBroker;
import brokers.BackupAndRestore;
import brokers.EmployeeBroker;
import com.google.gson.Gson;
import com.main.actor.Actor;
import com.main.menu.Item;
import com.main.menu.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import services.AdminService;
import services.MenuService;

/**
 * Class Description: A bridge between JSPs and the Services: This Servlet works on options available for Administrators.
 *
 * @author cuong
 */
@MultipartConfig
public class AdminServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        MenuService ms = new MenuService();
          AdminBroker ab = new AdminBroker();
          BackupAndRestore br = new BackupAndRestore();
          
        List<Item> items = null;
        List<Actor> actors = null;
        List<Order> orders = null;
        List<String> restoreFileList = null;
        orders = ab.getAllPastOrder();
        restoreFileList = br.listAllBackupFiles();
        items = ms.displayAllItem();
        try {
            actors = ab.getAll();
        } catch (SQLException ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        request.setAttribute("account_edit", actors);
        request.setAttribute("ItemMenu", items);
        request.setAttribute("orderList", orders);
        request.setAttribute("restoreFileList", restoreFileList);

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Gson gson = new Gson();
        if(action.equals("restore"))
        {
            String restoreFileName = request.getParameter("filename");
            BackupAndRestore br = new BackupAndRestore();
            boolean checkRestore = br.restore(restoreFileName);
                response.setContentType("application/json");
                response.getWriter().write(gson.toJson(checkRestore));

        }
        else if(action.equals("getAllRestoreFile"))
        {
            BackupAndRestore br = new BackupAndRestore();
            List<String> fileList = br.listAllBackupFiles();
            response.setContentType("application/json");
            response.getWriter().write(gson.toJson(fileList));
        }
        else if(action.equals("createBackup"))
        {
             BackupAndRestore br = new BackupAndRestore();
             String backupFileName = request.getParameter("bkfilename");
             DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
             LocalDateTime currentTime = LocalDateTime.now();
             String backuptime = dateFormat.format(currentTime);
             backupFileName += "_"+backuptime;
             boolean checkBackup = br.backup(backupFileName);
             response.setContentType("application/json");
             response.getWriter().write(gson.toJson(checkBackup));
            
        }
        else if(action.equals("addItem"))
        {
            MenuService ms = new MenuService();
          String itemName = request.getParameter("addItemName");
          double itemPrice = Double.parseDouble(request.getParameter("addItemPrice"));
          String itemDescription = request.getParameter("addItemDes");
          String itemCategory = request.getParameter("addItemCate");
          Part imageFile = request.getPart("addItemImage");
          String imageFileName = Paths.get(imageFile.getSubmittedFileName()).getFileName().toString(); 
          
          String filePath = getServletContext().getRealPath("") + "image/" + imageFileName;
          System.out.println(filePath);
          //String filePath = "/home/ubuntu/CapstoneImage/"+imageFileName;
          ms.addToItemList(itemName, itemPrice, itemDescription, itemCategory, filePath);
          imageFile.write(filePath);
          //String command = "sudo chmod ugo+rwx " + filePath;
          //Runtime rt = null;
          //Process backupProcess = rt.getRuntime().exec(command);
          request.setAttribute("addItemStatus", "Successfully add item");
          getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        }
        
        
    }

    // </editor-fold>

}
