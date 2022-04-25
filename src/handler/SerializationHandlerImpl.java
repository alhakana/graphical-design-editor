package handler;

import core.SerializationHandlerInterface;
import gui.swing.mainframe.MainFrame;
import notification.Notification;
import notification.NotificationCode;
import repository.model.*;
import repository.node.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializationHandlerImpl implements SerializationHandlerInterface {
	
	@Override
	public String importSlotContext(File file) {
		StringBuilder builder = new StringBuilder();
		try {
			FileReader fl = new FileReader(file);
			BufferedReader bf = new BufferedReader(fl);
		
			String line;
			while((line = bf.readLine()) != null) {
				
				builder.append(line);
				builder.append(System.lineSeparator());
			}
			bf.close();
			fl.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}
	
	@Override
	public void saveSlotContext(Slot slot, String contextHTML, File file) {
		if(slot.getFile() != null) {
			if(slot.getFile().exists())						// delete old because we don't need it anymore
				slot.getFile().getAbsoluteFile().delete();
		}
		
		File slotFile = new File(file.getParent() + "\\" + slot.getName() + ".txt");
		slot.setFile(slotFile);
		
		try {
			PrintWriter contextPrint = new PrintWriter(slotFile);
			contextPrint.write(contextHTML);
			contextPrint.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	@Override
	public void importProject(File file, NotificationCode code) {
		// first check if workspace has a project with the same name
		if(!file.exists()) {
			return;
		}
		Workspace ws = (Workspace) MainFrame.getInstance().getTree().getWorkspace().getNodeModel();
		for(Node project : ws.getChildren()) {
			if(file.getName().equals(project.getName() + ".gdep")) {
				MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.IMPORT_NAME, null));
				return;
			}
		}
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
			Project p = (Project)ois.readObject();
			MainFrame.getInstance().getTree().importProject(p, code);
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * If workspace has saved path to its projects and the name of that project is changed and the project is saved,
	 * in that moment workspace path is changed and txt workspace file needs to be refreshed. That happens here.
	 */
	private void changeWsFile(File ws, File oldFile, File newFile) {
		try {
			BufferedReader bf = new BufferedReader(new FileReader(ws));
			StringBuffer inputBuffer = new StringBuffer();
			String line;
			
			while((line = bf.readLine()) != null) {
				if(line.equals(oldFile.toString()))
					inputBuffer.append(newFile.toString());
				else
					inputBuffer.append(line);
				inputBuffer.append(System.lineSeparator());
			}
			
			bf.close();
			
			String input = inputBuffer.toString();
			
			
			FileOutputStream fileOut = new FileOutputStream(ws);
			fileOut.write(input.getBytes());
			fileOut.close();
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Override
	public void importWorkspace(File file) {
		// file has paths to projects
		
		try {
			FileReader fl = new FileReader(file);
			BufferedReader bf = new BufferedReader(fl);
			String line;
			while((line = bf.readLine()) != null)
				importProject(new File(line), null);
			bf.close();
			fl.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void save(Project project) {
		// it is possible that user changed the name of the project
		if(!project.getProjectFile().getName().equals(project.getName() + ".gdep")) {
			String parent = project.getProjectFile().getParent();
			File old = project.getProjectFile();
			project.getProjectFile().getAbsoluteFile().delete();
			File file = new File(parent + "\\" + project.getName() + ".gdep");
			project.setProjectFile(file);
			if(((Workspace) MainFrame.getInstance().getTree().getWorkspace().getNodeModel()).getWorkspaceFile() != null)
				changeWsFile(((Workspace) MainFrame.getInstance().getTree().getWorkspace().getNodeModel()).getWorkspaceFile(), old, file);
		}
		
		for(Node d : project.getChildren() ) {
			Document document = (Document)d;
			for(Node p : document.getChildren()) {
				Page page = (Page)p;
				for(Node s : page.getChildren()) {
					Slot slot = (Slot)s;
					slot.sendMessage(new Notification(NotificationCode.SAVE_CONTEXT, project));
				}
			}
			
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(project.getProjectFile()));
			oos.writeObject(project);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void saveProject(Project project, String pathname) {
		File file = new File(pathname);
		project.setProjectFile(file); 	// set path for the project
		for(Node d : project.getChildren() ) {
			Document document = (Document)d;
			for(Node p : document.getChildren()) {
				Page page = (Page)p;
				for(Node s : page.getChildren()) {
					Slot slot = (Slot)s;
					slot.sendMessage(new Notification(NotificationCode.SAVE_CONTEXT, project));
				}
			}
			
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(project);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void saveWorkspace(Workspace workspace, String pathname) {
		if(workspace.getChildren().size() == 0)
			return;
		
		// creating a directory for projects that are about to be saved
		File dir = new File(pathname);
		if(!dir.exists())
			dir.mkdir();
		else {
			MainFrame.getInstance().getEventHandler().generateMessage(new Notification(NotificationCode.SAME_DIR_NAME, null));
			return;
		}
		// save the projects and remember their paths
		List<File> paths = new ArrayList<>();
		for(Node p : workspace.getChildren()) {
			Project project = (Project)p;
			File file = new File(pathname + "\\" + project.getName() + ".gdep");
			paths.add(file);
			project.setProjectFile(file);
			save(project);
		}
	 
		File workspaceFile = new File(pathname + "\\" + workspace.getName() + ".gdew");
		workspace.setWorkspaceFile(workspaceFile);
		
		try {
			PrintWriter wsPrint = new PrintWriter(workspaceFile);
			for(File f : paths)
				wsPrint.println(f);
			wsPrint.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}