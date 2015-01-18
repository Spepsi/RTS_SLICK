package controllers;

import views.SideBar;
import views.View;
import models.Partie;

public class CreateControllers {
	ControllerOrdres ordres;
	ControllerElements elements;
	private ControllerSelections selections ;
	public UserActions actions;
	private ControllerProduction sideBar;

	public CreateControllers(View view,Partie partie,SideBar s,Liste_Booleen b){
		this.actions=new UserActions();
		this.ordres = new ControllerOrdres(view,partie,b,actions);
		this.elements = new ControllerElements(view,partie,b,actions);
		this.selections = new ControllerSelections(view,partie,b,actions);
		this.setSideBar(new ControllerProduction(s,view,partie,b,actions));

		view.addController(ordres);
		view.addController(elements);
		view.addController(selections);
		view.addControllerOrdre(selections);

//		view.addController(actions);
//		s.addController(actions);
//		view.addKeyController(actions);
//		s.addKeyController(actions);

		//s.addController(ordres);
		//s.addController(selections);
		s.addController(getSideBar());	



	}

	public ControllerSelections getControllerSelections(){
		return selections;
	}

	public ControllerProduction getSideBar() {
		return sideBar;
	}

	public void setSideBar(ControllerProduction sideBar) {
		this.sideBar = sideBar;
	}

}
