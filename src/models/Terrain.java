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


	// Fonction qui renvoie tous les éléments  qui intersectent une autre forme
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
	public Vector<Ressource> getRessources(){
		Vector<Ressource> ressources = new Vector<Ressource>();
		for(Element e : terrain){
			if(e instanceof Ressource){
				ressources.add((Ressource) e ) ;
			}
		}
		return ressources;
	}

	public Ressource getRessource(Point p){
		Vector<Ressource> r = getRessources();
		for(Ressource re : r){
			if(re.getCollisionBox().contains(p)){
				return re;
			}
		}
		return null;

	}

	public Batiment getBatiment(Point p){
		Vector<Batiment> b = getBatiments();
		for(Batiment re : b){
			if(re.getCollisionBox().contains(p)){
				return re;
			}
		}
		return null;
	}

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
	public static float distance(Element e,Element e2){
		return ((e.getX()-e2.getX())*(e.getX()-e2.getX())+(e.getY()-e2.getY())*(e.getY()-e2.getY()));
	}
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
	public float getDistanceEnnemiPlusProche(Element e){
		return distance(e,getEnnemiPlusProche(e));
	}

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
	public float getDistancePlusProche(Element e){
		return distance(e,getPlusProche(e));
	}

	//Pour les ressources :

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
	public float getDistanceRessourcePlusProche(Element e){
		return distance(e,getRessourcePlusProche(e));
	}

	public Vector<Element> getEnnemis(int numeroJoueur){
		Vector<Element> ennemis = new Vector<Element>();
		for(Element e : terrain){
			if(e.getJoueur().getNumeroJoueur()!=numeroJoueur){
				ennemis.add( e ) ;
			}
		}
		return ennemis;
	}

	public Element getEnnemi(Point p,int numeroJoueur){
		for(Element e: getEnnemis(numeroJoueur)){
			if(e.getCollisionBox().contains(p)){
				return e;
			}
		}
		return null;
	}

}
