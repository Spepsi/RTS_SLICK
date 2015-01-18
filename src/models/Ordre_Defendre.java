package models;

public class Ordre_Defendre extends Ordre {

	//concernant l'ordre d�fendre

	/* READ ME: Ordre d�fendre
	 * 
	 * d�fendre est accompagn� d'une position contenue dans deux variables ordre_defendre_X et _Y
	 * 
	 * pseudo-code:
	 * si l'�l�ment n'est pas proche de la position, il y va.
	 * si l'�l�ment est proche de la position: deux options
	 *    si il y a un ennemi dans la zone l'�l�ment va l'attaquer
	 *    sinon il reste � attendre au point d�fini par les variables d'ordre.
	 *     
	 */
	private float ordre_defendre_X;
	private float ordre_defendre_Y;

	public Ordre_Defendre(float x, float y){
		this.ordre_defendre_X = x;
		this.ordre_defendre_Y = y;
		this.setOrdre(TypeOrdre.DEFENDRE);
		this.setOrdreSecondaire(new Ordre());
	}

	
	@Override
	public void setOrdreDefendrePosition(int x, int y){
		this.ordre_defendre_X = x;
		this.ordre_defendre_Y = y;
	}
	@Override
	public float getOrdreDefendreX(){
		return this.ordre_defendre_X;
	}
	@Override
	public float getOrdreDefendreY(){
		return this.ordre_defendre_Y;
	}
}
