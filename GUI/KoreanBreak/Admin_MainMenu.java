package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Admin_MainMenu extends Application {
	

	public class AdminMenuController {

	    @FXML
	    private Button btn6;

	    @FXML
	    private Button btn7;

	    @FXML
	    private Button btn4;

	    @FXML
	    private Button btn5;

	    @FXML
	    private Button btn2;

	    @FXML
	    private Button btn3;

	    @FXML
	    private Button btn1;

	    @FXML
	    void btn1_Mouse_Clicked(ActionEvent event) {

	    }

	    @FXML
	    void btn2_Mouse_Clicked(ActionEvent event) {

	    }

	    @FXML
	    void btn4_Mouse_Clicked(ActionEvent event) {

	    }

	    @FXML
	    void btn3_Mouse_Clicked(ActionEvent event) {

	    }

	    @FXML
	    void bbtn7_Mouse_Clicked(ActionEvent event) {

	    }

	    @FXML
	    void btn6_Mouse_Clicked(ActionEvent event) {

	    }

	    @FXML
	    void btn5_Mouse_Clicked(ActionEvent event) {

	    }

	}


	public static void main(String[] args) {
		Application.launch(args);

	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Admin_MainMenu"));
		Scene scene = new Scene(root, 300, 275);
		primaryStage.setTitle("FXML Welcome");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
