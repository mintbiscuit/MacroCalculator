
    /**************************************************************************
     * This application gathers calorie input from a user and displays ratios *
     * and a pie chart in the UI.   It is geared towards individuals with an  *
     * interest in weight loss or those that have specific fitness goals.     *
     * This application uses JavaFX to construct its GUI, and it uses an      *
     * accompanying style sheet to make it look pretty. :)                    *
     * 									      *
     * Any questions/concerns/comments, please contact me from my website:    *
     * 		http://imagine-reality.net				      *
     * 									      *	
     * 			Thanks,						      *
     * 				Amelia					      *
     **************************************************************************/

package app;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
	    primaryStage.setTitle("MacroCalculator");
	    primaryStage.show();
        
	    // Set up a GridPane layout
	    final GridPane grid = new GridPane();
	    grid.setAlignment(Pos.CENTER);
	    grid.setHgap(10);
	    grid.setVgap(10);
	    grid.setPadding(new Insets(25, 25, 25, 25));
	
	    // Set up the 'scene', grid and size
	    Scene scene = new Scene(grid, 450, 425);
	    scene.getStylesheets().add("app/stylesheet.css");
	    primaryStage.setScene(scene);
	    
	    // Welcome message
	    final Text welcome = new Text("Welcome to the Macro Calculator application." +
	    		"\n\nThis application is designed to give you information regarding" +
	    		"\n your macronutrient ratio for weight-loss or strength training." +
	    		"\n\nTo begin, please fill out the following information.\n");
	    welcome.getStyleClass().add("welcome");
	    grid.add(welcome, 0, 0, 3, 1);
	
	    // Set all Labels + TextFields
	    
	    // Calories
	    final Label calLabel = new Label("Total Daily Calories: ");
	    // Set widths to make it look nice/stop resizing
	    calLabel.setMinWidth(200);
	    grid.add(calLabel, 0, 1);
	
	    final TextField calTextField = new TextField();
	    calTextField.setMinWidth(150);
	    grid.add(calTextField, 2, 1);
	    
	    
	    // Fat
	    final Label fatLabel = new Label("Fat (g) : ");
	    fatLabel.setMinWidth(200);
	    grid.add(fatLabel, 0, 2);
	
	    final TextField fatTextField = new TextField();
	    fatTextField.setMinWidth(150);
	    grid.add(fatTextField, 2, 2);
	    
	    
	    // Protein
	    final Label proteinLabel = new Label("Protein (g) : ");
	    proteinLabel.setMinWidth(200);
	    grid.add(proteinLabel, 0, 3);
	
	    final TextField proteinTextField = new TextField();
	    proteinTextField.setMinWidth(150);
	    grid.add(proteinTextField, 2, 3);
	    
	    
	    // Carbs
	    final Label carbLabel = new Label("Carbohydrates (g) : ");
	    carbLabel.setMinWidth(200);
	    grid.add(carbLabel, 0, 4);
	    
	    final TextField carbTextField = new TextField();
	    carbTextField.setMinWidth(150);
	    grid.add(carbTextField, 2, 4);
	    
	    
	    // Fiber
	    final Label fiberLabel = new Label("Fiber (g) : ");
	    fiberLabel.setMinWidth(200);
	    grid.add(fiberLabel, 0, 5);
	    
	    final TextField fiberTextField = new TextField();
	    fiberTextField.setMinWidth(150);
	    grid.add(fiberTextField, 2, 5);
	
	    
	    // Create Button, put it in an HBox and add it to the grid
	    Button submitButton = new Button("Calculate Macros");
	    submitButton.setId("button");
	    final HBox hbBtn = new HBox(10);
	    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
	    hbBtn.getChildren().add(submitButton);
	    grid.add(hbBtn, 1, 6, 2, 1);
	    
	    // Set up text to be used for output (set to HBox for nice alignment)
	    final Text output = new Text();
	    final HBox hbOutput = new HBox();
	    hbOutput.setAlignment(Pos.CENTER);
	    hbOutput.getChildren().add(output);
	    grid.add(hbOutput, 0, 2, 4, 2);
	    
	    // Create a PieChart object
	    // On the mouse click it will be visible and given information
	    final PieChart pie = new PieChart();
      
    
	    // Give the submitButton an action
	    submitButton.setOnAction(new EventHandler<ActionEvent>() {
    	 
        @Override
        public void handle(ActionEvent e) {
        	
        	// Set all inputs to Doubles
        	Double cal = Double.parseDouble(calTextField.getText());
        	Double fat = Double.parseDouble(fatTextField.getText());
        	Double protein = Double.parseDouble(proteinTextField.getText());
        	Double carb = Double.parseDouble(carbTextField.getText());
        	Double fiber = Double.parseDouble(fiberTextField.getText());
        	
        	// Get output from getOutput function and set the text
        	String s = "\nYour micronutrient ratio is:\n" + getOutput(cal, fat, protein, carb, fiber);
        	output.getStyleClass().add("output");
        	output.setText(s);
            
            // A few small functions to be thrown into the pieChartData
            Double t_f = ((fat * 9) / cal) * 100;		// Fat percent
            Double t_p = ((protein * 4) / cal) * 100;		// Protein percent
            Double t_c = (((carb - fiber) * 4) / cal) * 100;	// Carb percent
            
            // Add macro percentages to the pie chart
            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                    new PieChart.Data("Fat", t_f),
                    new PieChart.Data("Protein", t_p),
                    new PieChart.Data("Carbohydrates", t_c)); 
            
            // Set the data to the pie chart
            pie.setData(pieChartData);
            // Give it a nice name
    	    pie.setTitle("Your Personal MacroPie");
            // Add nice labels to the chart
            pie.setLabelLineLength(10);
            
            // And then finally, set the chart to be visible!
            pie.setVisible(true);
            
            // Change top message
            welcome.setText("Thanks for using MacroCalculator. Your results are displayed below.");
            
            // Remove input fields
            grid.getChildren().remove(calLabel);
            grid.getChildren().remove(calTextField);
            grid.getChildren().remove(fatLabel);
            grid.getChildren().remove(fatTextField);
            grid.getChildren().remove(proteinLabel);
            grid.getChildren().remove(proteinTextField);
            grid.getChildren().remove(carbLabel);
            grid.getChildren().remove(carbTextField);
            grid.getChildren().remove(fiberLabel);
            grid.getChildren().remove(fiberTextField);
            
            // Remove Button
            grid.getChildren().remove(hbBtn);
            
            // Add pie chart
            grid.add(pie, 0, 4, 3, 1); 
        }
    });
    
    }
    

    /**************************************************************************
     * This method will calculate the overall percent of each macro (based on *
     * 4 cal per gram of protein/carb and 9 cal per fat. It will multiply the *
     * amount by 100 to get an easily read percentage.                        *
     *                                                                        *
     * @param c (Total Calories)					      *
     * @param f (Total Fat)						      *
     * @param p (Total Protein)						      *
     * @param cb (Total Carbs)						      *
     * @param fi (Total Fiber)						      *
     * @return A string storing the % of each macro in regards to total cals  *
     **************************************************************************/

    public static String getOutput(Double c, Double f, Double p, Double cb, Double fi) {
    	// Storing net carbs (Carbs - Fiber)
    	Double netcarbs = cb - fi;
    	    	
    	// Find fat percentage
    	Double t_f = ((f * 9) / c) * 100;
    	
    	// Find protein percentage
    	Double t_p = ((p * 4) / c) * 100;
    	
    	// Find (net) carb percentage
    	Double t_c = ((netcarbs * 4) / c) * 100;
    	
    	// This will be the returned String (round the numbers nicely, too)
    	String s = String.valueOf(Math.round(t_f)) + "% fat / " + String.valueOf(Math.round(t_p)) + " % protein / " + 
    			   String.valueOf(Math.round(t_c)) + "% net carbohydrates";
    	
    	return s;
    
    }
}
