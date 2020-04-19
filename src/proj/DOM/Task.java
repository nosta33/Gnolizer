package proj.DOM;

import java.util.ArrayList;

public class Task {

	private String nom;
	private int duree;
	private ArrayList<Intervalle> intervalles;
	
	public Task(String nom, int duree, ArrayList<Intervalle> intervalle) {
		this.nom = nom;
		this.duree = duree;
		this.intervalles = intervalle;
	}

	public String getNom() {
		return nom;
	}


	public int getDuree() {
		return duree;
	}
	
	public ArrayList<Intervalle> getIntervalles() {
		return intervalles;
	}

}
