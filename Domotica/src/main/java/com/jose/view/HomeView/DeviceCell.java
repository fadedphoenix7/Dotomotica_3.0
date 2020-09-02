package com.jose.view.HomeView;

import com.jfoenix.controls.JFXButton;
import com.jose.controller.Home.DeviceController;
import com.jose.model.schemas.Device;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DeviceCell extends ListCell<Device> {
    HBox xbox = new HBox(10);
    Device device;
    Label labelName = new Label();
    Label labelStatus= new Label();
    HBox pane = new HBox(10);
    JFXButton onButton = new JFXButton("ON");
    JFXButton offButton = new JFXButton("OFF");
    JFXButton enterButton = new JFXButton("Enter");

    public DeviceCell(){
        super();
        enterButton.setButtonType(JFXButton.ButtonType.RAISED);
        enterButton.setOnAction(e -> {
            DeviceController.setDevice(device);
            DeviceController.enterDevice(device);
        });

        onButton.setOnAction( e -> {
            DeviceController.turnOnButton(device);
        });

        offButton.setOnAction( e -> {
            DeviceController.turnOffButton(device);
        });
        Font font = new Font(24);

        labelName.setFont(font);
        labelStatus.setFont(font);
        labelStatus.setBackground(new Background(new BackgroundFill(Color.RED,
                CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().addAll(labelName, labelStatus);
        pane.setPrefWidth(300.0);
        enterButton.setFont(font);
        onButton.setFont(font);
        offButton.setFont(font);
//            xbox.setHg
        xbox.setPrefSize(500,40);
        xbox.getChildren().addAll(pane, onButton, offButton, enterButton);
    }

    public void updateItem(Device name, boolean empty){
        super.updateItem(name,empty);
        setText(null);
        setGraphic(null);
        if(name != null && !empty){
            System.out.println(name);
            labelName.setText(name.getNameDevice());
            labelStatus.setText(name.getState() ? "ON" : "OFF" );
            this.device = name;
            setGraphic(xbox);
        }
    }
}
