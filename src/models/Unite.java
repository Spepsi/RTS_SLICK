package models;

import org.newdawn.slick.geom.*;
import java.util.Vector;

public class Unite extends Element {

	private int vitesse;
	private int defense;
	 int temps_creation;
	private int attente;
	private Vector<Point> passage = new Vector<Point>();
	private int portee;
	private int attaque;


	// Fonctions d'accès et d'écriture
	public int getAttaque() {
		return attaque;
	}
	public void setAttaque(int attaque) {
		this.attaque = attaque;
	}
	public void setPortee(int p){
		this.portee = p;
	}
	public int getPortee(){
		return this.portee;
	}
	public void setVitesse(int v){
		this.vitesse = v;
	}
	public int getVitesse(){
		return this.vitesse;
	}
	public void setDefense(int v){
		this.defense = v;
	}
	public int getDefense(){
		return this.defense;
	}
	public void setTempsCreation(int v){
		this.temps_creation = v;
	}
	public int getTempsCreation(){
		return this.temps_creation;
	}

	// Gérer les objectifs
	public boolean estProcheObjectif(int distance){
		//renvoie 'true' si la position définie dans les variables d'ordre de déplacement ordre_deplacer_X et _Y
		//est dans un carré de demi côté 'distance'
		//autour de la position de l'élément.
		if(!this.getOrdre().getOrdre().equals(TypeOrdre.SE_DEPLACER))
			return false;
		float ordre_X = this.getOrdre().getOrdreDeplacerX();
		float ordre_Y = this.getOrdre().getOrdreDeplacerY();
		if(distance==0){
			return this.getX()==ordre_X && this.getY()==ordre_Y;
		}
		if(this.getX()-ordre_X<=distance){
			if(this.getX()-ordre_X>=-distance){
				if(this.getY()-ordre_Y<=distance){
					if(this.getY()-ordre_Y>=-distance){
						return true;
					}
				}
			}
		}
		return false;

	}
	
	
		//System.out.println("début déplacement: ... élément " + this.getId() + " du joueur " + this.getNumeroJoueur() );

	
	

//	public void deplacerNaif(Point a,Point z){
//
//		//boolean existence=false;
//		int x=(int)a.getX();
//		int y=(int)a.getY();
//
//		Point c=z;
//		if(cheminNaif(a,c)){
//			int v=this.getVitesse();
//			int xz=(int)c.getX();
//			int yz=(int)c.getY();
//			int i=xz-x; int j=yz-y;
//			while(Math.abs(xz-x)>Math.abs(j)&&v>0){
//				x=x+(int)Math.signum(i);
//				this.setXY(x,y);
//				attente=0;
//				v=v-1;
//				ajouterPassage(new Point(x,y));
//			}
//			while(Math.abs(yz-y)>Math.abs(i)&&v>0){
//				y=y+(int)Math.signum(j);
//				this.setXY(x,y);
//				v=v-1;
//				attente=0;
//				ajouterPassage(new Point(x,y));
//			}
//			while(x!=xz&&y!=yz&&v>0){
//				x=x+(int)Math.signum(i);
//				y=y+(int)Math.signum(j);
//				this.setXY(x,y);
//				v=v-1;
//				attente=0;
//				ajouterPassage(new Point(x,y));
//
//
//
//			}
//		}
//	}
//	public void deplacerNaif2(Point a,Point z,int relais, int pas){
//		System.out.println("squalala"+cheminNaif2(a,z));
//		boolean existence2=false;
//		int x=(int)a.getX();
//		int y=(int)a.getY();
//		while(!existence2&&relais>0){
//			Point c=moyenne(z,a,(double)(relais)/10);
//			System.out.println("a="+c+cheminNaif2(a,c));
//			if(cheminNaif2(a,c)){
//				System.out.println("2 marche");
//				int v=this.getVitesse();
//				int xz=(int)c.getX();
//				int yz=(int)c.getY();
//				int i=xz-x; int j=yz-y;
//				while(x!=xz||y!=yz&&v>0){
//					x=x+(int)Math.signum(i);
//					y=y+(int)Math.signum(j);
//					this.setXY(x,y);
//					v=v-1;
//					attente=0;
//					ajouterPassage(new Point(x,y));
//
//				}
//				while(y!=yz&&v>0){
//					y=y+(int)Math.signum(j);
//					this.setXY(x,y);
//					v=v-1;
//					attente=0;
//					ajouterPassage(new Point(x,y));
//				}
//				while(x!=xz&&v>0){
//					x=x+(int)Math.signum(i);
//					this.setXY(x,y);
//					attente=0;
//					v=v-1;
//					ajouterPassage(new Point(x,y));
//				}
//
//
//				existence2=true;
//				a.x=x;a.y=y;
//			}
//			relais=relais-pas;
//		}
//	}

	//			boolean allerADroite = ordre_X>this.getX();
	//			boolean allerAGauche = ordre_X<this.getX();
	//			boolean allerEnHaut = ordre_Y>this.getY() ;
	//			boolean allerEnBas = ordre_Y<this.getY() ;
	//			//int nombreDeplacements = 0;
	//			int xObjectif = this.getX();
	//			int yObjectif = this.getY();
	//			if( allerADroite){
	//				xObjectif += vitesse;
	//			}
	//			if(allerAGauche){
	//				xObjectif -= vitesse;
	//			}
	//			if(allerEnHaut){
	//				yObjectif += vitesse;
	//			}
	//			if(allerEnBas){
	//				yObjectif -= vitesse;
	//			}
	//			if(xObjectif>=0 && xObjectif<this.getTerrainXsize() && yObjectif>=0 && yObjectif<this.getTerrainYsize())
	//				if(!this.estOccupee(xObjectif, yObjectif))
	//					this.setXY(xObjectif,yObjectif);
	//				else
	//					System.out.println("erreur: déplacement invalide, case occupée, id:"+this.getId()+" x: "+xObjectif+" y:"+yObjectif);
	//			else
	//				System.out.println("erreur: déplacement invalide, id:" + this.getId() + " x: " + xObjectif+" y:"+yObjectif);
	//		}



	//trouver un chemin de a à z qui soit le plus court possible
	public Point moyenne(Point a, Point b, double v){
		Point c=new Point((int)(v*a.getX()+(1-v)*b.getX()),(int)(v*a.getY()+(1-v)*b.getY()));
		return c;
	}
	public boolean estProcheObjectif(int distance, float f, float g){
		//renvoie 'true' si la position définie dans les variables d'ordre de déplacement ordre_deplacer_X et _Y
		//est dans un carré de demi côté 'distance'
		//autour de la position de l'élément.
		if(distance==0){
			return this.getX()==f && this.getY()==g;
		}
		if(this.getX()-f<=distance){
			if(this.getX()-f>=-distance){
				if(this.getY()-g<=distance){
					if(this.getY()-g>=-distance){
						return true;
					}
				}
			}
		}
		return false;

	}
	public boolean isAccessible(float ordre_X,float ordre_Y){
		boolean b=!this.estOccupee(new Point(ordre_X,ordre_Y),this)&&(!passage.contains(new Point(ordre_X,ordre_Y)));
		return b;
	}
	public Vector<Point> getPassage() {
		return passage;
	}
	public void setPassage(Vector<Point> passage) {
		this.passage = passage;
	}
	public static Direction add(Direction a){
		Direction b = a;
		switch(a){
		case DROITE: b = Direction.HAUT_DROIT; break;
		case HAUT_DROIT: b = Direction.HAUT; break;
		case HAUT: b = Direction.HAUT_GAUCHE; break;
		case HAUT_GAUCHE: b = Direction.GAUCHE; break;
		case GAUCHE: b = Direction.BAS_GAUCHE; break;
		case BAS_GAUCHE: b = Direction.BAS; break;
		case BAS: b = Direction.BAS_DROIT; break;
		case BAS_DROIT: b = Direction.DROITE; break;
		}
		return b;
	}

	public void ajouterPassage(Point p){
		if(this.passage==null)
			this.passage = new Vector<Point>();
		if(this.passage.size()>20)
			this.passage.remove(19);
		this.passage.add(p);
	}
}

