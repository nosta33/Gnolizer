package proj.DOM;

public class Intervalle {

	private int debut;
	private int fin;
	
	public Intervalle(int debut, int fin){
		this.debut = debut;
		this.fin = fin;
	}
	
	public int getDebut(){
		return debut;
	}
	
	public int getFin(){
		return fin;
	}
	
	public int getDuree(){
		return (fin-debut);
	}
	
	public Intervalle intersection(Intervalle intervalle){
		
		if(this.fin < intervalle.debut || this.debut > intervalle.fin){
			return new Intervalle(0,0);
		}

		else if (this.debut < intervalle.debut && this.fin < intervalle.fin && this.fin > intervalle.debut){
			return new Intervalle(intervalle.debut, this.fin);
		}
		
		else if (this.debut == intervalle.debut && this.fin == intervalle.fin){
			return intervalle;
		}
		
		else if (intervalle.debut < this.debut && this.debut < intervalle.fin && intervalle.fin < this.fin){
			return new Intervalle(this.debut, intervalle.fin);
		}
		
		else if (intervalle.debut == this.debut){
			return new Intervalle(this.debut, Math.min(this.fin, intervalle.fin));
		}
		
		else if (intervalle.fin == this.fin){
			return new Intervalle(Math.max(this.debut, intervalle.debut), intervalle.fin);
		}
		
		else if (this.debut < intervalle.debut && intervalle.fin < this.fin){
			return intervalle;
		}
		
		else if (intervalle.debut < this.debut && this.fin < intervalle.fin){
			return this;
		}
		
		else{
			return new Intervalle(0,0);
		}
		
	}
	

	
}
