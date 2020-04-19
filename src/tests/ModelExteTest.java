package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import proj.DOM.Intervalle;
import proj.DOM.Projet;
import proj.DOM.Task;
import proj.intro.Model;

public class ModelExteTest extends Model {

	@Test
	public void testgetTasksNames() {
		Intervalle i1 = new Intervalle(1,5);
		Intervalle i2 = new Intervalle(5,7);
		Intervalle i3 = new Intervalle(7,9);
		Intervalle i4 = new Intervalle(9,15);

		ArrayList<Intervalle> listeIntervalles1 = new ArrayList<Intervalle>();
		listeIntervalles1.add(i1);
		listeIntervalles1.add(i3);

		ArrayList<Intervalle> listeIntervalles2 = new ArrayList<Intervalle>();
		listeIntervalles1.add(i2);
		listeIntervalles1.add(i4);

		Task t1 = new Task("Tache1", 6, listeIntervalles1);
		Task t2 = new Task("Tache2", 8, listeIntervalles2);

		ArrayList<Task> listeTaches = new ArrayList<Task>();
		listeTaches.add(t1);
		listeTaches.add(t2);

		Projet p1 = new Projet("Projet", 14, listeTaches);

		if (Model.getTasksNames(p1).get(0) != "Tache1" & Model.getTasksNames(p1).get(1) != "Tache2"){
			fail("mauvais noms");
		}
	}

	@Test
	public void testgetTasksDuree(){
		Intervalle i1 = new Intervalle(1,5);
		Intervalle i2 = new Intervalle(5,7);
		Intervalle i3 = new Intervalle(7,9);
		Intervalle i4 = new Intervalle(9,15);

		ArrayList<Intervalle> listeIntervalles1 = new ArrayList<Intervalle>();
		listeIntervalles1.add(i1);
		listeIntervalles1.add(i3);

		ArrayList<Intervalle> listeIntervalles2 = new ArrayList<Intervalle>();
		listeIntervalles1.add(i2);
		listeIntervalles1.add(i4);

		Task t1 = new Task("Tache1", 6, listeIntervalles1);
		Task t2 = new Task("Tache2", 8, listeIntervalles2);

		ArrayList<Task> listeTaches = new ArrayList<Task>();
		listeTaches.add(t1);
		listeTaches.add(t2);

		Projet p1 = new Projet("Projet", 14, listeTaches);

		if (Model.getTasksNames(p1).get(0) != "Tache1" & Model.getTasksNames(p1).get(1) != "Tache2"){
			fail("mauvaise duree");

		}
	}
}
