package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import org.thekiddos.educationalportal.Utils;
import org.thekiddos.educationalportal.constants.ViewName;
import org.thekiddos.educationalportal.gui.SceneManager;
import org.thekiddos.educationalportal.models.Course;
import org.thekiddos.educationalportal.services.CourseService;
import org.thekiddos.educationalportal.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@FxmlView( "student-dashboard.fxml" )
public class StudentDashboardController {
    public JFXToggleButton allCoursesBtn;
    public JFXListView<Course> courseListView;
    public JFXTextField searchField;
    public JFXButton enrollBtn;

    private final CourseService courseService;
    private final UserService userService;
    private List<Course> courseList = new ArrayList<>();

    public StudentDashboardController( CourseService courseService, UserService userService ) {
        this.courseService = courseService;
        this.userService = userService;
    }

    public void initialize() {
        enrollBtn.disableProperty().bind( courseListView.getSelectionModel().selectedItemProperty().isNull() );
    }

    public void search( ActionEvent actionEvent ) {
        var searchQuery = searchField.getText();

        var filteredCourses = courseList.stream().filter(
                course -> course.getName().equalsIgnoreCase( searchQuery ) ||
                        course.getDescription().contains( searchQuery )
        ).collect( Collectors.toList() );

        courseListView.getItems().clear();
        courseListView.getItems().addAll( filteredCourses );
    }

    public void refresh() {
        var currentUser = userService.getAuthenticatedUser();

        if ( currentUser == null ) {
            Utils.showStandardErrorAlert( "Please Login!" );
            SceneManager.setView( ViewName.LOGIN_REGISTER );
            return;
        }

        courseList = ( allCoursesBtn.isSelected() ) ? courseService.getAll() : courseService.getCourses( currentUser );

        search( null );
    }

    public void toggleAllCourses( ActionEvent actionEvent ) {
        refresh();
    }

    public void enrollInCourse( ActionEvent actionEvent ) {
        var currentUser = userService.getAuthenticatedUser();

        if ( currentUser == null ) {
            Utils.showStandardErrorAlert( "Please Login!" );
            SceneManager.setView( ViewName.LOGIN_REGISTER );
            return;
        }

        try {
            courseService.enrollInCourse( courseListView.getSelectionModel().getSelectedItem(), currentUser );
        }
        catch ( Exception e ) {
            e.printStackTrace();
            Utils.showStandardErrorAlert( e.getMessage() );
        }

        refresh();
    }
}
