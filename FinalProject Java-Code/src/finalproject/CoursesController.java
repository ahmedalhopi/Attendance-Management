
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
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    } ;
    public void add_course() {
        paneContainer.setVisible(true);
        no_lecture_txt.setTextFormatter(createNumericTextFormatter());
    }
    public void saveDate() throws ClassNotFoundException{
        PreparedStatement pst;
        Connection conn;
        String sel = "INSERT INTO mang.courses (course_code, name, subject, book, number_lectures, teacher, place) VALUES(?, ?, ?, ?, ?, ?, ?);";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            int no_lect = Integer.parseInt(no_lecture_txt.getText());
            System.err.println(Integer.parseInt(no_lecture_txt.getText()));
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
    };
    
      public void update_course() {
          paneContainerUpdate.setVisible(true);

    };
      
      public void getDataForCourse(){
          
      };
      
      public void updateDate(){
          
      };
      
        public void delete_course() {

    }
;

}
