import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
 
public class Visualization extends Application {
	
	@Override public void start(Stage primaryStage) {
        Year[] years = BoxOfficeMain.years;
        
        LineChart<String, Number> lc1 = createLineChart(years, "Total");
        LineChart<String, Number> lc2 = createLineChart(years, "Average");
        
        FlowPane root = new FlowPane();
        root.getChildren().addAll(lc1, lc2);
        
        Scene scene = new Scene(root, 1050, 400);
        
        primaryStage.setTitle("BoxOffice");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	private LineChart<String, Number> createLineChart(Year[] values, String title) {
	    //defining the axes
	    final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Year");
	    //creating the chart
	    final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);

	    lineChart.setTitle("BoxOffice "+title+" revenue "+BoxOfficeMain.STARTYEAR+"-"+BoxOfficeMain.MAXYEAR+" [$]");
	    //defining a series
	    XYChart.Series<String, Number> series = new LineChart.Series<>();
	    series.setName(title);
	    //populating the series with data
	    for (int i = 0; i < values.length; i++) {
	    	int x = values[i].year;
	    	double y;
	    	if (title == "Total") {
	    		y = CalcData.sum(values[i].worldwideEarnings);
	    	}
	    	else if (title == "Average") {
	    		y = CalcData.avg(values[i].worldwideEarnings);
	    	}
	    	else {
	    		y = 0;
	    	}
	    	XYChart.Data<String, Number> data = new LineChart.Data<>(""+x, y);
	    	series.getData().add(data);
	    }
	    lineChart.getData().add(series);
	    return lineChart;
	  }
	
	public static void visualize(String[] args) {
		launch(args);
	}
}