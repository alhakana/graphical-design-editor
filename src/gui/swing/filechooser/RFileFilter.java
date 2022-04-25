package gui.swing.filechooser;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class RFileFilter extends FileFilter {
	private String extension, description;
	
	public RFileFilter(String extension, String description) {
		this.extension = extension;
		this.description = description;
	}

	@Override
	public boolean accept(File f) {
		return (f.getName().endsWith(extension) || f.isDirectory());
	}

	@Override
	public String getDescription() {
		return description + " (" + extension + ")";
	}
	
}
