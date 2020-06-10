package login;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public class koneksi2 {
    private static Connection koneksi;
    public static Connection getkoneksi (){
        if(koneksi==null){
            try{
                String url = new String ();
                String user = new String ();
                String password = new String();
                url = "jdbc:mysql://localhost:3306/db_product";
                user = "root";
                password = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = (Connection) DriverManager.getConnection(url, user, password);              
            }catch(SQLException t) {
                System.out.println("Koneksi Error!");
            }
        }
        return koneksi;
    }

}
