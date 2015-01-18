package models;

public class Ordre_Attaquer extends Ordre{

	//concernant l'ordre attaquer

	/* READ ME: Ordre attaquer
	 * 
	 * attaquer est accompagné d'une position et (éventuellent) d'une identité, 
	 * qui définit de manière unique un élément A
	 * 
	 * pseudo-code:
	 * si l'élément A est à portée de coup, l'élément frappe
	 * sinon si l'élément A est visible, l'élément se dirige vers lui
	 *       sinon si l'élément est proche de la zone définie par la position
	 *                  l'élément cherche un ennemi dans la zone
	 *                  si il y a un ennemi dans la zone
	 *                       on rédéfinit l'identité de l'élément A
	 *                  sinon l'élément attend
	 *             sinon l'élément va dans la zone définie par les coordonnées 
	 */
	private int ordre_attaquer_identite;
	private float ordre_attaquer_X;
	private float ordre_attaquer_Y;
	private int ordre_attaquer_joueur;
	
	private Ordre ordre_secondaire;
	
	public Ordre_Attaquer(int identite, int j,float f, float g){
		this.ordre_attaquer_identite = identite;
		this.ordre_attaquer_X = f;
		this.ordre_attaquer_Y = g;
		this.ordre_attaquer_joueur = j;
		this.ordre_secondaire = new Ordre();
		this.setOrdre(TypeOrdre.ATTAQUER);
	}
	
	public int getOrdreAttaquerIdentite(){
		return this.ordre_attaquer_identite;
	}
	public void setOrdreAttaquerIdentite(int a){
		this.ordre_attaquer_identite = a;
	}
	public void setOrdreAttaquerXY(int x, int y){
		this.ordre_attaquer_X = x;
		this.ordre_attaquer_Y = y;
	}
	public float getOrdreAttaquerX(){
		return this.ordre_attaquer_X;
	}
	public float getOrdreAttaquerY(){
		return this.ordre_attaquer_Y;
	}
	public void setOrdreSecondaire(Ordre o){
		this.ordre_secondaire = o;
	}
	public Ordre getOrdreSecondaire(){
		return this.ordre_secondaire;
	}

	public int getOrdreAttaquerJoueur() {
		return ordre_attaquer_joueur;
	}

	public void setOrdreAttaquerJoueur(int ordre_attaquer_joueur) {
		this.ordre_attaquer_joueur = ordre_attaquer_joueur;
	}

}
