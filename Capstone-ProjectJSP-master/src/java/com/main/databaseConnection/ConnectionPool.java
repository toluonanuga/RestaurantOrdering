/*
 * Class Description: This class is a singleton class that allows only one database connection at a time.
*/

package com.main.databaseConnection;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
/**
 * 
 * Class Description: This class is a singleton class that allows only one database connection at a time.
 * @author our great managers :)
 */
public class ConnectionPool {
    /**
     * @param pool ConnectionPool set to null
     * @param dataSource DataSource set to null
     */
    private static ConnectionPool pool = null;
    private static DataSource dataSource = null;
    /**
     * Creates a connection 
     */
    private ConnectionPool() {
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/tandoorigrilldb");
        } catch (NamingException e) {
            System.out.println(e);
        }

    }
    /**
     * If the connection pool is null(default) it will create the connection
     * @return the connection pool
     */
    public static synchronized ConnectionPool createConnection() {
        if(pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }
    /**
     * Gets the connection if possible.
     * @return returns the connection
     */
    public static Connection getConnection() {
        try{
            return dataSource.getConnection();
           
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    /**
     * Closes the connection so more users can connect at once.
     * @param c connection that is passed in
     */
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }
}
