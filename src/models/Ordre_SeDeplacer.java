package models;

public class Ordre_SeDeplacer extends Ordre{


	private float ordre_deplacer_X;
	private float ordre_deplacer_Y;
	
	public Ordre_SeDeplacer(float f, float g){
		this.ordre_deplacer_X = f;
		this.ordre_deplacer_Y = g;
		this.setOrdre(TypeOrdre.SE_DEPLACER);
	}
	
	@Override
	public void setOrdreDeplacer(int x, int y){
		this.ordre_deplacer_X = x;
		this.ordre_deplacer_Y = y;
	}
	@Override
	public float getOrdreDeplacerX(){
		return this.ordre_deplacer_X;
	}
	@Override
	public float getOrdreDeplacerY(){
		return this.ordre_deplacer_Y;
	}

}
