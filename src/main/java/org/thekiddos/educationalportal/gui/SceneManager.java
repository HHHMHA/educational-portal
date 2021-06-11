package org.thekiddos.educationalportal.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.Setter;

import java.util.HashMap;

public final class SceneManager {
    private static final HashMap<String, Parent> views = new HashMap<>();
    @Setter
    private static Scene scene;

    public static void addView( String name, Parent view ) {
        views.put( name, view );
    }

    public static void setView( String name ) {
        if ( scene == null )
            return;
        scene.setRoot( views.get( name ) );
    }
}
