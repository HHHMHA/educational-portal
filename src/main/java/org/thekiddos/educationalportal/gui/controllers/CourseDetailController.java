package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;
import org.thekiddos.educationalportal.models.User;
import org.thekiddos.educationalportal.models.Course;

import java.util.Collection;

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
    
    private void fillStudents( Collection<User> students ) {
        studentListView.getItems().clear();
        studentListView.getItems().addAll( students );
    }
}
