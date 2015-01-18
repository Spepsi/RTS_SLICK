package models;
import java.util.Vector;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;
public class Terrain {

	private Vector<Element> terrain=new Vector<Element>();
	private int size_X;
	private int size_Y;

	public Terrain(int xsize, int ysize, int nbjoueur){
		this.setTerrain(new Vector<Element>());
		this.size_X = xsize;
		this.size_Y = ysize;


	}

	public int getSizeX(){
		return this.size_X;
	}
	public int getSizeY(){
		return this.size_Y;
	}


	// Retourne l'élèment du point considéré
	public Element getElement(Point p){
		for( Element e : getTerrain()){
			if(e.getCollisionBox().contains(p)){
				return e;
			}
		}
		return null;
	}
	// Retourne l'élément du point (x,y) considéré
	public Element getElement(float x,float y){
		for( Element e : getTerrain()){
			if(e.getCollisionBox().contains(new Point(x,y))){
				return e;
			}
		}
		return null;
	}

	// Fonction qui renvoie tous les éléments  qui intersectent une autre forme s
	public Vector<Element> getElements(Shape s){

		Vector<Element> select = new Vector<Element>();
		for(Element e : getTerrain()){
			if(e.getCollisionBox().intersects(s)){
				select.add(e);
			}
		}

		return select;
	}

	public Vector<Element> getTerrain() {
		return terrain;
	}

	public void setTerrain(Vector<Element> terrain) {
		this.terrain = terrain;
	}

	// Retourne vrai si le point (x,y) contient un élèment
	public boolean estOccupe(float x,float y){
		Vector<Element> select = new Vector<Element>();
		for(Element e : getTerrain()){
			if(e.getCollisionBox().contains(new Point(x,y))){
				select.add(e);
				break;
			}
		}
		return select.size()>0;
	}
	// Retourne vrai si la zone représenté par s contient un élèment
	public boolean estOccupe(Shape s){
		Vector<Element> select = new Vector<Element>();
		for(Element e : getTerrain()){
			if(e.getCollisionBox().intersects(s)){
				select.add(e);
				break;
			}
		}
		return select.size()>0;
	}

	// Retourne toutes les ressources du plateau
	public Vector<Ressource> getRessources(){
		Vector<Ressource> ressources = new Vector<Ressource>();
		for(Element e : terrain){
			if(e instanceof Ressource){
				ressources.add((Ressource) e ) ;
			}
		}
		return ressources;
	}
	// Retourne la ressource sur le point p si il y en a une , retourne nul sinon
	public Ressource getRessource(Point p){
		Vector<Ressource> r = getRessources();
		for(Ressource re : r){
			if(re.getCollisionBox().contains(p)){
				return re;
			}
		}
		return null;

	}

	// Retourne le batiment au point p si il y en a un
	public Batiment getBatiment(Point p){
		Vector<Batiment> b = getBatiments();
		for(Batiment re : b){
			if(re.getCollisionBox().contains(p)){
				return re;
			}
		}
		return null;
	}
	// Retourne les batiments sur le plateau
	public Vector<Batiment> getBatiments() {
		Vector<Batiment> batiments = new Vector<Batiment>();
		for(Element e : terrain){
			if(e instanceof Batiment){
				batiments.add((Batiment) e ) ;
			}
		}
		return batiments;
	}


	// Ces fonctions devraient aider pour l'automatisation de la récolte

	// Renvoie la distance entre deux éléments
	public static float distance(Element e,Element e2){
		return ((e.getX()-e2.getX())*(e.getX()-e2.getX())+(e.getY()-e2.getY())*(e.getY()-e2.getY()));
	}

	// Renvoie l'élèment le plus proche de e ( e exclu)
	public Element getPlusProche(Element e){
		float distance = 10000 ;
		Element proche = null ;
		for(Element el : getTerrain()){
			/// Double condition pour ne pas recevoir el = e
			if(distance(e,el)<distance && distance(e,el)>e.getCollisionRadius()-0.1){
				distance = distance(e,el);
				proche = el ;
			}
		}
		return proche;
	}

	//Retourne la distance de l'ennemi le plus proche de e
	public float getDistanceEnnemiPlusProche(Element e){
		return distance(e,getEnnemiPlusProche(e));
	}

	// Retourne l'ennemi leplus proche de e
	public Element getEnnemiPlusProche(Element e){
		float distance = 10000 ;
		Element proche = null ;
		for(Element el : getEnnemis(e.getJoueur().getNumeroJoueur())){
			/// Double condition pour ne pas recevoir el = e
			if(distance(e,el)<distance && distance(e,el)>e.getCollisionRadius()-0.1){
				distance = distance(e,el);
				proche = el ;
			}
		}
		return proche;
	}


	// Retourne la distance de l'élèment le plus proche de e
	public float getDistancePlusProche(Element e){
		return distance(e,getPlusProche(e));
	}

	//Pour les ressources :
	// Retourne la ressource la plus proche de e
	public Ressource getRessourcePlusProche(Element e){
		float distance = 10000 ;
		Ressource proche = null ;
		for(Ressource el : getRessources()){
			/// Double condition pour ne pas recevoir el = e
			if(distance(e,el)<distance && distance(e,el)>e.getCollisionRadius()-0.1){
				distance = distance(e,el);
				proche = el ;
			}
		}
		return proche;
	}
	// Retourne la distance à la ressource la plus proche de e
	public float getDistanceRessourcePlusProche(Element e){
		return distance(e,getRessourcePlusProche(e));
	}
	// Retourne tous les élèments ennemis
	public Vector<Element> getEnnemis(int numeroJoueur){
		Vector<Element> ennemis = new Vector<Element>();
		for(Element e : terrain){
			if(e.getJoueur().getNumeroJoueur()!=numeroJoueur){
				ennemis.add( e ) ;
			}
		}
		return ennemis;
	}
	// Retourne un ennemi si il y en a un contenu dans le point p
	public Element getEnnemi(Point p,int numeroJoueur){
		for(Element e: getEnnemis(numeroJoueur)){
			if(e.getCollisionBox().contains(p)){
				return e;
			}
		}
		return null;
	}

}
