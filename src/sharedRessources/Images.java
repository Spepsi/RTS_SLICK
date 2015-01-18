package sharedRessources;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Images  {
	
	private static Images self= new Images();
	static HashMap<String,Image> images = new HashMap<String,Image>();
	
	public static void  initImages(){
		try {
			images.put("Peon0", new Image("data/images/peon-0.png"));
			images.put("Peon1", new Image("data/images/peon-1.png"));
			
			images.put("Fantassin0", new Image("data/images/fantassin-0.png"));
			images.put("Fantassin1", new Image("data/images/fantassin-1.png"));
			
			images.put("Centre_Ville0", new Image("data/images/centreville-0.png"));
			images.put("Centre_Ville1", new Image("data/images/centreville-1.png"));
			
			images.put("Caserne0", new Image("data/images/caserne-0.png"));
			images.put("Caserne1", new Image("data/images/caserne-1.png"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
	}
	
	public static Image getImage(String s){
		return images.get(s);
	}
	
	public static Images get(){
		return self;
	}
}
