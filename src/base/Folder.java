package base;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.regex.Pattern;

public class Folder implements Comparable<Folder>, Serializable {
	
	final String garble = "!@!@!@!@@!@!@@@@@!!!@";
	private static final long serialVersionUID = 1L;

	private ArrayList<Note> notes;
	private String name;

	public Folder(String name) {
		this.name=name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note){
		notes.add(note);
	}

	public String getName(){
		return name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for(Note note : notes) {
			if (note instanceof TextNote) {
				nText++;
			}
			if (note instanceof ImageNote) {
				nImage++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;

	}

	@Override
	public boolean equals(Object obj) {
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
	
	@Override
	public int compareTo(Folder o) {
		if (this.name.compareTo(o.name)>0)
			return 1;
		else if (this.name.compareTo(o.name)<0) 
			return -1;
		return 0;
	}
	
	public void sortNote() {
		Collections.sort(notes);
	}
	
	public boolean stringSearchNotes(Note note, String string) {
		if (string.contains(garble)) {
			String[] keys = string.split(Pattern.quote(garble), 0);
			for (String key : keys) {
				if (note.getTitle().toLowerCase().contains(key)||((note instanceof TextNote)&&(((TextNote) note).content.contains(key))))
					return true;
			}
		} else {
			if (note.getTitle().toLowerCase().contains(string)||((note instanceof TextNote)&&(((TextNote) note).content.contains(string)))) 
				return true;
		}

		return false;
	}
	
	public ArrayList<Note> searchNotes(String keywords) {
		keywords = keywords.toLowerCase();
		keywords = keywords.replaceAll(" or ", garble);
		String[] tokens = keywords.split(" ",0);
		ArrayList<Note> result = new ArrayList<Note>();
		
		for (Note note: notes) {
			boolean valid = true;
			for (String token : tokens) {
				if (!stringSearchNotes(note,token)) {
					valid=false;
					break;
				}
			}
			if (valid)
				result.add(note);
		}
		
		return result;
	}
}