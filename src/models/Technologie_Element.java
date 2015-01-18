package models;

public class Technologie_Element {
	
	private boolean on;
	private String nom;
	private String description;

	public boolean isOn() {
		return on;
	}
	public void setOn() {
		on = true;
	}
	
	public Technologie_Element(){
		on = false;
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

	
}
