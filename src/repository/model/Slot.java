package repository.model;

import java.io.File;

import notification.Notification;
import notification.NotificationCode;
import notification.ShapeType;
import repository.node.Node;

public class Slot extends Node {
	protected double x, y, xCopy, yCopy;
	protected double w, h, wCopy, hCopy;
	protected double coef, angle = 0.0;
	protected ShapeType type;
	protected ShapeType content;	//text or graphical
	protected Node parent;
	protected File file;
	
	public Slot(String name, Node parent) {
		super(name, parent);
	}
	
	public Slot() {}
	
	@Override
	public void sendMessage(Notification notification) {
		notifyObservers(notification);
	}
	
	@Override
	public void deleteMe() {
		sendMessage(new Notification(NotificationCode.DELETE_ME, null));
	}
	

	public void coordinates(int x, int y) {
		this.x = x;
		this.y = y;
		xCopy = x;
		yCopy = y;
		wCopy = w;
		hCopy = h;
	}

	public void changePosition(double newX, double newY) {
		x = newX;
		y = newY;
		
		sendMessage(new Notification(NotificationCode.RESET, null));
	}

	public void changeOriginalPosition() {
		xCopy = x;
		yCopy = y;
	}

	public void setCoef(double coef) {
		if (w*coef < 50)
			return;
		
		this.coef = coef;
		wCopy = w*coef;
		hCopy = h*coef;

		
		sendMessage(new Notification(NotificationCode.RESET, null));
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setSize() {
		w = wCopy;
		h = hCopy;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
		sendMessage(new Notification(NotificationCode.RESET, null));
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public ShapeType getContent() {
		return content;
	}

	public void setContent(ShapeType content) {
		this.content = content;
	}

	public double getXCopy() {
		return xCopy;
	}

	public double getYCopy() {
		return yCopy;
	}
	
	public double getW() {
		return w;
	}

	public double getH() {
		return h;
	}

	public ShapeType getType() {
		return type;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public double getHCopy() {
		return hCopy;
	}
	
	public double getWCopy() {
		return wCopy;
	}
	
	public double getAngle() {
		return angle;
	}

}
