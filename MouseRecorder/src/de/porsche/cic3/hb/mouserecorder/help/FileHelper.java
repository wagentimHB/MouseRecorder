package de.porsche.cic3.hb.mouserecorder.help;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public final class FileHelper {
	
	private final static FileHelper fileHelper = new FileHelper();
	private final StringBuilder sBuilder = new StringBuilder();
	
	private FileHelper() {}
	
	public final static FileHelper getInstance() {
		return fileHelper;
	}
	
	private void clearCache() {
		sBuilder.delete(0, sBuilder.length());
	}
	
	public void writeToFile(String contents, String file) {
		
		try {
			
			Files.write(Paths.get(file), contents.getBytes(), StandardOpenOption.CREATE);
		}catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
	
	public List<String> readMouseMovement(String file){
		
		List<String> allLines = null;
		
		try {
			allLines = Files.readAllLines(Paths.get(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allLines;
	}
}
