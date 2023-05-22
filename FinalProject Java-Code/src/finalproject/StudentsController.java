/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author IT
 */
public class StudentsController implements Initializable {

    @FXML
    private TableView<ObservableList<String>> tableView;
    @FXML
    private TableColumn<ObservableList<String>, String> number;
    @FXML
    private TableColumn<ObservableList<String>, String> name;
    @FXML
    private TableColumn<ObservableList<String>, String> gender;
    @FXML
    private TableColumn<ObservableList<String>, String> living;
    @FXML
    private TableColumn<ObservableList<String>, String> department;
    @FXML
    private TableColumn<ObservableList<String>, String> major;
    /////////////////////////////////////////////////////////
    @FXML
    private Pane paneContainer;
    @FXML
    private TextField number_txt;
    @FXML
    private TextField name_txt;
    @FXML
    private TextField department_txt;
    @FXML
    private TextField mobile_txt;
    @FXML
    private TextField living_txt;
    @FXML
    private TextField major_txt;
    @FXML
    private RadioButton male_radio;
    @FXML
    private RadioButton female_radio;
    @FXML
    private ToggleGroup genderInsert;
    /////////////////////////////////////////////////////////////////
    @FXML
    private Pane paneContainerUpdate;
    @FXML
    private TextField number_txt_update;
    @FXML
    private TextField name_txt_update;
    @FXML
    private TextField department_txt_update;
    @FXML
    private TextField mobile_txt_update;
    @FXML
    private TextField living_txt_update;
    @FXML
    private TextField major_txt_update;
    @FXML
    private RadioButton male_radio_update;
    @FXML
    private RadioButton female_radio_update;
    @FXML
    private Button updateBtn;
    @FXML
    private Button add_mobilebtn;
    @FXML
    private ToggleGroup genderUpdate;
    @FXML
    private TableView<ObservableList<String>> tableview_mobile;
    @FXML
    private TableColumn<ObservableList<String>, String> mobile_col;
    ////////////////////////////////////////////////////////////////
    @FXML
    private Pane paneContainerDelete;
    @FXML
    private TextField number_txt_delete;
    @FXML
    private TextField name_txt_delete;
    @FXML
    private TextField gender_txt_delete;
    @FXML
    private TextField living_txt_delete;
    @FXML
    private TextField department_txt_delete;
    @FXML
    private TextField major_txt_delete;
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
        add_mobilebtn.setDisable(true);
        mobile_txt.setTextFormatter(createNumericTextFormatter());
        number_txt_delete.setTextFormatter(createNumericTextFormatter());
        number_txt.setTextFormatter(createNumericTextFormatter());
        number_txt_update.setTextFormatter(createNumericTextFormatter());
        mobile_txt_update.setTextFormatter(createNumericTextFormatter());

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

    public void get_students() throws ClassNotFoundException {
        tableView.getItems().clear();
        tableView.setVisible(true);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(false);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        number.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        gender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        living.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        department.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        major.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        PreparedStatement pst;
        ResultSet rs = null;
        Connection conn;

        String sel = "select * from mang.students";

        try {
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("student_number"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("living"));
                row.add(rs.getString("department"));
                row.add(rs.getString("majoring"));
                tableView.getItems().add(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void add_assistants() {
        tableView.setVisible(false);
        paneContainer.setVisible(true);
        paneContainerUpdate.setVisible(false);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);

    }

    public void saveDate() throws ClassNotFoundException {
        PreparedStatement pst;  
        PreparedStatement pst_mobile;
        Connection conn;
        String sel = "INSERT INTO mang.students (student_number, full_name, gender, living, department, majoring) VALUES(?, ?, ?, ?, ?, ?);";    
        String sel_mobile = "INSERT INTO mang.students_mobiles (student_number, mobile) VALUES(?, ?);";

        try {
            String gender_option = "";
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst_mobile = conn.prepareStatement(sel_mobile);
            pst.setString(1, number_txt.getText());
            pst.setString(2, name_txt.getText());
            RadioButton selectedRadioButton = (RadioButton) genderInsert.getSelectedToggle();
        if (selectedRadioButton != null) {
            if (selectedRadioButton == male_radio) {
                gender_option = "Male";
                System.out.println("Selected: Male");
            } else if (selectedRadioButton == female_radio) {
                gender_option = "Female";
                System.out.println("Selected: Female");
            }
        }
            pst.setString(3, gender_option);
            pst.setString(4, living_txt.getText());
            pst.setString(5, department_txt.getText());
            pst.setString(6, major_txt.getText());
            pst_mobile.setString(1, number_txt.getText());
            pst_mobile.setString(2, mobile_txt.getText());
            pst.executeUpdate();
            pst_mobile.executeUpdate();
            JOptionPane.showMessageDialog(null, "A new student has been added");
            number_txt.setText("");
            name_txt.setText("");
            male_radio.setSelected(true);
            living_txt.setText("");
            department_txt.setText("");
            mobile_txt.setText("");
            major_txt.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
        }
    }

    public void update_assistants() {
        tableView.setVisible(false);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(true);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        add_mobilebtn.setDisable(true);

    }

    public void getDataForAssistants_delete () throws ClassNotFoundException {
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst;
        ResultSet rs;
        String log = "select * from mang.students where student_number = ? ";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, number_txt_delete.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                name_txt_delete.setText(rs.getString("full_name"));
                living_txt_delete.setText(rs.getString("living"));
                department_txt_delete.setText(rs.getString("department"));
                major_txt_delete.setText(rs.getString("majoring"));
                gender_txt_delete.setText(rs.getString("gender"));

                deleteBtn.setDisable(false);
                updateBtn.setDisable(true);
                add_mobilebtn.setDisable(true);
                System.out.println(rs);
            } else {
                JOptionPane.showMessageDialog(null, "No student has the input code");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void getDataForStudent_update() throws ClassNotFoundException {
        tableview_mobile.getItems().clear();
        mobile_col.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst;
        PreparedStatement pst_mobile;
        ResultSet rs;
        ResultSet rs_mobile;
        String log = "select * from mang.students where student_number = ? ";
        String log_mobile = "select mobile from mang.students_mobiles where student_number = ? ";
        try {
            pst = conn.prepareStatement(log);
            pst_mobile = conn.prepareStatement(log_mobile);
            pst.setString(1, number_txt_update.getText());
            pst_mobile.setString(1, number_txt_update.getText());
            rs = pst.executeQuery();
            rs_mobile = pst_mobile.executeQuery();
            if (rs.next()) {
                name_txt_update.setText(rs.getString("full_name"));
                if("male".equals(rs.getString("gender")))
                    male_radio_update.setSelected(true);
                else
                    female_radio_update.setSelected(true);
                living_txt_update.setText(rs.getString("living"));
                department_txt_update.setText(rs.getString("department"));
                major_txt_update.setText(rs.getString("majoring"));
                System.out.println(rs_mobile.getCursorName());
                while (rs_mobile.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs_mobile.getString("mobile"));
                tableview_mobile.getItems().add(row);
            }

                deleteBtn.setDisable(true);
                updateBtn.setDisable(false);
                add_mobilebtn.setDisable(false);
                System.out.println(rs);
            } else {
                JOptionPane.showMessageDialog(null, "No student has the input code");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void add_mobile() throws ClassNotFoundException{
        PreparedStatement pst;  
        Connection conn;
        String sel = "";    
        try {
            sel = "INSERT INTO mang.students_mobiles (student_number, mobile) VALUES(?, ?);";
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, number_txt_update.getText());
            pst.setString(2, mobile_txt_update.getText());
            pst.executeUpdate();
            mobile_txt_update.setText("");
            getDataForStudent_update();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "The mobile has been added");
            System.out.println(ex);
        }
    }

    public void updateDate() throws ClassNotFoundException {
        PreparedStatement pst;
        Connection conn;
        String sel = "UPDATE mang.students SET full_name=?, gender=?, living=?, department=?, majoring=? WHERE student_number=?;";
        try {
            String gender_option = "";
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, name_txt_update.getText());
            RadioButton selectedRadioButton = (RadioButton) genderUpdate.getSelectedToggle();
        if (selectedRadioButton != null) {
            if (selectedRadioButton == male_radio_update) {
                gender_option = "Male";
                System.out.println("Selected: Male");
            } else if (selectedRadioButton == female_radio_update) {
                gender_option = "Female";
                System.out.println("Selected: Female");
            }
        }
            pst.setString(2, gender_option);
            pst.setString(3, living_txt_update.getText());
            pst.setString(4, department_txt_update.getText());
            pst.setString(5, major_txt_update.getText());
            pst.setString(6, number_txt_update.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "The data has been updated");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println(ex);
        }
    }

    public void delete_assistants() {
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
            PreparedStatement pst_mobile;
            Connection conn;
            String sel = "DELETE FROM mang.students WHERE student_number=?;";
            String sel_mobile = "DELETE FROM mang.students_mobiles WHERE student_number=?;";
            try {
                conn = DatabaseConnect.connDB();
                pst_mobile = conn.prepareStatement(sel_mobile);
                pst_mobile.setString(1, number_txt_delete.getText());
                pst_mobile.executeUpdate();
                pst = conn.prepareStatement(sel);
                pst.setString(1, number_txt_delete.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "The Student has been deleted");
                number_txt_delete.setText("");
                name_txt_delete.setText("");
                gender_txt_delete.setText("");
                living_txt_delete.setText("");
                department_txt_delete.setText("");
                major_txt_delete.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                System.err.println(ex);
            }
        }
    }
     
    
}
