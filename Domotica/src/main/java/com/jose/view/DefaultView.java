package com.jose.view;

import com.jose.controller.LogReg.LogRegController;
import com.jose.view.HomeView.MainView;
import com.jose.view.LogRegView.LogHomeView;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DefaultView extends Application {

    private static double xOffset = 0;
    private static double yOffset = 0;
    protected final static String ROOTCSS = "css/root.css";
    private static  Stage globalPrimaryStage;

    public DefaultView(){}

    public DefaultView(int i){
        System.out.println("x");
        launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(LogHomeView.initLoginView());

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = primaryStage.getX() - event.getScreenX();
                yOffset = primaryStage.getY() - event.getScreenY();
            }
        });

        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() + xOffset);
                primaryStage.setY(event.getScreenY() + yOffset);
            }
        });

        LogRegController.setScene(scene);

        primaryStage.setTitle("First JavaFX Application");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        globalPrimaryStage = primaryStage;
        primaryStage.show();

    }

    public static void setLoginView(){
        try {
            globalPrimaryStage.getScene().setRoot(LogHomeView.initLoginView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setMainView(){
        try {
            globalPrimaryStage.getScene().setRoot(MainView.initMainView());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
