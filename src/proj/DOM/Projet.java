package proj.DOM;

import java.util.ArrayList;

public class Projet {

	private String nom;
	private int duree;
	private ArrayList<Task> taches;
	
	public Projet(String nom, int duree, ArrayList<Task> taches) {
		this.nom = nom;
		this.duree = duree;
		this.taches = taches;
	}
	
	public String getNom() {
		return nom;
	}

	public int getDuree() {
		return duree;
	}
	
	public ArrayList<Task> getTasks(){
		return taches;
	}
	
}	
