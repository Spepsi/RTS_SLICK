package models;

public class Production {
	
	private Element element;
	private String classe;
	private Technologie_Element tech;
	private int numeroProduction;
	
	public Production(Element e, String classe, int i){
		this.element = e;
		this.setClasse(classe);
		this.numeroProduction=i;
	}

	public Production(String nom, String des, Technologie_Element tech, int i, int temps){
		this.tech = tech;
		this.numeroProduction=i;
	}
	public String getNom() {
		return element.getNom();
	}
	public String getDescription() {
		return element.getDescription();
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public Technologie_Element getTech() {
		return tech;
	}
	public void setTech(Technologie_Element tech) {
		this.tech = tech;
	}
	public int getNumeroProduction() {
		return numeroProduction;
	}
	public void setNumeroProduction(int numeroProduction) {
		this.numeroProduction = numeroProduction;
	}
	public int getTemps_production() {
		return element.getTempsCreation();
	}
	public Element getElement() {
		return element;
	}
	public void setElement(Element element) {
		this.element = element;
	}
	

}
