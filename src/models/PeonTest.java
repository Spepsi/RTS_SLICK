package models;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

import sharedRessources.Images;
public class PeonTest {
	private boolean estSelectionne=false;
	private float x,y;
	private Circle collisionBox;
	int idJoueur ;
	private Image image ;
	public PeonTest(float x,float y,int idJoueur){
		this.x = x;
		this.y = y;
		idJoueur = idJoueur;
		image = Images.getImage("Peon"+idJoueur);
		this.setCollisionBox(new Circle(x,y,10));
		
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public Image getImage(){
		return image;
	}

	public boolean isEstSelectionne() {
		return estSelectionne;
	}

	public void setEstSelectionne(boolean estSelectionne) {
		this.estSelectionne = estSelectionne;
	}

	public Circle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Circle collisionBox) {
		this.collisionBox = collisionBox;
	}


	
}
