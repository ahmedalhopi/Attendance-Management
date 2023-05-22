package finalproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javax.swing.JOptionPane;
import javafx.scene.layout.Pane;


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
    private TextField code_txt_update;
    @FXML
    private TextField name_txt_update;
    @FXML
    private TextField subject_txt_update;
    @FXML
    private TextField book_txt_update;
    @FXML
    private TextField no_lecture_txt_update;
    @FXML
    private TextField teacher_txt_update;
    @FXML
    private TextField place_txt_update;
    @FXML
    private Button updateBtn;
    ////////////////////////////////////////////////////////////////
    @FXML
    private Pane paneContainerDelete;
    @FXML
    private TextField code_txt_delete;
    @FXML
    private TextField name_txt_delete;
    @FXML
    private TextField subject_txt_delete;
    @FXML
    private TextField book_txt_delete;
    @FXML
    private TextField no_lecture_txt_delete;
    @FXML
    private TextField teacher_txt_delete;
    @FXML
    private TextField place_txt_deletet;
    @FXML
    private Button deleteBtn;
    ///////////////////////////////////////////////////////////////

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableView.setVisible(false);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(false);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        no_lecture_txt.setTextFormatter(createNumericTextFormatter());
        no_lecture_txt_delete.setTextFormatter(createNumericTextFormatter());
        no_lecture_txt_update.setTextFormatter(createNumericTextFormatter());
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
        tableView.getItems().clear();
        tableView.setVisible(true);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(false);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
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

    public void add_course() {
        tableView.setVisible(false);
        paneContainer.setVisible(true);
        paneContainerUpdate.setVisible(false);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);

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
            code_txt.setText("");
            name_txt.setText("");
            subject_txt.setText("");
            book_txt.setText("");
            no_lecture_txt.setText("");
            teacher_txt.setText("");
            place_txt.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.err.println(ex);
        }
    }

    public void update_course() {
        tableView.setVisible(false);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(true);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);

    }

    public void getDataForCourse_delete() throws ClassNotFoundException {
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst;
        ResultSet rs;
        String log = "select * from mang.courses where course_code = ? ";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, code_txt_delete.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                name_txt_delete.setText(rs.getString("name"));
                subject_txt_delete.setText(rs.getString("subject"));
                book_txt_delete.setText(rs.getString("book"));
                no_lecture_txt_delete.setText(rs.getString("number_lectures"));
                teacher_txt_delete.setText(rs.getString("teacher"));
                place_txt_deletet.setText(rs.getString("place"));
                deleteBtn.setDisable(false);
                updateBtn.setDisable(true);
                System.out.println(rs);
            } else {
                JOptionPane.showMessageDialog(null, "No course has the input code");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getDataForCourse_update() throws ClassNotFoundException {
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst;
        ResultSet rs;
        String log = "select * from mang.courses where course_code = ? ";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, code_txt_update.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                name_txt_update.setText(rs.getString("name"));
                subject_txt_update.setText(rs.getString("subject"));
                book_txt_update.setText(rs.getString("book"));
                no_lecture_txt_update.setText(rs.getString("number_lectures"));
                teacher_txt_update.setText(rs.getString("teacher"));
                place_txt_update.setText(rs.getString("place"));
                deleteBtn.setDisable(true);
                updateBtn.setDisable(false);
            } else {
                JOptionPane.showMessageDialog(null, "No course has the input code");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateDate() throws ClassNotFoundException {
        System.out.println("Integer.parseInt(no_lecture_txt.getText())  =     " + no_lecture_txt.getText());
        PreparedStatement pst;
        Connection conn;
        String sel = "UPDATE mang.courses SET name=? , subject= ?, book= ?, number_lectures=? , teacher=? , place=? WHERE course_code=? ;";
        int no_lect = Integer.parseInt(no_lecture_txt_update.getText());
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, name_txt_update.getText());
            pst.setString(2, subject_txt_update.getText());
            pst.setString(3, book_txt_update.getText());
            pst.setInt(4, no_lect);
            pst.setString(5, teacher_txt_update.getText());
            pst.setString(6, place_txt_update.getText());
            pst.setString(7, code_txt_update.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "The data has been updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.err.println(ex);
        }
    }

    public void delete_course() {
        tableView.setVisible(false);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(false);
        paneContainerDelete.setVisible(true);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
    }

    public void deleteData() throws ClassNotFoundException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete the course?");

        ButtonType confirmButton = new ButtonType("Yes");
        ButtonType cancelButton = new ButtonType("No");

        alert.getButtonTypes().setAll(confirmButton, cancelButton);

// Wait for the user's response
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == confirmButton) {
            PreparedStatement pst;
            Connection conn;
            String sel = "DELETE from mang.courses WHERE course_code=?;";
            try {
                conn = DatabaseConnect.connDB();
                pst = conn.prepareStatement(sel);
                pst.setString(1, code_txt_delete.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "The Course has been deleted");
                code_txt_delete.setText("");
                name_txt_delete.setText("");
                subject_txt_delete.setText("");
                book_txt_delete.setText("");
                no_lecture_txt_delete.setText("");
                teacher_txt_delete.setText("");
                place_txt_deletet.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                System.err.println(ex);
            }
        }
    }

}
