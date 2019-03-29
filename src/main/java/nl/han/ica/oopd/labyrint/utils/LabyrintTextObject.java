package nl.han.ica.oopd.labyrint.utils;

import nl.han.ica.oopg.objects.TextObject;

public class LabyrintTextObject extends TextObject {

	public LabyrintTextObject(String text, int fontSize) {
		super(text, fontSize);
		// TODO Auto-generated constructor stub
	}

	public void draw(processing.core.PGraphics g) {
		g.fill(this.getRedValue(), this.getGreenValue(), this.getBlueValue(), this.getAlphaValue());
		g.textAlign(g.LEFT, g.TOP);
		g.textSize(getFontSize());
		g.text(getText(), getX(), getY());
	}
}
