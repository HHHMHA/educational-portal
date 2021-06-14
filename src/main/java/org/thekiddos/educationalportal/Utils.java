package org.thekiddos.educationalportal;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URL;

public final class Utils {
    public static final String STYLESHEET_PATH = Utils.getResource( "static/css/style.css" ).toExternalForm();
    public static final String ROOT_STYLE_CLASS = "body";
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    private static final ClassLoader CLASS_LOADER = Utils.class.getClassLoader();
    private static final Alert errorAlert = new Alert( Alert.AlertType.ERROR, "", ButtonType.OK );

    static {
        initialize();
    }

    /**
     * This method is used to retrieve the URL of a file in the Resources folder
     * @param fileName The resource to retrieve
     * @return A URL for the fileName passed
     * @throws IllegalArgumentException if the file can't be found
     */
    public static URL getResource( String fileName ) {
        assert CLASS_LOADER != null;
        URL resource = CLASS_LOADER.getResource( fileName );

        if ( resource == null )
            throw new IllegalArgumentException( "file is not found!" );

        return resource;
    }

    private static void initialize() {
        setupAlert( errorAlert, "Ops!" );
    }

    private static void setupAlert( Alert alert, String headerText ) {
        alert.setHeaderText( headerText );
        alert.initModality( Modality.WINDOW_MODAL );
        alert.getDialogPane().getStyleClass().add( ROOT_STYLE_CLASS );
        alert.getDialogPane().getScene().getStylesheets().add( STYLESHEET_PATH );
        // TODO: Set Alert Icon
    }

    public static void showStandardErrorAlert( String message ) {
        errorAlert.setContentText( message );
        errorAlert.showAndWait();
    }
}
