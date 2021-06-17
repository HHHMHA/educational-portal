package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import org.thekiddos.educationalportal.models.User;
import org.thekiddos.educationalportal.models.Course;

@Controller
@FxmlView( "course-details.fxml" )
public class CourseDetailController {
    public JFXTextField courseNameField;
    public JFXTextArea courseDescriptionField;
    public JFXListView<User> studentListView;

    public void showCourse( Course course ) {
        courseNameField.setText( course.getName() );
        courseDescriptionField.setText( course.getDescription() );
        
        fillStudents( course.getStudents() );
    }
    
    // TODO: set type list or collection
    private void fillStudents( Set<Student> students ) {
        studentListView.getItems().clear();
        studentListView.getItems().addAll( students );
    }
}
