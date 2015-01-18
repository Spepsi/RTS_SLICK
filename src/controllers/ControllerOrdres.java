package controllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import views.View;
import models.Element;
import models.Partie;
import models.Terrain;

//Stocke tous les élèments contenu dans la partie ainsi que le terrain
//Donne l'ordre à tous les élèments d'accomplir leurs ordres
public class ControllerOrdres extends Controller implements MouseListener {
	
	public ControllerOrdres(View view,Partie partie,Liste_Booleen b,UserActions actions){
		super(view,partie,b,actions);
	}
	
	@Override
	public void run(){
	
	}
	public void action(){
		
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
		
		if(e.getButton()==3){
			
			
			partie.majOrdres(e.getPoint(),true,liste_booleen.majPressed);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
