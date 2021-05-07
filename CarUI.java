package carbonFootprintTool;

import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CarUI extends Application {

	ListView<String> makeSelectionList = new ListView<>();
	ListView<String> modelSelectionList = new ListView<>();
	ListView<Integer> yearSelectionList = new ListView<>();

	ObservableList<String> makes = FXCollections.observableArrayList();
	ObservableList<String> models = FXCollections.observableArrayList();
	ObservableList<Integer> years = FXCollections.observableArrayList();
	
	String selectedMake;
	String selectedModel;
	Integer selectedYear;
	Integer milesDriven;
	
	public static final ObservableList makeName = FXCollections.observableArrayList();
	public static final ObservableList modelName = FXCollections.observableArrayList();
	public static final ObservableList yearName = FXCollections.observableArrayList();
    final Label label = new Label();

	@Override
	public void start(Stage primaryStage) {
    
		CarDatabase database = new CarDatabase();
		makes.addAll(database.getMakesStringListDB());
    	
    	VBox box = new VBox();
    	Label makeLabel = new Label("Make: ");
    	Label modelLabel = new Label("Model: ");
    	Label yearLabel = new Label("Year: ");
    	Label milesDrivenLabel = new Label("How many miles do you drive this car? ");
    	TextField milesDrivenInput = new TextField("12000");
    	milesDrivenInput.setPrefColumnCount(15);
    	Button submit = new Button("Submit");
    	Separator hLine1 = new Separator();
    	Separator hLine2 = new Separator();
    	Label resultLabel = new Label();
    	
    	primaryStage.setTitle("Car Program");   
    	box.getChildren().addAll(makeLabel, makeSelectionList,
    			modelLabel, modelSelectionList, 
    			yearLabel, yearSelectionList, 
    			milesDrivenLabel, milesDrivenInput, 
    			submit, hLine1, label, 
    			hLine2, resultLabel);

    	makeSelectionList.setPrefSize(200, 35);
    	makeSelectionList.setEditable(true);
    	modelSelectionList.setPrefSize(200, 35);
    	modelSelectionList.setEditable(true);
    	yearSelectionList.setPrefSize(200, 35);
    	yearSelectionList.setEditable(true);

    	makeName.add("Make");
    	modelName.add("Model");
    	yearName.add(2021);
    	
    	makeSelectionList.setItems(makeName);
    	makeSelectionList.setCellFactory(ComboBoxListCell.forListView(makes));
    	modelSelectionList.setItems(modelName);
    	modelSelectionList.setCellFactory(ComboBoxListCell.forListView(models));
    	yearSelectionList.setItems(yearName);
    	yearSelectionList.setCellFactory(ComboBoxListCell.forListView(years));
    	
    	makeSelectionList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val, 
                    String new_val) -> {
                    	selectedMake = new_val;
                    	models.clear();
                    	models.addAll(database.getModelsStringListDB(selectedMake));
                        label.setText("You have selected " + new_val);
            });
    	
    	modelSelectionList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> ov, String old_val, 
                    String new_val) -> {
                    	selectedModel = new_val;
                    	years.clear();
                    	years.addAll(database.getYearsIntListDB(selectedMake, selectedModel));
                        label.setText(new_val);
            });
    	
    	yearSelectionList.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends Integer> ov, Integer old_val, 
                    Integer new_val) -> {
                    	selectedYear = new_val;
                        label.setText(selectedYear.toString());
            });
    	
    	submit.setOnAction(new EventHandler<ActionEvent>() {
    		
    		@Override
    		    public void handle(ActionEvent e) {
    		        if ((milesDrivenInput.getText() != null && !milesDrivenInput.getText().isEmpty())) {
    		        	int init_distance_year = Integer.parseInt(milesDrivenInput.getText());
    		        	int init_mpg = database.returnMPG(selectedMake, selectedModel, selectedYear);
    		            label.setText("You selected " + selectedMake + ", " + selectedModel + ", " + selectedYear + 
    		            		", \nyou drive " + init_distance_year + " miles with this car, and your MPG is " + init_mpg + ". ");
    		            
    		            resultLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    		            DecimalFormat df = new DecimalFormat("#.##");
    		            resultLabel.setText("You emit " + df.format(new CarbonFootprint(init_mpg, init_distance_year).f_to_use()) + " kilograms of CO2 per year.");
    		        } else {
    		            label.setText("Please enter the amount of miles you drive annually with this car.");
    		        }
    		     }
    		 });
    	
//    	StackPane root = new StackPane();
//    	root.getChildren().add(listView);
    	primaryStage.setScene(new Scene(box, 500, 350));
    	primaryStage.show();

    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
