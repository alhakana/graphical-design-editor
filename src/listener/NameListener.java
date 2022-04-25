package listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;

public class NameListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isActionKey() || e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
			return;
		}
		
		JTextField jtf = (JTextField)e.getComponent();
		char c = e.getKeyChar();
		
		if (jtf.getText().length() == 15) {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.NAME_SIZE, null));
			jtf.setText("");
		} else if (c == '@' || c == '(' || c == ')') {
			jtf.setText(null);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField jtf = (JTextField)e.getComponent();
		char c = e.getKeyChar();
		if (c == '@' || c == '(' || c == ')')
			jtf.setText(null);
		
	}
}
