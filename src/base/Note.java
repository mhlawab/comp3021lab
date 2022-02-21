package base;

import java.util.Objects;

public class Note {
	java.util.Date date;
	private String title;

	@Override
	public boolean equals(Object obj) {
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	public Note(String title) {
		this.title=title;
		date = new java.util.Date();
	}
	
	public String getTitle() {
		return title;
	}
}
