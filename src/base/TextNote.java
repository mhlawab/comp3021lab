package base;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.Serializable;

public class TextNote extends Note implements Serializable {
	
	public String content;
	private static final long serialVersionUID = 1L;
	
	public TextNote(String content) {
		super(content);
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content=content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result ="";
		try {
			Scanner input = new Scanner(new File(absolutePath));
			while (input.hasNextLine())
				result = result + input.nextLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		
		if (pathFolder == "") {
			pathFolder = ".";
		}
		
		try {
			File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ", "_") +".txt");
			PrintWriter output = new PrintWriter(file);
			output.print(content);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
