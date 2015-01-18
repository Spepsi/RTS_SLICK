package models;

public class Cases {

	private Element element;
	private boolean[] decouverteParJoueur;
	private boolean[] visibleParJoueur;
	private Ressource ressource;
	private boolean estNaturellementAccessible;
	
	
	public Cases(int nbJoueur){
		this.element = null;
		this.decouverteParJoueur = new boolean[nbJoueur];
		this.visibleParJoueur = new boolean[nbJoueur];
		this.ressource = new Ressource();
		this.estNaturellementAccessible = true;
	}
	
	public Element getElement(){
		return this.element;
	}
	public void setElement(Element e){
		this.element = e;
	}
	public boolean estDecouverteParJoueur(int numeroJoueur){
		return decouverteParJoueur[numeroJoueur];
	}
	public boolean estVisibleParJoueur(int numeroJoueur){
		return visibleParJoueur[numeroJoueur];
	}
	public void setDecouverteParJoueur(int numeroJoueur, boolean b){
		this.decouverteParJoueur[numeroJoueur] = b;
	}
	public void setVisibleParJoueur(int numeroJoueur, boolean b){
		this.visibleParJoueur[numeroJoueur] = b;
	}
	public boolean estOccupee(){
		return this.element != null;
	}
	public Ressource getRessource(){
		return this.ressource;
	}
	public void setRessource(Ressource res){
		this.ressource = res;
	}
	public boolean[] getDecouverteParJoueur() {
		return decouverteParJoueur;
	}
	public void setDecouverteParJoueur(boolean[] decouverteParJoueur) {
		this.decouverteParJoueur = decouverteParJoueur;
	}
	public boolean isEstNaturellementAccessible() {
		return estNaturellementAccessible;
	}
	public void setEstNaturellementAccessible(boolean estNaturellementAccessible) {
		this.estNaturellementAccessible = estNaturellementAccessible;
	}
}
