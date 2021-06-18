package org.thekiddos.educationalportal;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.thekiddos.educationalportal.constants.ViewName;
import org.thekiddos.educationalportal.gui.SceneManager;
import org.thekiddos.educationalportal.gui.controllers.CourseDetailController;
import org.thekiddos.educationalportal.gui.controllers.InstructorDashboardController;
import org.thekiddos.educationalportal.gui.controllers.RegisterLoginController;
import org.thekiddos.educationalportal.gui.controllers.StudentDashboardController;

public class JavaFxApplication extends Application {
    private ConfigurableApplicationContext applicationContext;

    // TODO: use Dtos as services input
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
        Parent loginRegisterView = fxWeaver.loadView( RegisterLoginController.class );
        Parent instructorDashboardView = fxWeaver.loadView( InstructorDashboardController.class );
        Parent studentDashboardView = fxWeaver.loadView( StudentDashboardController.class );
        Parent courseDetailsView = fxWeaver.loadView( CourseDetailController.class );

        Scene scene = new Scene( loginRegisterView );
        scene.getStylesheets().add( Utils.ROOT_STYLE_CLASS );
        SceneManager.setScene( scene );
        SceneManager.addView( ViewName.LOGIN_REGISTER, loginRegisterView );
        SceneManager.addView( ViewName.INSTRUCTOR_DASHBOARD, instructorDashboardView );
        SceneManager.addView( ViewName.STUDENT_DASHBOARD, studentDashboardView );
        SceneManager.addView( ViewName.COURSE_DETAILS_INSTRUCTOR, courseDetailsView );

        stage.setScene( scene );
        stage.show();
    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }
}
