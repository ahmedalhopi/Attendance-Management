package finalproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IT
 */
public class LoginController implements Initializable {


    @FXML
    private TextField id_number_txt;
    @FXML
    private PasswordField password_txt;
    @FXML
    private CheckBox admin_login;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loginButtonClicked() throws ClassNotFoundException{
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst ;
        ResultSet rs ;
        boolean found = false;
        String log = "";
        if(admin_login.isSelected()){
            log = "select * from manager where manager_number = ? and password = ?";
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
        }
        }else{
            log = "select * from assistants where assistant_number = ? and password = ?";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, id_number_txt.getText());
            pst.setString(2, password_txt.getText());
            rs = pst.executeQuery();
            if (!rs.wasNull()) {
                found = true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
        
        if(found)
            System.out.print("Login Don");
        else
            JOptionPane.showMessageDialog(null, "Id Number or Password is not corrcet!");
    };
    
}
