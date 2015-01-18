package views2D;

import java.io.IOException;
import org.newdawn.slick.*;


import batiments.*;
import models.*;
public class Dessins {


	public static void dessiner(Graphics g,Element e,Game ge ) throws IOException{
		if(e!=null){
			
			g.drawImage(e.getImage(),e.getX()+ge.camera.getX()-e.getCollisionBox().getBoundingCircleRadius()/2,e.getY()+ge.camera.getY()-e.getCollisionBox().getBoundingCircleRadius()/2);
			if(e.getMaxPV()>e.getPV()){
				g.setColor(Color.red);
				g.fillRect(e.getX()+ge.camera.getX()-6,e.getY()+ge.camera.getY()-10,15,2);
				g.setColor(Color.green);
				g.fillRect(e.getX()+ge.camera.getX()-6,e.getY()+ge.camera.getY()-10,(int)Math.floor(15*e.getPV()/e.getMaxPV()),2);
			}

			if(e.isEstSelectionne()){
				g.setColor(Color.red);
				g.drawRect(e.getX()+ge.camera.getX()-e.getSizeX()/2-7,e.getY()+ge.camera.getY()-e.getSizeY()/2-7,e.getSizeX()+10,e.getSizeY()+10);
			}
		}

	}
	
}

