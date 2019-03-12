package it.polito.tdp.numero;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import it.polito.tdp.numero.model.NumeroModel;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		NumeroModel model;
		FXMLLoader loader;
		NumeroController controller;
		try {
			
			loader = new FXMLLoader(getClass().getResource("Numero.fxml"));
			model = new NumeroModel();
			
			BorderPane root = (BorderPane)loader.load();
			controller = (NumeroController)loader.getController();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			controller.setModel(model);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
