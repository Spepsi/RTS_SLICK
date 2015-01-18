package views;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import models.Production;


public class Panneau_Production    {
	private Vector<Bouton_Production> r;


	public Panneau_Production(Vector<Production> liste){
		setR(new Vector<Bouton_Production>());
		int iterator = 0 ;
		if(liste!=null){
			for(Production b : liste ){
				getR().add(new Bouton_Production(b,iterator));

				iterator++;
			}


			dessinerChoix();

		}
	}

	public void dessinerChoix(){


	}

	public Vector<Bouton_Production> getR() {
		return r;
	}

	public void setR(Vector<Bouton_Production> r) {
		this.r = r;
	}



}
