package application;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
import testcase.CalendarTest;
import testcase.TestCase;
import testcase.TriangleTest;

public class Controller {
	@FXML  
    private AnchorPane layoutPane;  
	
	@SuppressWarnings("rawtypes")
	@FXML
	private TableView parameterTable;
	
    @FXML  
    private TextArea  fileContent;  
    
    private String testClassName;
    
    private Class<?> testClass;

    private Method testMethod;
    
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
//    	ObservableList<String> list = FXCollections.observableArrayList("one",
//    	        "two");
//    	    System.out.println(list);
//
//    	    list.addListener(Controller::onChanged);
//
//    	    list.addAll("A", "B");
//    	    System.out.println("After addAll() - list: " + list);
//
//    	    list.remove(1, 3);
//    	    System.out.println("After remove() - list: " + list);
//
//    	    list.retainAll("one");
//    	    System.out.println("After retainAll() - list: " + list);
//
//    	    list.set(0, "ONE");
//    	    System.out.println("After set() - list: " + list);
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
//    	projects.getItems().setAll(list);
    	projects.setItems(list);
    	projects.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				System.out.println("old value : "+arg1);
				System.out.println("new value : "+arg2);
				switch (arg2) {
				case "test":
					ObservableList<String> classList = 
						FXCollections.observableArrayList("AddTest","TriangleTest","CalendarTest");
					classes.setItems(classList);
					break;
				default:
					classes.setItems(FXCollections.observableArrayList());
					methods.setItems(FXCollections.observableArrayList());
					testcases.setItems(FXCollections.observableArrayList());
					break;
				}
				
			}
		});
    	classes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				System.out.println("old value : "+arg1);
				System.out.println("new value : "+arg2);
				testcases.setItems(FXCollections.observableArrayList());
				methods.setItems(FXCollections.observableArrayList());
				ObservableList<String> methodList;
				switch (arg2) {
				case "AddTest":
					testClassName = "testcase.AddTest";
					testClass = AddTest.class;
					methodList = FXCollections.observableArrayList("add");
					methods.setItems(methodList);
					break;
				case "TriangleTest":
					testClassName = "testcase.TriangleTest";
					testClass = TriangleTest.class;
					methodList = FXCollections.observableArrayList("triangle");
					methods.setItems(methodList);
					break;
				case "CalendarTest":
					testClassName = "testcase.CalendarTest";
					testClass = CalendarTest.class;
					methodList = FXCollections.observableArrayList("calendar");
					methods.setItems(methodList);
					break;
				default:
					testcases.setItems(FXCollections.observableArrayList());
					methods.setItems(FXCollections.observableArrayList());
					break;
				}
				
			}
		});
    	methods.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				System.out.println("old value : "+arg1);
				System.out.println("new value : "+arg2);
				testcases.setItems(FXCollections.observableArrayList());
				ObservableList<String> caseList;
				try {
					if (arg2!= null) {
						int caseNumber = (int) Class.forName(testClassName).getMethod("getTestCaseNumber").invoke(null);
						String[] cases = new String[caseNumber];
						for (int i = 0; i < caseNumber; i++) {
							cases[i] = "testCase "+(i+1);
						}
						caseList = FXCollections.observableArrayList(Arrays.asList(cases));
						testcases.setItems(caseList);
					} else {
						testcases.setItems(FXCollections.observableArrayList());
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
					e.printStackTrace();
				}			
			}
		});
    	testcases.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@SuppressWarnings("unchecked")
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				if (arg2 == null) {
					return;
				}
		        if (arg2.startsWith("testCase")) {
					JUnitCore runner = new JUnitCore();
			        ExecutionListener listener = new ExecutionListener();
			        runner.addListener(listener);
					int caseid = Integer.parseInt(arg2.split(" ")[1]);
					System.out.println(testClass);
					try {
						Tool.setTestDataCollection((Collection<Object[]>) Class.forName(testClassName).getMethod("getParament",int.class).invoke(null, caseid));
					} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
							| ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					runner.run(testClass);
					 ResultRecorder recorder = listener.recorder;
					 fileContent.setText(Tool.getResult());
				} 
			}
		});
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
	private void showParameter(Collection<Object[]> testDataCollection) {
    	ObservableList<TableColumn> observableList = parameterTable.getColumns();
    	observableList.get(0).setCellValueFactory(new PropertyValueFactory<Parameter, String>("id"));
    	observableList.get(1).setCellValueFactory(new PropertyValueFactory<Parameter, String>("name"));
    	observableList.get(2).setCellValueFactory(new PropertyValueFactory<Parameter, String>("type"));
    	observableList.get(3).setCellValueFactory(new PropertyValueFactory<Parameter, String>("value"));
    	ObservableList<Parameter> data = FXCollections.observableArrayList();
    	int i = 0;
//    	List<Object[]> test = (List<Object[]>) testDataCollection;
    	for (Object[] para : testDataCollection) {
    		i++;
    		data.add(new Parameter(""+i,para[0].toString(), para[1].getClass().getSimpleName(), para[1].toString()));
		}
    	parameterTable.setItems(data);
    	parameterTable.setMinHeight(20 + 27*data.size());
    	parameterTable.setMaxHeight(900);
    	parameterTable.setVisible(true);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@FXML  
    private void onMenuClose(ActionEvent event) {  
    	ObservableList<TableColumn> observableList = parameterTable.getColumns();
    	observableList.get(0).setCellValueFactory(new PropertyValueFactory<Parameter, String>("id"));
    	observableList.get(1).setCellValueFactory(new PropertyValueFactory<Parameter, String>("name"));
    	observableList.get(2).setCellValueFactory(new PropertyValueFactory<Parameter, String>("type"));
    	observableList.get(3).setCellValueFactory(new PropertyValueFactory<Parameter, String>("value"));
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
