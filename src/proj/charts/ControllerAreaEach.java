package proj.charts;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import proj.DOM.GnoTimeData;
import proj.DOM.Intervalle;
import proj.DOM.Projet;
import proj.intro.Model;

public class ControllerAreaEach implements Initializable {

	private String projet;
	private Intervalle periode;
	
	@FXML
	private AreaChart<Number, Number> areaChart;
	
	@FXML
	private GridPane statsGrid;
	
	@FXML
	private ScrollPane sc;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		sc.setFitToWidth(true);
		sc.setPadding(new Insets(30.0));

		Projet pr = projetSelector(projet, periode);
		
		ArrayList<String> noms    =  Model.getTasksNames(pr);
	    ArrayList<Integer> duree    =  Model.getTasksDuree(pr);

	    areaChart.getXAxis().setTickLength(1);
        
	    XYChart.Series<Number, Number> series= new XYChart.Series<>();
	    
	    for(int i =0; i<noms.size(); i++){
	    	series.setName(noms.get(i));
	    	Intervalle jour = new Intervalle(periode.getDebut(), periode.getDebut()+86400);
	    	int k=0;
	    	while(jour.getFin() <= periode.getFin()){
	    		pr = projetSelector(projet, jour);
	    		series.getData().add(new XYChart.Data<Number, Number>(k, Model.getTasksDuree(pr).get(i)));
	    		k++;
	    		jour = new Intervalle(jour.getDebut()+86400, jour.getFin()+86400);
	    	}
	    	areaChart.getData().add(series);
	    	
	        series = new XYChart.Series<>();
	    }
		
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
	
	private Projet projetSelector(String projet, Intervalle periode){
		ArrayList<Projet> pl = GnoTimeData.getProjects(periode);
		int j=0;
		for (int i=0; i<pl.size(); i++){
			if (pl.get(i).getNom().equals(projet)){
				j = i;
			}
		}
		return pl.get(j);
	}
	
	public void setPeriode(Intervalle periode){
		this.periode = periode;
	}
	
	public void setProjet(String projet){
		this.projet = projet;
	}
}
