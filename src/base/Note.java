package base;

import java.util.Objects;
import java.io.Serializable;
import java.util.Date;

public class Note implements Comparable<Note>, Serializable{
	private static final long serialVersionUID = 1L;
	Date date;
	private String title;

	@Override
	public boolean equals(Object obj) {
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	public Note(String title) {
		this.title=title;
		date = new Date();
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public int compareTo(Note o) {
		if (this.date.before(o.date)) {
			return 1;
		} else if (this.date.after(o.date)) {
			return -1;
		} else return 0;
	}
	
	public String toString() {
		return date.toString() + "\t" + title;
	}
}
