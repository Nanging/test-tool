package application;
	
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import testcase.AddTest;
import testcase.Case;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Tool extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			URL location = getClass().getResource("test.fxml");
	        FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setLocation(location);
	        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
	        Parent root = fxmlLoader.load();
			Scene scene = new Scene(root,950,620);
			primaryStage.initStyle(StageStyle.DECORATED);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Java Test Tool");
			Controller controller =  fxmlLoader.getController();
			controller.init();
			primaryStage.show();
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}
	private ExcelReader reader = new ExcelReader();
	
	private static PrintStream ps = null;
	private static ByteArrayOutputStream bytes=new ByteArrayOutputStream();
	private static Collection<Object[]> testDataCollection = Arrays.asList(
	        new Object[][]{
		        {3,1,2},
		        {10,5,5},
		        {6,4,2},
		        {7,3,4}}
	        );		;
	
	
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	}

	public static void main(String[] args) {
//		JUnitCore runner = new JUnitCore();
//        ExecutionListener listener = new ExecutionListener();
//        runner.addListener(listener);
//        initTestData("", 1);
//        runner.run(AddTest.class); 
//        ResultRecorder recorder = listener.recorder;
//        System.out.println(recorder);
		

		Application.launch(Tool.class,args);
//		launch(args);
	}
	public static String getResult() {
		return bytes.toString();
	}
	public static Collection<Object[]> getTestDataCollection() {
		return testDataCollection;
	}

	public static void setTestDataCollection(Collection<Object[]> testDataCollection) {
		bytes = new ByteArrayOutputStream();
		bytes.toString();
		ps = new PrintStream(bytes);
		System.setOut(ps);
		Tool.testDataCollection = testDataCollection;
	}
}
