package controllers;
import java.awt.geom.Rectangle2D;
import java.awt.Event;
import java.awt.Polygon ;
import java.awt.Rectangle;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import models.Batiment;
import models.Partie;
import models.Selection;
import models.Element;
import models.Ordre;
import models.Terrain;
import models.Unite;

import java.awt.Point ;

import views.View;
// CONTROLE DU SYSTEME DE SELECTION
/* Possède un attribut r : rectangle de sélection
 * rectSelect : classe Sélection modèle du rectangle de sélection pour l'affichage
 * Clic gauche et drag de la souris : création et mise à jour du rectangle
 * Mouse released : Démarrage du thread, sélections des unités contenues dans le rectangle r
 * et si pas d'unités, sélections des batiments contenues dans le rectangle r
 * 
 */
import javafx.scene.input.*;
public class ControllerSelections extends Controller implements MouseMotionListener,MouseListener,KeyListener{	
	boolean button1 = false ;
	boolean button2 = false ;
	boolean unite = false;
	boolean maj = false ;
	boolean ctrl = false;
	Element selectionClique ;
	// Rectangle de sélection
	Rectangle r;
	// Unités sélectionnées à l'instant t

	// Ordre donnée à l'instant t
	Ordre ordre ;

	Point anchor;
	public ControllerSelections(View view,Partie partie,Liste_Booleen b,UserActions actions){
		super(view,partie,b,actions);
		this.partie.getSelection().setR(new Rectangle());

		idJoueur = 0 ;

		//this.start();
	}


	public void addView(View v){
		this.view=v;
	}


	// Activer la sélection d'un élèment
	public void selectionnerUnites(){


		for(int i=0;i<partie.getJoueur(idJoueur).getBatiments().size();i++){
			if(!this.partie.getElementSelectionne().contains(partie.getJoueur(idJoueur).getBatiments(i)))
				partie.getJoueur(idJoueur).getBatiments(i).setEstSelectionne(false);
		}
		for(int i=0;i<partie.getJoueur(idJoueur).getUnites().size();i++){
			if(!this.partie.getElementSelectionne().contains(partie.getJoueur(idJoueur).getUnites(i)))
				partie.getJoueur(idJoueur).getUnites(i).setEstSelectionne(false);

			if(estDansSelection(partie.getJoueur(idJoueur).getUnites(i))){

				ajouterUniteSelection(partie.getJoueur(idJoueur).getUnites(i));
			}
		}
		

	}
	public void selectionnerBatiments(){	
		for(int i=0;i<partie.getJoueur(idJoueur).getBatiments().size();i++){
			if(!this.partie.getElementSelectionne().contains(partie.getJoueur(idJoueur).getBatiments(i)))
				partie.getJoueur(idJoueur).getBatiments(i).setEstSelectionne(false);
			if(estDansSelection(partie.getJoueur(idJoueur).getBatiments(i))){
				ajouterUniteSelection(partie.getJoueur(idJoueur).getBatiments(i));
			}
		}
	}
	// Permet de déterminer si un élèment est dans le rectangle
	public boolean estDansSelection(Element element){				
		return this.r.contains(element.getP()) ;
	}
	public void selection(){
		selectionnerUnites();
		selectionnerBatiments();		
		if(selectionClique!=null && !partie.getElementSelectionne().contains(selectionClique)){
			//note: j'ai rajouté une condition ici pour qu'un élément n'apparraisse pas deux fois dans elementSelectionne
			//      c'est ça qui faisait planter la production
			partie.getElementSelectionne().add(selectionClique);
			selectionClique.setEstSelectionne(true);
		}		
	}

	@Override
	public synchronized void mouseDragged(MouseEvent arg0) {

		if(button1){

			this.partie.getSelection().getR().setBounds( (int)Math.min(anchor.x,arg0.getX()), (int)Math.min(anchor.y,arg0.getY()),
					(int)Math.abs(arg0.getX()-anchor.x), (int)Math.abs(arg0.getY()-anchor.y));
			try {
				this.partie.getSelection().updateSelection();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}	


	}

	@Override
	public synchronized void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void mouseEntered(MouseEvent e) {
		if ( e.getButton() == MouseEvent.BUTTON1 ){
			this.selectionClique=null;
			if(!maj)
				this.partie.getElementSelectionne().clear();
		}

	}

	@Override
	public synchronized void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public synchronized void mousePressed(MouseEvent e) {
		if ( e.getButton() == MouseEvent.BUTTON1 ){
			this.selectionClique(e.getX(),e.getY());
			button1 = true ;
			anchor = e.getPoint();


			partie.getSelection().setR(new Rectangle(anchor));
		}
		else if ( e.getButton() == MouseEvent.BUTTON2 ){
			button2 = true ;
		}


	}

	private synchronized void selectionClique(int x,int y) {
		if(!maj && !liste_booleen.doitConstruire){
			this.partie.getElementSelectionne().clear();
		}
		if(!liste_booleen.doitConstruire){
			for(int i=0;i<partie.getJoueur(idJoueur).getElements().size();i++){
				if(!this.partie.getElementSelectionne().contains(partie.getJoueur(idJoueur).getElements(i)))
					partie.getJoueur(idJoueur).getElements(i).setEstSelectionne(false);

				if(estDansSelectionClique(partie.getJoueur(idJoueur).getElements(i),x,y)){

					boolean test = ajouterUniteSelection(partie.getJoueur(idJoueur).getElements(i));
					if(test){
						selectionClique = partie.getJoueur(idJoueur).getElements(i) ;
						break;
					}
				}
			}
			if(ctrl && partie.getElementSelectionne().get(0)!=null){
				Element e = partie.getElementSelectionne().get(0);
				for(int i=0;i<partie.getJoueur(idJoueur).getElements().size();i++){

					if(partie.getJoueur(idJoueur).getElements(i).getClass().equals(e.getClass()) ){
						ajouterUniteSelection(partie.getJoueur(idJoueur).getElements(i));
					}
				}

				// FIN NOUVEAU FLO

			}

		}
	}




	private synchronized boolean estDansSelectionClique(Element e,int x,int y){
		int dist = (e.getX()-x)*(e.getX()-x)+(e.getY()-y)*(e.getY()-y);
		
		return dist<(((e.getSizeX()*e.getSizeX())+(e.getSizeY()*e.getSizeY())));
	}

	@Override
	public synchronized void mouseReleased(MouseEvent e) {

		button1 = false ;
		button2 = false ;
		if(e.getButton()==1){
			if(!this.liste_booleen.doitConstruire){

				this.r = this.partie.getSelection().getR();

				this.selection();
				this.selectionClique=null;
				this.partie.getSelection().deleteSelection();
			}
			else{
				System.out.println("CONSTRUIT");
				if(!partie.getTerrain().getCases(e.getX(), e.getY()).estOccupee()){
					

					partie.debutConstruction(liste_booleen.numeroConstruction,e.getX(), e.getY());
					liste_booleen.doitConstruire= false ;
				}
			}

		}

	}

	public boolean ajouterUniteSelection(Element e){
		boolean reussiteAjout= false;
		if(partie.getElementSelectionne().size()==0){
			partie.getElementSelectionne().add(e);
			e.setEstSelectionne(true);
			reussiteAjout= true;
		}
		else{
			if(partie.getElementSelectionne().get(0) instanceof Unite && e instanceof Unite){
				partie.getElementSelectionne().add(e);
				e.setEstSelectionne(true);
				reussiteAjout= true;
			}
			else if(partie.getElementSelectionne().get(0) instanceof Batiment && e instanceof Batiment){
				partie.getElementSelectionne().add(e);
				e.setEstSelectionne(true);
				reussiteAjout= true;
			}
		}
		return reussiteAjout;
	}
	@Override
	public synchronized void keyPressed(KeyEvent arg0) {
		// MAJ
				if(arg0.getExtendedKeyCode()==KeyEvent.VK_SHIFT){
					this.liste_booleen.majPressed = true ;
					maj = true;

				}
				else if(arg0.getKeyCode()==KeyEvent.VK_CONTROL){
					System.out.println("OK CONTROL");
					ctrl = true ;
				}
		// CREATION GROUPE D'UNITES 
		if(ctrl){
			if(keyCodeToGroup(arg0.getExtendedKeyCode())!=-1){
				partie.creerGroupesUnites(keyCodeToGroup(arg0.getExtendedKeyCode()),idJoueur);
			}
			
		}
		// SELECTION GROUPE D'UNITE
		else if(!maj){
			if(keyCodeToGroup(arg0.getExtendedKeyCode())!=-1){
				partie.selectionnerGroupesUnites(keyCodeToGroup(arg0.getExtendedKeyCode()),idJoueur);
			}
			
		}
		//AJOUT 
		else if(maj){
			if(keyCodeToGroup(arg0.getExtendedKeyCode())!=-1){
				partie.ajouterGroupesUnites(keyCodeToGroup(arg0.getExtendedKeyCode()),idJoueur);
			}
			
		}

		

	}
	public int keyCodeToGroup(int keyCode){
		switch(keyCode) {
			case KeyEvent.VK_AMPERSAND :
				return 0 ;
			case 16777449 :
				return 1;
			case 152 :
				return 2;
			case 222 :
				return 3;
			case 519 :
				return 4;
			case 45 :
				return 5;
			case 16777448 :
				return 6 ;
			case 523 :
				return 7;
			case 16777415 :
				return 8;
			case 16777440 :
				return 9;
			default : 
				return -1 ;
		}
			
			
	}

	@Override
	public synchronized void keyReleased(KeyEvent arg0) {
		// MAJ
		if(arg0.getExtendedKeyCode()==KeyEvent.VK_SHIFT){
			this.liste_booleen.majPressed = false ;
			maj = false;

		}
		else if(arg0.getKeyCode()==KeyEvent.VK_CONTROL){
			ctrl = false ;
		}

	}


	@Override
	public void keyTyped(KeyEvent arg0) {


	}
}
