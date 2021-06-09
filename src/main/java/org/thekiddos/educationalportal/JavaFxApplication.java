package org.thekiddos.educationalportal;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.thekiddos.educationalportal.controllers.RegisterLoginController;

public class JavaFxApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources( EducationalPortalApplication.class )
                .run( args );
    }

    @Override
    public void start( Stage stage ) throws Exception {
        FxWeaver fxWeaver = applicationContext.getBean( FxWeaver.class );
        Parent root = fxWeaver.loadView( RegisterLoginController.class );
        Scene scene = new Scene( root );
        stage.setScene( scene );
        stage.show();
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
