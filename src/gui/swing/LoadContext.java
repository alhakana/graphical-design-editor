package gui.swing;

import app.AppCore;
import core.*;
import gui.swing.controller.ActionManager;
import gui.swing.filechooser.RFileChooser;
import gui.swing.mainframe.MainFrame;
import handler.EventHandlerImpl;
import handler.SerializationHandlerImpl;
import handler.SlotHandlerImpl;
import notification.Notification;
import notification.NotificationCode;
import repository.RepositoryImpl;
import repository.model.Workspace;
import utils.FileUtilImpl;
import javax.swing.*;
import java.awt.*;

public class LoadContext extends JFrame {
	
	private final JButton btnNew;
	private final JButton btnLast;
	private final JButton btnBrowse;
	
	public LoadContext() {
		btnNew = new JButton("New");
		btnLast = new JButton("Last");
		btnBrowse = new JButton("Browse");
		
		lookAndFeel();
		set();
	}
	
	private void lookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private void set() {
		setLayout(new FlowLayout());
		setTitle("Choose workspace");
		setSize(300,75);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(btnNew);
		add(btnLast);
		add(btnBrowse);
		
		
		ApplicationFramework appCore = AppCore.getInstance();
		Repository repository = new RepositoryImpl();
		Gui gui = new SwingGUI(repository);
		EventHandlerInterface eventHandler = new EventHandlerImpl();
		SerializationHandlerInterface serializationHandler = new SerializationHandlerImpl();
		FileUtilHandler fileUtilHandler = new FileUtilImpl();
		SlotHandler slotHandler = new SlotHandlerImpl();
		ActionManager actionManager = new ActionManager();
		appCore.initialize(gui, repository, eventHandler, serializationHandler, fileUtilHandler, slotHandler, actionManager);
		
		actionOnButtons();
		setVisible(true);
	}
	
	private void actionOnButtons() {
		btnNew.addActionListener(e -> {
			dispose();
			makeGUI(new Notification(NotificationCode.NEW, null));

		});
		
		btnBrowse.addActionListener(e -> {
			RFileChooser chooser = new RFileChooser(NotificationCode.IMPORT_WORKSPACE);
			if(chooser.showOpenDialog(MainFrame.getInstance().getWorkspaceView()) == JFileChooser.APPROVE_OPTION) {
				if(!chooser.getSelectedFile().exists()) {
					chooser.cancelSelection();
					MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.IMPORT_PROJECT, null));
				}else {
					Workspace workspace = new Workspace(chooser.getSelectedFile().getName().substring(0, chooser.getSelectedFile().getName().indexOf(".")));
					workspace.setWorkspaceFile(chooser.getSelectedFile());
					dispose();
					makeGUI(new Notification(NotificationCode.BROWSE, workspace));
				}
			}else {
				dispose();
			}

		});
		
		btnLast.addActionListener(e -> {
			Workspace workspace = AppCore.getInstance().getFileUtilHandler().readLastWs();

			if(workspace == null) {
				JOptionPane.showMessageDialog(null, "Last used workspace is not saved.");
				return;
			}
			dispose();
			makeGUI(new Notification(NotificationCode.LAST, workspace));
		});
	}
	
	private void makeGUI(Notification notification) {
		AppCore.getInstance().run(notification);
	}

}
