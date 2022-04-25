package gui.swing.node.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.File;
import javax.swing.SwingUtilities;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.editor.Editor;
import gui.swing.node.editor.GraphicEditor;
import gui.swing.node.editor.TextEditor;
import gui.swing.painters.CirclePainter;
import gui.swing.painters.ElementPainter;
import gui.swing.painters.RectanglePainter;
import gui.swing.painters.TrianglePainter;
import gui.swing.tree.model.TreeItem;
import notification.Notification;
import notification.NotificationCode;
import notification.ShapeType;
import observer.IListener;
import repository.model.Project;
import repository.model.Slot;

public class SlotView implements IListener {
	private Stroke stroke;
	private final Slot slotModel;
	private final PageView containerParent;
	private ElementPainter painter;
	private Editor editor;
								
	public SlotView(Slot slotModel, PageView containerParent) {
		this.slotModel = slotModel;
		this.containerParent = containerParent;
		load();
		if(slotModel.countObservers() < 2)
			refreshContext();
	}
	
	@Override
	public void update(Notification notification) {
		if (notification.getNotificationCode() != null) {
			switch(notification.getNotificationCode()) {
			case DELETE_ME:
				deleteMe();
				break;
			case SET_FOCUS:
				focus();
				break;
			case RESET:
				makePainter();
				if (!containerParent.getSelectedSlots().contains(this))
					containerParent.getSelectedSlots().add(this);
				
				painter.setColor(new Color(0,204,255));
				MainFrame.getInstance().repaint();
				break;
			case OPEN:
				if(editor != null) 
					editor.setVisible(true);
				break;	
			case GRAPH:
				if(slotModel.countObservers() < 2 || containerParent.getContainerParent().getContainerParent().getProjectModel().equals(slotModel.getParent().getParent().getParent()))
					setEditor(NotificationCode.GRAPH);
				break;
			case TEXT:
				if(slotModel.countObservers() < 2 ||  containerParent.getContainerParent().getContainerParent().getProjectModel().equals(slotModel.getParent().getParent().getParent()))
					setEditor(NotificationCode.TEXT );
				break;	
			case IMPORT_IMG:
				changeImg();
				break;	
			case SAVE_CONTEXT:
				if(editor != null)
					if(editor instanceof TextEditor) 
						saveContext((Project) notification.getObject());	
				break;
			default:
				break;
			}
		}
	}
	
	private void saveContext(Project project) {
		if(editor != null) {
			TextEditor t = (TextEditor)editor;
			t.saveContext(project);
		}
	}
	
	private void changeImg() {
		if(editor != null) {
			GraphicEditor ge = (GraphicEditor)editor;
			ge.changePicture(slotModel.getFile().getAbsolutePath());
		}
	}
	
	private void refreshContext() {
		if(slotModel.getFile() != null)
			if(!slotModel.getFile().exists()) 
				slotModel.setFile(null);
		
		if(slotModel.getContent() == ShapeType.GRAPH)
			setEditor(NotificationCode.GRAPH);
		else if(slotModel.getContent() == ShapeType.TEXT)
			setEditor(NotificationCode.TEXT);
	}
	
	private void focus() {
		containerParent.focus();
	}
	
	
	
	private void setEditor(NotificationCode code) {
		if(code == NotificationCode.GRAPH)
			editor = new GraphicEditor(this, slotModel.getFile());
		else {	// it's text
			File file = slotModel.getFile();
			if(file != null)
				editor = new TextEditor(this, MainFrame.getInstance().getSerializationHandler().importSlotContext(file));
			else
				editor = new TextEditor(this,null);
		}
	}
	
	private void deleteMe() {
		//delete once from tree as tree item
		if(slotModel.getParent().getParent().getParent().getName().equals(containerParent.getContainerParent().getContainerParent().getProjectModel().getName())) {
			for(int i = 0; i < MainFrame.getInstance().getTree().getSelectedTreeItem().getChildCount() ; i++) {
				if(((TreeItem)MainFrame.getInstance().getTree().getSelectedTreeItem().getChildAt(i)).getNodeModel().getName().equals(slotModel.getName())) {
					MainFrame.getInstance().getTree().getSelectedTreeItem().remove(MainFrame.getInstance().getTree().getSelectedTreeItem().getIndex(MainFrame.getInstance().getTree().getSelectedTreeItem().getChildAt(i)));
				}
			}
		}
			
		
		containerParent.getSlots().remove(this);
		containerParent.getSelectedSlots().remove(this);
		containerParent.getPageModel().getChildren().remove(slotModel);

//		MainFrame.getInstance().getWorkspaceTree().setSelectionRow(0);
		SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getWorkspaceTree());	
		MainFrame.getInstance().repaint();
	}
	
	private void load() {
		slotModel.addObserver(this);
		stroke = new BasicStroke(2f);
		makePainter();
	}
	private void makePainter() {
		painterType();
	}
	
	private void painterType() {
		if(slotModel.getType() == ShapeType.CIRCLE)
			painter = new CirclePainter(this);
		else if(slotModel.getType() == ShapeType.TRIANGLE)
			painter = new TrianglePainter(this);
		else 
			painter = new RectanglePainter(this);
	}

	public boolean intersect(Rectangle2D rect) {
		double x = getSlotModel().getX(), y = getSlotModel().getY();
		double w = getSlotModel().getW(), h = getSlotModel().getH();
		return rect.intersects(x - w / 2, y - h / 2, w, h);
	}
	
	public Stroke getStroke() {
		return stroke;
	}

	public Slot getSlotModel() {
		return slotModel;
	}

	public ElementPainter getPainter() {
		return painter;
	}
}