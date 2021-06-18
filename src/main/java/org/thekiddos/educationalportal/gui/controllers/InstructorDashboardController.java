package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
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
@FxmlView( "instructor-dashboard.fxml" )
public class InstructorDashboardController {
    public JFXListView<Course> courseListView;
    public JFXTextField courseNameField;
    public JFXTextArea courseDescriptionField;
    public JFXButton courseDetailsBtn;
    public JFXTextField searchField;

    private final UserService userService;
    private final CourseService courseService;
    private final CourseDetailController courseDetailsController;
    private List<Course> courseList = new ArrayList<>();

    @Autowired
    public InstructorDashboardController( UserService userService, CourseService courseService, CourseDetailController courseDetailsController ) {
        this.userService = userService;
        this.courseService = courseService;
        this.courseDetailsController = courseDetailsController;
    }

    public void initialize() {
        courseDetailsBtn.disableProperty().bind( courseListView.getSelectionModel().selectedItemProperty().isNull() );
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

    public void addCourse( ActionEvent actionEvent ) {
        var currentUser = userService.getAuthenticatedUser();

        // TODO: type check
        if ( currentUser == null ) {
            Utils.showStandardErrorAlert( "Please Login!" );
            SceneManager.setView( ViewName.LOGIN_REGISTER );
            return;
        }

        try {
            courseService.addCourse( currentUser, courseNameField.getText(), courseDescriptionField.getText() );
            refresh();
        }
        catch ( Exception e ) {
            e.printStackTrace();
            Utils.showStandardErrorAlert( e.getMessage() );
        }
    }

    public void refresh() {
        var currentUser = userService.getAuthenticatedUser();

        if ( currentUser == null ) {
            Utils.showStandardErrorAlert( "Please Login!" );
            SceneManager.setView( ViewName.LOGIN_REGISTER );
            return;
        }

        courseList = courseService.getCourses( currentUser );
        search( null );
    }

    public void showCourseDetails( ActionEvent actionEvent ) {
        Course course = courseListView.getSelectionModel().getSelectedItem();
        courseDetailsController.showCourse( course );
        SceneManager.setView( ViewName.COURSE_DETAILS_INSTRUCTOR );
    }
}
