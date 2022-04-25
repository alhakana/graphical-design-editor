package gui.swing.mainframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import gui.swing.tree.model.TreeItem;

public class DocumentDialog extends JDialog {
	
	@SuppressWarnings("rawtypes")
	private JList projectList;
	/**
	 * document that is about to be shared.
	 */
	private TreeItem document;

	public DocumentDialog() {
		super(MainFrame.getInstance(),	"Share document",true);
		shareGUI();
	}
	
	private void shareGUI() {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel("Choose project:");
		JButton shareBtn = new JButton("Share");
		
		logic();
		
		label.setMaximumSize(new Dimension(100,40));
		projectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scroll = new JScrollPane(projectList, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		shareBtn.addActionListener(getActionListener());
		panel.add(label,BorderLayout.NORTH);
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(shareBtn,BorderLayout.SOUTH);
		this.add(panel);
		setSize(new Dimension(150,150));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void logic() {
		ArrayList<TreeItem> projects = MainFrame.getInstance().getTree().shareList();
		document = MainFrame.getInstance().getTree().getSelectedTreeItem();

		projectList = new JList(projects.toArray());
	}
	
	private ActionListener getActionListener() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TreeItem item = (TreeItem)projectList.getSelectedValue();
				if (item != null) {
					MainFrame.getInstance().getTree().addSharedDocument(document, item);
					if(!document.getName().contains("(shared)"))
						document.setName(document.getName() + " (shared)");
				}
				dispose();
			}
		};
	}
	

}
