package de.wagentim.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public final class FileHelper {
	
	private final static FileHelper fileHelper = new FileHelper();
	private final StringBuilder sBuilder = new StringBuilder();
	
	private FileHelper() {}
	
	public final static FileHelper getInstance() {
		return fileHelper;
	}
	
	
	public void writeToFile(String contents, String file) {
		
		try {
			
			Files.write(Paths.get(file), contents.getBytes(), StandardOpenOption.CREATE);
		}catch (IOException ex) {
		    ex.printStackTrace();
		}
	}

	public String getCurrentProjectPath(){
		return Paths.get("").toAbsolutePath().toString();
	}
	
	public List<String> readMouseMovement(String file){
		
		List<String> allLines = null;
		String dir = getCurrentProjectPath();
		try {
			Path filePath = Paths.get(dir + File.separator + file);
			
			if(filePath == null)
			{
				return new ArrayList<String>();
			}
			allLines = Files.readAllLines(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allLines;
	}
}
