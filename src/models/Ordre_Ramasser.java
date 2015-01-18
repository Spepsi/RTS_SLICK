package models;

import org.newdawn.slick.geom.*;

public class Ordre_Ramasser extends Ordre {
	
	private float ordre_Ramasser_X;
	private float ordre_Ramasser_Y;
	private Ressource ressource;
	
	public Ordre_Ramasser(Ressource res, Terrain t){
		this.setOrdre(TypeOrdre.RAMASSER);
		this.ordre_Ramasser_X = res.getEmplacement().getX();
		this.ordre_Ramasser_Y = res.getEmplacement().getY();
		this.ressource = res;
	}
	
	public Point trouverCaseLibre(Ressource res, Terrain t){
		return null;
	}
	// J'ai modifié la méthode estOccupé !!!
	public boolean isAccessible(float x,float y, Terrain t){
		boolean b=x>-1 && x<t.getSizeX()&&y>0&&y<t.getSizeY()&&!t.estOccupe(x,y);
		return b;
	}
	
	public float getOrdreRamasserX() {
		return ordre_Ramasser_X;
	}
	public void setOrdreRamasserX(int ordre_Ramasser_X) {
		this.ordre_Ramasser_X = ordre_Ramasser_X;
	}
	public float getOrdreRamasserY() {
		return ordre_Ramasser_Y;
	}
	public void setOrdreRamasserY(int ordre_Ramasser_Y) {
		this.ordre_Ramasser_Y = ordre_Ramasser_Y;
	}
	public Ressource getRessource() {
		return ressource;
	}
	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}

}
