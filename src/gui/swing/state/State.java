package gui.swing.state;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class State extends MouseAdapter {
	
	public void mousePressed(MouseEvent event) {}
	public void mouseDragged(MouseEvent event) {}
	public void mouseReleased(MouseEvent event) {}
	
}