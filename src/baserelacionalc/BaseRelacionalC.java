/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionalc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class BaseRelacionalC {

    Connection conn;
    ResultSet rs;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BaseRelacionalC brC = new BaseRelacionalC();
        brC.connection();
        brC.listar();
        brC.insertar();
        brC.listar();
        brC.actualizar();
        brC.listar();
    }

    public void connection() {
        try {
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

            conn = DriverManager.getConnection(url);
            System.out.println("Conexion Establecida.\n");

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listar() {
        try {
            PreparedStatement pS = conn.prepareStatement("select produtos.* from produtos where prezo>?");
            pS.setInt(1, 5);
            rs = (ResultSet) pS.executeQuery();

            while (rs.next()) {
                System.out.print(rs.getString(1) + " - ");
                System.out.print(rs.getString(2) + " - ");
                System.out.println(rs.getString(3));
            }
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalC.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void actualizar() {
        try {
            PreparedStatement pS = conn.prepareStatement("update produtos set prezo=? where codigo=?");
            pS.setInt(1, 111);
            pS.setString(2, "p4");
            pS.executeQuery();
            System.out.println("Actualización Realizada con Éxito.\n");

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertar() {
        try {
            PreparedStatement pS = conn.prepareStatement("insert into produtos values(?,?,?)");
            pS.setString(1, "p5");
            pS.setString(2, "chave alen");
            pS.setInt(3, 18);
            pS.executeQuery();
            System.out.println("Inserccion Realizada con Éxito.\n");

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
