package Ciclo3.Dao;

import Ciclo3.Control.Main;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.derby.drda.NetworkServerControl;

public class ControlConexao {
    
    private String dbURL = "jdbc:derby://localhost:21255/3e_CO2;create=true;user=root;password=root";
    private Connection conn = null;
    
    public ControlConexao() {
        try {
            NetworkServerControl server = new NetworkServerControl(InetAddress.getByName("localhost"),21255);
            server.start(null);
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection(dbURL);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão com o banco de dados, abra o programa novamente");
            System.exit(0);
        }
    }

    public Connection getConn() {
        return conn;
    }    
}
