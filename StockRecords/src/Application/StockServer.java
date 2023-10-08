package Application;

import java.util.ArrayList;
import java.util.Date;
import Application.LogList;
import Application.RecordList;
import Application.ErrorWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StockServer extends Application {
	private TextField tfItem = new TextField();
	private TextField tfQuantity = new TextField();
	private TextArea taNote = new TextArea();
	private int CurrentStockValue;
	private int EntryStockValue;
	private int ExitStockValue;
	
	private int exist;
	
	private int CurrentStockValueD;
	private int EntryStockValueD;
	private int ExitStockValueD;
	
	private int tableViewSize;
	private String statusDeleted = "DELETED";
	private String statusApproved = "APPROVED";
	private ObservableList<LogList> data = FXCollections.observableArrayList();
	private ObservableList<RecordList> dataDetails = FXCollections.observableArrayList();
	//private java.util.Date DateRegistered;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		TableView<LogList> tableView = new TableView<>();
		//ObservableList<LogList> data = FXCollections.observableArrayList();
		tableView.setItems(data);
		tableView.setMinHeight(530);
		
		TableColumn ItemColumn = new TableColumn("Item Name");
		ItemColumn.setMinWidth(100);
		ItemColumn.setCellValueFactory(
				new PropertyValueFactory<LogList, String>("Item"));
		    
		TableColumn CurrentStockColumn = new TableColumn("Current Stock");
		CurrentStockColumn.setMinWidth(100);
		CurrentStockColumn.setCellValueFactory(
				new PropertyValueFactory<LogList, Integer>("CurrentStock"));

		TableColumn TotalEntriesColumn = new TableColumn("Total Entries");
		TotalEntriesColumn.setMinWidth(125);
		TotalEntriesColumn.setCellValueFactory(
				new PropertyValueFactory<LogList, String>("TotalEntries"));
		    
		TableColumn TotalExitsColumn = new TableColumn("Total Exits");
		TotalExitsColumn.setMinWidth(150);
		TotalExitsColumn.setCellValueFactory(
				new PropertyValueFactory<LogList, String>("TotalExits"));
		  
		  //TableColumn dateColumn = new TableColumn("Date Logged");
		  //dateColumn.setMinWidth(200);
		  //dateColumn.setCellValueFactory(
		  //  new PropertyValueFactory<LogList, Date>("dateCreated"));
		  	    
		tableView.getColumns().addAll(ItemColumn, CurrentStockColumn, TotalEntriesColumn, TotalExitsColumn);
		//.....................................................................................................................................................
		
		TableView<RecordList> tableViewDetails = new TableView<>();
		//ObservableList<LogList> data = FXCollections.observableArrayList();
		tableViewDetails.setItems(dataDetails);
		tableViewDetails.setMinHeight(530);
		
		TableColumn ItemNColumn = new TableColumn("Item");
		ItemNColumn.setMinWidth(100);
		ItemNColumn.setCellValueFactory(
				new PropertyValueFactory<RecordList, String>("ItemName"));
		    
		TableColumn EntryQColumn = new TableColumn("Entry");
		EntryQColumn.setMinWidth(100);
		EntryQColumn.setCellValueFactory(
				new PropertyValueFactory<RecordList, Integer>("EntryQuantity"));

		TableColumn ExitQColumn = new TableColumn("Exit");
		ExitQColumn.setMinWidth(125);
		ExitQColumn.setCellValueFactory(
				new PropertyValueFactory<RecordList, String>("ExitQuantity"));
		    
		TableColumn NotesColumn = new TableColumn("Notes");
		NotesColumn.setMinWidth(150);
		NotesColumn.setCellValueFactory(
				new PropertyValueFactory<RecordList, String>("Notes"));
		  
		TableColumn dateColumn = new TableColumn("Date Registered");
		dateColumn.setMinWidth(200);
		dateColumn.setCellValueFactory(
				new PropertyValueFactory<RecordList, Date>("DateRegistered"));
		
		TableColumn QuantityColumn = new TableColumn("Quantity");
		QuantityColumn.getColumns().add(EntryQColumn); 
		QuantityColumn.getColumns().add(ExitQColumn);
		
		TableColumn StatusColumn = new TableColumn("Status");
		StatusColumn.setMinWidth(150);
		StatusColumn.setCellValueFactory(
				new PropertyValueFactory<RecordList, String>("Status"));
		
		tableViewDetails.getColumns().addAll(StatusColumn, ItemNColumn, QuantityColumn, NotesColumn, dateColumn);
		
		//.....................................................................................................................................................
		
		final Button btRecord = new Button("Record");
		final Button btDelete = new Button("Delete");
		ComboBox<String> cBox = new ComboBox<>();
		String[] option = {"Entry", "Exit"};
		ObservableList<String> movement = FXCollections.observableArrayList(option);
		cBox.getItems().addAll(movement);
		
		taNote.setPrefHeight(50);
		taNote.setPrefWidth(250);
		taNote.setWrapText(true);
				
		//tfQuantity.setPrefHeight(150);
		tfQuantity.setPrefWidth(70);
		
		HBox RecordBar = new HBox(10);
		RecordBar.getChildren().addAll(new Label(" Item:"), tfItem, new Label("Quantity:"), tfQuantity, 
				new Label("Action:"), cBox, new Label("Note:"), taNote, btRecord, btDelete);
		  	
		  	
		ScrollPane scroll1 = new ScrollPane(tableView);
		ScrollPane scroll2 = new ScrollPane(tableViewDetails);
		//scroll.setPrefHeight();
		
		HBox SetupBox = new HBox(10);
		SetupBox.getChildren().addAll(scroll1,scroll2);
		
		BorderPane pane = new BorderPane();
		pane.setTop(RecordBar);
		pane.setCenter(SetupBox);
		
		btRecord.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				exist = 0;
				tableViewSize = tableView.getItems().size();
				if(cBox.getValue() == null ||tfItem.getText().isEmpty()||tfQuantity.getText().isEmpty()) {
					ErrorWindow.display("Invalid Record!\nMissing specifications.");
				}
				else {				
					if (tableViewSize == 0){
						if(cBox.getValue().toString().equalsIgnoreCase("Entry")) {
							data.add(new LogList(tfItem.getText(), Integer.parseInt(tfQuantity.getText()), Integer.parseInt(tfQuantity.getText()), 0));
							dataDetails.add(new RecordList(statusApproved, tfItem.getText(), Integer.parseInt(tfQuantity.getText()), 0, taNote.getText()));
							tableView.refresh();
						}
						if(cBox.getValue().toString().equalsIgnoreCase("Exit")){
							ErrorWindow.display("\tInvalid Exit!\nNo stock available.");
						}
					}
					else {
						if(cBox.getValue().toString().equalsIgnoreCase("Entry")) {
							dataDetails.add(new RecordList(statusApproved, tfItem.getText(), Integer.parseInt(tfQuantity.getText()), 0, taNote.getText()));
							for(int i = 0; i < tableViewSize; i++) {
								System.out.println(tfItem.getText().equalsIgnoreCase(tableView.getItems().get(i).getItem())+".....");
								if(tfItem.getText().equalsIgnoreCase(tableView.getItems().get(i).getItem())) {
									EntryStockValue = Integer.parseInt(tfQuantity.getText()) + tableView.getItems().get(i).getTotalEntries();
									ExitStockValue =  tableView.getItems().get(i).getTotalExits();
									CurrentStockValue = EntryStockValue - ExitStockValue;
									tableView.getItems().get(i).setTotalEntries(EntryStockValue);
									tableView.getItems().get(i).setTotalExits(ExitStockValue);
									tableView.getItems().get(i).setCurrentStock(CurrentStockValue);
									tableView.refresh();
								}
								else if (i == tableViewSize-1 && !tfItem.getText().equalsIgnoreCase(tableView.getItems().get(tableViewSize-1).getItem())){
									data.add(new LogList(tfItem.getText(), Integer.parseInt(tfQuantity.getText()), Integer.parseInt(tfQuantity.getText()), 0));
									tableView.refresh();
								}
							}
						}				
						else if(cBox.getValue().toString().equalsIgnoreCase("Exit")) {
							//dataDetails.add(new RecordList(tfItem.getText(), 0, Integer.parseInt(tfQuantity.getText()), taNote.getText()));
							for(int i = 0; i < tableViewSize; i++) {
								if(tfItem.getText().equalsIgnoreCase(tableView.getItems().get(i).getItem()) == true) {
									exist = exist + 1;
								}
								
								if(tfItem.getText().equalsIgnoreCase(tableView.getItems().get(i).getItem()) && 
										tableView.getItems().get(i).getCurrentStock() >= Integer.parseInt(tfQuantity.getText())) {
									
									dataDetails.add(new RecordList(statusApproved, tfItem.getText(), 0, Integer.parseInt(tfQuantity.getText()), taNote.getText()));
									ExitStockValue = tableView.getItems().get(i).getTotalExits()+ Integer.parseInt(tfQuantity.getText());
									EntryStockValue =  tableView.getItems().get(i).getTotalEntries();
									CurrentStockValue = EntryStockValue - ExitStockValue;
									tableView.getItems().get(i).setTotalEntries(EntryStockValue);
									tableView.getItems().get(i).setTotalExits(ExitStockValue);
									tableView.getItems().get(i).setCurrentStock(CurrentStockValue);
									tableView.refresh();
									System.out.println("Works");
								}
								else if(tfItem.getText().equalsIgnoreCase(tableView.getItems().get(i).getItem()) && 
										tableView.getItems().get(i).getCurrentStock() < Integer.parseInt(tfQuantity.getText())) {
									
									ErrorWindow.display("\tInvalid Exit!\nExit Amount greater than stock.");
								}
							}
							if (exist == 0) {
								ErrorWindow.display("\tInvalid Exit!\nNo Existing stock.");
							}
						}			
					}
				}
				cBox.valueProperty().set(null);
				tfQuantity.clear();
				taNote.clear();
				tfItem.clear();
			}
		});
		//tableViewDetails.setEditable(true);
		//dataDetails.
		
		btDelete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				System.out.println(tableViewDetails.getSelectionModel().selectedIndexProperty().get());
				int delete = tableViewDetails.getSelectionModel().selectedIndexProperty().get();
				///tableViewDetails.getSelectionModel().select(delete);
				Date date = dataDetails.get(delete).getDateRegistered();
				Integer entryQty = dataDetails.get(delete).getEntryQuantity();
				Integer exitQty = dataDetails.get(delete).getExitQuantity();
				String notesR = dataDetails.get(delete).getNotes();
				String itemR = dataDetails.get(delete).getItemName();
				String statusR = statusDeleted;
				tableViewSize = tableView.getItems().size();
				for(int i = 0; i < tableViewSize; i++) {
					if(itemR.equalsIgnoreCase(tableView.getItems().get(i).getItem())) {
						EntryStockValueD = tableView.getItems().get(i).getTotalEntries() - entryQty;
						ExitStockValueD = tableView.getItems().get(i).getTotalExits() - exitQty;
						CurrentStockValueD = EntryStockValueD - ExitStockValueD;
						if(CurrentStockValueD<0) {
							ErrorWindow.display("\tInvalid Action!\nExit Amount greater than stock.");
						}
						else if (CurrentStockValueD >=0){
							tableView.getItems().get(i).setTotalEntries(EntryStockValueD);
							tableView.getItems().get(i).setTotalExits(ExitStockValueD);
							tableView.getItems().get(i).setCurrentStock(CurrentStockValueD);
							tableView.refresh();
							dataDetails.set(delete, new RecordList(statusR, itemR, entryQty, exitQty, notesR + " - "+ date));
							if(CurrentStockValueD == 0) {
								ErrorWindow.display("\tWARNING!\nCurrent Stock = 0.");
							}
						}

					}
				}
			}
		});

		Scene scene = new Scene(pane, 1200, 600);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
        launch();
    }
}