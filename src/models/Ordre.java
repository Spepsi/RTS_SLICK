package models;

public class Ordre {

	private TypeOrdre ordre;
	private Ordre ordre_secondaire;
	
	public Ordre(){
		this.ordre = TypeOrdre.NE_RIEN_FAIRE;
	}
	
	public void setOrdre(TypeOrdre o){
		this.ordre = o;
	}
	public TypeOrdre getOrdre(){
		return this.ordre;
	}
	public void setOrdreDeplacer(int x, int y){
	}
	public float getOrdreDeplacerX(){
		return 0;
	}
	public float getOrdreDeplacerY(){
		return 0;
	}	
	public void setOrdreSecondaire(Ordre o){
		this.ordre_secondaire = o;
	}
	public Ordre getOrdreSecondaire(){
		return this.ordre_secondaire;
	}
	public void setOrdreDefendrePosition(int x, int y){
	}
	public float getOrdreDefendreX(){
		return 0;
	}
	public float getOrdreDefendreY(){
		return 0;
	}
	public int getOrdreAttaquerIdentite(){
		return 0;
	}
	public void setOrdreAttaquerIdentite(int a){
	}
	public void setOrdreAttaquerXY(int x, int y){
	}
	public float getOrdreAttaquerX(){
		return 0;
	}
	public float getOrdreAttaquerY(){
		return 0;
	}
	public float getOrdreRamasserX() {
		return 0;
	}
	public void setOrdreRamasserX(int ordre_Ramasser_X) {}
	public float getOrdreRamasserY() {
		return 0;
	}
	public void setOrdreRamasserY(int ordre_Ramasser_Y) {}
	public Ressource getRessource() {
		return null;
	}
	public void setRessource(Ressource ressource){}


	public int getOrdreAttaquerJoueur() {
		return 0;
	}

	public void setOrdreAttaquerJoueur(int ordre_attaquer_joueur) {
	}
	public int getNumeroProduction() {
		return 0;
	}
	public void setNumeroProduction(int numeroProduction) {}

	public String getIdBatiment() {
		return "";
	}
	public void setIdBatiment(String id_batiment) {
	}
	public float getX() {
		return 0;
	}
	public void setPosition_x(float position_x) {
	}
	public float getY() {
		return 0;
	}
	public void setPosition_y(float position_y) {
	}
	public boolean isDebutConstruction() {
		return false;
	}
	public void setDebutConstruction(boolean débutConstruction) {
	}
	public Batiment getBatiment() {
		return null;
	}

	public void setBatiment(Batiment batiment) {
	}
	
	public TypeOrdre getTypeOrdre(){
		return this.ordre;
	}
}
