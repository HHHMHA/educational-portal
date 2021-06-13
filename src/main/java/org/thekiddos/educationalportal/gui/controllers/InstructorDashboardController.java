package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView( "instructor-dashboard.fxml" )
public class InstructorDashboardController {
    public JFXListView courseListView;
    public JFXTextField courseNameField;
    public JFXTextArea courseDescriptionField;
    public JFXButton courseDetailsBtn;

    public void search( ActionEvent actionEvent ) {

    }

    public void addCourse( ActionEvent actionEvent ) {

    }

    public void showCourseDetails( ActionEvent actionEvent ) {

    }
}
