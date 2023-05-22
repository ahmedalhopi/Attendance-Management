package finalproject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

public class LecturesController implements Initializable {

    @FXML
    private TableView<ObservableList<String>> tableView;
    @FXML
    private TableColumn<ObservableList<String>, String> lect_id_col;
    @FXML
    private TableColumn<ObservableList<String>, String> code_col;
    @FXML
    private TableColumn<ObservableList<String>, String> course_col;
    @FXML
    private TableColumn<ObservableList<String>, String> lect_title_col;
    @FXML
    private TableColumn<ObservableList<String>, String> place_col;
    @FXML
    private TableColumn<ObservableList<String>, String> day_col;
    @FXML
    private TableColumn<ObservableList<String>, String> from_col;
    @FXML
    private TableColumn<ObservableList<String>, String> to_col;
    @FXML
    private TableColumn<ObservableList<String>, String> date_col;
    /////////////////////////////////////////////////////////
    @FXML
    private Pane paneContainer;
    @FXML
    private TextField code_txt;
    @FXML
    private TextField title_txt;
    @FXML
    private TextField place_txt;
    @FXML
    private SplitMenuButton day_select;
    @FXML
    private TextField houre_from_txt;
    @FXML
    private TextField houre_to_txt;
    @FXML
    private DatePicker date_txt;
    /////////////////////////////////////////////////////////////////
    @FXML
    private Pane paneContainerUpdate;
    @FXML
    private TextField lectureId_txt_update;
    @FXML
    private TextField code_txt_update;
    @FXML
    private TextField title_txt_update;
    @FXML
    private TextField place_txt_update;
    @FXML
    private SplitMenuButton day_select_update;
    @FXML
    private TextField houre_from_txt_update;
    @FXML
    private TextField houre_to_txt_update;
    @FXML
    private DatePicker date_txt_update;
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
        houre_from_txt.setTextFormatter(createNumericTextFormatter());
        houre_to_txt.setTextFormatter(createNumericTextFormatter());
        no_lecture_txt_delete.setTextFormatter(createNumericTextFormatter());
        houre_from_txt_update.setTextFormatter(createNumericTextFormatter());
        houre_to_txt_update.setTextFormatter(createNumericTextFormatter());
        lectureId_txt_update.setTextFormatter(createNumericTextFormatter());
    }

    private TextFormatter<String> createNumericTextFormatter() {
        Pattern pattern = Pattern.compile("[0-9:]*"); // Only allow digits (0-9)
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String newText = change.getControlNewText();
            if (pattern.matcher(newText).matches()) {
                return change;
            }
            return null; // Reject the change
        };
        return new TextFormatter<>(filter);
    }

    public void get_lectures() throws ClassNotFoundException {
        tableView.getItems().clear();
        tableView.setVisible(true);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(false);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        lect_id_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        code_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        course_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        lect_title_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        place_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        day_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        from_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
        to_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(7)));
        date_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(8)));
        PreparedStatement pst;
        ResultSet rs;
        Connection conn;

        String sel = "SELECT lecture_id, course_code,(select name from mang.courses c where c.course_code = r.course_code) ,title, place, day, hour_from, hour_to, date FROM mang.lectures r;";

        try {

            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("lecture_id"));
                row.add(rs.getString("course_code"));
                row.add(rs.getString("name"));
                row.add(rs.getString("title"));
                row.add(rs.getString("place"));
                row.add(rs.getString("day"));
                row.add(rs.getString("hour_from"));
                row.add(rs.getString("hour_to"));
                row.add(rs.getString("date"));
                tableView.getItems().add(row);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
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
        String sel = "INSERT INTO mang.lectures (course_code, title, place, day, hour_from, hour_to, date) VALUES (?, ?, ?, ?, ?, ?, to_date(?,'yyyy-mm-dd'));";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, code_txt.getText());
            pst.setString(2, title_txt.getText());
            pst.setString(3, place_txt.getText());
            pst.setString(4, day_select.getText());
            pst.setString(5, houre_from_txt.getText());
            pst.setString(6, houre_to_txt.getText());
            pst.setString(7, date_txt.getValue().toString());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "A new lecture has been added");
            code_txt.setText("");
            title_txt.setText("");
            place_txt.setText("");
            day_select.setText("");
            houre_from_txt.setText("");
            houre_to_txt.setText("");
            date_txt.setValue(null);

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
        String log = "SELECT * FROM mang.lectures WHERE lecture_id = ?";
        try {
            pst = conn.prepareStatement(log);
            int lec_id = Integer.parseInt(lectureId_txt_update.getText());
            pst.setInt(1, lec_id);
            rs = pst.executeQuery();
            if (rs.next()) {
                code_txt_update.setText(rs.getString("course_code"));
                title_txt_update.setText(rs.getString("title"));
                place_txt_update.setText(rs.getString("place"));
                day_select_update.setText(rs.getString("day"));

                houre_from_txt_update.setText(rs.getString("hour_from"));
                houre_to_txt_update.setText(rs.getString("hour_to"));

                LocalDate date = rs.getDate("date").toLocalDate();
                date_txt_update.setValue(date);

                deleteBtn.setDisable(true);
                updateBtn.setDisable(false);
            } else {
                JOptionPane.showMessageDialog(null, "No lecture has the input code");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateDate() throws ClassNotFoundException {
        PreparedStatement pst;
        Connection conn;
        String sel = "UPDATE mang.lectures SET course_code=?, title=?, place=?, day=?, hour_from=?, hour_to=?, date=to_date(?,'yyyy-mm-dd') WHERE lecture_id=?;";
        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, code_txt_update.getText());
            pst.setString(2, title_txt_update.getText());
            pst.setString(3, place_txt_update.getText());
            pst.setString(4, day_select_update.getText());
            pst.setString(5, houre_from_txt_update.getText());
            pst.setString(6, houre_to_txt_update.getText());
            pst.setString(7, date_txt_update.getValue().toString());
            int lec_id = Integer.parseInt(lectureId_txt_update.getText());
            pst.setInt(8, lec_id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "The data has been updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
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
