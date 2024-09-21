package zm.penguin;


import processing.core.*;
import processing.event.*;
import static processing.core.PApplet.*;

import zm.penguin.components.Component;
import zm.penguin.interactions.Scrollable;
import zm.penguin.styles.Style;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Penguin
 */

public class Penguin {
	public List<Component> components;
	// app is a reference to the parent sketch
	public static PApplet app;
	public Style style;
	public final static String VERSION = "##library.prettyVersion##";
	public static boolean debug = false;

	/**
	 * a Constructor, usually called in the setup() method in your sketch to
	 * initialize and start the Library.
	 * 
	 * @class Penguin
	 * @param pApplet the parent PApplet
	 */
	public Penguin(PApplet pApplet) {
		app = pApplet;
		Context.initialize(app);
		style = new Style(app);

		components = new ArrayList<>();

		app.registerMethod("mouseEvent", this);
		app.registerMethod("keyEvent", this);
		app.registerMethod("draw", this);
	}

	public void mouseEvent(MouseEvent event) {
		int button = event.getButton();
		int x = event.getX();
		int y = event.getY();

		switch (event.getAction()) {
			case MouseEvent.CLICK:

			case MouseEvent.DRAG:

			case MouseEvent.ENTER:

			case MouseEvent.EXIT:

			case MouseEvent.MOVE:

			case MouseEvent.PRESS:
				components.stream()
						.filter(c -> c.mouseOver(x,y))
						.forEach(c -> c.click(x,y));
			case MouseEvent.RELEASE:

			case MouseEvent.WHEEL:
				components.stream()
						.filter(c -> c.mouseOver(x,y) && c instanceof Scrollable)
						.forEach(c -> ((Scrollable)c).scrollWheel(event.getCount()));
			default:

		}
	}

	public void setDebug(boolean debug) {
		Penguin.debug = debug;
	}

	private void welcome() {
		System.out.println("##library.name## ##library.prettyVersion## by ##author##");
	}

	/**
	 * return the version of the Library.
	 * 
	 * @return String
	 */
	public static String version() {
		return VERSION;
	}
}

