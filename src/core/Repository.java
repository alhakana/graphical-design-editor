package core;

import repository.model.Workspace;

/**
 *	For accessing models.
 */
public interface Repository {
	Workspace getWorkspace();
	void setWorkspace(Workspace workspace);
}
