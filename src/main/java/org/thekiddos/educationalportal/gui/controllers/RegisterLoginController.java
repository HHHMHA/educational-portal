package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thekiddos.educationalportal.Utils;
import org.thekiddos.educationalportal.constants.ViewName;
import org.thekiddos.educationalportal.gui.SceneManager;
import org.thekiddos.educationalportal.models.UserType;
import org.thekiddos.educationalportal.services.UserService;

@Component
@FxmlView( "register-login.fxml" )
public class RegisterLoginController {
    private final UserService userService;
    public JFXTextField nameField;
    public JFXPasswordField passwordField;
    public JFXTextField registerNameField;
    public JFXPasswordField registerPasswordField;
    public ToggleGroup userType;
    public JFXRadioButton instructorRadioBtn;
    public JFXRadioButton studentRadioBtn;

    @Autowired
    public RegisterLoginController( UserService userService ) {
        this.userService = userService;
        // TODO: Add Validators for fields
    }

    public void initialize() {
        instructorRadioBtn.setUserData( UserType.INSTRUCTOR );
        studentRadioBtn.setUserData( UserType.STUDENT );
    }

    public void login( ActionEvent event ) {
        try {
            var user = userService.login( nameField.getText(), passwordField.getText() );
            if ( user.isStudent() ) {
                SceneManager.setView( ViewName.STUDENT_DASHBOARD );
            } else {
                SceneManager.setView( ViewName.INSTRUCTOR_DASHBOARD );
            }
        }
        catch ( Exception e ) {
            Utils.showStandardErrorAlert( e.getMessage() );
        }
    }

    public void register( ActionEvent event ) {
        try {
            var user = userService.register( registerNameField.getText(), registerPasswordField.getText(), getUserType(), true );
            if ( user.isStudent() ) {
                SceneManager.setView( ViewName.STUDENT_DASHBOARD );
            } else {
                SceneManager.setView( ViewName.INSTRUCTOR_DASHBOARD );
            }
        }
        catch ( Exception e ) {
            Utils.showStandardErrorAlert( e.getMessage() );
        }
    }

    private String getUserType() {
        switch ( (String) userType.getUserData() ) {
            case UserType.STUDENT: return UserType.STUDENT;
            case UserType.INSTRUCTOR: return UserType.INSTRUCTOR;
            default: return null;
        }
    }
}
