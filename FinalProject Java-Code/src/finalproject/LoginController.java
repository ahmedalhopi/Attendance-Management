package finalproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javax.swing.JOptionPane;


public class LoginController implements Initializable {


    @FXML
    private TextField id_number_txt;
    @FXML
    private PasswordField password_txt;
    @FXML
    private CheckBox admin_login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = DatabaseConnect.connDB();
            System.out.println(conn);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void loginButtonClicked() throws ClassNotFoundException{
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst ;
        ResultSet rs ;
        boolean found = false;
        boolean isAdmin = false;
        String log = "";
        if(admin_login.isSelected()){
            log = "select * from mang.manager where manager_number = ? and password = ?";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, id_number_txt.getText());
            pst.setString(2, password_txt.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                found = true;
                isAdmin = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }else{
            log = "select * from mang.assistants where assistant_number = ? and password = ?";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, id_number_txt.getText());
            pst.setString(2, password_txt.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                found = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
        }
        
        if(found && isAdmin)
            System.out.println("Login Don Admin");
        else if(found && !isAdmin)
            System.out.println("Login Don User");
        else
            JOptionPane.showMessageDialog(null, "Id Number or Password is not corrcet!");
    };
    
}
