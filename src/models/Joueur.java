package models;

import java.util.Vector;

public class Joueur {
	
	private int numero_Joueur;

	private Vector<Element> unites;
	private Vector<Element> batiments;
	private Vector<Element> elements;
	private Vector<Vector<Element>> groupesUnites; 
	
	private Technologie tech;
	
	private Partie p;
	private int[] ressources;
	
	public Joueur(int numero,Partie p){
		unites = new Vector<Element>();
		batiments = new Vector<Element>();
		elements = new Vector<Element>();
		numero_Joueur = numero;
		tech = new Technologie();
		this.p=p;
		this.ressources = new int[3];
		this.groupesUnites = new Vector<Vector<Element>>();
		for(int i= 0; i<10; i++){
			this.groupesUnites.add(i,new Vector<Element>());
		}
	}
	//fonctions d'accès et d'écriture
	public int getNumeroJoueur(){
		return this.numero_Joueur;
	}
	public Partie getPartie(){
		return p;
	}
	public Vector<Element> getUnites(){
		return this.unites;
	}

	
	public Element getUnites(int n){
		if(n>unites.size()){
		
			return null;
		}
		return unites.get(n);
	}
	public void setUnites(Element[] e){
		this.unites = new Vector<Element>();
		for(int i = 0; i<e.length; i++){
			this.unites.add(e[i]);
		}
	}
	public Vector<Element> getBatiments(){
		return this.batiments;
	}

	public Element getBatiments(int n){
		if(n>=this.batiments.size()){
			System.out.println("erreur: batiment inexistant");
			System.out.println("joueur: " + this.numero_Joueur + " batiment recherche: " + n);
			return null;
		}
		return this.batiments.get(n);
	}
	public void setBatiment(Element[] e){
		this.batiments = new Vector<Element>();
		for(int i = 0; i<e.length; i++){
			this.batiments.add(e[i]);
		}
	}
	public Technologie getTech(){
		return this.tech;
	}
	public int getRessources(int n) {
		if(n<ressources.length)
			return ressources[n];
		System.out.println("ressources inxistantes: Joueur.java l.70");
		return 0;
	}
	public void setRessources(int n, int ressource) {
		if(n<ressources.length)
			this.ressources[n] = ressource;
	}
	public void addRessources(int n, int res){
		if(n<ressources.length)
			this.ressources[n] += res;
	}
	public Vector<Element> getElements() {
		return elements;
	}
	public Element getElements(int n){
		if(n>elements.size()){
			System.out.println("bug de merde, getters à flo d'enculé de sa race, Joueur.java l.91");
			return null;
		}
		return elements.get(n);
	}
	
	
	public Element getElementsParId(int n){
		int i=0;
		while(i<this.elements.size() && this.elements.get(i).getId()!=n){
			i++;
		}
		if(i<this.elements.size())
			return this.elements.get(i);
		return null;
	}
	public Element getUnitesParId(int n){
		int i=0;
		while(i<this.unites.size() && this.unites.get(i).getId()!=n){
			i++;
		}
		if(i<this.unites.size())
			return this.unites.get(i);
		System.out.println("unité inexistante");
		return null;
	}
	public Object getBatimentsParId(int id) {
		int i=0;
		while(i<this.batiments.size() && this.batiments.get(i).getId()!=id){
			i++;
		}
		if(i<this.batiments.size())
			return this.batiments.get(i);
		System.out.println("unité inexistante");
		return null;
	}
	public Vector<Vector<Element>> getGroupesUnites() {
		return groupesUnites;
	}
	public void setGroupesUnites(Vector<Vector<Element>> groupesUnites) {
		this.groupesUnites = groupesUnites;
	}

	public Vector<Element> getGroupesUnites(int i) {
		if(i>10)
			return null;
		return groupesUnites.get(i);
	}
	public void setGroupesUnites(Vector<Element> groupesUnites, int i) {

		System.out.print("model== création d'éléments au groupe " + i);
		if(i>10)
			return;
		this.groupesUnites.set(i, new Vector<Element>());
		for(Element e : groupesUnites)
			this.groupesUnites.get(i).add(e);
		System.out.println("  nouveau groupe: " + this.groupesUnites.get(i));
	}
	public void ajouterGroupesUnites(int i){
		System.out.print("model== ajout d'éléments au groupe " + i);
		if(i>10)
			return;
		for(Element e : this.getPartie().getElementSelectionne())
			this.groupesUnites.get(i).add(e);

		System.out.println("  nouveau groupe: " + this.groupesUnites.get(i));
	}
	public void selectionnerGroupesUnites(int i){
		if(i>10)
			return;
		for(Element e : this.getPartie().getElementSelectionne())
			e.setEstSelectionne(false);
		this.getPartie().setElementSelectionne(new Vector<Element>());
		for(Element e : this.groupesUnites.get(i)){
			this.getPartie().getElementSelectionne().add(e);
			e.setEstSelectionne(true);
		}
	}
	
}
