package views;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import models.Production;

public class Bouton_Production {
Rectangle r;

private int numero ;
private String nom;
int x;
int y;
private Production production ;

public Bouton_Production(Production b,int n){
	this.setProduction(b);
	this.x = (n%5)*50+15;
	this.y = ((int)(n/5))*50+50 ;
	this.setNumero(n) ;
	this.setNom(b.getNom());
	this.r = new Rectangle(x,y,50,50);
	
}
	
public Rectangle getR(){
	return r;
}

public int getNumero() {
	return numero;
}

public void setNumero(int numero) {
	this.numero = numero;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public Production getProduction() {
	return production;
}

public void setProduction(Production production) {
	this.production = production;
}
}
