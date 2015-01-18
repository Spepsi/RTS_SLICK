package Unites;
import java.awt.Point;

import models.*;
import TypeUnite.*;

public class Baliste extends Archer_Lourd {
	public Baliste() {
		// TODO Auto-generated constructor stub
	}
	public void initialiser(int x, int y, Joueur j, int identite, boolean b){
		this.initiate(identite, j);
		Baliste z = (Baliste) this.getData().getBaliste();
		//copie de data
		this.setDefense(z.getDefense());
		this.setAttaque(z.getAttaque());
		this.setPortee(z.getPortee());
		this.setOrdre(new Ordre());
		this.setVitesse(z.getVitesse());
		this.setSizeXY(z.getSizeX(), z.getSizeY());
		this.setChampVision(z.getChampVision());
		this.setTempsCreation(z.getTempsCreation());
		this.setMaxPV(z.getMaxPV());
		//assignation de la position et de l'ordre
		this.setPV(this.getMaxPV());
		this.setP(new Point());
		this.setXY(x,y);
		this.setOrdre(new Ordre());
		
		//pour montrer l'exemple d'utilisation des technologies
	}

}
