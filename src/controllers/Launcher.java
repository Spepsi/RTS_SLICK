package controllers;

import java.io.IOException;

import javax.swing.JApplet;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import IA.IA;
import models.Partie;
import models.Terrain_Ressources;
import views.GameEngine;
import views.View;
import models.*;
public class Launcher{

	public static void main(String[] args)  throws InstantiationException, IllegalAccessException, ClassNotFoundException {

		
		GameEngine g;
		
		try {
			g = new GameEngine(1080,720);
			
			g.getP().creerUnites("models.Peon", 10, 10, 0);

			
		
			g.getP().creerBatiments("batiments.Centre_Ville", 500, 300, 1,true);
			g.getP().creerUnites("models.Peon", 480, 290, 1);
			g.getP().creerBatiments("batiments.Centre_Ville", 200, 300, 0,true);
			g.getP().creerBatiments("batiments.Centre_Ville", 300, 500, 1,true);
			g.getP().creerRessource(Terrain_Ressources.OR, 460, 270, 1000);
			g.getP().creerRessource(Terrain_Ressources.OR, 470, 270, 1000);
			g.getP().creerRessource(Terrain_Ressources.OR, 480, 270, 1000);
			
			g.getP().creerRessource(Terrain_Ressources.BOIS,300, 540, 1000);
			g.getP().creerRessource(Terrain_Ressources.BOIS,310, 540, 1000);
			g.getP().creerRessource(Terrain_Ressources.BOIS, 320, 540, 1000);
			IA ia = new IA(1,g.getP());
			g.addIA(ia);
			for(int i = 10; i<100; i++){
				for(int j = 300; j<500; j++){
					g.getP().creerRessource(Terrain_Ressources.OR, i, j, 1000);
				}
			}

			//		for(int i = 10; i<50 ; i+=10){
			//			for(int j = 10 ; j<50 ; j+=10){
			//				g.getP().creerUnites("models.Fantassin", i, j, 0);
			//			}
			//		}
			//		
			//		for(int i = 200; i<250 ; i+=10){
			//			for(int j = 200 ; j<250 ; j+=10){
			//				g.getP().creerUnites("models.Fantassin", i, j, 1);
			//			}
			//		}

			//		Terrain t = g.getP().getTerrain();
			//		for(int i = 0; i<100;i++){
			//			for(int j = 0; j<100;j++){
			//				if(g.getP().estOccupee(i, j))
			//					System.out.print("X");
			//				else
			//					System.out.print("O");
			//			}
			//			System.out.println();
			//		}
			//		System.out.println("mythe");


			g.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

}
