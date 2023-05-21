package finalproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class Assistants_CoursesController implements Initializable {

    @FXML
    private TableView tableview;
     @FXML
    private TableColumn<ObservableList<String>, String> id_col;
    @FXML
    private TableColumn<ObservableList<String>, String> name_col;
    @FXML
    private TableColumn<ObservableList<String>, String> course_col;
    @FXML
    private Pane linkPane;
    @FXML
    private TextField assistants_number_link;
    @FXML
    private TextField course_code_link;
    @FXML
    private Pane unlinkPane;
    @FXML
    private TextField assistants_number_unlink;
    @FXML
    private TextField course_code_unlink;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void getAssistantsAndCoursesData(){
        tableview.setVisible(true);
        linkPane.setVisible(false);     
        unlinkPane.setVisible(false);

    }
    
    public void linkingData(){
        tableview.setVisible(false);
        linkPane.setVisible(true);     
        unlinkPane.setVisible(false);
    }
    
    public void unLinkingData(){
        tableview.setVisible(false);
        linkPane.setVisible(false);     
        unlinkPane.setVisible(true);
    }
    
    public void linkingAssistantsWithCourse(){
        
    }
    
    public void unlinkingAssistantsWithCourse(){
        
    }
}
