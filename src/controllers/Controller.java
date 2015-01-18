package controllers;

import views.View;
import models.Partie;

public  class Controller extends Thread{
	int idJoueur;
	Partie partie;
	View view;
	Liste_Booleen liste_booleen;
	UserActions actions;
	public Controller(View view,Partie partie,Liste_Booleen b ,UserActions actions){
		this.partie=partie;
		this.view = view;
		liste_booleen =b ;
		this.idJoueur=0;
		this.actions = actions ;
	}
	@Override
	public void run(){
		
	}

}
