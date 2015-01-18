package models;

public class Ordre_Defendre extends Ordre {

	//concernant l'ordre défendre

	/* READ ME: Ordre défendre
	 * 
	 * défendre est accompagné d'une position contenue dans deux variables ordre_defendre_X et _Y
	 * 
	 * pseudo-code:
	 * si l'élément n'est pas proche de la position, il y va.
	 * si l'élément est proche de la position: deux options
	 *    si il y a un ennemi dans la zone l'élément va l'attaquer
	 *    sinon il reste à attendre au point défini par les variables d'ordre.
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
