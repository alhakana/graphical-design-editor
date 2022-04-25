package core;

import repository.model.Workspace;

public interface FileUtilHandler {
	
	void saveContext(String wsPath);
	
	void deleteTxt();
	
	Workspace readLastWs();
}
