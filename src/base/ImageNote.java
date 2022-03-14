package base;

import java.io.Serializable;

public class ImageNote extends Note implements Serializable{
	private static final long serialVersionUID = 1L;
	java.io.File Image;
	public ImageNote(String Image) {
		super(Image);
	} 
}
