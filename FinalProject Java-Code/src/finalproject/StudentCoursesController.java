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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

public class StudentCoursesController implements Initializable {

    @FXML
    private TableView<ObservableList<String>> tableView;
    @FXML
    private TableColumn<ObservableList<String>, String> student_number_col;
    @FXML
    private TableColumn<ObservableList<String>, String> student_name_col;
    @FXML
    private TableColumn<ObservableList<String>, String> course_col;
    @FXML
    private TableColumn<ObservableList<String>, String> status_col;
    ///////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Pane linkPane;
    @FXML
    private TextField student_number_txt;
    @FXML
    private TextField code_txt;
    @FXML
    private RadioButton registered_radio;
    @FXML
    private RadioButton withdrawn_radio;
    @FXML
    private ToggleGroup radioSelect;
    //////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Pane updatePane;
    @FXML
    private TextField student_number_txt_update;
    @FXML
    private TextField code_txt_update;
    @FXML
    private RadioButton registered_radio_update;
    @FXML
    private RadioButton withdrawn_radio_update;
    @FXML
    private ToggleGroup radioSelect_update;
    ////////////////////////////////////////////////////////////////////////////////////

    ObservableList<ObservableList<String>> dataTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.setVisible(false);
        linkPane.setVisible(false);
        updatePane.setVisible(false);
    }

    public void getStudentsAndCourses() throws ClassNotFoundException {
        tableView.setVisible(true);
        linkPane.setVisible(false);
        updatePane.setVisible(false);

        dataTable.clear();
        tableView.getItems().clear();
        student_number_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        student_name_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        course_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        status_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        ResultSet rs;
        Connection conn;
        PreparedStatement pst;

        String sel = "SELECT student_number,(select full_name from mang.students s where s.student_number = sc.student_number) ,(select name from mang.courses c where c.course_code = sc.course_code), status FROM mang.students_courses sc;";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("student_number"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("name"));
                row.add(rs.getString("status"));
                tableView.getItems().add(row);
                dataTable.add(row);
            }

            System.out.println(dataTable);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void addStudent() {
        tableView.setVisible(false);
        linkPane.setVisible(true);
        updatePane.setVisible(false);
    }

    public void linkStudentWithCourse() throws ClassNotFoundException {
        PreparedStatement pst;
        Connection conn;
        String sel = "INSERT INTO mang.students_courses (student_number, course_code, status) VALUES(?, ?, ?);";

        try {
            String status_option = "";
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, student_number_txt.getText());
            pst.setString(2, code_txt.getText());
            RadioButton selectedRadioButton = (RadioButton) radioSelect.getSelectedToggle();
            if (selectedRadioButton != null) {
                if (selectedRadioButton == registered_radio) {
                    status_option = "Registered";
                    System.out.println("Selected: Registered");
                } else if (selectedRadioButton == withdrawn_radio) {
                    status_option = "Withdrawn";
                    System.out.println("Selected: Withdrawn");
                }
            }
            pst.setString(3, status_option);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "A new student has been added");
            student_number_txt.setText("");
            code_txt.setText("");
            registered_radio.setSelected(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
        }
    }

    public void updateStudent() {
        tableView.setVisible(false);
        linkPane.setVisible(false);
        updatePane.setVisible(true);
    }

    public void updateStudentWithCourse() throws ClassNotFoundException {
          PreparedStatement pst;
        Connection conn;
        String sel = "UPDATE mang.students_courses SET  status=? WHERE student_number=? and course_code=?;";
        try {
            String status_option = "";
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            RadioButton selectedRadioButton = (RadioButton) radioSelect_update.getSelectedToggle();
        if (selectedRadioButton != null) {
            if (selectedRadioButton == registered_radio_update) {
                status_option = "Registered";
                System.out.println("Selected: Registered");
            } else if (selectedRadioButton == withdrawn_radio_update) {
                status_option = "Withdrawn";
                System.out.println("Selected: Withdrawn");
            }
        }
            pst.setString(1, status_option);
            pst.setString(2, student_number_txt_update.getText());
            pst.setString(3, code_txt_update.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "The data has been updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
        }
    }

}
