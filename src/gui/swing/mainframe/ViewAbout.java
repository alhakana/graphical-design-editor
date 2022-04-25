package gui.swing.mainframe;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ViewAbout extends JDialog {
	
	public ViewAbout(Frame parent, String title, boolean modal) {
		setLayout(new GridLayout(2, 3, 6, 6));
		setTitle(title);
		setModal(modal);
		setSize(1000, 700);
		setLocationRelativeTo(parent);
		
		JLabel imageOne = new JLabel();
		imageOne.setIcon(new ImageIcon(getClass().getResource("/resource/images/alya.png")));
		add(imageOne);
		Label nameOne = new Label("Alya Al Hakan <3");
		nameOne.setFont(new Font("Lucida Calligraphy", Font.BOLD, 25));
		add(nameOne);
		
		Label indexOne = new Label("111/19 RN");
		indexOne.setFont(new Font("Lucida Calligraphy", Font.BOLD, 25));

		add(indexOne);
		Label mailOne = new Label("aalhakan11119rn@raf.rs");
		mailOne.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));

		add(mailOne);
		
		
		Label nameTwo = new Label("Stefan Peric");
		nameTwo.setFont(new Font("Lucida Calligraphy", Font.BOLD, 25));

		add(nameTwo);
		Label indexTwo = new Label("84/19 RN");
		indexTwo.setFont(new Font("Lucida Calligraphy", Font.BOLD, 25));

		add(indexTwo);
		Label mailTwo = new Label("speric8419rn@raf.rs");
		mailTwo.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));

		add(mailTwo);
		JLabel imageTwo = new JLabel();
		imageTwo.setIcon(new ImageIcon(getClass().getResource("/resource/images/stefan.png")));
		add(imageTwo);
	}
	
}