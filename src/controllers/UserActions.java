package controllers;

import java.awt.event.*;
import java.util.LinkedList;

public class UserActions implements MouseListener,MouseMotionListener,KeyListener {

	LinkedList<InputEvent> event ;	

	public UserActions(){
		event = new  LinkedList<InputEvent>();
	}

	public InputEvent get(){
		if(event.isEmpty()){
			return null ;
		}
		else 
			return event.removeFirst();
	}
	public InputEvent peek(){
		
		return event.getFirst();
	}
	public int size(){
		return event.size();
	}
	@Override
	public  synchronized void keyPressed(KeyEvent arg0) {


		System.out.println("KeyPressed");
	}

	@Override
	public synchronized void keyReleased(KeyEvent arg0) {
		event.add(arg0);
		System.out.println("KeyReleased");

	}

	@Override
	public synchronized void keyTyped(KeyEvent arg0) {
		event.add(arg0);
		System.out.println("KeyTyped");
	}

	@Override
	public  synchronized void mouseDragged(MouseEvent arg0) {
		event.add(arg0);
		System.out.println("MouseDragged");
	}

	@Override
	public  synchronized void mouseMoved(MouseEvent arg0) {
		event.add(arg0);
		System.out.println("MouseMoved");
	}

	@Override
	public  synchronized void mouseClicked(MouseEvent arg0) {
		event.add(arg0);
		System.out.println("MouseClicked");
	}

	@Override
	public  synchronized void mouseEntered(MouseEvent arg0) {
		event.add(arg0);
		System.out.println("MouseEntered");
	}

	@Override
	public  synchronized void mouseExited(MouseEvent arg0) {
		event.add(arg0);
		System.out.println("MouseExited");
	}

	@Override
	public  synchronized void mousePressed(MouseEvent arg0) {
		event.add(arg0);
		System.out.println("MousePressed");
	}

	@Override
	public  synchronized void mouseReleased(MouseEvent arg0) {
		event.add(arg0);
		System.out.println("MouseReleased");
	}

}
