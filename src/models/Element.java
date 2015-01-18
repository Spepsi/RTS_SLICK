package models;


import java.util.Vector;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public abstract class Element {
	protected Terrain terrain;
	private int id;
	private Joueur joueur;
	
	private float X;
	private float Y;
	
	private float collisionRadius;
	protected Shape collisionBox;
	private Image image;
	
	private int position_X;
	private int position_Y;
	private Point p;

	private int size_X;
	private int size_Y;
	
	private int points_vie;
	private int max_points_vie;
	private int champ_vision;

	private boolean estSelectionne;

	private Vector<Ordre> ordre;
	public int def;

	private Data data;

	private String nom, description;
	private Vector<Production> liste_Production;

	// fonction d'ordre et d'ordres génériques vides
	public void action() throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException{
		switch(this.getOrdre().getOrdre()){
		case ATTAQUER: this.attaquer(false); break;
		case DEFENDRE: this.defendre(false); break;
		case SE_DEPLACER: this.deplacer(false); break;
		case RAMASSER: this.ramasser2(); break;
		case CONSTRUIRE: this.construire(); break;
		default:
			break;

		}
	}

	public void deplacer(boolean secondaire) throws InterruptedException{}
	public void attaquer(boolean secondaire) throws InterruptedException{}
	public void defendre(boolean secondaire) throws InterruptedException{}
	public void ramasser2() throws InterruptedException{}
	public void construire()throws InstantiationException, IllegalAccessException, ClassNotFoundException, InterruptedException{}

	// fonction d'écriture des variables privées, retourne faux si le placement n'a pas été possible
	public boolean setXY(float x, float y){
		boolean placementPossible = false;
		
		if(!(terrain.getElements(getCollisionBox()).size()>0)){
		
			this.X = x;
			this.Y = y;
			
			return true;
		}
		else return false ;
		
	}
	public void setOrdre(Ordre o){
		if(this.ordre!=null && this.ordre.size()>1){
			if(o.getOrdre()==TypeOrdre.NE_RIEN_FAIRE ){
				this.ordre.remove(0);
			} else{
				this.ordre = new Vector<Ordre>();
				this.ordre.add(o);
			}
		} else {
			this.ordre = new Vector<Ordre>();
			this.ordre.add(o);
		}
	}
	public void addOrdre(Ordre o){
		if(this.ordre.size()==1 && this.ordre.get(0).getOrdre()==TypeOrdre.NE_RIEN_FAIRE)
			this.ordre = new Vector<Ordre>();
		this.ordre.add(o);
	}
	public void setSizeXY(int x, int y){
		this.size_X = x;
		this.size_Y = y;
	}
	public void setMaxPV(int m){
		this.max_points_vie = m;
	}

	// fonctions d'accès des variables privées
	public float getX(){
		return this.X;
	}
	public float getY(){
		return this.Y;
	}
	public int getPV(){
		return this.points_vie;
	}
	public void setPV(int pv){
		this.points_vie = pv;
	}
	public int getMaxPV(){
		return this.max_points_vie;
	}
	public Ordre getOrdre(){
		if(this.ordre==null || this.ordre.size()==0)
			return new Ordre();
		return this.ordre.get(0);
	}
	public Joueur getJoueur(){
		return this.joueur;
	}
	public int getNumeroJoueur(){
		return this.joueur.getNumeroJoueur();
	}
	public int getId(){
		return id;
	}
	public int getChampVision(){
		return this.champ_vision;
	}
	public void setChampVision(int cv){
		this.champ_vision = cv;
	}
	public int getSizeX(){
		return this.size_X;
	}
	public int getSizeY(){
		return this.size_Y;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
	}
	public boolean isEstSelectionne() {
		return estSelectionne;
	}
	public void setEstSelectionne(boolean estSelectionne) {
		this.estSelectionne = estSelectionne;
	}
	public int getDefense() {
		return 0;
	}
	public Point getPointDeRalliement() {
		return null;
	}
	public void setPointDeRalliement(Point pointDeRalliement) {
	}
	public Vector<Production> getListeProduction() {
		return this.liste_Production;
	}
	public void setListeProduction(Vector<Production> liste_Production) {
		this.liste_Production = liste_Production;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isEnConstruction() {
		return false;
	}

	// fonctions d'accès auxiliaires qui appellent différentes caractéristiques de la carte
	public Terrain getTerrain(){
		return this.getJoueur().getPartie().getTerrain();
	}
	public int getTerrainXsize(){
		return this.terrain.getSizeX();
	}
	public int getTerrainYsize(){
		return this.terrain.getSizeY();
	}
	public boolean estOccupee(Point p){
		return getTerrain().estOccupe(new Circle(p.getX(),p.getY(),this.getCollisionBox().getBoundingCircleRadius()));
	}
	public boolean estOccupee(Point p,Element e){
		return getTerrain().estOccupe(new Circle(p.getX(),p.getY(),e.getCollisionBox().getBoundingCircleRadius()));
	}

	// Initialiser les variables privées
	public void initialiser(float x2, float y2, Joueur j, int n,boolean b){}
	public void initiate(int identite, Joueur j){
		this.id = identite;
		this.joueur = j; 
	}


	// Gérer les points de vie de l'Element
	public void retirerPointsDeVie(int pv){
		this.points_vie -= pv;
	}
	public void ajouterPointsDeVie(int pv){
		this.points_vie += pv;
	}

	// Fonctions auxiliaires de combat
	public Element existeEnnemiProche(int numeroJoueur, int distance){
		for(int i=0; i<this.getJoueur().getPartie().getNombreJoueurs(); i++){
			if(i!=numeroJoueur){
				for(Element e : this.getJoueur().getPartie().getJoueur(i).getElements()){
					if(this.distanceFrom(e)<=distance){
						return e;
					}
				}
			}
		}
		return null;
	}
	public float distanceFrom(Element e){
		return (Math.abs(e.getX()-this.getX())+Math.abs(e.getY()-this.getY()));
	}
	public boolean estProcheObjectif(int identite, int distance){
		for(int i=0; i<this.getJoueur().getPartie().getNombreJoueurs(); i++){
			if(this.getJoueur().getNumeroJoueur()!=i){
				Element e = this.getJoueur().getElementsParId(identite);
				if(e!=null && e.distanceFrom(this)<=distance)
					return true;
			}
		}
		return false;
	}

	public void setTempsCreation(int v){
	}
	public int getTempsCreation(){
		return 0;
	}

	public Shape getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Shape collisionBox) {
		this.collisionBox = collisionBox;
		this.collisionRadius=this.collisionBox.getBoundingCircleRadius();
	}

	public float getCollisionRadius() {
		return collisionRadius;
	}

	public void setCollisionRadius(float collisionRadius) {
		this.collisionRadius = collisionRadius;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}


/* Arborescence
 * 
 * Element : tout type d'élément appartenant à un joueur.
 * Unite   : sous-classe de 'Element' concernant toutes les unites mobiles
 * Soldat  : sous-classe de 'Unite' concernant toutes les unites militaires
 * Peon    : sous-classe de 'Unite' concernant les villageois
 * 
 * Batiment: sous-classe de 'Element' concernant les bâtiments
 * Centre_Ville : sous-classe de 'Batiment' permet de créer les peons
 * Caserne      : sous-classe de 'Batiment' permet de créer les fantassins
 * Archerie     : sous-classe de 'Batiment' permet de créer les archers
 * Ecurie       : sous-classe de 'Batiment' permet de créer les cavaliers
 * Maison       : sous-classe de 'Batiment' permet de monter la pop max
 * Forgeron     : sous-classe de 'Batiment' permet de faire des améliorations
 */

