package models;

import java.util.Vector;

import batiments.*;
import Unites.*;

public class Data {

	Joueur j;

	private Batiment centre_ville;
	private Batiment caserne;
	private Peon peon;
	private Fantassin fantassin;
	private Zeppelin zeppelin;
	private Legion legion;
	private Equites equites;
	private LanceurDeJavelot lanceur;
	private Baliste baliste;
	private Char chariot;
	private Gladiateur gladiateur;

	public Data(Joueur j){
		this.j = j;
		Vector<Production> liste;

		//DEFINITION DES UNITES

		//définition du Peon
		peon = new Peon();
		peon.setSizeXY(9, 9);
		peon.setDefense(5);
		peon.setChampVision(15);
		peon.setMaxPV(750);
		peon.setTempsCreation(50);
		peon.setVitesse(1);
		peon.setVitesseTravail(1);
		peon.setTempsCreation(150);
		peon.setNom("Peon");
		peon.setCollisionRadius(10);
		peon.setDescription("Coût: 50 Or \r\n Peon");

		//définition du Fantassin
		fantassin = new Fantassin();
		fantassin.setSizeXY(9, 9);
		fantassin.setDefense(5);
		fantassin.setChampVision(15);
		fantassin.setMaxPV(7500);
		fantassin.setTempsCreation(50);
		fantassin.setVitesse(1);
		fantassin.setAttaque(15);
		fantassin.setPortee(10);
		fantassin.setTempsCreation(300);
		fantassin.setNom("Fantassin");
		fantassin.setCollisionRadius(10);
		fantassin.setDescription("Coût: 50 Or \r\n Peon");

		//definition du zeppelin
		setZeppelin(new Zeppelin());
		getZeppelin().setSizeXY(9, 9);
		getZeppelin().setDefense(5);
		getZeppelin().setChampVision(15);
		getZeppelin().setMaxPV(7500);
		getZeppelin().setTempsCreation(50);
		getZeppelin().setVitesse(1);
		getZeppelin().setAttaque(15);
		getZeppelin().setPortee(5);
		getZeppelin().setTempsCreation(300);
		getZeppelin().setNom("Zeppelin");
		getZeppelin().setDescription("Coût: 50 Or \r\n Fantassin");

		//voici la légion
		setLegion(new Legion());
		getLegion().setSizeXY(9, 9);
		getLegion().setDefense(5);
		getLegion().setChampVision(15);
		getLegion().setMaxPV(7500);
		getLegion().setTempsCreation(50);
		getLegion().setVitesse(1);
		getLegion().setAttaque(15);
		getLegion().setPortee(5);
		getLegion().setTempsCreation(300);
		getLegion().setNom("Légionnaire");
		getLegion().setDescription("Coût: 50 Or \r\nFantassin");

		//voici des dadas
		setEquites(new Equites());
		getEquites().setSizeXY(9, 9);
		getEquites().setDefense(5);
		getEquites().setChampVision(15);
		getEquites().setMaxPV(7500);
		getEquites().setTempsCreation(50);
		getEquites().setVitesse(1);
		getEquites().setAttaque(15);
		getEquites().setPortee(5);
		getEquites().setTempsCreation(300);
		getEquites().setNom("Equites");
		getEquites().setDescription("Coût: 50 Or \r\nFantassin");

		//les archers
		setLanceurDeJavelot(new LanceurDeJavelot());
		getLanceurDeJavelot().setSizeXY(9, 9);
		getLanceurDeJavelot().setDefense(5);
		getLanceurDeJavelot().setChampVision(15);
		getLanceurDeJavelot().setMaxPV(7500);
		getLanceurDeJavelot().setTempsCreation(50);
		getLanceurDeJavelot().setVitesse(1);
		getLanceurDeJavelot().setAttaque(15);
		getLanceurDeJavelot().setPortee(5);
		getLanceurDeJavelot().setTempsCreation(300);
		getLanceurDeJavelot().setNom("Lanceur De Javelot");
		getLanceurDeJavelot().setDescription("Coût: 50 Or \r\nLanceur de Javelots");

		//voici des balistes
		setBaliste(new Baliste());
		getBaliste().setSizeXY(9, 9);
		getBaliste().setDefense(5);
		getBaliste().setChampVision(15);
		getBaliste().setMaxPV(7500);
		getBaliste().setTempsCreation(50);
		getBaliste().setVitesse(1);
		getBaliste().setAttaque(15);
		getBaliste().setPortee(5);
		getBaliste().setTempsCreation(300);
		getBaliste().setNom("Baliste");
		getBaliste().setDescription("Coût: 50 Or \r\n Baliste");

		//et les chars
		setChar(new Char());
		getChar().setSizeXY(9, 9);
		getChar().setDefense(5);
		getChar().setChampVision(15);
		getChar().setMaxPV(7500);
		getChar().setTempsCreation(50);
		getChar().setVitesse(1);
		getChar().setAttaque(15);
		getChar().setPortee(5);
		getChar().setTempsCreation(300);
		getChar().setNom("Chariot de guerre");
		getChar().setDescription("Coût: 50 Or \r\n Char");

		//et les gladiateurs
		setGladiateur(new Gladiateur());
		getGladiateur().setSizeXY(9, 9);
		getGladiateur().setDefense(5);
		getGladiateur().setChampVision(15);
		getGladiateur().setMaxPV(7500);
		getGladiateur().setTempsCreation(50);
		getGladiateur().setVitesse(1);
		getGladiateur().setAttaque(15);
		getGladiateur().setPortee(5);
		getGladiateur().setTempsCreation(300);
		getGladiateur().setNom("Gladiateur");
		getGladiateur().setDescription("Coût: 50 Or \r\n Gladiateur");
		//DEFINITION DES BATIMENTS

		//définition du Centre Ville
		centre_ville = new Centre_Ville();
		centre_ville.setSizeXY(19,19);
		centre_ville.setDefense(1);
		centre_ville.setMaxPV(2000);
		centre_ville.setTempsConstruction(200);
		centre_ville.setChampVision(15);
		centre_ville.setNom("Centre Ville");
		centre_ville.setCollisionRadius(32);
		centre_ville.setDescription("Coût: 50 Or \r\n Produit des Peons");

		//définition de la caserne
		caserne = new Caserne();
		caserne.setSizeXY(15,15);
		caserne.setDefense(1);
		caserne.setMaxPV(2000);
		caserne.setTempsConstruction(200);
		caserne.setChampVision(15);
		caserne.setNom("caserne");
		caserne.setCollisionRadius(32);
		caserne.setDescription("Coût: 50 Or \r\n Produit des fantassins");

		//DEFINITION DES LISTES DE PRODUCTION

		//peon
		liste = new Vector<Production>();
		this.ajouter(liste, centre_ville,"batiments.Centre_Ville",1);
		this.ajouter(liste, caserne,"batiments.Caserne",2);
		peon.setListeProduction(liste);
		//centreville
		liste = new Vector<Production>();
		this.ajouter(liste, peon, "models.Peon",1);
		centre_ville.setListeProduction(liste);
		//caserne
		liste = new Vector<Production>();
		this.ajouter(liste, fantassin, "models.Fantassin",1);
		caserne.setListeProduction(liste);
		//this.ajouter(liste, j.getTech().defense_peon_1);

	}

	public void ajouter(Vector<Production> liste, Element e, String classe, int numeroProduction){
		liste.add(new Production(e,classe,numeroProduction));
	}
	public void ajouter(Vector<Production> liste, Technologie_Element t, int numeroProduction){
		liste.add(new Production(t.getNom(), t.getDescription(),t,numeroProduction,150));
	}

	public Zeppelin getZeppelin() {
		return zeppelin;
	}

	public void setZeppelin(Zeppelin zeppelin) {
		this.zeppelin = zeppelin;
	}
	public Legion getLegion() {
		return legion;
	}

	public void setLegion(Legion legion) {
		this.legion = legion;
	}
	public LanceurDeJavelot getLanceurDeJavelot() {
		return lanceur;
	}

	public void setLanceurDeJavelot(LanceurDeJavelot lanceur) {
		this.lanceur = lanceur;
	}

	public void setEquites(Equites equites) {
		this.equites = equites;
	}
	public Equites getEquites(){
		return equites;
	}
	public void setBaliste(Baliste baliste) {
		this.baliste = baliste;
	}
	public Baliste getBaliste(){
		return baliste;
	}
	public void setChar(Char chariot) {
		this.chariot = chariot;
	}
	public Char getChar(){
		return chariot;
	}
	public void setGladiateur(Gladiateur gladiateur) {
		this.gladiateur = gladiateur;
	}
	public Gladiateur getGladiateur(){
		return gladiateur;
	}

	public Batiment getCentre_ville() {
		return centre_ville;
	}

	public void setCentre_ville(Batiment centre_ville) {
		this.centre_ville = centre_ville;
	}

	public Batiment getCaserne() {
		return caserne;
	}

	public void setCaserne(Batiment caserne) {
		this.caserne = caserne;
	}

	public Peon getPeon() {
		return peon;
	}

	public void setPeon(Peon peon) {
		this.peon = peon;
	}

	public Fantassin getFantassin() {
		return fantassin;
	}

	public void setFantassin(Fantassin fantassin) {
		this.fantassin = fantassin;
	}
}
