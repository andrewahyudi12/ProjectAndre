package koneksi;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import java.sql.SQLException;


public class koneksiDB {

  Connection conn = null;
    
  public static Connection getkoneksi(){
        try {
           Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_laundry","root","");
            //JOptionPane.showMessageDialog(null,"koneksi berhasil");
            //System.out.println("Koneksi Berhasil");
          return conn;
    }catch (Exception e){
        JOptionPane.showMessageDialog(null,"koneksi gagal"+ e.getMessage());
   return null;
    }
  }
}
 
    

