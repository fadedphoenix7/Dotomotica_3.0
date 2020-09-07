package com.jose.view.HomeView;

import com.jfoenix.controls.JFXButton;
import com.jose.controller.Home.AreaController;
import com.jose.controller.Home.DeviceController;
import com.jose.model.schemas.Area;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AreaCell extends ListCell<Area> {
    HBox xbox = new HBox(5);
    Area area;
    Label labelName = new Label();
    Label labelStatus= new Label();
    JFXButton onButton = new JFXButton("ON");
    JFXButton offButton = new JFXButton("OFF");
    JFXButton enterButton = new JFXButton("Enter");

    public AreaCell(ListView list, double size){
        super();
        enterButton.setButtonType(JFXButton.ButtonType.RAISED);
        enterButton.setOnAction(e -> {
            AreaController.setArea(area);
            AreaController.enterArea();
        });

        onButton.setOnAction( e -> {
            AreaController.turnOnArea(area);
            list.refresh();
        });

        offButton.setOnAction( e -> {
            AreaController.turnOffArea(area);
            list.refresh();
        });
        Font font = new Font(size < 400 ? 18 : 22);

        labelName.setFont(font);
//        labelName.setPrefWidth(size /2);

        labelStatus.setFont(font);
        labelStatus.setBackground(new Background(new BackgroundFill(Color.RED,
                CornerRadii.EMPTY, Insets.EMPTY)));
        enterButton.setFont(font);
        onButton.setFont(font);
        offButton.setFont(font);
        xbox.setPrefHeight(40);
//        xbox.setPrefSize(size,40);
        xbox.getChildren().addAll(labelName,labelStatus, onButton, offButton, enterButton);
        this.setId("listCells");
        enterButton.setId("enterButton");
        onButton.setId("onButton");
        offButton.setId("offButton");
    }

    public void updateItem(Area area, boolean empty) {
        super.updateItem(area, empty);
        setText(null);
        setGraphic(null);
        if (area != null && !empty) {
            labelName.setText(area.getNameArea());
//            labelStatus.setText(name.getState() + "");
            this.area = area;
            setGraphic(xbox);
        }
    }
}
