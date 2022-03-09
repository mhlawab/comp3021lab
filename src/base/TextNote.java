package base;

public class TextNote extends Note {
	public String content;
	public TextNote(String content) {
		super(content);
	}
	public TextNote(String title, String content) {
		super(title);
		this.content=content;
	}
}
