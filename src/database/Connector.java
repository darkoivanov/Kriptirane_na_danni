
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector{
    // chlenove
    private Connection connect;
    private static Connector connectionObj = null;
    
    // private constructor
    private Connector(){
        this.EstablishConnection();
    }
    
    private void EstablishConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/aesdb", "root", "str8rebell");
        } catch (Exception ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //funkciq koqto vryshta instancqi ot vryzkata
    public static Connector getConnectionInstance(){
        if(connectionObj == null){
            connectionObj = new Connector();
        }
        
        return connectionObj;
    }
    
    // functiq koqto vryshta java.sql obketa
    public Connection getConnection(){
        try {
            // proverqva dali vrazkata e zatvoren 
            if(this.connect.isClosed()){
                this.EstablishConnection();
                System.out.println("New Connection");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.connect;
    }
}
