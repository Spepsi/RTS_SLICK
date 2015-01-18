package models;

public class Ordre_Attaquer extends Ordre{

	//concernant l'ordre attaquer

	/* READ ME: Ordre attaquer
	 * 
	 * attaquer est accompagn� d'une position et (�ventuellent) d'une identit�, 
	 * qui d�finit de mani�re unique un �l�ment A
	 * 
	 * pseudo-code:
	 * si l'�l�ment A est � port�e de coup, l'�l�ment frappe
	 * sinon si l'�l�ment A est visible, l'�l�ment se dirige vers lui
	 *       sinon si l'�l�ment est proche de la zone d�finie par la position
	 *                  l'�l�ment cherche un ennemi dans la zone
	 *                  si il y a un ennemi dans la zone
	 *                       on r�d�finit l'identit� de l'�l�ment A
	 *                  sinon l'�l�ment attend
	 *             sinon l'�l�ment va dans la zone d�finie par les coordonn�es 
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
