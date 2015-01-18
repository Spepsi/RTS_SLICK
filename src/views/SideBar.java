package views;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Observable;

import javax.swing.JPanel;

import org.newdawn.slick.Graphics;

import models.*;

public class SideBar extends Canvas {
	Fenetre fenetre ;
	private Panneau_Production production;
	// BUFFER 
	BufferStrategy buffer ;
	BufferedImage bi;

	// GRAPHICS
	Graphics g ;
	Graphics2D g2d ;
	Color background = Color.BLACK ;



	public SideBar(int x,int y){
		super() ;
		buildPanel(x,y);
		setIgnoreRepaint(true);

		//repaint();


	}




	public Graphics afficherProduction(Graphics g,Panneau_Production p){
		if( production !=null){
			for(Bouton_Production b : p.getR()){	
				g.setColor(Color.gray);
				g.fill(b.r);
				g.setColor(Color.black);
				g.drawString(b.getNom(), b.x+5, b.y+25);
				g.setColor(Color.white);
				g.draw(b.r);
			}
		}
		return g;
	}




	public void addKeyController(KeyListener controller){
		this.addKeyListener( controller);
	} //addController()
	public void addController(MouseListener controller){
		this.addMouseListener( controller);
	} //addController()

	public void addControllerOrdre(MouseMotionListener controller){
		this.addMouseMotionListener( controller);
	} //addController()

	public Panneau_Production getProduction() {
		return production;
	}

	public void setProduction(Panneau_Production production) {
		this.production = production;
	}


}