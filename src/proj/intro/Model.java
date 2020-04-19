package proj.intro;

import java.util.ArrayList;

import proj.DOM.*;

public class Model {

	static ArrayList<Projet> listeProjets = GnoTimeData.getProjects();
	
	public static ArrayList<String> getProjectsNames() {
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i<listeProjets.size(); i++){
			res.add(listeProjets.get(i).getNom());
		}
		
		return res;
	}
	
	public static ArrayList<Integer> getProjectsDuree() {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i<listeProjets.size(); i++){
			res.add(listeProjets.get(i).getDuree());
		}
		
		return res;
	}
	
	public static ArrayList<String> getTasksNames(Projet projet){
		ArrayList<String> res = new ArrayList<String>();
		ArrayList<Task> taches = projet.getTasks();
		for (int i=0; i<taches.size(); i++){
			res.add(taches.get(i).getNom());
		}
		return res;
	}
	
	public static ArrayList<Integer> getTasksDuree(Projet projet){
		ArrayList<Integer> res = new ArrayList<Integer>();
		ArrayList<Task> taches = projet.getTasks();
		for (int i=0; i<taches.size(); i++){
			res.add(taches.get(i).getDuree());
		}
		return res;
	}
	
	public static ArrayList<String> getProjectsNames(Intervalle intervalle) {
		ArrayList<String> res = new ArrayList<String>();
		for (int i = 0; i<listeProjets.size(); i++){
			res.add(listeProjets.get(i).getNom());
		}
		
		return res;
	}
	
	public static ArrayList<Integer> getProjectsDuree(Intervalle intervalle) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int i = 0; i<GnoTimeData.getProjects(intervalle).size(); i++){
			res.add(GnoTimeData.getProjects(intervalle).get(i).getDuree());
		}
		
		return res;
	}

}
