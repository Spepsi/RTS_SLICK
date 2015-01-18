package views;



import java.io.File;
import java.io.IOException;
import org.newdawn.slick.*;
import javax.imageio.ImageIO;

import batiments.*;
import models.*;
public class Dessins {


	public static void dessiner(Graphics g,Element e,GameEngine ge ) throws IOException{
		if(e!=null){
			if(e instanceof Unite){
				if(e instanceof Peon){
					Peon(g,e,ge);
				}
				else if(e instanceof Fantassin){
					Fantassin(g,e,ge);
				}
			}
			else if(e instanceof Batiment){
				if(e instanceof Centre_Ville){
					Centre_Ville(g,e,ge);
				}
				if(e instanceof Caserne){
					Caserne(g,e,ge);
				}
			}
		}

	}

	private static void Centre_Ville(Graphics g, Element e,GameEngine ge ) throws IOException {
		Image img=ge.centre_ville[e.getNumeroJoueur()];
		//g.fillOval(e.getX()-e.getSizeX()/2,e.getY()-e.getSizeY()/2,e.getSizeX(),e.getSizeY());
		//g.setColor(Color.BLACK);
		g.drawImage(img,e.getX()-e.getSizeX()/2-2,e.getY()-e.getSizeY()/2-2);
		//g.drawString("P", e.getX()-3,e.getY()+5);
		// BARRE DE VIE 
		if(e.getMaxPV()>e.getPV()){
			g.setColor(Color.red);
			g.fillRect(e.getX()-6,e.getY()-10,15,2);
			g.setColor(Color.green);
			g.fillRect(e.getX()-6,e.getY()-10,(int)Math.floor(15*e.getPV()/e.getMaxPV()),2);
		}

		if(e.isEstSelectionne()){
			g.setColor(Color.RED);
			g.drawRect(e.getX()-e.getSizeX()/2-7,e.getY()-e.getSizeY()/2-7,e.getSizeX()+10,e.getSizeY()+10);
		}
	}
	
	
	private static void Caserne(Graphics g, Element e, GameEngine ge) throws IOException {
		BufferedImage img=ge.caserne[e.getNumeroJoueur()];
		g.drawImage(img,e.getX()-e.getSizeX()/2-2,e.getY()-e.getSizeY()/2-2,ge.v);
		if(e.getMaxPV()>e.getPV()){
			g.setColor(Color.red);
			g.fillRect(e.getX()-6,e.getY()-10,15,2);
			g.setColor(Color.green);
			g.fillRect(e.getX()-6,e.getY()-10,(int)Math.floor(15*e.getPV()/e.getMaxPV()),2);
		}

		if(e.isEstSelectionne()){
			g.setColor(Color.red);
			g.drawRect(e.getX()-e.getSizeX()/2-5,e.getY()-e.getSizeY()/2-5,e.getSizeX()+10,e.getSizeY()+10);
		}
	}

	private static void Fantassin(Graphics g, Element e, GameEngine ge) throws IOException {
		BufferedImage img=ge.fantassin[e.getNumeroJoueur()];
		g.drawImage(img,e.getX()-e.getSizeX()/2-2,e.getY()-e.getSizeY()/2-2,ge.v);
		// BARRE DE VIE 
		if(e.getMaxPV()>e.getPV()){
			g.setColor(Color.red);
			g.fillRect(e.getX()-6,e.getY()-10,15,2);
			g.setColor(Color.green);
			g.fillRect(e.getX()-6,e.getY()-10,(int)Math.floor(15*e.getPV()/e.getMaxPV()),2);
		}

		if(e.isEstSelectionne()){
			g.setColor(Color.red);
			g.drawOval(e.getX()-e.getSizeX()/2-5,e.getY()-e.getSizeY()/2-5,e.getSizeX()+10,e.getSizeY()+10);
		}

	}

	private static void Peon(Graphics g, Element e,GameEngine ge) throws IOException {
		BufferedImage img=ge.peon[e.getNumeroJoueur()];

		//g.fillOval(e.getX()-e.getSizeX()/2,e.getY()-e.getSizeY()/2,e.getSizeX(),e.getSizeY());
		//g.setColor(Color.BLACK);
		g.drawImage(img,e.getX()-e.getSizeX()/2-2,e.getY()-e.getSizeY()/2-2);
		//g.drawString("P", e.getX()-3,e.getY()+5);
		// BARRE DE VIE 
		if(e.getMaxPV()>e.getPV()){
			g.setColor(Color.red);
			g.fillRect(e.getX()-6,e.getY()-10,15,2);
			g.setColor(Color.green);
			g.fillRect(e.getX()-6,e.getY()-10,(int)Math.floor(15*e.getPV()/e.getMaxPV()),2);
		}

		if(e.isEstSelectionne()){
			g.setColor(Color.red);
			g.drawOval(e.getX()-e.getSizeX()/2-5,e.getY()-e.getSizeY()/2-5,e.getSizeX()+10,e.getSizeY()+10);
		}

	}

	public static void sideBar(Graphics g,GameEngine e){
		g.setColor(Color.green);
		g.fillRect(10,10, 12, 12);

		//g.setColor(Color.getHSBColor(40,(float) 0.7, (float) 0.8));
		g.fillRect(14,20, 4, 11);
		g.setColor(Color.white);
		g.drawString(":",33,23);
		g.drawString(Integer.toString(e.getP().getJoueur(e.idJoueur).getRessources(0)),44,23);
		// OR 
		g.setColor(Color.yellow);
		g.fillOval(75,10, 12, 12);
		g.fillOval(85,15, 12, 12);
		g.setColor(Color.white);
		g.drawString(":",105,23);
		g.drawString(Integer.toString(e.getP().getJoueur(e.idJoueur).getRessources(1)),116,23);
		// FOI
		//g.setColor(Color.getHSBColor(40,(float) 0.7, (float) 0.8));

		g.fillRect(149,16,15,5);
		g.fillRect(154,12,5,15);
		g.setColor(Color.white);
		g.drawString(":",174,23);
		g.drawString(Integer.toString(e.getP().getJoueur(e.idJoueur).getRessources(2)),184,23);
		g.fillRect(0, 40, 280, 2);


		// PRODUCTION
		e.c.getSideBar().afficherProductionSelection();
		g=e.s.afficherProduction(g,e.s.getProduction());
		
		
		// Barre D'état 
		if( e.getP().getElementSelectionne().size()>0){
			
		Element select = e.getP().getElementSelectionne().get(0);
		
		g.setColor(Color.red);
		g.fillRect(10, 350,140 , 10);
		g.setColor(Color.green);
		g.fillRect(10, 350, (int)Math.floor(140*select.getPV()/select.getMaxPV()),10);
		g.setColor(Color.white);
		g.drawString(select.getNom(), 5, 340);
		g.drawString(select.getPV()+"/"+select.getMaxPV(), 200, 360);
		g.drawString("Def : " +select.getDefense(), 55, 380);
		if(select instanceof Unite){
			g.drawString("Att : " +((Unite)select).getAttaque(),5, 380);
			g.drawString("Vit : " +((Unite)select).getVitesse(),105, 380);
		}
		if(select instanceof Batiment){
			Batiment selection = (Batiment) select;
			int iterator = 0 ;
			for(Production p : selection.getFileDattente()){
				
				g.drawRect(5+50*(iterator%5),430+50*((int)iterator/5),50, 50);
				g.drawString(p.getNom(),5+50*(iterator%5)+10 ,430+50*((int)iterator/5)+30);
				iterator ++ ;
			}
		g.setColor(Color.green);
		g.drawString("Production :",5, 410 );
		}
		
		}
		
		
	}
	
	public static void ressource(Graphics2D g, Ressource r,GameEngine ge){
		switch(r.getTypeRessource()){
		case BOIS:	g.setColor(Color.green);
		g.fillRect(r.getEmplacement().x-r.def/2,r.getEmplacement().y-r.def/2,r.def,r.def);
		break;
		case OR :	g.setColor(Color.yellow);
		g.fillRect(r.getEmplacement().x-r.def/2,r.getEmplacement().y-r.def/2,r.def,r.def);
		break;
		default:
			break;

		}
	}
}
