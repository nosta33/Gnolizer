package proj.intro;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proj.DOM.Intervalle;
import proj.charts.ControllerBarsEach;
import proj.charts.ControllerPieAll;
import proj.charts.ControllerPieEach;
import proj.charts.ControllerAreaAll;
import proj.charts.ControllerAreaEach;
import proj.charts.ControllerBarsAll;

public class ControllerIntro implements Initializable  {

	@FXML
	private ComboBox<String> projCombo;
	
	@FXML
	private ComboBox<String> comboGraph;
	
	@FXML
	private TextField debutField;
	
	@FXML
	private TextField finField;
	
	@FXML
	private Button boutonAfficher;
	
	@FXML
	private void process(ActionEvent event) throws IOException, ParseException{
		/*Récupère les différentes infos rentrées par l'utilisateur*/
		String proj = projCombo.getValue();
		String debut = debutField.getText();
		String fin = finField.getText();
		String graphe = comboGraph.getValue();
		/*Transforme les dates de début et de fin rentrées par l'utilisateur en Timestamp*/
		int debutInt = -1;
		int finInt = -1;
		if (!fin.equals("") && !debut.equals("")){
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    Date debutDate = dateFormat.parse(debut);
		    Date finDate = dateFormat.parse(fin);
		    Timestamp timestampDebut = new java.sql.Timestamp(debutDate.getTime());
		    Timestamp timestampFin = new java.sql.Timestamp(finDate.getTime());
		    debutInt = Math.toIntExact((long)timestampDebut.getTime()/1000);
		    finInt = Math.toIntExact((long)timestampFin.getTime()/1000);
		}
	    
		Stage stage = new Stage();
		if (proj == "Tous" || proj == null){
			if (graphe != null && graphe.equals("Camembert")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../charts/pieAll.fxml"));
				ControllerPieAll controller = new ControllerPieAll();
		        controller.setPeriode(new Intervalle(debutInt, finInt));
		        loader.setController(controller);
		        Parent root = loader.load();
				Scene scene = new Scene(root);
			    stage.setScene(scene);
			    stage.setWidth(800);
		        stage.setHeight(600);
			    stage.setTitle("GnoLyser - Tous les projets - ".concat(debut).concat(" - ").concat(fin));
			    stage.setResizable(false);
			    stage.show();
			}
			else if (graphe != null && graphe.equals("Courbe") && !fin.equals("") && !debut.equals("")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../charts/areaAll.fxml"));
				ControllerAreaAll controller = new ControllerAreaAll();
		        controller.setPeriode(new Intervalle(debutInt, finInt));
		        loader.setController(controller);
		        Parent root = loader.load();
				Scene scene = new Scene(root);
			    stage.setScene(scene);
			    stage.setWidth(800);
		        stage.setHeight(600);
			    stage.setTitle("GnoLyser - Tous les projets - ".concat(debut).concat(" - ").concat(fin));
			    stage.setResizable(false);
			    stage.show();
			}
			else{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../charts/barsAll.fxml"));
				ControllerBarsAll controller = new ControllerBarsAll();
		        controller.setPeriode(new Intervalle(debutInt, finInt));
		        loader.setController(controller);
		        Parent root = loader.load();
				Scene scene = new Scene(root);
			    stage.setScene(scene);
			    stage.setWidth(800);
		        stage.setHeight(600);
			    stage.setTitle("GnoLyser - Tous les projets - ".concat(debut).concat(" - ").concat(fin));
			    stage.setResizable(false);
			    stage.show();
			}
		}
		else {
			if (graphe != null && graphe.equals("Camembert")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../charts/pieEach.fxml"));
		        ControllerPieEach controller = new ControllerPieEach();
		        controller.setProjet(proj);
		        controller.setPeriode(new Intervalle(debutInt, finInt));
		        loader.setController(controller);
		        Parent root = loader.load(); 
				Scene scene = new Scene(root);
			    stage.setScene(scene);	
			    stage.setResizable(false);
			    stage.setWidth(800);
		        stage.setHeight(600);
		        stage.setTitle("GnoLyser - ".concat(proj).concat(" - ").concat(debut).concat(" - ").concat(fin));
			    stage.show();
			}
			else if (graphe != null && graphe.equals("Courbe") && !fin.equals("") && !debut.equals("")){
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../charts/areaEach.fxml"));
		        ControllerAreaEach controller = new ControllerAreaEach();
		        controller.setProjet(proj);
		        controller.setPeriode(new Intervalle(debutInt, finInt));
		        loader.setController(controller);
		        Parent root = loader.load(); 
				Scene scene = new Scene(root);
			    stage.setScene(scene);	
			    stage.setResizable(false);
			    stage.setWidth(800);
		        stage.setHeight(600);
		        stage.setTitle("GnoLyser - ".concat(proj).concat(" - ").concat(debut).concat(" - ").concat(fin));
			    stage.show();
			}
			else{
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../charts/barsEach.fxml"));
		        ControllerBarsEach controller = new ControllerBarsEach();
		        controller.setProjet(proj);
		        controller.setPeriode(new Intervalle(debutInt, finInt));
		        loader.setController(controller);
		        Parent root = loader.load(); 
				Scene scene = new Scene(root);
			    stage.setScene(scene);	
			    stage.setResizable(false);
			    stage.setWidth(800);
		        stage.setHeight(600);
		        stage.setTitle("GnoLyser - ".concat(proj).concat(" - ").concat(debut).concat(" - ").concat(fin));
			    stage.show();
			}
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		projCombo.getItems().add("Tous");
		ArrayList<String> nomsProj = Model.getProjectsNames();
		for (int i=0; i<nomsProj.size(); i++){
			projCombo.getItems().add(nomsProj.get(i));
		}
		
		comboGraph.getItems().addAll("Histogramme", "Camembert", "Courbe");
		
	}
	
}
