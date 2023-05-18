package finalproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    @FXML
    private TextField id_number_txt;
    @FXML
    private PasswordField password_txt;
    @FXML
    private CheckBox admin_login;
    @FXML
    private Button login_btn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = DatabaseConnect.connDB();
            System.out.println(conn);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id_number_txt.setText("M001");
        password_txt.setText("m001");
        admin_login.setSelected(true);
    }

    public void loginButtonClicked(ActionEvent actionEvent) throws ClassNotFoundException, IOException {
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst;
        ResultSet rs;
        boolean found = false;
        boolean isAdmin = false;
        String log = "";
        if (admin_login.isSelected()) {
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
        } else {
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

        if (found && isAdmin) {
            System.out.println("Login Don Admin");
            Stage primaryStage = new Stage();
            Stage currentStage = (Stage) login_btn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHome.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Attendance Management");
            primaryStage.setResizable(false);
            primaryStage.iconifiedProperty();
            Image icon = new Image(getClass().getResourceAsStream("img/icon.jpg"));
            primaryStage.getIcons().add(icon);
            currentStage.close();
            primaryStage.show();

        } else if (found && !isAdmin) {
            System.out.println("Login Don User");
        } else {
            JOptionPane.showMessageDialog(null, "Id Number or Password is not corrcet!");
        }
    }
;

}
