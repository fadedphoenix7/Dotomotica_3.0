package com.jose.view.HomeView;

import com.jfoenix.controls.JFXButton;
import com.jose.controller.Home.AreaController;
import com.jose.controller.Home.DeviceController;
import com.jose.model.schemas.Area;
import com.jose.model.schemas.Device;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
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

    public AreaCell(){
        super();
        enterButton.setButtonType(JFXButton.ButtonType.RAISED);
        enterButton.setOnAction(e -> {
            AreaController.setArea(area);
            AreaController.enterArea();
        });

        onButton.setOnAction( e -> {
//            DeviceController.turnOnButton(area);
        });

        offButton.setOnAction( e -> {
//            DeviceController.turnOffButton(area);
        });
        Font font = new Font(24);

        labelName.setFont(font);
        labelName.setPrefWidth(300);
        labelStatus.setFont(font);
        labelStatus.setBackground(new Background(new BackgroundFill(Color.RED,
                CornerRadii.EMPTY, Insets.EMPTY)));
        enterButton.setFont(font);
        onButton.setFont(font);
        offButton.setFont(font);
//            xbox.setHg
        xbox.setPrefSize(500,40);
        xbox.getChildren().addAll(labelName,labelStatus, onButton, offButton, enterButton);
    }

    public void updateItem(Area area, boolean empty) {
        super.updateItem(area, empty);
        setText(null);
        setGraphic(null);
        if (area != null && !empty) {
            System.out.println(area);
            labelName.setText(area.getNameArea());
//            labelStatus.setText(name.getState() + "");
            this.area = area;
            setGraphic(xbox);
        }
    }
}
