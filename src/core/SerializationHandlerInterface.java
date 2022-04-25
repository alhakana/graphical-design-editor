package core;

import java.io.File;

import notification.NotificationCode;
import repository.model.Project;
import repository.model.Slot;
import repository.model.Workspace;

public interface SerializationHandlerInterface {
	/**
	 * Saves project on location path.
	 */
	void saveProject(Project project, String path);
	
	/**
	 * Imports project
	 * @param file written project
	 */
	void importProject(File file, NotificationCode code);
	
	void save(Project project);
	
	/**
	 * Workspace is saved like .gdew file which has paths to all projects.
	 * All projects must be saves before saving workspace.
	 */
	void saveWorkspace(Workspace workspace, String pathname);
	
	void importWorkspace(File file);
	
	void saveSlotContext(Slot slot, String contextHTML, File file);
	
	String importSlotContext(File file);

}
