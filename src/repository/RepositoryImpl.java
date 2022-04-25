package repository;

import core.Repository;
import repository.model.Workspace;

/**
 * Creates root of the tree.
 */
public class RepositoryImpl implements Repository {
	private Workspace root;

	public RepositoryImpl() {
		root = new Workspace("Workspace");
	}
	
	@Override
	public Workspace getWorkspace() {
		return root;
	}

	@Override
	public void setWorkspace(Workspace workspace) {
		root = workspace;
	}

}
