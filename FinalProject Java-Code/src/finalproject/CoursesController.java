package finalproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javax.swing.JOptionPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author IT
 */
public class CoursesController implements Initializable {

    @FXML
    private TableView<ObservableList<String>> tableView;
    @FXML
    private TableColumn<ObservableList<String>, String> code;
    @FXML
    private TableColumn<ObservableList<String>, String> course;
    @FXML
    private TableColumn<ObservableList<String>, String> subject;
    @FXML
    private TableColumn<ObservableList<String>, String> book;
    @FXML
    private TableColumn<ObservableList<String>, String> number_lecture;
    @FXML
    private TableColumn<ObservableList<String>, String> teacher;
    @FXML
    private TableColumn<ObservableList<String>, String> place;
    /////////////////////////////////////////////////////////
    @FXML
    private Pane paneContainer;
    @FXML
    private TextField code_txt;
    @FXML
    private TextField name_txt;
    @FXML
    private TextField subject_txt;
    @FXML
    private TextField book_txt;
    @FXML
    private TextField no_lecture_txt;
    @FXML
    private TextField teacher_txt;
    @FXML
    private TextField place_txt;
    /////////////////////////////////////////////////////////////////
    @FXML
    private Pane paneContainerUpdate;
    @FXML
    private Pane paneContainerDelete;
    @FXML
    private Button deleteBtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        no_lecture_txt.setTextFormatter(createNumericTextFormatter());
    }

    private TextFormatter<String> createNumericTextFormatter() {
        Pattern pattern = Pattern.compile("\\d*"); // Only allow digits (0-9)
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (pattern.matcher(newText).matches()) {
                return change;
            }
            return null; // Reject the change
        };
        return new TextFormatter<>(filter);
    }

    public void get_courses() throws ClassNotFoundException {
        tableView.setVisible(true);
        code.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        subject.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        book.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        number_lecture.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        teacher.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        place.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
        PreparedStatement pst;
        ResultSet rs = null;
        Connection conn;

        String sel = "select * from mang.courses";

        try {

            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("course_code"));
                row.add(rs.getString("name"));
                row.add(rs.getString("subject"));
                row.add(rs.getString("book"));
                row.add(rs.getString("number_lectures"));
                row.add(rs.getString("teacher"));
                row.add(rs.getString("place"));
                tableView.getItems().add(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    ;
    public void add_course() {
        paneContainer.setVisible(true);

    }

    public void saveDate() throws ClassNotFoundException {
        PreparedStatement pst;
        Connection conn;
        String sel = "INSERT INTO mang.courses (course_code, name, subject, book, number_lectures, teacher, place) VALUES(?, ?, ?, ?, ?, ?, ?);";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            int no_lect = Integer.parseInt(no_lecture_txt.getText());
            pst.setString(1, code_txt.getText());
            pst.setString(2, name_txt.getText());
            pst.setString(3, subject_txt.getText());
            pst.setString(4, book_txt.getText());
            pst.setInt(5, no_lect);
            pst.setString(6, teacher_txt.getText());
            pst.setString(7, place_txt.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "A new course has been added");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.err.println(ex);
        }
    }

    ;
    
      public void update_course() {
        paneContainerUpdate.setVisible(true);

    }

    ;
      
      public void getDataForCourse() throws ClassNotFoundException {
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst;
        ResultSet rs;
        String log = "select * from mang.courses where course_code = ? ";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, code_txt.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                name_txt.setText(rs.getString("name"));
                subject_txt.setText(rs.getString("subject"));
                book_txt.setText(rs.getString("book"));
                no_lecture_txt.setText(rs.getString("number_lectures"));
                teacher_txt.setText(rs.getString("teacher"));
                place_txt.setText(rs.getString("place"));
                deleteBtn.setDisable(false);
            } else {
                JOptionPane.showMessageDialog(null, "No course has the input code");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    ;
      
      public void updateDate() throws ClassNotFoundException {
        PreparedStatement pst;
        Connection conn;
        String sel = "UPDATE mang.courses SET name=? , subject= ?, book= ?, number_lectures=? , teacher=? , place=? WHERE course_code=? ;";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            int no_lect = Integer.parseInt(no_lecture_txt.getText());
            pst.setString(1, name_txt.getText());
            pst.setString(2, subject_txt.getText());
            pst.setString(3, book_txt.getText());
            pst.setInt(4, no_lect);
            pst.setString(5, teacher_txt.getText());
            pst.setString(6, place_txt.getText());
            pst.setString(7, code_txt.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "The data has been updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.err.println(ex);
        }
    }

    ;
      
        public void delete_course() {
        paneContainerDelete.setVisible(true);
    }

    ;
        
        public void deleteData() throws ClassNotFoundException {
        JOptionPane.showMessageDialog(null, "Do you want to delete course?");
        PreparedStatement pst;
        Connection conn;
        String sel = "DELETE FROM mang.courses WHERE course_code=?;";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, code_txt.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "The Course has been deleted");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.err.println(ex);
        }
    }
;
}
