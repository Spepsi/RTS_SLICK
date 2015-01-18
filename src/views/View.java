package views;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
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

import models.*;

public class View extends Canvas {
	Fenetre fenetre ;
	
	// BUFFER 
	BufferStrategy buffer ;
	BufferedImage bi;
	
	// GRAPHICS
	Graphics g ;
	Graphics2D g2d ;
	Color background = Color.getHSBColor(10, 255, 20);
	
	
	
	public View(int x , int y ){
		super() ;
		buildPanel(x,y);
		setIgnoreRepaint(true);
		
		//repaint();
	
		
		
	}

	public void initPanel(){
		
		this.createBufferStrategy(2);
		buffer = this.getBufferStrategy();
		bi = fenetre.gc.createCompatibleImage(fenetre.x-280,fenetre.y-50);
		//ge = GraphicsEnviron
		g = null;
		g2d =null ;
	}

	private void buildPanel(int x, int y) {
		
		this.setSize(x-280,y-50);
		//this.setBackground(Color.BLACK);
		

	}
	

	public void addController(MouseListener controller){
		this.addMouseListener( controller);
	} //addController()
	
	public void addKeyController(KeyListener controller){
		this.addKeyListener( controller);
	} //addController()

	public void addControllerOrdre(MouseMotionListener controller){
		this.addMouseMotionListener( controller);
	} //addController()


}