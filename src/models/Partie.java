package models;


import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import batiments.*;
import models.*;

@SuppressWarnings("unused")
public class Partie {
	private final Lock _mutex = new ReentrantLock(true);
	private Joueur[] joueurs;
	private Terrain terrain;
	private Vector<Element> elementSelectionne;
	private Vector<Element> elementSelectionneBis;
	private Rectangle selection ;
	private int definition;
	private Data[] data;
	private int compteur = 0;
	private Vector<Ressource> ressources= new Vector<Ressource>();
	private Vector<Element> peonInoccupes = new Vector<Element>();
	int indexInoccupes = 0;

	//Crée une nouvelle partie avec n joueurs et une carte de xsize par ysize.
	//C'est la méthode à lancer dans main pour démarrer une partie

	public Partie(int n, int xsize, int ysize){
		definition=10;
		joueurs = new Joueur[n];
		data = new Data[n];
		for(int i=0; i<n; i++){
			joueurs[i] = new Joueur(i,this);
			data[i] = new Data(joueurs[i]);
		}
		terrain = new Terrain(xsize,ysize, n);
		elementSelectionne = new Vector<Element>();
		selection = new Rectangle(0,0,0,0);
	}


	// fonction appellées par les controlleurs
	public void majOrdres(Point p, boolean selectionPrincipale, boolean ajout){
		Vector<Element> select;
		Ordre o;
		//System.out.println(this.getElementSelectionne());
		if(selectionPrincipale)	
			select = this.getElementSelectionne();
		else
			select = this.getElementSelectionneBis();
		if(select.size()==0)
			return;
		if(select.get(0) instanceof Batiment){
			for(Element b : select){
				System.out.println("nouveau point de ralliement: " + p.getX() + " " + p.getY());
				b.setPointDeRalliement(p);
			}
		} else {
			Element ennemi = getTerrain().getEnnemi(p, select.get(0).getJoueur().getNumeroJoueur());
			if(selectionEstPeon(selectionPrincipale)){
				Ressource r = this.getTerrain().getRessource(p);
				if(r!=null){
					for(Element e : select){
						o = new Ordre_Ramasser(r,this.getTerrain());
						if(ajout)
							e.addOrdre(o);
						else
							e.setOrdre(o);
					}
				} else if(this.getTerrain().getBatiment(p)!=null) {
					Batiment b = this.getTerrain().getBatiment(p);
					if(b.getJoueur().getNumeroJoueur()==select.get(0).getJoueur().getNumeroJoueur() && b.isEnConstruction()){
						for(Element e : select){
							o = new Ordre_Construire(p.getX(),p.getY(), "");
							if(ajout)
								e.addOrdre(o);
							else
								e.setOrdre(o);
						}
					}
				} else {
					for(Element e : select){
						o = new Ordre_SeDeplacer(p.getX(), p.getY());
						if(ajout)
							e.addOrdre(o);
						else
							e.setOrdre(o);
					}
				}
			} else if (selectionEstSoldat(selectionPrincipale) && ennemi!= null) {
				for(Element e : select){
					o = new Ordre_Attaquer(ennemi.getId(), ennemi.getNumeroJoueur(), ennemi.getX(), ennemi.getY());
					if(ajout)
						e.addOrdre(o);
					else
						e.setOrdre(o);
				}
				
			} else {
				
				for(Element e : select){
					o = new Ordre_SeDeplacer(p.getX(), p.getY());
					if(ajout)
						e.addOrdre(o);
					else
						e.setOrdre(o);
				}
			}
		}
	}
	public synchronized void action() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException{

		this.miseAjourRessource();
		for(Joueur j : joueurs){
			this.miseAjourPeonInoccupes(j);
			synchronized(j.getUnites()){
				//on lance les actions de toutes les unités puis de tous les bâtiments du joueur
				Iterator<Element> it = j.getUnites().iterator();
				while(it.hasNext()){

					//System.out.println("J1");
					Element e;

					e = it.next();

					e.action();


				}
				for(Element e : j.getBatiments()){
					e.action();
				}
			}
		}

	}
	private void miseAjourPeonInoccupes(Joueur j) {
		for(Element u : j.getUnites()){
			if(u instanceof Peon && ((Peon)u).getOrdre().getOrdre()==TypeOrdre.NE_RIEN_FAIRE){

			}
		}


	}

	// FONCTIONS DE L'IA - OBSOLETES CAR FONCTIONS PROGRAMMES DANS ELEMENT

	public void debutConstructionIA(int i, int x, int y,Peon p){
		System.out.println("taille selection: " + this.elementSelectionne.size());
		if(elementSelectionne.size()>0){
			String s = p.getListeProduction().get(i).getClasse();	
			p.setOrdre(new Ordre_Construire(x,y,s));
		}
	}


	public void debutProductionIA(Production i){
		Element e = i.getElement();
		((Batiment)e).getFileDattente().add(i);
		e.setOrdre(new Ordre_Produire());
	
	}

	// FIN FONCTIONS DE L'IA

	public void debutProduction(Production i){

		for(Element e : elementSelectionne){
			
			((Batiment)e).getFileDattente().add(i);
			e.setOrdre(new Ordre_Produire());
			
		}

	}
	public void debutConstruction(int i, int x, int y){
		
		if(elementSelectionne.size()>0){
			String s = elementSelectionne.get(0).getListeProduction().get(i).getClasse();
			
			for(Element e : elementSelectionne){
				
				e.setOrdre(new Ordre_Construire(x,y,s));
			}
		}
	}

	// fonctions auxiliaires utilisées par les controlleurs
	public Element contientEnnemi(Point p, int joueur){
		//renvoie l'élément s'il existe contenu dans un carré de taille 10 autour du point et ennemi du joueur;
	return getTerrain().getEnnemi(p, joueur);
	
	}
	public boolean selectionEstSoldat(boolean b){
		Vector<Element> select;
		if(b)
			select = this.elementSelectionne;
		else
			select = this.elementSelectionneBis;
		for(Element e : select){
			if((e instanceof Soldat))
				return true;
		}
		return false;
	}
	public boolean selectionEstPeon(boolean b){
		Vector<Element> select;
		if(b)
			select = this.elementSelectionne;
		else
			select = this.elementSelectionneBis;
		for(Element e : select){
			if(!(e instanceof Peon))
				return false;
		}
		return true;

	}
	// Légère approximation,on prend le cercle recouvrant comme collisionBox
	public boolean estOccupee(Point p,Element e){
		return getTerrain().estOccupe(new Circle(p.getX(),p.getY(),e.getCollisionBox().getBoundingCircleRadius()));
	}
	public boolean estOccupee(Point p){
		return getTerrain().estOccupe(p);
	}
	public boolean estOccupee(Shape s){
		return getTerrain().estOccupe(s);
	}

	public void selectionPeonInnoccupee(){
		for(Element e : this.getJoueur(0).getUnites()){
			if(e instanceof Peon){
				if(e.getOrdre().getOrdre()==TypeOrdre.NE_RIEN_FAIRE){
					if(!this.getPeonInoccupes().contains(e))
						this.getPeonInoccupes().add(e);
				} else {
					if(this.getPeonInoccupes().contains(e))
						this.getPeonInoccupes().remove(e);
				}
			}
		}
		if(this.getPeonInoccupes().size()==0){
			System.out.println("pas de péon innoccupe");
		} else {
			indexInoccupes++;
			if(indexInoccupes>=this.getPeonInoccupes().size())
				indexInoccupes = 0;
			for(Element e : this.elementSelectionne){
				e.setEstSelectionne(false);
			}
			this.elementSelectionne = new Vector<Element>();
			this.elementSelectionne.add(this.getPeonInoccupes().get(indexInoccupes));
			this.getPeonInoccupes().get(indexInoccupes).setEstSelectionne(true);
		}

	}

	// fonctions de création et de destruction d'éléments
	public void creerPeon(float x, float y, int n) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		// créée une unité
		
		Peon element = new Peon();
		
		
		compteur ++;
		
		element.setData(this.getData(n));
		element.initialiser(x,y,this.joueurs[n],compteur,true);
		
		if(!getTerrain().estOccupe(element.getCollisionBox())){
			
			
			this.joueurs[n].getUnites().add(element);
			this.joueurs[n].getElements().add(element);
			this.terrain.getTerrain().add(element);
		}
		
	}
	
	public void creerFantassin(float x, float y, int n) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		// créée une unité
		
		Fantassin element = new Fantassin();
		
		
		compteur ++;
		
		element.setData(this.getData(n));
		element.initialiser(x,y,this.joueurs[n],compteur,true);
	
		if(!getTerrain().estOccupe(element.getCollisionBox())){
			
			
			this.joueurs[n].getUnites().add(element);
			this.joueurs[n].getElements().add(element);
			this.terrain.getTerrain().add(element);
		}
		
	}
	
	public Centre_Ville creerCentre_Ville(float f, float g, int n,boolean dejaFini) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		// créée une unité
		
		Centre_Ville element = new Centre_Ville();
		
		
		compteur ++;
		element.setData(this.getData(n));
		element.initialiser(f,g,this.joueurs[n],compteur,dejaFini);
		if(!getTerrain().estOccupe(element.getCollisionBox())){
			
			
			this.joueurs[n].getUnites().add(element);
			this.joueurs[n].getElements().add(element);
			this.terrain.getTerrain().add(element);
		}
		
		return (Centre_Ville) element;
		
	}
	
	public Caserne creerCaserne(float f, float g, int n,boolean dejaFini) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		// créée une unité
		
		Caserne element = new Caserne();
		
		
		
		compteur ++;
		element.setData(this.getData(n));
		element.initialiser(f,g,this.joueurs[n],compteur,dejaFini);
		if(!getTerrain().estOccupe(element.getCollisionBox())){
			
			
			this.joueurs[n].getUnites().add(element);
			this.joueurs[n].getElements().add(element);
			this.terrain.getTerrain().add(element);
		}
		
		return (Caserne) element;
	}
	
	// A AJOUTER : RESSOURCES INITIALISATEURS
	
	
	
	public void creerUnites(String e, float x, float y, int n) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		// créée une unité
		
		Element element = (Element) Class.forName(e).newInstance();
		
		
		compteur ++;
		System.out.println("xdemandé :"+x);
		element.setData(this.getData(n));
		element.initialiser(x,y,this.joueurs[n],compteur,true);
		System.out.println("X: "+element.getX());
		if(!getTerrain().estOccupe(element.getCollisionBox())){
			
			
			this.joueurs[n].getUnites().add(element);
			this.joueurs[n].getElements().add(element);
			this.terrain.getTerrain().add(element);
		}
		
	}
	public void creerUnites(String e, float x, float y, int n, Point p) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		// créée une unité et lui assigne automatiquement l'ordre d'aller au point p
	
	Element element = (Element) Class.forName(e).newInstance();
		
		
		compteur ++;
		element.setData(this.getData(n));
		element.initialiser(x,y,this.joueurs[n],compteur,true);
		if(!getTerrain().estOccupe(element.getCollisionBox())){
			
			
			this.joueurs[n].getUnites().add(element);
			this.joueurs[n].getElements().add(element);
			this.terrain.getTerrain().add(element);
			this.majOrdres(p, false, false);
		}
	}
	public void enleverElement(Element element){
		
		this.terrain.getTerrain().remove(element);
		
		this.elementSelectionne.remove(element);
		Joueur j = element.getJoueur();
		element.getJoueur().getElements().remove(element);
		if(element instanceof Batiment)
			element.getJoueur().getBatiments().remove(element);
		else
			element.getJoueur().getUnites().remove(element);
	}
	public Batiment creerBatiments(String e, float f, float g, int n, boolean dejaFini) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		
		Element element = (Element) Class.forName(e).newInstance();
		
		
		compteur ++;
		element.setData(this.getData(n));
		element.initialiser(f,g,this.joueurs[n],compteur,dejaFini);
		if(!getTerrain().estOccupe(element.getCollisionBox())){
			
			
			this.joueurs[n].getUnites().add(element);
			this.joueurs[n].getElements().add(element);
			this.terrain.getTerrain().add(element);
		}
		
		return (Batiment) element;
	}
	public void creerRessource(Terrain_Ressources res, float x, float y,int quantite){
		Ressource ressource = new Ressource(res, new Point(x, y),quantite);
		
		this.getRessources().add(ressource);
		this.terrain.getTerrain().add(ressource);
	}
	public void miseAjourRessource(){
		float x;
		float y;
		
				// On va faire avec les collision box, le péon va chercher tout seul à se mettre sur une ressource dispo

		

	}
	public void enleverRessource(Ressource res){
		if(res!=null){
			float ix = res.getEmplacement().getX();
			float iy = res.getEmplacement().getY();
			getTerrain().getTerrain().remove(res);
			this.getRessources().remove(this.getRessources().indexOf(res));
			System.out.println("flou");
			for(int i=0; i< this.joueurs.length; i++){
				for(Element e : joueurs[i].getUnites()){
					if(e.getOrdre().getRessource()!=null && e.getOrdre().getOrdre()==TypeOrdre.RAMASSER && e.getOrdre().getRessource().equals(res))
						e.getOrdre().setRessource(null);
				}
			}
		}
	}


	// fonctions d'accès et d'écriture
	public Joueur getJoueur(int n){
		if(n>=this.joueurs.length){
			System.out.println("erreur: dans Partie, appel d'un joueur inexistant, joueur: " + n);
			return null;
		}
		return this.joueurs[n];
	}
	public int getNombreJoueurs(){
		return this.joueurs.length;
	}
	public Terrain getTerrain(){
		return this.terrain;
	}
	public int getTerrainXsize(){
		return this.getTerrain().getSizeX();
	}
	public int getTerrainYsize(){
		return this.getTerrain().getSizeY();
	}
	public Rectangle getSelection() {
		return selection;
	}
	public void setSelection(Rectangle selection) {
		this.selection = selection;
	}
	public int getDefinition() {
		return definition;
	}
	public void setDefinition(int definition) {
		this.definition = definition;
	}	
	public void setElementSelectionne(Vector<Element> elementSelectionne) {
		this.elementSelectionne = elementSelectionne;
	}
	public Vector<Element> getElementSelectionneBis() {
		return elementSelectionneBis;
	}
	public void setElementSelectionneBis(Vector<Element> elementSelectionneBis) {
		this.elementSelectionneBis = elementSelectionneBis;
	}
	public Data[] getData() {
		return data;
	}
	public Data getData(int n){
		if(n>this.joueurs.length){
			
			return null;
		}
		return this.data[n];

	}
	public void setData(Data[] data) {
		this.data = data;
	}
	public Vector<Element> getElementSelectionne() {
		return elementSelectionne;
	}
	public Vector<Ressource> getRessources() {
		return ressources;
	}
	public void setRessources(Vector<Ressource> ressources) {
		this.ressources = ressources;
	}

	// gestion des groupes d'unités
	public void creerGroupesUnites(int numeroGroupe, int numeroJoueur){
		this.getJoueur(numeroJoueur).setGroupesUnites(this.elementSelectionne, numeroGroupe);
	}
	public void ajouterGroupesUnites(int numeroGroupe, int numeroJoueur){
		this.getJoueur(numeroJoueur).ajouterGroupesUnites(numeroGroupe);
	}
	public void selectionnerGroupesUnites(int numeroGroupe, int numeroJoueur){
		this.getJoueur(numeroJoueur).selectionnerGroupesUnites(numeroGroupe);
	}


	public Vector<Element> getPeonInoccupes() {
		return peonInoccupes;
	}


	public void setPeonInoccupes(Vector<Element> peonInoccupes) {
		this.peonInoccupes = peonInoccupes;
	}


	public boolean estCarreLibre(float f, float g, Batiment batiment) {
		// TODO Auto-generated method stub
		return getTerrain().estOccupe(new Circle(f,g,batiment.getCollisionBox().getBoundingCircleRadius()));
	}




	// fonction de debugage

}
