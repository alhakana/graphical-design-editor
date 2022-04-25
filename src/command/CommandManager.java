package command;

import java.util.ArrayList;

import gui.swing.mainframe.MainFrame;

public class CommandManager {
	private final ArrayList<Command> commands = new ArrayList<>();
	private int currentCommand = 0;
	
	public void addCommand(Command command) {
		while(currentCommand < commands.size()) {
			commands.remove(currentCommand);
		}
		commands.add(command);
		doCommand();
	}
	
	public void doCommand() {
		if(currentCommand < commands.size()) {
			commands.get(currentCommand++).doCommand();
			MainFrame.getInstance().getActionManager().getCommandUndo().setEnabled(true);
		}
		if(currentCommand == commands.size()) {
			MainFrame.getInstance().getActionManager().getCommandRedo().setEnabled(false);
		}
	}
	
	public void undoCommand() {
		if(currentCommand > 0) {			
			MainFrame.getInstance().getActionManager().getCommandRedo().setEnabled(true);
			commands.get(--currentCommand).undoCommand();
		}
		if(currentCommand == 0) {
			MainFrame.getInstance().getActionManager().getCommandUndo().setEnabled(false);
		}
	}

}
