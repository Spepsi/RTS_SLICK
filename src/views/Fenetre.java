package views;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Observable;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame implements java.util.Observer {
	// SIZE SCREEN
	int x;
	int y ;
	// GRAPHICS CONFIGURATION
	GraphicsEnvironment ge ;
	GraphicsDevice gd;
	GraphicsConfiguration gc ;
	JPanel conteneur;
	public Fenetre(int x , int y){
		super();
		this.x = x ;
		this.y = y ;
		conteneur = new JPanel();
		conteneur.setIgnoreRepaint(true);
		
	}
	
	public void build(View panneau,SideBar s){
		setTitle("RTS"); //On donne un titre à l'application
		setSize(x,y);
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(true); //On interdit la redimensionnement de la fenêtre
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setIgnoreRepaint(true);
		conteneur.add(panneau);
		
		conteneur.add(s);
		add(conteneur);
		pack();
		setVisible(true) ;
		ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		gd=ge.getDefaultScreenDevice();
		gc = gd.getDefaultConfiguration();
		
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		
	}
}
