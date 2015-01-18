package models;

import org.newdawn.slick.geom.*;

import sharedRessources.Images;

public class Fantassin extends Soldat{
	
	//Data contient les champs: SizeXY;Defense;ChampVision;MaxPV;TempsCreation;Vitesse;Attaque;Portée

	public Fantassin(){
	}
	
	
	public void initialiser(float x, float y, Joueur j, int identite, boolean b){
		this.initiate(identite, j);
		Fantassin f = (Fantassin) this.getData().getFantassin();
		this.terrain= this.getJoueur().getPartie().getTerrain();
		//copie de data
		this.setDefense(f.getDefense());
		this.setAttaque(f.getAttaque());
		this.setPortee(f.getPortee());
		this.setOrdre(new Ordre());
		this.setVitesse(f.getVitesse());
		this.setSizeXY(f.getSizeX(), f.getSizeY());
		this.setChampVision(f.getChampVision());
		this.setTempsCreation(f.getTempsCreation());
		this.setMaxPV(f.getMaxPV());
		this.setNom(f.getNom());
		this.setDescription(f.getDescription());
		//assignation de la position et de l'ordre
		this.setPV(this.getMaxPV());
		this.setP(new Point(x,y));
		this.setCollisionBox(new Circle(x,y,10));
		this.setXY(x,y);
		this.setOrdre(new Ordre_Defendre(x,y));
		this.setImage(Images.get().getImage("Fantassin"+this.getJoueur().getNumeroJoueur()));
		//pour montrer l'exemple d'utilisation des technologies
	}
	public int getTempsCreation(){
		return this.temps_creation;
	}
}
