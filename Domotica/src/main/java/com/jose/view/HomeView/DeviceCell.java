package com.jose.view.HomeView;

import com.jfoenix.controls.JFXButton;
import com.jose.controller.Home.DeviceController;
import com.jose.model.schemas.Device;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class DeviceCell extends ListCell<Device> {
    HBox xbox = new HBox(5);
    Device device;
    Label labelName = new Label();
    Label labelStatus= new Label();
    HBox pane = new HBox(10);
    JFXButton onButton = new JFXButton("ON");
    JFXButton offButton = new JFXButton("OFF");
    JFXButton enterButton = new JFXButton("Enter");
    Background onDevice = new Background(new BackgroundFill(Color.valueOf("99D529"),
            CornerRadii.EMPTY, Insets.EMPTY));
    Background offDevice = new Background(new BackgroundFill(Color.RED,
            CornerRadii.EMPTY, Insets.EMPTY));

    public DeviceCell(ListView list,double size){
        super();

        enterButton.setButtonType(JFXButton.ButtonType.RAISED);
        enterButton.setOnAction(e -> {
            DeviceController.setDevice(device);
            DeviceController.enterDevice(device);
        });


        onButton.setOnAction( e -> {
            DeviceController.turnOnButton(device);
            list.refresh();
        });

        offButton.setOnAction( e -> {
            DeviceController.turnOffButton(device);
            list.refresh();
        });
        Font font = new Font(size < 400 ? 18 : 22);

        labelName.setFont(font);
        labelStatus.setFont(font);

        pane.getChildren().addAll(labelName, labelStatus);
//        pane.setPrefWidth(size/2);
        enterButton.setFont(font);
        onButton.setFont(font);
        offButton.setFont(font);
//            xbox.setHg
//        xbox.setPrefSize(size,40);
        xbox.setPrefHeight(40);
        xbox.getChildren().addAll(pane, onButton, offButton, enterButton);
        this.setId("listCells");
        enterButton.setId("enterButton");
        onButton.setId("onButton");
        offButton.setId("offButton");

    }

    public void updateItem(Device name, boolean empty){
        super.updateItem(name,empty);
        setText(null);
        setGraphic(null);
        if(name != null && !empty){
            labelName.setText(name.getNameDevice());
            labelStatus.setText(name.getState() ? "ON" : "OFF" );
            labelStatus.setBackground(name.getState() ? onDevice : offDevice);
            this.device = name;
            setGraphic(xbox);
        }
    }
}
