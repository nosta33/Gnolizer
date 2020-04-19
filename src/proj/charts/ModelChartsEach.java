package proj.charts;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import proj.DOM.GnoTimeData;
import proj.DOM.Intervalle;
import proj.DOM.Projet;
import proj.intro.Model;

public class ModelChartsEach {

	public static ObservableList<Series<String, Number>> barsEach(String projet, Intervalle periode){
		/*Récupère les noms et durées de chaque tâche du projet*/
        final  ArrayList<String>	noms 	= 	ModelChartsEach.statsEachNames(projet, periode);
	    final  ArrayList<Integer> 	duree 	= 	ModelChartsEach.statsEachDuree(projet, periode);
	    /*Construit la liste observable des valeurs de l'histogramme*/
	    ObservableList<XYChart.Series<String, Number>> barChartData = FXCollections.observableArrayList();
	    final BarChart.Series<String, Number> series1 =  new BarChart.Series<String, Number>(); 
	    for (int i = 0; i<noms.size(); i++){
	    	series1.getData().add(new XYChart.Data<String, Number>(noms.get(i), (double)duree.get(i)/3600)); 
	    }
	    barChartData.add(series1);
	    return barChartData;
	}
	
	public static ObservableList<Data> pieEach(String projet, Intervalle periode){
		/*Récupère les noms et durées de chaque tâche du projet*/
        final  ArrayList<String>	noms 	= 	ModelChartsEach.statsEachNames(projet, periode);
	    final  ArrayList<Integer> 	duree 	= 	ModelChartsEach.statsEachDuree(projet, periode);
	    /*Construit la liste observable des valeurs de l'histogramme*/
	    ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
 
	    for (int i = 0; i<noms.size(); i++){
	    	pieChartData.add(new PieChart.Data(noms.get(i), (double)duree.get(i)/3600)); 
	    }
	    return pieChartData;
	}

	public static ArrayList<String> statsEachNames(String projet, Intervalle periode){
		/* Vérifie si la période est définie ou non*/
		ArrayList<Projet> pl = GnoTimeData.getProjects();
		if (periode.getDebut() != -1 && periode.getFin() != -1){
			pl = GnoTimeData.getProjects(periode);
		}
		/*Choisis le projet associé au nom de projet en argument*/
		int j=0;
		for (int i=0; i<pl.size(); i++){
			if (pl.get(i).getNom().equals(projet)){
				j = i;
			}
		}
		Projet pr = pl.get(j);
		/*Renvoie la liste des noms des tâches du projet*/
        return Model.getTasksNames(pr);
	}
	
	public static ArrayList<Integer> statsEachDuree(String projet, Intervalle periode){
		/* Vérifie si la période est définie ou non*/
		ArrayList<Projet> pl = GnoTimeData.getProjects();
		if (periode.getDebut() != -1 && periode.getFin() != -1){
			pl = GnoTimeData.getProjects(periode);
		}
		/*Choisis le projet associé au nom de projet en argument*/
		int j=0;
		for (int i=0; i<pl.size(); i++){
			if (pl.get(i).getNom().equals(projet)){
				j = i;
			}
		}
		Projet pr = pl.get(j);
		/*Renvoie la liste des durées des tâches du projet*/
        return Model.getTasksDuree(pr);
	}
}
