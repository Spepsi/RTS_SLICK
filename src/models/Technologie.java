package models;

public class Technologie {

	//liste des technologies et am�liorations disponibles
	
	Technologie_Element tech1;
	//    exemple 1 d'am�lioration
	Technologie_Element tech2;
	//    exemple 2 d'am�lioration
	Technologie_Element vitesse_travail_peon_1;
	//    technologie qui augmente la vitesse de travail des p�ons
	Technologie_Element defense_peon_1;
	
	public Technologie(){
		tech1 = new Technologie_Element();
		tech2 = new Technologie_Element();
		vitesse_travail_peon_1 = new Technologie_Element();
		defense_peon_1 = new Technologie_Element();
		
	}
}
