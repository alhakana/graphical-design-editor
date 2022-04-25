package gui.swing.filechooser;

import javax.swing.JFileChooser;

import notification.NotificationCode;

@SuppressWarnings("serial")
public class RFileChooser extends JFileChooser {
	
	public RFileChooser(NotificationCode notificationCode) {
		inicijalizacija(notificationCode);
	}
	
	private void inicijalizacija(NotificationCode code) {
		setMultiSelectionEnabled(false);
		if (code == NotificationCode.SAVE_AS || code == NotificationCode.SAVE_WORKSPACE) {
			setDialogType(RFileChooser.SAVE_DIALOG);
			if (code == NotificationCode.SAVE_AS)
				setDialogTitle("Save As");
			else
				setDialogTitle("Save Workspace");
			setFileSelectionMode(RFileChooser.DIRECTORIES_ONLY);  //moguce da ce ovo morati da se promeni
		} else if (code == NotificationCode.IMPORT_PROJECT || code == NotificationCode.IMPORT_WORKSPACE || code == NotificationCode.IMPORT_IMG) {
			setDialogType(RFileChooser.OPEN_DIALOG);
			setFileSelectionMode(RFileChooser.FILES_ONLY);
			setAcceptAllFileFilterUsed(false);
			if (code == NotificationCode.IMPORT_PROJECT) { 
				addChoosableFileFilter(new RFileFilter(".gdep", "GDE project"));
				setDialogTitle("Open project");
			} else if (code == NotificationCode.IMPORT_WORKSPACE) { 
				addChoosableFileFilter(new RFileFilter(".gdew", "GDE workspace"));
				setDialogTitle("Open workspace");
			} else {
				addChoosableFileFilter(new RFileFilter(".png", "RuDok slot img"));
				setDialogTitle("Open img");
			}
				
		}
	}
}
