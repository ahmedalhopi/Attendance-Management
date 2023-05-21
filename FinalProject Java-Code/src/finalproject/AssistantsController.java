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
import javax.swing.JOptionPane;
import javafx.scene.layout.Pane;

public class AssistantsController implements Initializable {

    @FXML
    private TableView<ObservableList<String>> tableView;
    @FXML
    private TableColumn<ObservableList<String>, String> number;
    @FXML
    private TableColumn<ObservableList<String>, String> name;
    @FXML
    private TableColumn<ObservableList<String>, String> mobile;
    @FXML
    private TableColumn<ObservableList<String>, String> gender;
    @FXML
    private TableColumn<ObservableList<String>, String> living;
    @FXML
    private TableColumn<ObservableList<String>, String> department;
    @FXML
    private TableColumn<ObservableList<String>, String> password;
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
    private TextField password_txt;
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
    private TextField password_txt_update;
    @FXML
    private RadioButton male_radio_update;
    @FXML
    private RadioButton female_radio_update;
    @FXML
    private Button updateBtn;
    @FXML
    private ToggleGroup genderUpdate;
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
    private TextField mobile_txt_delete;
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
        mobile_txt.setTextFormatter(createNumericTextFormatter());
        number_txt_delete.setTextFormatter(createNumericTextFormatter());
        number_txt.setTextFormatter(createNumericTextFormatter());
        mobile_txt_delete.setTextFormatter(createNumericTextFormatter());
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

    public void get_assistants() throws ClassNotFoundException {
        tableView.getItems().clear();
        tableView.setVisible(true);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(false);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);
        number.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        mobile.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        gender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        living.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        department.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        password.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
        PreparedStatement pst;
        ResultSet rs = null;
        Connection conn;

        String sel = "select * from mang.assistants";

        try {

            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("assistant_number"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("mobile"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("living"));
                row.add(rs.getString("department"));
                row.add(rs.getString("password"));
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
        Connection conn;
        String sel = "INSERT INTO mang.assistants (assistant_number, full_name, gender, living, department, mobile, password) VALUES(?, ?, ?, ?, ?,? ,? );";
        try {
            String gender_option = "";
            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
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
            pst.setString(6, mobile_txt.getText());
            pst.setString(7, password_txt.getText());
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "A new course has been added");
            number_txt.setText("");
            name_txt.setText("");
            male_radio.setSelected(true);
            living_txt.setText("");
            department_txt.setText("");
            mobile_txt.setText("");
            password_txt.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.err.println(ex);
        }
    }

    public void update_assistants() {
        tableView.setVisible(false);
        paneContainer.setVisible(false);
        paneContainerUpdate.setVisible(true);
        paneContainerDelete.setVisible(false);
        deleteBtn.setDisable(true);
        updateBtn.setDisable(true);

    }

    public void getDataForAssistants_delete () throws ClassNotFoundException {
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst;
        ResultSet rs;
        String log = "select * from mang.assistants where assistant_number = ? ";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, number_txt_delete.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                name_txt_delete.setText(rs.getString("full_name"));
                gender_txt_delete.setText(rs.getString("gender"));
                living_txt_delete.setText(rs.getString("living"));
                department_txt_delete.setText(rs.getString("department"));
                mobile_txt_delete.setText(rs.getString("mobile"));
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

    public void getDataForAssistants_update() throws ClassNotFoundException {
        Connection conn = DatabaseConnect.connDB();
        System.out.println(conn);
        PreparedStatement pst;
        ResultSet rs;
        String log = "select * from mang.assistants where assistant_number = ? ";
        try {
            pst = conn.prepareStatement(log);
            pst.setString(1, number_txt_update.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                name_txt_update.setText(rs.getString("full_name"));
                if("male".equals(rs.getString("gender")))
                    male_radio_update.setSelected(true);
                else
                    female_radio_update.setSelected(true);
                living_txt_update.setText(rs.getString("living"));
                department_txt_update.setText(rs.getString("department"));
                mobile_txt_update.setText(rs.getString("mobile"));   
                password_txt_update.setText(rs.getString("password"));

                deleteBtn.setDisable(true);
                updateBtn.setDisable(false);
                System.out.println(rs);
            } else {
                JOptionPane.showMessageDialog(null, "No course has the input code");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updateDate() throws ClassNotFoundException {
        PreparedStatement pst;
        Connection conn;
        String sel = "UPDATE mang.assistants SET full_name=?, gender=?, living=?, department=?, mobile=?, password=? WHERE assistant_number=?;";
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
            pst.setString(5, mobile_txt_update.getText());
            pst.setString(6, password_txt_update.getText());
            pst.setString(7, number_txt_update.getText());
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
            Connection conn;
            String sel = "DELETE from mang.assistants WHERE assistant_number=?;";
            try {
                conn = DatabaseConnect.connDB();
                pst = conn.prepareStatement(sel);
                pst.setString(1, number_txt_delete.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "The Course has been deleted");
                number_txt_delete.setText("");
                name_txt_delete.setText("");
                gender_txt_delete.setText("");
                living_txt_delete.setText("");
                department_txt_delete.setText("");
                mobile_txt_delete.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
                System.err.println(ex);
            }
        }
    }

}
