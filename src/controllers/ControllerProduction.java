package controllers;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Vector;

import views.*;
import models.*;
public class ControllerProduction extends Controller implements MouseListener, KeyListener{
SideBar s;
int production_potentielle ;
Vector<Bouton_Production> boutons;	
Panneau_Production panneau_Production;
	public ControllerProduction(SideBar s,View v,Partie p,Liste_Booleen b,UserActions actions) {
		super(v,p,b,actions);
		this.s=s;
		boutons = new Vector<Bouton_Production>();
	}
	// Fonction qui affiche la production de la sélection
	public void afficherProductionSelection(){
		Element e;
		if(partie.getElementSelectionne().size()>0){
			e = partie.getElementSelectionne().get(0);
			panneau_Production = new Panneau_Production(e.getListeProduction());
			s.setProduction(panneau_Production);
			//comment
		}
		else{
			panneau_Production = null;
			this.s.setProduction(null);
		}
	}
	
	public void setProduction(Vector<Production> p){
		// CREER LA LISTE DES RECTANGLES CORRESPONDANTE
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		produireOuConstruire(arg0.getX(),arg0.getY());
		
		
	}
	private void construire(int x, int y) {
		for(Bouton_Production b : panneau_Production.getR()){
			
			if(b.getR().contains(new Point(x,y))){
				production_potentielle = b.getNumero()+1;
				this.liste_booleen.doitConstruire = true ;
				liste_booleen.numeroConstruction = b.getNumero();
				// partie.debutConstruction(b.getNumero()+1,x,y);
				//partie.debutProduction(b.getNumero()+1);
				break;
			}
		}
		
	}
	private void produire(int x, int y) {
		
		for(Bouton_Production b : panneau_Production.getR()){
		
			if(b.getR().contains(new Point(x,y))){
			
				partie.debutProduction(b.getProduction());
				break;
			}
		}
		
	}
	private void produireOuConstruire(int x, int y){
		if(partie.getElementSelectionne().size()>0 && partie.getElementSelectionne().get(0) instanceof
				Batiment)
		produire(x,y);
		else if(partie.getElementSelectionne().size()>0 && partie.getElementSelectionne().get(0) instanceof
				Unite){
			construire(x,y);
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		
		
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_A){
			produireOuConstruire(25,55);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_Z){	
			produireOuConstruire(75,55);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_E){	
			produireOuConstruire(125,55);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_R){	
			produireOuConstruire(175,55);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_T){	
			produireOuConstruire(225,55);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_Q){	
			produireOuConstruire(25,105);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_S){	
			produireOuConstruire(75,105);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_D){	
			produireOuConstruire(125,105);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_F){	
			produireOuConstruire(175,105);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_G){	
			produireOuConstruire(225,155);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_W){	
			produireOuConstruire(25,155);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_X){	
			produireOuConstruire(75,155);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_C){	
			produireOuConstruire(125,155);
		}
		
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_V){	
			produireOuConstruire(175,155);
		}
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_B){	
			produireOuConstruire(225,155);
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		
		
	}
	
	
	
}
