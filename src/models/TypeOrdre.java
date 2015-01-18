package models;

public enum TypeOrdre {

	//ordre générique
	NE_RIEN_FAIRE,
	
	//	ordre des unités
	SE_DEPLACER,
	// 		ordre des soldats
	ATTAQUER, DEFENDRE,
	//		ordre des peons
	RAMASSER, CONSTRUIRE,
	
	//	ordre des bâtiments
	PRODUIRE, AMELIORER,
}
