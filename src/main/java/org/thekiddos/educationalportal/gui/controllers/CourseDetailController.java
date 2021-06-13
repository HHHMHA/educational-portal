package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Controller;

@Controller
@FxmlView( "course-details.fxml" )
public class CourseDetailController {
    public JFXTextField courseNameField;
    public JFXTextArea courseDescriptionField;
    public JFXListView studentListView;
    public JFXToggleButton enrollRequestsBtn;

    public void showRequests( ActionEvent actionEvent ) {

    }

    public void approveStudent( ActionEvent actionEvent ) {

    }
}
