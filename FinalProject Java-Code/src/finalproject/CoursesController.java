
package finalproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javax.swing.JOptionPane;

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
    @FXML
    private TableColumn<ObservableList<String>, String> procedure;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void get_courses() throws ClassNotFoundException {
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

    }
;

}
