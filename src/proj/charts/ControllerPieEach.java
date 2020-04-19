package proj.charts;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import proj.DOM.Intervalle;

public class ControllerPieEach implements Initializable {

	private Intervalle periode;
	private String projet;
	
	@FXML
	private PieChart pieChart;
	
	@FXML
	private GridPane statsGrid;
	
	@FXML
	private ScrollPane sc;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		sc.setFitToWidth(true);
		sc.setPadding(new Insets(30.0));

		ObservableList<Data> pieChartData = ModelChartsEach.pieEach(projet, periode);

        pieChart.setData(pieChartData);
		
        ArrayList<String> noms = ModelChartsEach.statsEachNames(projet, periode);
        ArrayList<Integer> duree = ModelChartsEach.statsEachDuree(projet, periode);
        
        double somme = 0; for(int i=0; i<duree.size(); i++){somme+=duree.get(i);}
        
        for(int i=0; i<duree.size(); i++){
        	Label proj = new Label(noms.get(i));
        	Label perc = new Label(String.valueOf((int) Math.round((duree.get(i)/somme*100))) + " %");
        	perc.setId("data");
        	proj.setId("dataname");
        	
        	statsGrid.add(proj, 0, i+3);
        	statsGrid.add(perc, 1, i+3);
        }
        
        
       
	}
	
	public void setPeriode(Intervalle periode){
		this.periode = periode;
	}
	
	public void setProjet(String projet){
		this.projet = projet;
	}
}
