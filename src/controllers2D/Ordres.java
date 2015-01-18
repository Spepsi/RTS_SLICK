package controllers2D;

import models.Partie;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;

import views2D.Camera;

public class Ordres {

	Partie partie ;
	GameContainer gc;
	Camera camera;
	
	
	public Ordres(GameContainer gc,Camera camera ,Partie p){
		this.gc = gc;
		this.camera = camera;
		this.partie= p;
	}
	
	public void updateOrdres(){
		Input i = gc.getInput();
		
		if(i.isMousePressed(Input.MOUSE_RIGHT_BUTTON)){		
			boolean majPressed=i.isKeyDown(Input.KEY_LSHIFT);
			partie.majOrdres(new Point(i.getMouseX()-camera.getX(),i.getMouseY()-camera.getY()),true,majPressed);
		}
		
	}
	
}
