package gui.swing.mainframe;

import javax.swing.BoxLayout;
import javax.swing.JToolBar;

public class PaintToolbar extends JToolBar{
	
	public PaintToolbar() {
		super(JToolBar.VERTICAL);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(MainFrame.getInstance().getActionManager().getDrawRectangle());
		add(MainFrame.getInstance().getActionManager().getDrawCircle());
		add(MainFrame.getInstance().getActionManager().getDrawTriangle());
		add(MainFrame.getInstance().getActionManager().getSlotSelect());
		add(MainFrame.getInstance().getActionManager().getSlotMove());
		add(MainFrame.getInstance().getActionManager().getSlotRotate());
		add(MainFrame.getInstance().getActionManager().getSlotResize());
		add(MainFrame.getInstance().getActionManager().getSlotDelete());
		add(MainFrame.getInstance().getActionManager().getGraphSlotAction());
		add(MainFrame.getInstance().getActionManager().getTextSlotAction());
		
	}
}
