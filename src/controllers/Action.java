package controllers;

import java.awt.event.InputEvent;
// PAS BESOIN EN FAIT
public class Action {
	InputEvent event;
	Object source;
	
	public Action(InputEvent event,Object source){
		this.event = event ;
		this.source = source ;
	}
}
