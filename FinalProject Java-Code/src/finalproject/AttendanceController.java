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
import javax.swing.JOptionPane;

public class AttendanceController implements Initializable {

    @FXML
    private TableView<ObservableList<String>> tableView;
    @FXML
    private TableColumn<ObservableList<String>, String> lecture_id_col;
    @FXML
    private TableColumn<ObservableList<String>, String> student_number_col;
    @FXML
    private TableColumn<ObservableList<String>, String> full_name_col;
    @FXML
    private TableColumn<ObservableList<String>, String> status_col;
    @FXML
    private TextField no_lecture_txt;
    @FXML
    private TextField student_number_txt;
    @FXML
    private TextField full_name_txt;
    @FXML
    private RadioButton present_radio;
    @FXML
    private RadioButton absent_radio;
    @FXML
    private ToggleGroup radioSelect;

    ObservableList<ObservableList<String>> dataTable = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void getStudentsForLecture() throws ClassNotFoundException {
        dataTable.clear();
        tableView.getItems().clear();
        lecture_id_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        student_number_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        full_name_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        status_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        ResultSet rs;
        Connection conn;
        PreparedStatement pst;

        String sel = "INSERT INTO mang.attendance (lecture_id, student_number, student_name) select ?,s.student_number,full_name  from  mang.students s where student_number in (select st.student_number from mang.students_courses st where st.status = 'Registered' and course_code = (select l.course_code from mang.lectures l where lecture_id = ?));";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            int no_lect = Integer.parseInt(no_lecture_txt.getText());
            pst.setInt(1, no_lect);
            pst.setInt(2, no_lect);
            pst.executeQuery();
        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, ex);
        }

        sel = "SELECT  lecture_id, student_number, student_name, status FROM mang.attendance where lecture_id = ? order by status,student_number  ;";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            int no_lect = Integer.parseInt(no_lecture_txt.getText());
            pst.setInt(1, no_lect);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("lecture_id"));
                row.add(rs.getString("student_number"));
                row.add(rs.getString("student_name"));
                row.add(rs.getString("status"));
                tableView.getItems().add(row);
                dataTable.add(row);
            }
            ObservableList<String> firstRow = dataTable.get(0);
            String[] rowData = firstRow.toArray(new String[0]);

            student_number_txt.setText(rowData[1]);
            full_name_txt.setText(rowData[2]);

            System.out.println(dataTable);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void getNextStudent() throws ClassNotFoundException {

        if (!dataTable.isEmpty()) {

            RadioButton selectedRadioButton = (RadioButton) radioSelect.getSelectedToggle();
            String status_option = "";
            if (selectedRadioButton != null) {
                if (selectedRadioButton == present_radio) {
                    status_option = "Present";
                    System.out.println("Selected: Present");
                } else if (selectedRadioButton == absent_radio) {
                    status_option = "Absent";
                    System.out.println("Selected: Female");
                }
            }

            PreparedStatement pst;
            Connection conn;
            String sel = "UPDATE mang.attendance SET  status = ? WHERE student_number = ? and lecture_id = ?;";
            try {
                conn = DatabaseConnect.connDB();
                pst = conn.prepareStatement(sel);
                pst.setString(1, status_option);
                pst.setString(2, student_number_txt.getText());
                int no_lect = Integer.parseInt(no_lecture_txt.getText());
                pst.setInt(3, no_lect);
                pst.executeUpdate();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                System.out.println(ex);
            }
            tableView.getItems().clear();
            lecture_id_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
            student_number_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
            full_name_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
            status_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));

            sel = "SELECT  lecture_id, student_number, student_name, status FROM mang.attendance where lecture_id = ? order by status,student_number;";
            try {
                ResultSet rs;
                conn = DatabaseConnect.connDB();
                pst = conn.prepareStatement(sel);
                int no_lect = Integer.parseInt(no_lecture_txt.getText());
                pst.setInt(1, no_lect);
                rs = pst.executeQuery();
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    row.add(rs.getString("lecture_id"));
                    row.add(rs.getString("student_number"));
                    row.add(rs.getString("student_name"));
                    row.add(rs.getString("status"));
                    tableView.getItems().add(row);
                }
                dataTable.remove(0);
                ObservableList<String> firstRow = dataTable.get(0);
                String[] rowData = firstRow.toArray(new String[0]);

                student_number_txt.setText(rowData[1]);
                full_name_txt.setText(rowData[2]);

                System.out.println(LoginController.USER_ID);
                present_radio.setSelected(true);
                System.out.println(dataTable);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Finsed all students");
        }
    }



}
