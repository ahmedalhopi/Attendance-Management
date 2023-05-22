package finalproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

public class Assistants_CoursesController implements Initializable {

    @FXML
    private TableView tableview;
    @FXML
    private TableColumn<ObservableList<String>, String> id_col;
    @FXML
    private TableColumn<ObservableList<String>, String> name_col;
    @FXML
    private TableColumn<ObservableList<String>, String> course_col;
    @FXML
    private Pane linkPane;
    @FXML
    private TextField assistants_number_link;
    @FXML
    private TextField course_code_link;
    @FXML
    private Pane unlinkPane;
    @FXML
    private TextField assistants_number_unlink;
    @FXML
    private TextField course_code_unlink;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void getAssistantsAndCoursesData() throws ClassNotFoundException {
        tableview.setVisible(true);
        linkPane.setVisible(false);
        unlinkPane.setVisible(false);
        tableview.getItems().clear();
        id_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        name_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        course_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        PreparedStatement pst;
        ResultSet rs = null;
        Connection conn;
        int i = 0;

        String sel = "select (select full_name from mang.assistants where assistant_number = r.assistant_number),(select name from mang.courses  where course_code = r.code) from mang.assistants_courses r;";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(String.valueOf(++i));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("name"));
                tableview.getItems().add(row);
                System.out.println(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void linkingData() {
        tableview.setVisible(false);
        linkPane.setVisible(true);
        unlinkPane.setVisible(false);
    }

    public void unLinkingData() {
        tableview.setVisible(false);
        linkPane.setVisible(false);
        unlinkPane.setVisible(true);
    }

    public void linkingAssistantsWithCourse() throws ClassNotFoundException {
        PreparedStatement pst;
        Connection conn;
        String sel = "INSERT INTO mang.assistants_courses (assistant_number, code) VALUES(?, ?);";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, assistants_number_link.getText());
            pst.setString(2, course_code_link.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "The assistants is linked to the course");
            assistants_number_link.setText("");
            course_code_link.setText("");

        } catch (SQLException ex) {
            int errorCode = 0;
            String stm1 = "ERROR: insert or update on table \"assistants_courses\" violates foreign key constraint \"assistants_courses_assistant_number_fkey\"\n"
                    + "  Detail: Key (assistant_number)=(" + assistants_number_link.getText() + ") is not present in table \"assistants\".";
            String stm2 = "ERROR: insert or update on table \"assistants_courses\" violates foreign key constraint \"assistants_courses_code_fkey\"\n"
                    + "  Detail: Key (code)=("+course_code_link.getText()+") is not present in table \"courses\".";
            String stm3 = "ERROR: duplicate key value violates unique constraint \"assistants_courses_un\"\n"
                    + "  Detail: Key (assistant_number, code)=(" + assistants_number_link.getText() + ", " + course_code_link.getText() + ") already exists.";
            if (stm1.equals(ex.getMessage())) {
                errorCode = 75001;
            } else if (stm2.equals(ex.getMessage())) {
                errorCode = 75002;
            } else if (stm3.equals(ex.getMessage())) {
                errorCode = 75003;
            }
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println(ex.getMessage());
            switch (errorCode) {
                case 75001:
                    JOptionPane.showMessageDialog(null, "The assistant number not found");
                    break;
                case 75002:
                    JOptionPane.showMessageDialog(null, "The course code not found");
                    break;
                case 75003:
                    JOptionPane.showMessageDialog(null, "The assistant already linked with this course.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, ex);
                    break;
            }

        }
    }

    public void unlinkingAssistantsWithCourse() throws ClassNotFoundException {
        PreparedStatement pst;
        Connection conn;
        String sel = "DELETE FROM mang.assistants_courses WHERE assistant_number = ? and code = ?;";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, assistants_number_unlink.getText());
            pst.setString(2, course_code_unlink.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "The assistants is unlinked with the course");
            assistants_number_link.setText("");
            course_code_link.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
}
