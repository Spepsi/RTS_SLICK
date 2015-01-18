package controllers2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import models.*;
import views2D.Camera;
import views2D.Game;

public class Selections {
	// Mouse released:
	boolean mouseReleased=false;
	Partie partie ;
	GameContainer gc;
	// Rectangle vis � vis de l'�cran
	private Rectangle rectangle;
	// Rectangle vis � vis des coordonn�es absolues
	private Rectangle rectangleAbs;
	float recX,recY;
	Camera camera;
	boolean mousePressed= false ;
	public Selections(GameContainer gc,Camera camera,Partie p){
		this.gc = gc;
		this.camera = camera;
		this.setRectangle(new Rectangle(0,0,0,0));
		this.partie= p;
	}


	public void updateSelection(){
		Input i = gc.getInput();
		if(i.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
			recX = i.getAbsoluteMouseX();
			recY = i.getAbsoluteMouseY();
			setRectangle(new Rectangle(recX,recY,0,0));
			mouseReleased = true;
		}
		else if(i.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)){
			getRectangle().setBounds( (int)Math.min(recX,i.getAbsoluteMouseX()), (int)Math.min(recY, i.getAbsoluteMouseY()),
					(int)Math.abs(i.getAbsoluteMouseX()-recX), (int)Math.abs(i.getAbsoluteMouseY()-recY));
			setRectangle(getRectangle());

		}
		else if(mouseReleased){
			if(rectangle.getHeight()<1 || rectangle.getWidth()<1){
				rectangle.setWidth(1);
				rectangle.setHeight(1);
				setRectangle(rectangle);
			}
			selectionner(i);
			setRectangle(new Rectangle(0,0,0,0));
			mouseReleased=false;
		}

	}
	// idJoueur qui controle vaut 0 pour le moment 
	public void selectionner(Input i){
		// D'abord on reset la s�lection :
		boolean shift = i.isKeyDown(Input.KEY_LSHIFT);
		if(!shift){
			resetSelection();


			//On s�lectionne d'abord les unit�s
			selectionnerUnites();
			// SI on a selectionne aucune unit� alors on s�lectionne les batiments contenus dans le rectangle ( ou alors touche
			// shift appuy�e )
			if( this.partie.getElementSelectionne().size()==0 ){
				selectionnerBatiments();
			}

		}
		// Si on appuie sur shift on veut ajouter les unit�s de m�me type que la s�lection actuelle (Unit� ou batiment)
		else{
			// Si la s�lection est nulle on fait comme avant
			if(this.partie.getElementSelectionne().size()==0){
				selectionnerUnites();
				if( this.partie.getElementSelectionne().size()==0 ){
					selectionnerBatiments();
				}
			}
			// Sinon selon la nature du premier �l�ment de la selection on s�lectionne les Unit�s ou les Batiments
			else{
				Element e = this.partie.getElementSelectionne().get(0);
				if(e instanceof Unite){
					selectionnerUnites();
				}
				else if (e instanceof Batiment){
					selectionnerBatiments();
				}
			}

		}
	}
	public void resetSelection(){
		for(Element e : this.partie.getJoueur(0).getElements()){
			enleverASelection(e);
		}
	}
	public void selectionnerUnites(){
		for(Element e : this.partie.getJoueur(0).getUnites()){
			if(estDansSelection(e)){
				ajouterASelection((Unite) e);
			}
		}
	}
	public void selectionnerBatiments(){
		for(Element e : this.partie.getJoueur(0).getBatiments()){
			if(estDansSelection(e)){
				ajouterASelection((Batiment) e);
			}
		}
	}
	public void ajouterASelection(Element e){
		this.partie.getElementSelectionne().add(e);
		e.setEstSelectionne(true);
	}
	public void enleverASelection(Element e){
		e.setEstSelectionne(false);
		this.partie.getElementSelectionne().remove(e);
	}
	public  boolean estDansSelection(Element e){		
		return e.getCollisionBox().intersects(rectangleAbs);

	}

	// TEST
	public  boolean estDansSelection(PeonTest e){		
		return e.getCollisionBox().intersects(rectangleAbs);

	}
	public Rectangle getRectangle() {
		return rectangle;
	}
	public Rectangle getRectangleAbs() {
		return rectangleAbs;
	}


	public void setRectangle(Rectangle rectangle) {
		this.rectangle = rectangle;
		this.rectangleAbs=(new Rectangle(rectangle.getX()-camera.getX(),rectangle.getY()-camera.getY(),rectangle.getWidth(),
				rectangle.getHeight()));
	}
	public void setRectangleAbs(Rectangle rectangle) {
		this.rectangleAbs = rectangle;
		this.rectangle=new Rectangle(rectangle.getX()-camera.getX(),rectangle.getY()-camera.getY(),rectangle.getWidth(),
				rectangle.getHeight());
	}
}
