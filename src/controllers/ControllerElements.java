package controllers;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import views.View;
import models.Element;
import models.Partie;
import models.Terrain;
// Gère la naissance, la mort et la destruction des éléments
public class ControllerElements extends Controller implements MouseListener {

	
	public ControllerElements(View view,Partie partie,Liste_Booleen b,UserActions actions){
		super(view,partie,b,actions);
		
	}
	
	@Override
	public void run(){
		while(true){
			for(int i=0; i<10; i++){
				
			}
		}
	}
	
	public void nouveauElement(Element e){
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
