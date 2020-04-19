package proj.charts;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart.Series;
import proj.DOM.Intervalle;
import proj.intro.Model;

public class ModelChartsAll {

	public static ObservableList<Series<String, Number>> bars(Intervalle periode){
		/*Récupère le nom des projets et leurs durées*/
		ArrayList<String> noms = statsNames(periode);
		ArrayList<Integer> duree = statsDuree(periode);
	    /*Construit les données de l'histogramme*/
	    ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
	    final BarChart.Series<String, Number> series1 =  new BarChart.Series<String, Number>(); 
	    for (int i = 0; i<noms.size(); i++){
	    	series1.getData().add(new XYChart.Data<String, Number>(noms.get(i), (double)duree.get(i)/3600)); 
	    }
	    barChartData.add(series1);
	    return barChartData;
	}
	
	public static ObservableList<Data> pie(Intervalle periode){
		/*Récupère le nom des projets et leurs durées*/
		ArrayList<String> noms = statsNames(periode);
		ArrayList<Integer> duree = statsDuree(periode);
	    /*Construit les données du camembert*/
	    ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
 
	    for (int i = 0; i<noms.size(); i++){
	    	pieChartData.add(new PieChart.Data(noms.get(i), (double)duree.get(i)/3600)); 
	    }
	    return pieChartData;
	}

	public static ArrayList<String> statsNames(Intervalle periode){
		/*Récupère le nom des projets et leurs durées*/
		ArrayList<String> noms    =  Model.getProjectsNames();
	    /*Vérifie si la période a été définie par l'utilisateur*/
	    if (periode.getDebut() != -1 && periode.getFin() != -1){
		    noms    =  Model.getProjectsNames(periode);
	    }
	    return noms;
	}
	
	public static ArrayList<Integer> statsDuree(Intervalle periode){
		/*Récupère le nom des projets et leurs durées*/
		ArrayList<Integer> duree = Model.getProjectsDuree();
	    /*Vérifie si la période a été définie par l'utilisateur*/
	    if (periode.getDebut() != -1 && periode.getFin() != -1){
		    duree = Model.getProjectsDuree(periode);
	    }
	    return duree;
	}
}
