package models;

import org.newdawn.slick.geom.Point;

import java.util.Vector;

import batiments.Centre_Ville;

public class Batiment extends Element{

	private int temps_restant_production;
	private int id_production;
	private int defense;
	private Point pointDeRalliement;
	private int temps_construction;
	private boolean enConstruction;
	private boolean enProduction = false;
	private Vector<Production> fileDattente = new Vector<Production>();

	// fonction d'ordres
	public void action() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		if(this.getOrdre().getOrdre()==TypeOrdre.PRODUIRE){
			this.produire();
		
		}
	}
	public void produire() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		Production prod;
		if(!this.isEnProduction()){
			prod = this.fileDattente.get(0);
			this.setProduction(prod.getTemps_production(),prod.getNumeroProduction());
			this.setEnProduction(true);
			System.out.println(this.fileDattente);
		} else if(this.getTempsProduction()>=0){
			this.setTempsProduction(temps_restant_production-1);
			
		} else {
			if(this.getListeProduction().get(this.id_production-1).getElement() instanceof Unite){
				Point pSize = this.productionSize();
				Point p = this.pointLibre(pSize.getX(), pSize.getY()), rp = this.getPointDeRalliement();
				if(p!=null){
					if(rp==null)
						this.getJoueur().getPartie().creerUnites(this.getProduction(), p.getX(), p.getY(), this.getNumeroJoueur());
					else{
						this.getJoueur().getPartie().creerUnites(this.getProduction(), p.getX(), p.getY(), this.getNumeroJoueur(), rp);
					}
					this.fileDattente.remove(0);
					if(this.fileDattente.size()>0){
						//System.out.println("nouvelle production demarrée!");
						prod = this.fileDattente.get(0);
						this.setProduction(prod.getTemps_production(),prod.getNumeroProduction());
					} else { 
						this.setOrdre(new Ordre());
						this.setEnProduction(false);
						System.out.println("chou blanc travail!");
					}
				}
				
			} else {

			}
		}
	}

	// getters et setters
	public void setTempsProduction(int duree){
		this.temps_restant_production = duree;
	}
	public int getTempsProduction(){
		return this.temps_restant_production;
	}
	public void setIdProduction(int duree){
		this.id_production = duree;
	}
	public int getIdProduction(){
		return this.id_production;
	}
	public void setProduction(int temps, int numero){
		this.setTempsProduction(temps);
		this.setIdProduction(numero);
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public Point getPointDeRalliement() {
		return pointDeRalliement;
	}
	public void setPointDeRalliement(Point pointDeRalliement) {
		this.pointDeRalliement = pointDeRalliement;
	}
	public void setTempsConstruction(int n){
		this.temps_construction = n;
	}
	public int getTempsConstruction(){
		return this.temps_construction;
	}
	public boolean isEnConstruction() {
		return enConstruction;
	}
	public void setEnConstruction(boolean enConstruction) {
		this.enConstruction = enConstruction;
	}

	// fonctions auxiliaires
	public String getProduction(){
		// Renvoie la classe de l'element en cours de production sous forme de String
		return this.getListeProduction().get(this.id_production-1).getClasse();
	}
	public boolean productionEstUnite(){
		// renvoie true si la production est une unité et non une technologie
		if( this instanceof Centre_Ville){
			switch(id_production){
			case 1: return true;
			}
		}
		return false;
	}
	public Point pointLibre(float f, float g){
		// renvoie un point libre au centre d'un rectangle de taille sizeX par sizeY à proximité du bâtiment
		// PAS COMPRIS, mais si c'est encore pour les slots, à changer.
		
		float y = this.getY()+this.getSizeY()+2;
		
			if(this.getJoueur().getPartie().estCarreLibre(f,g,this))
				return new Point(f,y+g/2);
		
		return null;
	}
	public Point productionSize() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		// renvoie la taille de l'élément produit
		//		String s = this.getProduction();
		//		Element e = (Element) Class.forName(s).newInstance();
		//		return new Point(e.getSizeX(), e.getSizeY());
		return new Point(9,9);
	}
	public Vector<Production> getFileDattente() {
		return fileDattente;
	}
	public void setFileDattente(Vector<Production> fileDattente) {
		this.fileDattente = fileDattente;
	}
	public boolean isEnProduction() {
		return enProduction;
	}
	public void setEnProduction(boolean enProduction) {
		this.enProduction = enProduction;
	}



}
