package views2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;


public class Camera {

	GameContainer gc;
	private int X;
	private int Y;

	
	public Camera(GameContainer gc) {
		this.gc = gc;
		
	}
	
	public void updateCamera(){
		
		Input i = gc.getInput();
		if(i.getAbsoluteMouseX()<0.005f*gc.getWidth() || i.isKeyDown(Input.KEY_LEFT) ){
			if(getX()<400)
			setX(getX() + 5) ;
		}
		if(i.getAbsoluteMouseX()>0.995f*gc.getWidth() || i.isKeyDown(Input.KEY_RIGHT)){
			if(getX()>-2600)
			setX(getX() - 5) ;
		}
		if(i.getAbsoluteMouseY()>0.995f*gc.getHeight() || i.isKeyDown(Input.KEY_DOWN)){
			if(getY()>-2900)
			setY(getY() - 5) ;
		}
		if(i.getAbsoluteMouseY()<0.005f*gc.getHeight()|| i.isKeyDown(Input.KEY_UP)){
			if(getY()<400)
			setY(getY() + 5) ;
		}
	
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}


	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}
	
}
