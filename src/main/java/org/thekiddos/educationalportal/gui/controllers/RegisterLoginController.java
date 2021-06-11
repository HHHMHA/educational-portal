package org.thekiddos.educationalportal.gui.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.ToggleGroup;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView( "register-login.fxml" )
public class RegisterLoginController {
    public JFXTextField nameField;
    public JFXPasswordField passwordField;
    public JFXTextField registerNameField;
    public JFXPasswordField registerPasswordField;
    public ToggleGroup userType;

    public void login( ActionEvent event ) {

    }

    public void register( ActionEvent event ) {

    }
}
