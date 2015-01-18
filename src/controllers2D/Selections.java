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
	// Rectangle vis à vis de l'écran
	private Rectangle rectangle;
	// Rectangle vis à vis des coordonnées absolues
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
			selectionner();
			setRectangle(new Rectangle(0,0,0,0));
			mouseReleased=false;
		}
		
	}
	// idJoueur qui controle vaut 0 pour le moment 
	public void selectionner(){
		// D'abord on reset la sélection :
		resetSelection();
		selectionnerUnites();
		if(this.partie.getElementSelectionne().size()==0){
			selectionnerBatiments();
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
				ajouterASelection(e);
			}
		}
	}
	public void selectionnerBatiments(){
		for(Element e : this.partie.getJoueur(0).getUnites()){
			if(estDansSelection(e)){
				ajouterASelection(e);
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
