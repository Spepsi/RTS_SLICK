package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;





import javax.imageio.ImageIO;

import IA.IA;
import controllers.*;	
import models.*;
public class GameEngine extends Thread   {

	// IMAGES UNITES 
	BufferedImage[] peon =  new BufferedImage[4];
	BufferedImage[] fantassin = new BufferedImage[4];
	BufferedImage[] centre_ville= new BufferedImage[4];
	BufferedImage[] caserne=new BufferedImage[4]; ;
	BufferedImage herbe ;
	BufferedImage or ;

	IA ia;
	int sizeScreenX ;
	int sizeScreenY ;
	boolean running ;
	CreateControllers c;
	private Partie p ;
	View v ;
	SideBar s;
	int idJoueur;
	Liste_Booleen liste_booleen;

	public GameEngine(int x,int y) throws IOException{
		liste_booleen = new Liste_Booleen();
		sizeScreenX = x ;
		sizeScreenY =y;
		Fenetre f = new Fenetre(x,y);
		setP(new Partie(2,x-280,y));
		v = new View(x,y-50);
		s = new SideBar(280,y-50);
		c = new CreateControllers(v,getP(),s,liste_booleen);
		idJoueur = 0 ;
		v.fenetre=f;
		s.fenetre=f;

		f.build(v,s);
		v.addKeyController(c.getControllerSelections());
		v.initPanel();
		s.initSideBar();
		s.addKeyController(c.getSideBar());
		v.addKeyListener(c.getSideBar());
		v.requestFocus();

		for(int i=0;i<2;i++){
			peon[i] = ImageIO.read(new File("data/images/peon-"+i+".png"));
			fantassin[i] = ImageIO.read(new File("data/images/fantassin-"+i+".png"));
			caserne[i] = ImageIO.read(new File("data/images/caserne-"+i+".png"));
			centre_ville[i]=ImageIO.read(new File("data/images/centreville-"+i+".png"));
		}
		herbe = ImageIO.read(new File("data/images/grass.jpg"));
		or = ImageIO.read(new File("data/images/or.gif"));
		

	}
	@Override
	public synchronized void run(){
		running = true;

		while(running){
		
			v.requestFocus();
			// REINITIATE VIEW 
			v.g2d =v.bi.createGraphics();
			v.g2d.setColor(v.background);
			v.g2d.fillRect(0,0,this.sizeScreenX-280,this.sizeScreenY);
			// DESSIN FOND
			for(int i=0;i<4;i++){
				for(int j=0;j<3;j++){
					v.g2d.drawImage(herbe,herbe.getWidth()*i,herbe.getHeight()*j,null);
				}
			}
		
		
		// REINITIATE SIDEBAR
		s.g2d =s.bi.createGraphics();
		s.g2d.setColor(s.background);
		s.g2d.fillRect(0,0,280,this.sizeScreenY);

		try {
			gameUpdate();
		} catch (InterruptedException | InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try{
			try {
				gameRender();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			paintScreen();

		}finally{
			if(v.g2d!=null){
				v.g2d.dispose();
			}
			if(s.g2d!=null){
				s.g2d.dispose();
			}
		}


		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	System.exit(0);



}

private void paintScreen() {
	if(!s.buffer.contentsLost()){
		s.buffer.show();
	}

	if(!v.buffer.contentsLost()){
		v.buffer.show();
	}



}

private void gameRender() throws IOException {
	// SIDEBAR

	Dessins.sideBar(s.g2d,this);
	// MAINSCREEN
	redessinerRectangle(v.g2d);
	redessinerTerrain(v.g2d);


	v.g =  v.buffer.getDrawGraphics();
	v.g.drawImage(v.bi,0,0,null);

	s.g =  s.buffer.getDrawGraphics();
	s.g.drawImage(s.bi,0,0,null);
}


private void redessinerRectangle(Graphics2D g) {

	if(getP().getSelection().getR()!=null){
		//System.out.println(p.getSelection().getR());
		g.setColor(Color.GREEN);
		g.drawRect(getP().getSelection().getR().x, getP().getSelection().getR().y, getP().getSelection().getR().width, getP().getSelection().getR().height);
	}

}
private void gameUpdate() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	getP().action();
	ia.play();

}


public void addIA(IA ia){
	this.ia= ia;
}


public void redessinerTerrain(Graphics2D g) throws IOException{
	for(Ressource r : p.getRessources()){

		Dessins.ressource(g, r,this);
	}



	// On parcourt tout le terrain
	for(int i=0;i<getP().getNombreJoueurs();i++){
		for(Element e : getP().getJoueur(i).getElements()){
			
			Dessins.dessiner(g, e,this);

		}
	}
}




//	public void dessinUnites(Graphics2D g,Element e,int i, int j){
//		if(e!=null){
//
//			//System.out.println(p.getTerrain().getCases(i, j).getElement().getOrdre().getOrdre());
//			if(e instanceof Peon){
//				if(e.getNumeroJoueur()==0)
//					g.setColor(Color.cyan);
//				if(e.getNumeroJoueur()==1)
//					g.setColor(Color.red);
//				g.fillOval(e.getX()-e.getSizeX()/2,e.getY()-e.getSizeY()/2,e.getSizeX(),e.getSizeY());
//				g.setColor(Color.BLACK);
//				g.drawString("P", e.getX()-3,e.getY()+5);
//				// BARRE DE VIE 
//				if(e.getMaxPV()>e.getPV()){
//					g.setColor(Color.RED);
//					g.fillRect(e.getX()-6,e.getY()-10,15,2);
//					g.setColor(Color.GREEN);
//					g.fillRect(e.getX()-6,e.getY()-10,(int)Math.floor(15*e.getPV()/e.getMaxPV()),2);
//				}
//
//				if(e.isEstSelectionne()){
//					g.setColor(Color.RED);
//					g.drawOval(e.getX()-e.getSizeX()/2-5,e.getY()-e.getSizeY()/2-5,e.getSizeX()+10,e.getSizeY()+10);
//				}
//			}
//			if(e instanceof Fantassin){
//				if(e.getNumeroJoueur()==0)
//					g.setColor(Color.cyan);
//				if(e.getNumeroJoueur()==1)
//					g.setColor(Color.red);
//				g.fillOval(e.getX()-e.getSizeX()/2,e.getY()-e.getSizeY()/2,e.getSizeX(),e.getSizeY());
//				g.setColor(Color.BLACK);
//				g.drawString("X", e.getX()-3,e.getY()+5);
//				// BARRE DE VIE 
//				if(e.getMaxPV()>e.getPV()){
//					g.setColor(Color.RED);
//					g.fillRect(e.getX()-6,e.getY()-10,15,2);
//					g.setColor(Color.GREEN);
//					g.fillRect(e.getX()-6,e.getY()-10,(int)Math.floor(15*e.getPV()/e.getMaxPV()),2);
//				}
//
//				if(e.isEstSelectionne()){
//					g.setColor(Color.RED);
//					g.drawOval(e.getX()-e.getSizeX()/2-5,e.getY()-e.getSizeY()/2-5,e.getSizeX()+10,e.getSizeY()+10);
//				}
//			}
//
//		}
//	}
public void dessinTerrain(Graphics2D g,Ressource r,int i, int j){
	switch(r.getTypeRessource()){
	case BOIS:	g.setColor(Color.GREEN);
	g.fillRect(i,j,1,1);
	break;
	case OR :	g.setColor(Color.YELLOW);
	g.fillRect(i,j,1,1);
	break;
	default:
		break;

	}

}
public Partie getP() {
	return p;
}
public void setP(Partie p) {
	this.p = p;
}
}
