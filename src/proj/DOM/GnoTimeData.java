package proj.DOM;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class GnoTimeData {
	
	
	public static ArrayList<Projet> getProjects(){
		ArrayList<Element> listeNomsProjets = XMLDOM.getProjectsList();
		ArrayList<Projet> listeProjets = new ArrayList<Projet>();
		for (int i = 0; i<listeNomsProjets.size(); i++){
			String nom = listeNomsProjets.get(i).getElementsByTagName("title").item(0).getTextContent();
			ArrayList<Task> tasks = GnoTimeData.setTasks(listeNomsProjets.get(i));
			int duree = 0;
			for(int j=0; j<tasks.size(); j++){
				duree += tasks.get(j).getDuree();
			}

			listeProjets.add(new Projet(nom, duree, tasks));
		}
		
		return listeProjets;
	}
	
	private static ArrayList<Task> setTasks(Element projet){
		
		ArrayList<Task> result = new ArrayList<Task>();
		ArrayList<Element> taches = XMLDOM.getTasksList(projet);
		
		for (int i = 0; i<taches.size(); i++){
			String nom = ((Element)taches.get(i).getElementsByTagName("memo").item(0)).getTextContent();
			ArrayList<Intervalle> intervalles = GnoTimeData.setIntervalles(taches.get(i));
			int duree = 0;
			for(int j=0; j<intervalles.size(); j++){
				duree += intervalles.get(j).getDuree();
			}
			result.add(new Task(nom, duree, intervalles));
		}
		
		return result;
	}
	
	private static ArrayList<Intervalle> setIntervalles(Element task){
		
		ArrayList<Intervalle> result = new ArrayList<Intervalle>();
		ArrayList<Element> intervalles = XMLDOM.getIntervallesList(task);
		
		for (int i = 0; i<intervalles.size(); i++){
			int debut = Integer.parseInt((intervalles.get(i)).getElementsByTagName("start").item(0).getTextContent());
			int fin = Integer.parseInt((intervalles.get(i)).getElementsByTagName("stop").item(0).getTextContent());
			result.add(new Intervalle(debut, fin));
		}
		
		return result;
	}

	
	public static ArrayList<Projet> getProjects(Intervalle intervalle){
		ArrayList<Element> listeNomsProjets = XMLDOM.getProjectsList();
		ArrayList<Projet> listeProjets = new ArrayList<Projet>();
		for (int i = 0; i<listeNomsProjets.size(); i++){
			String nom = listeNomsProjets.get(i).getElementsByTagName("title").item(0).getTextContent();
			ArrayList<Task> tasks = GnoTimeData.setTasks(listeNomsProjets.get(i), intervalle);
			int duree = 0;
			for(int j=0; j<tasks.size(); j++){
				duree += tasks.get(j).getDuree();
			}

			listeProjets.add(new Projet(nom, duree, tasks));
		}
		
		return listeProjets;
	}
	
	private static ArrayList<Task> setTasks(Element projet, Intervalle intervalle){
		
		ArrayList<Task> result = new ArrayList<Task>();
		ArrayList<Element> taches = XMLDOM.getTasksList(projet);
		
		for (int i = 0; i<taches.size(); i++){
			String nom = ((Element)taches.get(i).getElementsByTagName("memo").item(0)).getTextContent();
			ArrayList<Intervalle> intervalles = GnoTimeData.setIntervalles(taches.get(i), intervalle);
			int duree = 0;
			for(int j=0; j<intervalles.size(); j++){
				duree += intervalles.get(j).getDuree();
			}
			result.add(new Task(nom, duree, intervalles));
		}
		
		return result;
	}
	
	private static ArrayList<Intervalle> setIntervalles(Element task, Intervalle intervalle){
		
		ArrayList<Intervalle> result = new ArrayList<Intervalle>();
		ArrayList<Element> intervalles = XMLDOM.getIntervallesList(task);
		
		for (int i = 0; i<intervalles.size(); i++){
			int debut = Integer.parseInt((intervalles.get(i)).getElementsByTagName("start").item(0).getTextContent());
			int fin = Integer.parseInt((intervalles.get(i)).getElementsByTagName("stop").item(0).getTextContent());
			
			Intervalle inter = new Intervalle(debut, fin);
			
			result.add(inter.intersection(intervalle));
		}
		
		return result;
	}
	
}
