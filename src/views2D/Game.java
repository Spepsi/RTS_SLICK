package views2D;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.TiledMap;

import sharedRessources.Images;
import models.*;
import controllers2D.Ordres;
import controllers2D.Selections;


public class Game extends BasicGame 
{	
	

	/// Les images
	Images images;
	
	// Toutes les informations sur la partie :
	Partie partie;
	// Le conteneur (fenêtre) du jeu
	GameContainer container;
	// Le fond de map du jeu :
	TiledMap map;
	// La caméra du jeu :
	Camera camera;
	// Le controleur de selection :
	Selections selection ;
	// Le controleur des ordres :
	Ordres ordres;
	// Render everything
	
	// Peon TEST
	
	PeonTest p;
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		this.map.render(camera.getX(),camera.getY());
		
		g.setColor(Color.red);
		g.draw(selection.getRectangle());
		for(Element e : partie.getTerrain().getTerrain()){
			try {
				Dessins.dessiner(g, e, this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

	// Do our logic 
	@Override
	public void update(GameContainer gc, int t) throws SlickException 
	{
		try {
			partie.action();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Camera :
		camera.updateCamera();
		selection.updateSelection();
		ordres.updateOrdres();
	}

	// Init our Gameobjects
	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		// Création du conteneur
		this.container = gc ;
		// Création du fond de carte :
		this.map = new TiledMap("data/maps/map.tmx");
		// Initialisation de la caméra :
		this.camera= new Camera(this.container);
		
		
		
		// Initialisation de la partie :
		this.partie = new Partie(2,800,600);
		// Initialisation du controlleur de sélection :
		this.selection = new Selections(this.container,this.camera,this.partie);
		// Initialisation du controlleur d'ordres :
		this.ordres= new Ordres(this.container,this.camera,this.partie);
		Images images =Images.get();
		// Initialisation des images :
		images.initImages();
		
		
		/// CREATION D'UNITES :
		
		
		try {
			this.partie.creerPeon(100, 100, 0);
			this.partie.creerFantassin(200, 100, 0);
			this.partie.creerCentre_Ville( 500, 300, 1,true);
			this.partie.creerPeon(480, 290, 1);
			this.partie.creerCentre_Ville( 200, 300, 0,true);
			this.partie.creerCaserne( 300, 300, 0,true);
			this.partie.creerCentre_Ville(300, 500, 1,true);
			//this.partie.creerRessource(Terrain_Ressources.OR, 460, 270, 1000);
			//this.partie.creerRessource(Terrain_Ressources.OR, 470, 270, 1000);
			//this.partie.creerRessource(Terrain_Ressources.OR, 480, 270, 1000);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public Game ()
	{
		super("Ultra Mythe RTS");
	}
}
