package Application;
import java.util.ArrayList;
import java.util.Date;

import Application.LogList;
import Application.RecordList;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ErrorWindow {
	
	public static void display(String ErrorMessage) {
	    Stage window = new Stage();
	    window.setTitle("INVALID");
	    window.initModality(Modality.APPLICATION_MODAL);
	    StackPane layout1 = new StackPane();
	    layout1.setAlignment(Pos.CENTER);
	    Scene scene1 = new Scene(layout1, 250, 200);
	    Label label = new Label(ErrorMessage);
	    label.setCenterShape(true);
	    layout1.getChildren().add(label);
	    window.setScene(scene1);
	    window.showAndWait();
	}
}
