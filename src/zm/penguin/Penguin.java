package zm.penguin;


import processing.core.*;
import zm.penguin.styles.Style;

/**
 * This is a template class and can be used to start a new processing Library.
 * Make sure you rename this class as well as the name of the example package 'template' 
 * to your own Library naming convention.
 * 
 * (the tag example followed by the name of an example included in folder 'examples' will
 * automatically include the example in the javadoc.)
 *
 * @example Hello 
 */

public class Penguin {
	
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

