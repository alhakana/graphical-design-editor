package gui.swing.node.editor;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.SlotView;
import repository.model.Project;

public class TextEditor extends Editor {
	private JTextPane centerPanel;
	private TextToolbar textToolbar;
	
	public TextEditor(SlotView slotView, String context) {
		super(slotView);
		initialize();
		settings(context);
	}

	private void initialize() {
		centerPanel = new JTextPane();
		textToolbar = new TextToolbar();
	}
	
	private void settings(String context) {
		centerPanel.setContentType("text/html");
		JScrollPane pane = new JScrollPane(centerPanel);
		
		if(context != null)
			centerPanel.setText(context);
		
		add(pane, BorderLayout.CENTER);
		add(textToolbar,BorderLayout.NORTH);
	}
	
	public void saveContext(Project project) {
		MainFrame.getInstance().getSerializationHandler().saveSlotContext(slotView.getSlotModel(), centerPanel.getText(), project.getProjectFile());
	}
	
}
