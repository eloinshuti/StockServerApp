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

public class RecordList {
	private final SimpleStringProperty ItemName;
    private final SimpleIntegerProperty EntryQuantity;
    private final SimpleIntegerProperty ExitQuantity;
    private final SimpleStringProperty Notes;
    private final SimpleStringProperty Status;
    private java.util.Date DateRegistered;

    RecordList(String StatusD, String ItemN, Integer EntryQ, Integer ExitQ, String NotesD) {
    	this.ItemName = new SimpleStringProperty(ItemN);
	    this.EntryQuantity = new SimpleIntegerProperty(EntryQ);
	    this.ExitQuantity = new SimpleIntegerProperty(ExitQ);
	    this.Notes = new SimpleStringProperty(NotesD);
	    this.Status = new SimpleStringProperty(StatusD);
	    DateRegistered = new java.util.Date();
	    }
    
    public String getStatus() {
    	return Status.get();
    }
    public void setStatus(String StatusD) {
    	Status.set(StatusD);
    }
    public String getItemName() {
    	return ItemName.get();
    }
    
    public void setItemName(String ItemN) {
    	ItemName.set(ItemN);
    }

	public Integer getEntryQuantity() {
		return EntryQuantity.get();
	}

	public void setEntryQuantity(Integer EntryQ) {
		EntryQuantity.set(EntryQ);
	}
	    
	public Integer getExitQuantity() {
		return ExitQuantity.get();
	}
	    
	public void setExitQuantity(Integer ExitQ) {
		ExitQuantity.set(ExitQ);
	}
	    
	public String getNotes() {
		return Notes.get();
	}
	    
	public void setNotes(String NotesD) {
		Notes.set(NotesD);
	} 
	
	public java.util.Date getDateRegistered(){
		return DateRegistered;
	}     
}
