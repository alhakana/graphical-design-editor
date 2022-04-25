package utils;

import core.FileUtilHandler;
import repository.model.Workspace;

import java.io.*;

public class FileUtilImpl implements FileUtilHandler{

	@Override
	public void saveContext(String wsPath) {
		File file = new File("lastws.txt");
		file.delete();	// deletes lastws.txt
		try {
			FileWriter fw = new FileWriter("lastws.txt", true);
			PrintWriter pw = new PrintWriter(fw);
			pw.print(wsPath);
			fw.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	@Override
	public void deleteTxt() {
		File file = new File("lastws.txt");
		file.delete();
		
		try {
			FileWriter fw = new FileWriter("lastws.txt", true);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@SuppressWarnings("resource")
	@Override
	public Workspace readLastWs() {
		Workspace ws = null;
		try {
			FileReader	fr = new FileReader("lastws.txt");
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			if(line != null) {
				File file = new File(line);
			
				if (!file.exists())
					return null;
				ws = new Workspace(file.getName().substring(0, file.getName().indexOf(".")));
				ws.setWorkspaceFile(file);
			}
			fr.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ws;
	}
	
}
