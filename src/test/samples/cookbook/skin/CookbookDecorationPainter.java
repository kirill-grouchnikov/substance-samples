/*
 * Copyright (c) 2005-2010 Substance Kirill Grouchnikov. All Rights Reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  o Neither the name of Substance Kirill Grouchnikov nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
/**
 * 
 */
package test.samples.cookbook.skin;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.LinearGradientPaint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.image.Kernel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceColorScheme;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.painter.decoration.SubstanceDecorationPainter;

import test.samples.cookbook.icons.dialog_information;

import com.jhlabs.image.BlurFilter;
import com.jhlabs.image.BrushedMetalFilter;
import com.jhlabs.image.CompoundFilter;
import com.jhlabs.image.Gradient;
import com.jhlabs.image.HSBAdjustFilter;
import com.jhlabs.image.LookupFilter;
import com.jhlabs.image.WoodFilter;

class CookbookDecorationPainter implements SubstanceDecorationPainter {
	private BufferedImage brushedMetalImage;

	private BufferedImage woodImage;

	private BufferedImage lightImage;

	public CookbookDecorationPainter() {
		GoldenBrownColorScheme goldenBrownScheme = new GoldenBrownColorScheme();
		DarkBrownColorScheme darkBrownScheme = new DarkBrownColorScheme();

		Rectangle virtualBounds = new Rectangle();
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gds = ge.getScreenDevices();
		for (GraphicsDevice gd : gds) {
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			virtualBounds = virtualBounds.union(gc.getBounds());
		}

		int screenWidth = virtualBounds.width;
		int screenHeight = virtualBounds.height;

		// brushed metal filter
		BrushedMetalFilter brushedMetalFilter = new BrushedMetalFilter();
		brushedMetalFilter.setAmount(10);
		LookupFilter brushedMetalLookupFilter = new LookupFilter(new Gradient(
				new int[] { 0, 96, 255 }, new int[] {
						goldenBrownScheme.getUltraDarkColor().getRGB(),
						goldenBrownScheme.getLightColor().getRGB(),
						goldenBrownScheme.getUltraLightColor().getRGB() }));

		this.brushedMetalImage = new CompoundFilter(brushedMetalFilter,
				brushedMetalLookupFilter).filter(new BufferedImage(screenWidth,
				screenHeight, BufferedImage.TYPE_INT_ARGB), null);

		// wood filter
		WoodFilter woodFilter = new WoodFilter();
		woodFilter.setScale(100);
		woodFilter.setAngle((float) Math.PI / 2.0f);
		woodFilter.setStretch(10);
		woodFilter.setTurbulence(0);
		woodFilter.setRings(0.4f);
		woodFilter.setFibres(0.3f);
		woodFilter.setgain(0.7f);

		LookupFilter woodLookupFilter = new LookupFilter(new Gradient(
				new int[] { 0, 64, 196, 255 }, new int[] {
						darkBrownScheme.getUltraDarkColor().getRGB(),
						darkBrownScheme.getUltraDarkColor().getRGB(),
						darkBrownScheme.getDarkColor().getRGB(),
						darkBrownScheme.getLightColor().getRGB() }));

		this.woodImage = new CompoundFilter(woodFilter, woodLookupFilter)
				.filter(new BufferedImage(screenWidth, screenHeight,
						BufferedImage.TYPE_INT_ARGB), null);

		this.lightImage = getLightImage();
	}

	@Override
	public String getDisplayName() {
		return "Cookbook";
	}

	@Override
	public void paintDecorationArea(Graphics2D graphics, Component comp,
			DecorationAreaType decorationAreaType, int width, int height,
			SubstanceSkin skin) {
		SubstanceColorScheme fillScheme = skin
				.getBackgroundColorScheme(decorationAreaType);
		BufferedImage toOverlay = this.getWatermarkImage(decorationAreaType);
		Component farthestOfTheSameAreaType = this.getFarthest(comp,
				decorationAreaType);
		if ((decorationAreaType == DecorationAreaType.PRIMARY_TITLE_PANE)
				|| (decorationAreaType == DecorationAreaType.SECONDARY_TITLE_PANE)
				|| (decorationAreaType == DecorationAreaType.HEADER)
				|| (decorationAreaType == DecorationAreaType.TOOLBAR)) {
			int dy = comp.getLocationOnScreen().y
					- SwingUtilities.getWindowAncestor(comp)
							.getLocationOnScreen().y;
			// main background gradient
			graphics.setPaint(new LinearGradientPaint(0, -dy, 0, 70 - dy,
					new float[] { 0.0f, 0.05f, 0.6f, 0.8f, 1.0f }, new Color[] {
							fillScheme.getUltraLightColor(),
							fillScheme.getExtraLightColor(),
							fillScheme.getMidColor(),
							fillScheme.getDarkColor(),
							fillScheme.getUltraDarkColor() }));
			graphics.fillRect(0, 0, width, height);
		} else if (decorationAreaType == DecorationAreaType.FOOTER) {
			// main background gradient
			int dy = comp.getLocationOnScreen().y
					- farthestOfTheSameAreaType.getLocationOnScreen().y;
			int footerHeight = farthestOfTheSameAreaType.getHeight();
			graphics.setPaint(new LinearGradientPaint(0, -dy, 0, -dy
					+ footerHeight, new float[] { 0.0f, 0.5f, 0.75f, 1.0f },
					new Color[] { fillScheme.getLightColor(),
							fillScheme.getMidColor(),
							fillScheme.getDarkColor(),
							fillScheme.getUltraDarkColor() }));
			graphics.fillRect(0, 0, width, height);
		} else if (decorationAreaType == DecorationAreaType.GENERAL) {
			// general background gradient
			graphics.setPaint(new GradientPaint(0, 0, fillScheme
					.getLightColor(), 0, height, fillScheme.getMidColor()));
			graphics.fillRect(0, 0, width, height);
		} else {
			// main background gradient
			graphics
					.setPaint(new GradientPaint(0, 0, fillScheme
							.getExtraLightColor(), 0, height, fillScheme
							.getMidColor()));
			graphics.fillRect(0, 0, width, height);
		}

		int dy = comp.getLocationOnScreen().y
				- SwingUtilities.getWindowAncestor(comp).getLocationOnScreen().y;
		int dx = comp.getLocationOnScreen().x
				- SwingUtilities.getWindowAncestor(comp).getLocationOnScreen().x;
		graphics.setComposite(AlphaComposite.SrcOver.derive(0.25f));
		graphics.drawImage(toOverlay, 0, 0, width, height, dx, dy, dx + width,
				dy + height, null);
		graphics.setComposite(AlphaComposite.SrcOver);

		if (decorationAreaType == DecorationAreaType.FOOTER) {
			// special case - draw lines on top
			int offset = comp.getLocationOnScreen().y
					- farthestOfTheSameAreaType.getLocationOnScreen().y;

			graphics.setColor(fillScheme.getDarkColor());
			graphics.drawLine(0, -offset, width, -offset);
			graphics.setColor(fillScheme.getExtraLightColor());
			graphics.drawLine(0, 1 - offset, width, 1 - offset);
		}

		if (decorationAreaType == DecorationAreaType.GENERAL) {
			// special case - draw lights
			int offsetX = comp.getLocationOnScreen().x
					- farthestOfTheSameAreaType.getLocationOnScreen().x;
			int offsetY = comp.getLocationOnScreen().y
					- farthestOfTheSameAreaType.getLocationOnScreen().y;

			int currTileX = -offsetX;
			while (currTileX < width) {
				graphics.drawImage(this.lightImage, currTileX, -offsetY, null);
				currTileX += this.lightImage.getWidth();
			}
		}
	}

	// private int getVerticalOffset(Component comp, DecorationAreaType type) {
	// int toLastDecorated = 0;
	// int total = 0;
	// while (true) {
	// int dy = comp.getY();
	// comp = comp.getParent();
	// if (comp == null)
	// break;
	// total += dy;
	// DecorationAreaType curr = SubstanceLookAndFeel
	// .getDecorationType(comp);
	// if (curr == type) {
	// toLastDecorated = total;
	// }
	// }
	// return toLastDecorated;
	// }

	private Component getFarthest(Component comp, DecorationAreaType type) {
		Component result = comp;
		while (true) {
			comp = comp.getParent();
			if (comp == null)
				break;
			DecorationAreaType curr = SubstanceLookAndFeel
					.getDecorationType(comp);
			if (curr == type) {
				result = comp;
			}
		}
		return result;
	}

	// private int getHorizontalOffset(Component comp, DecorationAreaType type)
	// {
	// int toLastDecorated = 0;
	// int total = 0;
	// while (true) {
	// int dx = comp.getX();
	// comp = comp.getParent();
	// if (comp == null)
	// break;
	// total += dx;
	// DecorationAreaType curr = SubstanceLookAndFeel
	// .getDecorationType(comp);
	// if (curr == type) {
	// toLastDecorated = total;
	// }
	// }
	// return toLastDecorated;
	// }

	private static BufferedImage getLightImage() {
		BufferedImage lightConeImage = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration().createCompatibleImage(200, 100,
						Transparency.TRANSLUCENT);
		Graphics2D lightConeGraphics = lightConeImage.createGraphics();
		lightConeGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		GeneralPath path = new GeneralPath();
		path.moveTo(100, 5);
		path.quadTo(155, 20, 155, 25);
		path.curveTo(110, 40, 110, 60, 100, 50);
		path.curveTo(90, 60, 90, 40, 45, 25);
		path.quadTo(45, 20, 100, 5);
		lightConeGraphics.setComposite(AlphaComposite.SrcOver.derive(0.5f));
		Color color = new Color(200, 200, 255);
		lightConeGraphics.setColor(color);
		lightConeGraphics
				.setPaint(new GradientPaint(0, 0, color, 0, 43, new Color(color
						.getRed(), color.getGreen(), color.getBlue(), 0)));
		lightConeGraphics.fill(path);
		lightConeGraphics.dispose();

		BlurFilter blurFilter = new BlurFilter();
		blurFilter.setUseAlpha(true);
		int kernelSide = 19;
		float[] kernelData = new float[kernelSide * kernelSide];
		for (int i = 0; i < kernelData.length; i++)
			kernelData[i] = 1.0f / kernelData.length;
		blurFilter.setKernel(new Kernel(kernelSide, kernelSide, kernelData));
		lightConeImage = blurFilter.filter(lightConeImage, null);

		BufferedImage finalImage = GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration().createCompatibleImage(150, 100,
						Transparency.TRANSLUCENT);
		Graphics2D finalGraphics = finalImage.createGraphics();
		finalGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		finalGraphics.drawImage(lightConeImage, -25, -10, null);
		AffineTransform at = AffineTransform.getQuadrantRotateInstance(2);
		at.translate(-83, -6);
		at.scale(0.3, 0.3);
		finalGraphics.setTransform(at);
		dialog_information.paint(finalGraphics);
		finalGraphics.dispose();

		return new HSBAdjustFilter(0.5f, 0.0f, 0.0f).filter(finalImage, null);
	}

	public static void main(String[] args) {
		GoldenBrownColorScheme headerScheme = new GoldenBrownColorScheme();
		DarkBrownColorScheme sidebarScheme = new DarkBrownColorScheme();

		Rectangle virtualBounds = new Rectangle();
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice[] gds = ge.getScreenDevices();
		for (GraphicsDevice gd : gds) {
			GraphicsConfiguration gc = gd.getDefaultConfiguration();
			virtualBounds = virtualBounds.union(gc.getBounds());
		}
		int screenWidth = virtualBounds.width;
		int screenHeight = virtualBounds.height;

		BufferedImage temp = ge.getDefaultScreenDevice()
				.getDefaultConfiguration().createCompatibleImage(screenWidth,
						screenHeight, Transparency.OPAQUE);
		temp.getGraphics().setColor(Color.white);
		temp.getGraphics().fillRect(0, 0, screenWidth, screenHeight);

		// BrushedMetalFilter mainFilter = new BrushedMetalFilter();
		// mainFilter.setAmount(10);
		// LookupFilter lookupFilter = new LookupFilter(new Gradient(new int[] {
		// 0, 64, 255 }, new int[] {
		// headerScheme.getUltraDarkColor().getRGB(),
		// headerScheme.getLightColor().getRGB(),
		// headerScheme.getUltraLightColor().getRGB() }));

		WoodFilter mainFilter = new WoodFilter();
		mainFilter.setScale(100);
		mainFilter.setAngle((float) Math.PI / 2.0f);
		mainFilter.setStretch(10);
		mainFilter.setTurbulence(0);
		mainFilter.setRings(0.4f);
		mainFilter.setFibres(0.3f);
		mainFilter.setgain(0.7f);
		LookupFilter lookupFilter = new LookupFilter(new Gradient(new int[] {
				0, 64, 196, 255 }, new int[] {
				sidebarScheme.getUltraDarkColor().getRGB(),
				sidebarScheme.getUltraDarkColor().getRGB(),
				sidebarScheme.getDarkColor().getRGB(),
				sidebarScheme.getLightColor().getRGB() }));

		final BufferedImage finalImage = new CompoundFilter(mainFilter,
				lookupFilter).filter(temp, null);

		final BufferedImage lightImage = getLightImage();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JPanel panel = new JPanel() {
					@Override
					protected void paintComponent(Graphics g) {
						int dy = getLocationOnScreen().y
								- SwingUtilities.getWindowAncestor(this)
										.getLocationOnScreen().y;
						int dx = getLocationOnScreen().x
								- SwingUtilities.getWindowAncestor(this)
										.getLocationOnScreen().x;
						g
								.drawImage(finalImage, 0, 0, getWidth(),
										getHeight(), dx, dy, dx + getWidth(),
										dy + getHeight(), null);
						g.setColor(new Color(90, 40, 0));
						g.fillRect(0, 0, getWidth(), getHeight());
						g.drawImage(lightImage, 0, 0, null);
					}
				};
				panel.setOpaque(false);

				JFrame frame = new JFrame();
				frame.add(panel, BorderLayout.CENTER);
				frame.setSize(500, 400);
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);

			}
		});
	}

	public BufferedImage getWatermarkImage(DecorationAreaType decorationAreaType) {
		if ((decorationAreaType == DecorationAreaType.PRIMARY_TITLE_PANE)
				|| (decorationAreaType == DecorationAreaType.SECONDARY_TITLE_PANE)
				|| (decorationAreaType == DecorationAreaType.HEADER)
				|| (decorationAreaType == DecorationAreaType.TOOLBAR)
				|| (decorationAreaType == DecorationAreaType.FOOTER)) {
			return this.brushedMetalImage;
		}

		if (decorationAreaType == DecorationAreaType.GENERAL) {
			return this.woodImage;
		}

		return null;
	}
}