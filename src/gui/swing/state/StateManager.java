package gui.swing.state;

import gui.swing.node.view.PageView;
import gui.swing.state.states.*;

public class StateManager {
	private State currentState;
	private final PageView mediator;
	
	// shapes
	private RectangleState rectangleState;
	private TriangleState triangleState;
	private CircleState circleState;
	
	// operations with shapes
	private SelectState selectState;
	private MoveState moveState;
	private ResizeState resizeState;
	private RotateState rotateState;
	private DeleteState deleteState;
	
	public StateManager(PageView mediator) {
		this.mediator = mediator;
		loadAll();
	}
	
	private void loadAll() {
		rectangleState = new RectangleState(mediator);
		triangleState = new TriangleState(mediator);
		circleState = new CircleState(mediator);
		selectState = new SelectState(mediator);
		moveState = new MoveState(mediator);
		resizeState = new ResizeState(mediator);
		rotateState = new RotateState(mediator);
		deleteState = new DeleteState(mediator);
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setRectangleState() {
		currentState = rectangleState;
	}
	
	public void setTriangleState() {
		currentState = triangleState;
	}
	
	public void setCircleState() {
		currentState = circleState;
	}

	public void setSelectState() {
		currentState = selectState;
	}
	
	public void setMoveState() {
		currentState = moveState;
	}

	public void setResizeState() {
		currentState = resizeState;
	}
	
	public void setRotateState() {
		currentState = rotateState;
	}
	
	public void setDeleteState() {
		currentState = deleteState;
	}
}
