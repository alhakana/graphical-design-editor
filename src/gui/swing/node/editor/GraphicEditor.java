package gui.swing.node.editor;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import gui.swing.mainframe.MainFrame;
import gui.swing.node.view.SlotView;

public class GraphicEditor extends Editor {

	private JPanel panel;
	private JLabel imageLabel;
	private JButton load;
	
	public GraphicEditor(SlotView slotView, File file) {
		super(slotView);
		initialize();
		settings();
		if (file != null)
			changePicture(file.getAbsolutePath());
	}
	
	private void initialize() {
		panel = new JPanel();
		load = new JButton("Load");
		imageLabel = new JLabel();
		
		load.addActionListener(e -> MainFrame.getInstance().getActionManager().getChangeImgAction().actionPerformed(null));
	}
	
	private void settings() {
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.add(load, BorderLayout.SOUTH);
		panel.add(imageLabel, BorderLayout.CENTER);
		setTitle("GraphSlot");
		setSize(500, 500);
	}
	
	public void changePicture(String path) {
		Image image = new ImageIcon(path).getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(image);
		imageLabel.setIcon(icon);
		repaint();	
	}

}
