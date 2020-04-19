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
import proj.DOM.Intervalle;
import proj.intro.Model;

public class ControllerAreaAll implements Initializable {

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

		
		ArrayList<String> noms    =  Model.getProjectsNames();
	    ArrayList<Integer> duree    =  Model.getProjectsDuree();
		
	    if (periode.getDebut() != -1 && periode.getFin() != -1){
		    noms    =  Model.getProjectsNames(periode);
		    duree   =  Model.getProjectsDuree(periode);
	    }

	    areaChart.getXAxis().setTickLength(1);
        
	    XYChart.Series<Number, Number> series= new XYChart.Series<>();
	    
	    for(int i =0; i<noms.size(); i++){
	    	series.setName(noms.get(i));
	    	Intervalle jour = new Intervalle(periode.getDebut(), periode.getDebut()+86400);
	    	int j=0;
	    	while(jour.getFin() <= periode.getFin()){
	    		series.getData().add(new XYChart.Data<Number, Number>(j, Model.getProjectsDuree(jour).get(i)));
	    		j++;
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
	
	public void setPeriode(Intervalle periode){
		this.periode = periode;
	}
}
