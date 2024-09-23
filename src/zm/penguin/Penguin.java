package zm.penguin;

import processing.core.*;
import processing.event.*;
import static processing.core.PApplet.*;

import zm.penguin.components.Component;
import zm.penguin.containers.Scrollable;
import zm.penguin.styles.Style;

import java.util.ArrayList;
import java.util.Collections;
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

	public int last_w, last_h;
	public boolean initialized = false;
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
		last_w = app.width;
		last_h = app.height;
		style = new Style(app);

		components = new ArrayList<>();

		app.registerMethod("mouseEvent", this);
		app.registerMethod("keyEvent", this);
		app.registerMethod("draw", this);
		app.registerMethod("pre", this);
	}

	public void mouseEvent(MouseEvent event) {
		int button = event.getButton();
		int x = event.getX();
		int y = event.getY();

		switch (event.getAction()) {
			case MouseEvent.CLICK:
				break;
			case MouseEvent.DRAG:
				components.stream()
						.filter(c -> (c instanceof Scrollable && ((Scrollable<?>)c).scroll.locked))
						.forEach(c -> c.mouseDragged(x,y));
				break;
			case MouseEvent.ENTER:
				break;
			case MouseEvent.EXIT:
				break;
			case MouseEvent.MOVE:
				components.stream()
						.filter(c -> c.mouseOver(x,y))
						.forEach(c -> c.mouseMoved(x,y));
				break;
			case MouseEvent.PRESS:
				components.stream()
						.filter(c -> c.mouseOver(x,y))
						.forEach(c -> {
							c.click(x,y);
						});
				break;
			case MouseEvent.RELEASE:
				components.forEach(c -> c.mouseReleased(x,y));
				break;
			case MouseEvent.WHEEL:
				components.stream()
						.filter(c -> c.mouseOver(x,y) && c instanceof Scrollable)
						.forEach(c -> ((Scrollable<?>)c).scrollWheel(event.getCount()));
				break;
			default:
				break;
		}
	}

	public void keyEvent(KeyEvent event) {

	}

	public void pre() {
//		if (initialized && ((last_w != app.width) || (last_h != app.height))){
//		}
	}

	public void draw() {
		for (Component c : components) {
			if (c.isVisible()) c.draw();
		}
	}

	public void add(Component c) {
		components.add(c);
		initialized = true;
	}

	public void add(Component...args) {
        Collections.addAll(components, args);
	}

	public void setDebug(boolean debug) {
		for (Component c : components) c.debug = debug;
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

