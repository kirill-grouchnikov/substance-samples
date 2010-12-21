package test.samples.cookbook.icons;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/**
 * This class has been automatically generated using <a
 * href="https://flamingo.dev.java.net">Flamingo SVG transcoder</a>.
 */
public class list_add implements
		org.pushingpixels.flamingo.api.common.icon.ResizableIcon {
	/**
	 * Paints the transcoded SVG image on the specified graphics context. You
	 * can install a custom transformation on the graphics context to scale the
	 * image.
	 * 
	 * @param g
	 *            Graphics context.
	 */
	public static void paint(Graphics2D g) {
		Shape shape = null;
		Paint paint = null;
		Stroke stroke = null;

		AffineTransform defaultTransform_ = g.getTransform();
		// 
		g.setComposite(AlphaComposite.getInstance(3, 1.0f));
		AffineTransform defaultTransform__0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		// _0
		g.setComposite(AlphaComposite.getInstance(3, 1.0f));
		AffineTransform defaultTransform__0_0 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		// _0_0
		g.setComposite(AlphaComposite.getInstance(3, 0.10824742f));
		AffineTransform defaultTransform__0_0_0 = g.getTransform();
		g
				.transform(new AffineTransform(1.5504870414733887f, 0.0f, 0.0f,
						1.9787139892578125f, -12.481300354003906f,
						-32.491031646728516f));
		// _0_0_0
		paint = new RadialGradientPaint(new Point2D.Double(23.070682525634766,
				35.127437591552734), 10.31934f, new Point2D.Double(
				23.070682525634766, 35.127437591552734), new float[] { 0.0f,
				1.0f }, new Color[] { new Color(0, 0, 0, 255),
				new Color(0, 0, 0, 0) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						0.9148120284080505f, 0.012650229968130589f,
						-0.008215020410716534f, 0.2135619968175888f,
						2.2539141178131104f, 27.18889045715332f));
		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(33.278214, 34.94062);
		((GeneralPath) shape).curveTo(33.278214, 36.222027, 28.658087,
				37.260815, 22.958874, 37.260815);
		((GeneralPath) shape).curveTo(17.259659, 37.260815, 12.639532,
				36.222027, 12.639532, 34.94062);
		((GeneralPath) shape).curveTo(12.639532, 33.659214, 17.259659,
				32.620426, 22.958872, 32.620426);
		((GeneralPath) shape).curveTo(28.658087, 32.620426, 33.278214,
				33.659214, 33.278214, 34.94062);
		((GeneralPath) shape).closePath();
		g.setPaint(paint);
		g.fill(shape);
		g.setTransform(defaultTransform__0_0_0);
		g.setComposite(AlphaComposite.getInstance(3, 1.0f));
		AffineTransform defaultTransform__0_0_1 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		// _0_0_1
		paint = new Color(117, 161, 208, 255);
		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(27.514357, 37.542683);
		((GeneralPath) shape).lineTo(27.514357, 28.515722);
		((GeneralPath) shape).lineTo(37.49282, 28.475542);
		((GeneralPath) shape).lineTo(37.49282, 21.480219);
		((GeneralPath) shape).lineTo(27.523285, 21.480219);
		((GeneralPath) shape).lineTo(27.514357, 11.520049);
		((GeneralPath) shape).lineTo(20.498081, 11.53121);
		((GeneralPath) shape).lineTo(20.502546, 21.462362);
		((GeneralPath) shape).lineTo(10.51292, 21.536022);
		((GeneralPath) shape).lineTo(10.477206, 28.50456);
		((GeneralPath) shape).lineTo(20.511475, 28.475542);
		((GeneralPath) shape).lineTo(20.518171, 37.515896);
		((GeneralPath) shape).lineTo(27.514357, 37.542683);
		((GeneralPath) shape).closePath();
		g.setPaint(paint);
		g.fill(shape);
		paint = new Color(52, 101, 164, 255);
		stroke = new BasicStroke(1.0000004f, 0, 0, 4.0f, null, 0.0f);
		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(27.514357, 37.542683);
		((GeneralPath) shape).lineTo(27.514357, 28.515722);
		((GeneralPath) shape).lineTo(37.49282, 28.475542);
		((GeneralPath) shape).lineTo(37.49282, 21.480219);
		((GeneralPath) shape).lineTo(27.523285, 21.480219);
		((GeneralPath) shape).lineTo(27.514357, 11.520049);
		((GeneralPath) shape).lineTo(20.498081, 11.53121);
		((GeneralPath) shape).lineTo(20.502546, 21.462362);
		((GeneralPath) shape).lineTo(10.51292, 21.536022);
		((GeneralPath) shape).lineTo(10.477206, 28.50456);
		((GeneralPath) shape).lineTo(20.511475, 28.475542);
		((GeneralPath) shape).lineTo(20.518171, 37.515896);
		((GeneralPath) shape).lineTo(27.514357, 37.542683);
		((GeneralPath) shape).closePath();
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);
		g.setTransform(defaultTransform__0_0_1);
		g.setComposite(AlphaComposite.getInstance(3, 0.40860215f));
		AffineTransform defaultTransform__0_0_2 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		// _0_0_2
		paint = new LinearGradientPaint(new Point2D.Double(34.89284896850586,
				36.42298889160156), new Point2D.Double(45.918697357177734,
				48.54798889160156), new float[] { 0.0f, 1.0f }, new Color[] {
				new Color(114, 159, 207, 255), new Color(81, 135, 214, 255) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.0f, 0.0f, 0.0f, 1.0f, -18.017850875854492f,
						-13.571189880371094f));
		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(26.498701, 36.53392);
		((GeneralPath) shape).lineTo(26.498701, 27.499739);
		((GeneralPath) shape).lineTo(36.501305, 27.499739);
		((GeneralPath) shape).lineTo(36.494606, 22.47531);
		((GeneralPath) shape).lineTo(26.50763, 22.47531);
		((GeneralPath) shape).lineTo(26.50763, 12.480335);
		((GeneralPath) shape).lineTo(21.512796, 12.498193);
		((GeneralPath) shape).lineTo(21.521725, 22.47531);
		((GeneralPath) shape).lineTo(11.495536, 22.493166);
		((GeneralPath) shape).lineTo(11.46875, 27.466255);
		((GeneralPath) shape).lineTo(21.533142, 27.475185);
		((GeneralPath) shape).lineTo(21.51975, 36.50267);
		((GeneralPath) shape).lineTo(26.498701, 36.53392);
		((GeneralPath) shape).closePath();
		g.setPaint(paint);
		g.fill(shape);
		paint = new LinearGradientPaint(new Point2D.Double(16.874998092651367,
				22.85179901123047), new Point2D.Double(27.900846481323242,
				34.97679901123047), new float[] { 0.0f, 1.0f }, new Color[] {
				new Color(255, 255, 255, 255), new Color(255, 255, 255, 87) },
				MultipleGradientPaint.CycleMethod.NO_CYCLE,
				MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(
						1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		stroke = new BasicStroke(1.0000006f, 0, 0, 4.0f, null, 0.0f);
		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(26.498701, 36.53392);
		((GeneralPath) shape).lineTo(26.498701, 27.499739);
		((GeneralPath) shape).lineTo(36.501305, 27.499739);
		((GeneralPath) shape).lineTo(36.494606, 22.47531);
		((GeneralPath) shape).lineTo(26.50763, 22.47531);
		((GeneralPath) shape).lineTo(26.50763, 12.480335);
		((GeneralPath) shape).lineTo(21.512796, 12.498193);
		((GeneralPath) shape).lineTo(21.521725, 22.47531);
		((GeneralPath) shape).lineTo(11.495536, 22.493166);
		((GeneralPath) shape).lineTo(11.46875, 27.466255);
		((GeneralPath) shape).lineTo(21.533142, 27.475185);
		((GeneralPath) shape).lineTo(21.51975, 36.50267);
		((GeneralPath) shape).lineTo(26.498701, 36.53392);
		((GeneralPath) shape).closePath();
		g.setPaint(paint);
		g.setStroke(stroke);
		g.draw(shape);
		g.setTransform(defaultTransform__0_0_2);
		g.setComposite(AlphaComposite.getInstance(3, 0.31182796f));
		AffineTransform defaultTransform__0_0_3 = g.getTransform();
		g.transform(new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f));
		// _0_0_3
		paint = new Color(255, 255, 255, 255);
		shape = new GeneralPath();
		((GeneralPath) shape).moveTo(11.0, 25.0);
		((GeneralPath) shape).curveTo(11.0, 26.9375, 36.984375, 24.03125,
				36.984375, 24.96875);
		((GeneralPath) shape).lineTo(36.984375, 21.96875);
		((GeneralPath) shape).lineTo(27.0, 22.0);
		((GeneralPath) shape).lineTo(27.0, 12.034772);
		((GeneralPath) shape).lineTo(21.0, 12.034772);
		((GeneralPath) shape).lineTo(21.0, 22.0);
		((GeneralPath) shape).lineTo(11.0, 22.0);
		((GeneralPath) shape).lineTo(11.0, 25.0);
		((GeneralPath) shape).closePath();
		g.setPaint(paint);
		g.fill(shape);
		g.setTransform(defaultTransform__0_0_3);
		g.setTransform(defaultTransform__0_0);
		g.setTransform(defaultTransform__0);
		g.setTransform(defaultTransform_);

	}

	/**
	 * Returns the width of the bounding box of the original SVG image.
	 * 
	 * @return The width of the bounding box of the original SVG image.
	 */
	public static int getOrigWidth() {
		return 48;
	}

	/**
	 * Returns the height of the bounding box of the original SVG image.
	 * 
	 * @return The height of the bounding box of the original SVG image.
	 */
	public static int getOrigHeight() {
		return 48;
	}

	/**
	 * The current width of this resizable icon.
	 */
	int width;

	/**
	 * The current height of this resizable icon.
	 */
	int height;

	/**
	 * Creates a new transcoded SVG image.
	 */
	public list_add() {
		this.revertToOriginalDimension();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#getIconHeight()
	 */
	public int getIconHeight() {
		return width;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#getIconWidth()
	 */
	public int getIconWidth() {
		return height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jvnet.flamingo.common.icon.ResizableIcon#revertToOriginalDimension()
	 */
	public void revertToOriginalDimension() {
		width = getOrigWidth();
		height = getOrigHeight();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jvnet.flamingo.common.icon.ResizableIcon#setDimension(java.awt.Dimension
	 * )
	 */
	public void setDimension(Dimension newDimension) {
		width = newDimension.width;
		height = newDimension.height;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jvnet.flamingo.common.icon.ResizableIcon#setHeight(int)
	 */
	public void setHeight(int height) {
		setDimension(new Dimension(height, height));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jvnet.flamingo.common.icon.ResizableIcon#setWidth(int)
	 */
	public void setWidth(int width) {
		setDimension(new Dimension(width, width));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.Icon#paintIcon(java.awt.Component, java.awt.Graphics,
	 * int, int)
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.translate(x, y);

		double coef1 = (double) this.width / (double) getOrigWidth();
		double coef2 = (double) this.height / (double) getOrigHeight();
		double coef = Math.min(coef1, coef2);
		g2d.scale(coef, coef);
		paint(g2d);
		g2d.dispose();
	}
}
