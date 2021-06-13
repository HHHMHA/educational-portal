package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView( "student-dashboard.fxml" )
public class StudentDashboardController {
    public JFXToggleButton allCoursesBtn;
    public JFXListView courseListView;

    public void search( ActionEvent actionEvent ) {

    }

    public void showAllCourses( ActionEvent actionEvent ) {

    }

    public void enrollInCourse( ActionEvent actionEvent ) {

    }
}
