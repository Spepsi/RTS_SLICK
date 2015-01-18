package models;

public class Technologie {

	//liste des technologies et améliorations disponibles
	
	Technologie_Element tech1;
	//    exemple 1 d'amélioration
	Technologie_Element tech2;
	//    exemple 2 d'amélioration
	Technologie_Element vitesse_travail_peon_1;
	//    technologie qui augmente la vitesse de travail des péons
	Technologie_Element defense_peon_1;
	
	public Technologie(){
		tech1 = new Technologie_Element();
		tech2 = new Technologie_Element();
		vitesse_travail_peon_1 = new Technologie_Element();
		defense_peon_1 = new Technologie_Element();
		
	}
}
