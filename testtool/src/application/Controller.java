package application;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.junit.runner.JUnitCore;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import testcase.AddTest;

public class Controller {
	@FXML  
    private AnchorPane layoutPane;  
	
	@SuppressWarnings("rawtypes")
	@FXML
	private TableView parameterTable;
	
    @FXML  
    private TextArea  fileContent;  
    
    @FXML
    private ComboBox<String> projects;
    @FXML
    private ComboBox<String> classes;
    @FXML
    private ComboBox<String> methods;
    @FXML
    private ComboBox<String> testcases;
    
    private File result;  
    
    public Controller() {
    	ObservableList<String> list = FXCollections.observableArrayList("one",
    	        "two");
    	    System.out.println(list);

    	    list.addListener(Controller::onChanged);

    	    list.addAll("A", "B");
    	    System.out.println("After addAll() - list: " + list);

    	    list.remove(1, 3);
    	    System.out.println("After remove() - list: " + list);

    	    list.retainAll("one");
    	    System.out.println("After retainAll() - list: " + list);

    	    list.set(0, "ONE");
    	    System.out.println("After set() - list: " + list);
//    	dropdown.setItems(list);
		// TODO Auto-generated constructor stub
	}
    /**
     * 初始化
     */
    public void init() {
    	ObservableList<String> list = FXCollections.observableArrayList("test",
    	        "two");
    	fileContent.setEditable(false);
    	System.out.println("123 :"+projects.getValue());
    	projects.getItems().setAll(list);
    	projects.setItems(list);
    	projects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				System.out.println("old value : "+arg1);
				System.out.println("new value : "+arg2);
				
			}
		});
    	ObservableList<String> testList = FXCollections.observableArrayList("testcase1",
    	        "testcase2");
    	testcases.setItems(testList);
    	testcases.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				JUnitCore runner = new JUnitCore();
		        ExecutionListener listener = new ExecutionListener();
		        runner.addListener(listener);
		       
				switch (arg2) {
				case "testcase1":
					System.out.println("testcase1");
			        Tool.initTestData("", 1);
					break;
				case "testcase2":
					System.out.println("testcase2");
			        Tool.initTestData("", 2);   	
					break;
				default:
					break;
				}
				runner.run(AddTest.class);
				 ResultRecorder recorder = listener.recorder;
				 System.out.println(recorder);
				 fileContent.setText(listener.record.toString());
			}
		});
	}
    
    public void testForAdd() {
		
	}
    
	public static void onChanged(ListChangeListener.Change<? extends String> change) {
		while (change.next()) {
			if (change.wasPermutated()) {
				System.out.println("A permutation is detected.");
			} else if (change.wasUpdated()) {
				System.out.println("An update is detected.");
			} else if (change.wasReplaced()) {
				System.out.println("A replacement is detected.");
			} else {
				if (change.wasRemoved()) {
					System.out.println("A removal is detected.");
				} else if (change.wasAdded()) {
					System.out.println("An addiiton is detected.");
				}
			}
		}
	}
    @FXML  
    private void onMenuOpen(ActionEvent event) {  
        FileChooser fileChooser = new FileChooser();  
        result = fileChooser.showOpenDialog(layoutPane.getScene().getWindow());  
        if (result != null) {  
            fileContent.setText(FileTools.readFile(result));  
        }  
    }  
      
    @FXML  
    private void onMenuSave(ActionEvent event) {  
        if(result != null){  
            FileTools.writeFile(result, fileContent.getText());  
        }  
    }  
      
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML  
    private void onMenuClose(ActionEvent event) {  
    	ObservableList<TableColumn> observableList = parameterTable.getColumns();
    	observableList.get(0).setCellValueFactory(new PropertyValueFactory<Parameter, String>("id"));
    	observableList.get(1).setCellValueFactory(new PropertyValueFactory<Parameter, String>("name"));
    	observableList.get(2).setCellValueFactory(new PropertyValueFactory<Parameter, String>("type"));
    	observableList.get(2).setCellValueFactory(new PropertyValueFactory<Parameter, String>("value"));
    	ObservableList<Parameter> data = FXCollections.observableArrayList(
    			new Parameter("1","a", "int", "5"),
    			new Parameter("2","a", "long", "999"),
    			new Parameter("3", "a","string", "abc"),
    			new Parameter("1", "a","int", "5"),
    			new Parameter("2","a", "long", "999"),
    			new Parameter("3","a", "string", "abc"),
    			new Parameter("1","a", "int", "5"),
    			new Parameter("2","a", "long", "999"),
    			new Parameter("3","a", "string", "abc"),
    			new Parameter("1","a", "int", "5"),
    			new Parameter("2", "a","long", "999"),
    			new Parameter("3","a", "string", "abc")
    	        );
    	data.size();
    	parameterTable.setItems(data);
    	parameterTable.setMinHeight(20 + 27*data.size());
    	parameterTable.setMaxHeight(900);
    	parameterTable.setVisible(true);

    	
//    	Platform.runLater(new Runnable() {
//    	    public void run() {
//
//    	    	
//    		}
//    	});
    }  
      
    @FXML  
    private void onMenuDelete(ActionEvent event) {  
        fileContent.replaceSelection("");  
    }  
      
    @FXML  
    private void onMenuAbout(ActionEvent event) {  
        JOptionPane.showMessageDialog(null, "JavaFX记事本是一款使用JavaFX开发的记事本。" ,"关于",  JOptionPane.PLAIN_MESSAGE);  
    }  
      
    @FXML  
    private void onContextSelectAll(ActionEvent event) {  
        fileContent.selectAll();  
    }  
}
