package finalproject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.scene.control.TextField;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.hssf.usermodel.HSSFPalette;

public class ReportsController implements Initializable {

    @FXML
    private Pane top10Lecturs;
    @FXML
    private TableView<ObservableList<String>> tableView_top10;
    @FXML
    private TableColumn<ObservableList<String>, String> lecture_id_col_top10;
    @FXML
    private TableColumn<ObservableList<String>, String> course_code_col_top10;
    @FXML
    private TableColumn<ObservableList<String>, String> course_col_top10;
    @FXML
    private TableColumn<ObservableList<String>, String> lecture_title_col_top10;
    @FXML
    private TableColumn<ObservableList<String>, String> date_col_top10;
    @FXML
    private TableColumn<ObservableList<String>, String> attendance_rate_col_top10;
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Pane studentsUp80;
    @FXML
    private TableView<ObservableList<String>> tableView_up80;
    @FXML
    private TableColumn<ObservableList<String>, String> student_number_col_up80;
    @FXML
    private TableColumn<ObservableList<String>, String> student_name_col_up80;
    @FXML
    private TableColumn<ObservableList<String>, String> gender_col_up80;
    @FXML
    private TableColumn<ObservableList<String>, String> department_col_up80;
    @FXML
    private TableColumn<ObservableList<String>, String> major_col_up80;
    @FXML
    private TableColumn<ObservableList<String>, String> living_col_up80;
    @FXML
    private TableColumn<ObservableList<String>, String> mobile_col_up80;
    @FXML
    private TableColumn<ObservableList<String>, String> rate_col_up80;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Pane studentsMoreCommited;
    @FXML
    private TableView<ObservableList<String>> tableView_mor_commited;
    @FXML
    private TableColumn<ObservableList<String>, String> student_number_col_mor_commited;
    @FXML
    private TableColumn<ObservableList<String>, String> student_name_col_mor_commited;
    @FXML
    private TableColumn<ObservableList<String>, String> gender_col_mor_commited;
    @FXML
    private TableColumn<ObservableList<String>, String> department_col_mor_commited;
    @FXML
    private TableColumn<ObservableList<String>, String> major_col_mor_commited;
    @FXML
    private TableColumn<ObservableList<String>, String> living_col_mor_commited;
    @FXML
    private TableColumn<ObservableList<String>, String> mobile_col_mor_commited;
    @FXML
    private TableColumn<ObservableList<String>, String> rate_col_mor_commited;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Pane above25;
    @FXML
    private TableView<ObservableList<String>> tableView_above25;
    @FXML
    private TableColumn<ObservableList<String>, String> student_number_col_above25;
    @FXML
    private TableColumn<ObservableList<String>, String> student_name_col_above25;
    @FXML
    private TableColumn<ObservableList<String>, String> major_col_above25;
    @FXML
    private TableColumn<ObservableList<String>, String> code_col_above25;
    @FXML
    private TableColumn<ObservableList<String>, String> course_col_above25;
    @FXML
    private TableColumn<ObservableList<String>, String> total_lect_col_above25;
    @FXML
    private TableColumn<ObservableList<String>, String> total_abs_col_above25;
    @FXML
    private TableColumn<ObservableList<String>, String> rate_col_above25;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Pane lecturesForCourse;
    @FXML
    private TextField course_code_txt;
    @FXML
    private TableView<ObservableList<String>> tableView_lectures;
    @FXML
    private TableColumn<ObservableList<String>, String> lect_id_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> title_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> place_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> day_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> hour_from_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> hour_to_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> date_col_course;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private Pane lecturesForStudent;
    @FXML
    private TextField student_number_txt;
    @FXML
    private TextField course_code_txt1;
    @FXML
    private TableView<ObservableList<String>> tableView_lectures_student;
    @FXML
    private TableColumn<ObservableList<String>, String> lect_id_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> title_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> place_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> day_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> hour_from_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> hour_to_col_course;
    @FXML
    private TableColumn<ObservableList<String>, String> date_col_course;
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        top10Lecturs.setVisible(false);
        studentsUp80.setVisible(false);
        studentsMoreCommited.setVisible(false);
        above25.setVisible(false);
        lecturesForCourse.setVisible(false);
        lecturesForStudent.setVisible(false);
    }
//////////////////////////////////////////////////////////////////////////////////  

    public void getTop10Lectures() throws ClassNotFoundException {
        above25.setVisible(false);
        lecturesForCourse.setVisible(false);
        studentsUp80.setVisible(false);
        lecturesForStudent.setVisible(false);
        studentsMoreCommited.setVisible(false);
        tableView_top10.getItems().clear();
        top10Lecturs.setVisible(true);
        lecture_id_col_top10.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        course_code_col_top10.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        course_col_top10.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        lecture_title_col_top10.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        date_col_top10.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        attendance_rate_col_top10.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        PreparedStatement pst;
        ResultSet rs;
        Connection conn;

        String sel = "SELECT l.lecture_id, l.course_code, c.name , l.title, l.date, COUNT(l.lecture_id),(select count(lecture_id) from mang.attendance a2 where a2.lecture_id = l.lecture_id ) as fullcount \n"
                + "FROM mang.lectures l\n"
                + "JOIN mang.attendance a ON l.lecture_id = a.lecture_id\n"
                + "JOIN mang.courses c ON c.course_code = l.course_code\n"
                + "WHERE a.status = 'Present'\n"
                + "GROUP BY l.lecture_id, l.course_code, c.name, l.title, l.date\n"
                + "ORDER BY l.date DESC, COUNT(l.lecture_id) DESC\n"
                + "LIMIT 10;";

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
                row.add(rs.getString("date"));
                int count = Integer.parseInt(rs.getString("count"));
                int fullCount = Integer.parseInt(rs.getString("fullcount"));
                int rate = (count * 100) / fullCount;
                String rateString = rate + "%";
                row.add(rateString);
                tableView_top10.getItems().add(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void top10ExportExcle() {
        String userHome = System.getProperty("user.home");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatterDate);
        String directoryPath = userHome + "/Documents/Export Excel/Top10Lecture/";
        String filePath = directoryPath + formattedDateTime + ".xls"; // Specify the file path for the Excel file

        // Create the directory if it doesn't exist
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory: " + e);
                return;
            }
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        ObservableList<ObservableList<String>> tableData = tableView_top10.getItems();

        // Add headers for each column
        ObservableList<String> headers = FXCollections.observableArrayList(
                "#", "Lecture ID", "Course Code", "Course", "Lecture Title", "Date", "Attendance Rate");
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(headers.get(col));
        }

        // Apply header style
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.getCell(col);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(col);
        }

        CellStyle dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        dataStyle.setFont(dataFont);
        dataStyle.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        int rowNum = 1; // Start from row 1, after the header row
        int j = 0;
        for (ObservableList<String> rowData : tableData) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(++j);
            cell.setCellStyle(dataStyle);
            int colNum = 1;
            for (String cellData : rowData) {
                cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
                cell.setCellStyle(dataStyle);
            }
        }

        int columnCount = tableView_top10.getColumns().size();
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            workbook.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

//////////////////////////////////////////////////////////////////////////////////
    public void getUp80Students() throws ClassNotFoundException {
        studentsUp80.setVisible(true);
        lecturesForCourse.setVisible(false);
        studentsMoreCommited.setVisible(false);
        lecturesForStudent.setVisible(false);
        above25.setVisible(false);
        tableView_up80.getItems().clear();
        top10Lecturs.setVisible(false);
        student_number_col_up80.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        student_name_col_up80.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        gender_col_up80.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        department_col_up80.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        major_col_up80.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        living_col_up80.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        mobile_col_up80.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
        rate_col_up80.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(7)));
        PreparedStatement pst;
        ResultSet rs;
        Connection conn;

        String sel = "SELECT s.*, (a.total_present * 100 / a.total_lectures) AS attendance_rate,(select max(sm.mobile) as mobile from mang.students_mobiles sm  where sm.student_number = s.student_number)\n"
                + "FROM mang.students s\n"
                + "JOIN (\n"
                + "    SELECT student_number, COUNT(*) AS total_lectures,\n"
                + "        SUM(CASE WHEN status = 'Present' THEN 1 ELSE 0 END) AS total_present\n"
                + "    FROM mang.attendance\n"
                + "    GROUP BY student_number\n"
                + "    HAVING COUNT(*) > 0 AND (SUM(CASE WHEN status = 'Present' THEN 1 ELSE 0 END) * 100 / COUNT(*)) >= 80\n"
                + ") a ON s.student_number = a.student_number;";

        try {

            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("student_number"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("department"));
                row.add(rs.getString("majoring"));
                row.add(rs.getString("living"));
                row.add(rs.getString("mobile"));
                String rateString = rs.getString("attendance_rate") + "%";
                row.add(rateString);

                tableView_up80.getItems().add(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void studentsUp80ExportExcle() {
        String userHome = System.getProperty("user.home");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatterDate);
        String directoryPath = userHome + "/Documents/Export Excel/Up80%Students/";
        String filePath = directoryPath + formattedDateTime + ".xls"; // Specify the file path for the Excel file

        // Create the directory if it doesn't exist
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory: " + e);
                return;
            }
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        ObservableList<ObservableList<String>> tableData = tableView_up80.getItems();

        // Add headers for each column
        ObservableList<String> headers = FXCollections.observableArrayList(
                "#", "Student Number", "Name", "Gender", "Department", "Major", "Living", "Mobile", "Rate");
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(headers.get(col));
        }

        // Apply header style
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.getCell(col);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(col);
        }

        CellStyle dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        dataStyle.setFont(dataFont);
        dataStyle.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        int rowNum = 1; // Start from row 1, after the header row
        int j = 0;
        for (ObservableList<String> rowData : tableData) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(++j);
            cell.setCellStyle(dataStyle);
            int colNum = 1;
            for (String cellData : rowData) {
                cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
                cell.setCellStyle(dataStyle);
            }
        }

        int columnCount = tableView_up80.getColumns().size();
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            workbook.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////
    public void getStudentsMoreCommitedToLess() throws ClassNotFoundException {
        studentsMoreCommited.setVisible(true);
        studentsUp80.setVisible(false);
        lecturesForStudent.setVisible(false);
        lecturesForCourse.setVisible(false);
        above25.setVisible(false);
        tableView_up80.getItems().clear();
        top10Lecturs.setVisible(false);
        student_number_col_mor_commited.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        student_name_col_mor_commited.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        gender_col_mor_commited.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        department_col_mor_commited.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        major_col_mor_commited.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        living_col_mor_commited.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        mobile_col_mor_commited.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
        rate_col_mor_commited.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(7)));
        PreparedStatement pst;
        ResultSet rs;
        Connection conn;

        String sel = "SELECT s.*, (a.total_present * 100 / a.total_lectures) AS attendance_rate, (SELECT MAX(sm.mobile) as mobile FROM mang.students_mobiles sm WHERE sm.student_number = s.student_number) AS mobile\n"
                + "FROM mang.students s\n"
                + "JOIN (\n"
                + "    SELECT student_number, COUNT(*) AS total_lectures,\n"
                + "        SUM(CASE WHEN status = 'Present' THEN 1 ELSE 0 END) AS total_present\n"
                + "    FROM mang.attendance\n"
                + "    GROUP BY student_number\n"
                + "    HAVING COUNT(*) >= 0 \n"
                + ") a ON s.student_number = a.student_number\n"
                + "ORDER BY attendance_rate DESC;";

        try {

            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("student_number"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("department"));
                row.add(rs.getString("majoring"));
                row.add(rs.getString("living"));
                row.add(rs.getString("mobile"));
                String rateString = rs.getString("attendance_rate") + "%";
                row.add(rateString);

                tableView_mor_commited.getItems().add(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void studentsMoreCommitedExportExcle() {
        String userHome = System.getProperty("user.home");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatterDate);
        String directoryPath = userHome + "/Documents/Export Excel/StudentsMoreCommited/";
        String filePath = directoryPath + formattedDateTime + ".xls"; // Specify the file path for the Excel file

        // Create the directory if it doesn't exist
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory: " + e);
                return;
            }
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        ObservableList<ObservableList<String>> tableData = tableView_mor_commited.getItems();

        // Add headers for each column
        ObservableList<String> headers = FXCollections.observableArrayList(
                "#", "Student Number", "Name", "Gender", "Department", "Major", "Living", "Mobile", "Rate");
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(headers.get(col));
        }

        // Apply header style
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.getCell(col);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(col);
        }

        CellStyle dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        dataStyle.setFont(dataFont);
        dataStyle.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        int rowNum = 1; // Start from row 1, after the header row
        int j = 0;
        for (ObservableList<String> rowData : tableData) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(++j);
            cell.setCellStyle(dataStyle);
            int colNum = 1;
            for (String cellData : rowData) {
                cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
                cell.setCellStyle(dataStyle);
            }
        }

        int columnCount = tableView_mor_commited.getColumns().size();
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            workbook.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
////////////////////////////////////////////////////////////////////////////////////////

    public void getStudentsAbove25() throws ClassNotFoundException {
        studentsMoreCommited.setVisible(false);
        above25.setVisible(true);
        lecturesForStudent.setVisible(false);
        lecturesForCourse.setVisible(false);
        studentsUp80.setVisible(false);
        tableView_above25.getItems().clear();
        top10Lecturs.setVisible(false);
        student_number_col_above25.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        student_name_col_above25.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        major_col_above25.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        code_col_above25.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        course_col_above25.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        total_lect_col_above25.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        total_abs_col_above25.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
        rate_col_above25.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(7)));
        PreparedStatement pst;
        ResultSet rs;
        Connection conn;

        String sel = "select s.student_number,s.full_name ,s.majoring ,ac.code ,ac.course_name,ac.totalLectureCourse,ac.total_absences ,(ac.total_absences * 100 / ac.totalLectureCourse) AS absence_rate\n"
                + "from mang.students s , (\n"
                + "select a.student_number,(select l.course_code from mang.lectures l  where l.lecture_id = a.lecture_id) as code \n"
                + ",count(a.lecture_id)AS total_absences,(select count(*) from mang.lectures l where l.course_code = (select l2.course_code from mang.lectures l2  where l2.lecture_id = a.lecture_id)) as totalLectureCourse\n"
                + ",(select name from mang.courses c where c.course_code = (select l.course_code from mang.lectures l  where l.lecture_id = a.lecture_id)) as course_name\n"
                + "from mang.attendance a \n"
                + "group by  student_number,status,code,totalLectureCourse,course_name\n"
                + "having (SUM(CASE WHEN status = 'Absent' THEN 1 ELSE 0 END) * 100 / COUNT(*)) > 25\n"
                + ") ac where s.student_number = ac.student_number;";

        try {

            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("student_number"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("majoring"));
                row.add(rs.getString("code"));
                row.add(rs.getString("course_name"));
                row.add(rs.getString("totallecturecourse"));
                row.add(rs.getString("total_absences"));
                String rateString = rs.getString("absence_rate") + "%";
                row.add(rateString);

                tableView_above25.getItems().add(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void studentsAbove25ExportExcle() {
        String userHome = System.getProperty("user.home");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatterDate);
        String directoryPath = userHome + "/Documents/Export Excel/StudentsAbove25%Absence/";
        String filePath = directoryPath + formattedDateTime + ".xls"; // Specify the file path for the Excel file

        // Create the directory if it doesn't exist
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory: " + e);
                return;
            }
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        ObservableList<ObservableList<String>> tableData = tableView_above25.getItems();

        // Add headers for each column
        ObservableList<String> headers = FXCollections.observableArrayList(
                "#", "Student Number", "Name", "Major", "Course Code", "Course Name", "Total Lectures Of Course", "Total Absebce", "Rate");
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(headers.get(col));
        }

        // Apply header style
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.getCell(col);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(col);
        }

        CellStyle dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        dataStyle.setFont(dataFont);
        dataStyle.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        int rowNum = 1; // Start from row 1, after the header row
        int j = 0;
        for (ObservableList<String> rowData : tableData) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(++j);
            cell.setCellStyle(dataStyle);
            int colNum = 1;
            for (String cellData : rowData) {
                cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
                cell.setCellStyle(dataStyle);
            }
        }

        int columnCount = tableView_above25.getColumns().size();
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            workbook.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    public void getLectures() {
        studentsMoreCommited.setVisible(false);
        above25.setVisible(false);
        studentsUp80.setVisible(false);
        lecturesForStudent.setVisible(false);
        top10Lecturs.setVisible(false);
        lecturesForCourse.setVisible(true);
    }

    public void getLecturesForCourse() throws ClassNotFoundException {
        tableView_lectures.getItems().clear();
        lect_id_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        title_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        place_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        day_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        hour_from_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        hour_to_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        date_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
        PreparedStatement pst;
        ResultSet rs;
        Connection conn;

        String sel = "select * from mang.lectures l where l.course_code = ?";

        try {

            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, course_code_txt.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("lecture_id"));
                row.add(rs.getString("title"));
                row.add(rs.getString("place"));
                row.add(rs.getString("day"));
                row.add(rs.getString("hour_from"));
                row.add(rs.getString("hour_to"));
                row.add(rs.getString("date"));

                tableView_lectures.getItems().add(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void lectuesForCourseExportExcle() {
        String userHome = System.getProperty("user.home");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatterDate);
        String directoryPath = userHome + "/Documents/Export Excel/LecturesForCourses/";
        String filePath = directoryPath + formattedDateTime + ".xls"; // Specify the file path for the Excel file

        // Create the directory if it doesn't exist
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory: " + e);
                return;
            }
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        ObservableList<ObservableList<String>> tableData = tableView_lectures.getItems();

        // Add headers for each column
        ObservableList<String> headers = FXCollections.observableArrayList(
                "#", "Lectuer Id", "Tilte", "Place", "Day", "Hour From", "Hour To", "Date");
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(headers.get(col));
        }

        // Apply header style
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.getCell(col);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(col);
        }

        CellStyle dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        dataStyle.setFont(dataFont);
        dataStyle.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        int rowNum = 1; // Start from row 1, after the header row
        int j = 0;
        for (ObservableList<String> rowData : tableData) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(++j);
            cell.setCellStyle(dataStyle);
            int colNum = 1;
            for (String cellData : rowData) {
                cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
                cell.setCellStyle(dataStyle);
                sheet.autoSizeColumn(colNum);
            }
        }

        int columnCount = tableView_lectures.getColumns().size();
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            workbook.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
//////////////////////////////////////////////////////////////////////////////////////////
    public void getLecturesStudents() {
        studentsMoreCommited.setVisible(false);
        above25.setVisible(false);
        studentsUp80.setVisible(false);
        top10Lecturs.setVisible(false);
        lecturesForCourse.setVisible(false);
        lecturesForStudent.setVisible(true);
    }

    public void getLecturesForStudent() throws ClassNotFoundException {
        tableView_lectures_student.getItems().clear();
        lect_id_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(0)));
        title_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(1)));
        place_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(2)));
        day_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(3)));
        hour_from_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(4)));
        hour_to_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(5)));
        date_col_course.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().get(6)));
        PreparedStatement pst;
        ResultSet rs;
        Connection conn;

        String sel = "select * from mang.lectures l where l.course_code = ?";

        try {

            conn = DatabaseConnect.connDB();
            pst = conn.prepareStatement(sel);
            pst.setString(1, course_code_txt.getText());
            rs = pst.executeQuery();
            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("lecture_id"));
                row.add(rs.getString("title"));
                row.add(rs.getString("place"));
                row.add(rs.getString("day"));
                row.add(rs.getString("hour_from"));
                row.add(rs.getString("hour_to"));
                row.add(rs.getString("date"));

                tableView_lectures_student.getItems().add(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public void lectuesForstudentExportExcle() {
        String userHome = System.getProperty("user.home");
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        String formattedDateTime = currentDateTime.format(formatterDate);
        String directoryPath = userHome + "/Documents/Export Excel/LecturesForStudents/";
        String filePath = directoryPath + formattedDateTime + ".xls"; // Specify the file path for the Excel file

        // Create the directory if it doesn't exist
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(directory);
            } catch (IOException e) {
                System.out.println("Failed to create directory: " + e);
                return;
            }
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        ObservableList<ObservableList<String>> tableData = tableView_lectures_student.getItems();

        // Add headers for each column
        ObservableList<String> headers = FXCollections.observableArrayList(
                "#", "Lectuer Id", "Tilte", "Place", "Day", "Hour From", "Hour To", "Date");
        Row headerRow = sheet.createRow(0);
        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.createCell(col);
            cell.setCellValue(headers.get(col));
        }

        // Apply header style
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);

        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        for (int col = 0; col < headers.size(); col++) {
            Cell cell = headerRow.getCell(col);
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(col);
        }

        CellStyle dataStyle = workbook.createCellStyle();
        Font dataFont = workbook.createFont();
        dataFont.setFontHeightInPoints((short) 12);
        dataStyle.setFont(dataFont);
        dataStyle.setAlignment(HorizontalAlignment.CENTER); // Set text alignment to center

        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());

        int rowNum = 1; // Start from row 1, after the header row
        int j = 0;
        for (ObservableList<String> rowData : tableData) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(++j);
            cell.setCellStyle(dataStyle);
            int colNum = 1;
            for (String cellData : rowData) {
                cell = row.createCell(colNum++);
                cell.setCellValue(cellData);
                cell.setCellStyle(dataStyle);
                sheet.autoSizeColumn(colNum);
            }
        }

        int columnCount = tableView_lectures_student.getColumns().size();
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            System.out.println(e);
        }

        try {
            workbook.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
    
}
