package Application;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LogList {
	private final SimpleStringProperty Item;
    private final SimpleIntegerProperty CurrentStock;
    private final SimpleIntegerProperty TotalEntries;
    private final SimpleIntegerProperty TotalExits;
    //private java.util.Date dateCreated;

    LogList(String fItem, Integer fCurrentStock, Integer fTotalEntries, Integer fTotalExits) {
    	this.Item = new SimpleStringProperty(fItem);
	    this.CurrentStock = new SimpleIntegerProperty(fCurrentStock);
	    this.TotalEntries = new SimpleIntegerProperty(fTotalEntries);
	    this.TotalExits = new SimpleIntegerProperty(fTotalExits);
	    //dateCreated = new java.util.Date();
	    }
    
    public String getItem() {
    	return Item.get();
    }
    
    public void setItem(String fItem) {
    	Item.set(fItem);
    }

	public Integer getCurrentStock() {
		return CurrentStock.get();
	}

	public void setCurrentStock(Integer fCurrentStock) {
		CurrentStock.set(fCurrentStock);
	}
	    
	public Integer getTotalEntries() {
		return TotalEntries.get();
	}
	    
	public void setTotalEntries(Integer fTotalEntries) {
		TotalEntries.set(fTotalEntries);
	}
	    
	public Integer getTotalExits() {
		return TotalExits.get();
	}
	    
	public void setTotalExits(Integer fTotalExits) {
        TotalExits.set(fTotalExits);
	}    
	//public java.util.Date getDateCreated(){
	//	return dateCreated;
	//}     
}
