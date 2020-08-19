package com.jose.view;

import com.jfoenix.animation.alert.JFXAlertAnimation;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class PopupView {

    public PopupView(String msg){
        JFXAlert alert = new JFXAlert();
        alert.initStyle(StageStyle.UNDECORATED);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("Alert"));
        layout.setBody(new Label(msg));
        layout.setActions(getCancelButton(alert));
        alert.setContent(layout);
        alert.show();
    }

    public PopupView(String msg, String title){
        JFXAlert alert = new JFXAlert();
        alert.initStyle(StageStyle.UNDECORATED);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label(title));
        layout.setBody(new Label("Code:"));
        layout.setBody(getLabelMsg(msg));
        layout.setActions(getCancelButton(alert));
        alert.setContent(layout);

        alert.show();
    }

    public Button getCancelButton(JFXAlert alert){
        JFXButton cancelButton = new JFXButton("Accept");
        cancelButton.setButtonType(JFXButton.ButtonType.FLAT);
        cancelButton.setOnAction(e -> alert.hideWithAnimation());
        return cancelButton;
    }

    public JFXTextField getLabelMsg(String msg){
        JFXTextField msgTxt = new JFXTextField(msg);
        msgTxt.setEditable(false);
        msgTxt.setFont(Font.font("Gadugi",16));

        return msgTxt;
    }

}
