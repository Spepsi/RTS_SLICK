package models;

import org.newdawn.slick.geom.*;
public class Ressource extends Element {

	private Terrain_Ressources typeRessource;
	private int ressourcesRestantes;
	private Point emplacement;
	private Partie p;
	private boolean libreHaut = true, libreBas = true, libreGauche = true, libreDroite=true;
	
	public Ressource(Terrain_Ressources t, Point point, int ta){
		this.typeRessource = t;
		this.emplacement = point;
		this.ressourcesRestantes = ta;
		this.def = 9;
	}
	public Ressource(){
		this.typeRessource = Terrain_Ressources.VIDE;
	}
	
	public boolean isLibre(){
		return this.isLibreBas()||isLibreDroite()||isLibreGauche()||isLibreHaut();
	}
	
	//fonction d'écriture et d'accès
	public Terrain_Ressources getTypeRessource() {
		return typeRessource;
	}
	public void setTypeRessource(Terrain_Ressources typeRessource) {
		this.typeRessource = typeRessource;
	}
	public int getRessourcesRestantes() {
		return ressourcesRestantes;
	}
	public void setRessourcesRestantes(int ressourcesRestantes) {
		this.ressourcesRestantes = ressourcesRestantes;
	}
	public Point getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(Point emplacement) {
		this.emplacement = emplacement;
	}
	public Partie getPartie() {
		return p;
	}
	public void setPartie(Partie p) {
		this.p = p;
	}
	public boolean isLibreHaut() {
		return libreHaut;
	}
	public void setLibreHaut(boolean libreHaut) {
		this.libreHaut = libreHaut;
	}
	public boolean isLibreBas() {
		return libreBas;
	}
	public void setLibreBas(boolean libreBas) {
		this.libreBas = libreBas;
	}
	public boolean isLibreGauche() {
		return libreGauche;
	}
	public void setLibreGauche(boolean libreGauche) {
		this.libreGauche = libreGauche;
	}
	public boolean isLibreDroite() {
		return libreDroite;
	}
	public void setLibreDroite(boolean libreDroite) {
		this.libreDroite = libreDroite;
	}
	
	
}
