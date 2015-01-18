package models;

public class Ordre_Construire extends Ordre{

	private String id_batiment;
	private float position_x;
	private float position_y;
	private Batiment batiment;
	
	public Ordre_Construire(float x, float y, String id){
		this.id_batiment=id;
		this.position_x = x;
		this.position_y = y;
		this.setOrdre(TypeOrdre.CONSTRUIRE);	
	}
	
	
	public String getIdBatiment() {
		return id_batiment;
	}
	public void setIdBatiment(String id_batiment) {
		this.id_batiment = id_batiment;
	}

	public void setPosition_x(float position_x) {
		this.position_x = position_x;
	}

	public void setPosition_y(float position_y) {
		this.position_y = position_y;
	}
	public float getY(){
		return position_y;
	}
	public float getX(){
		return position_x;
	}
	public Batiment getBatiment() {
		return batiment;
	}

	public void setBatiment(Batiment batiment) {
		this.batiment = batiment;
	}

}
