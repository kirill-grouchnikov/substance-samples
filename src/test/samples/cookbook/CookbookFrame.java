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
package test.samples.cookbook;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;

import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceConstants.FocusKind;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import test.samples.cookbook.panels.CategoryListPanel;
import test.samples.cookbook.panels.CookbookToolBar;
import test.samples.cookbook.panels.RecipeListPanel;
import test.samples.cookbook.panels.SingleContentPanel;
import test.samples.cookbook.skin.CookbookSkin;

public class CookbookFrame extends JFrame {
	public CookbookFrame() {
		super("Cookbook");

		JPanel selectionPanel = new JPanel(new BorderLayout());
		JPanel subSelectionPanel = new JPanel(new BorderLayout());

		SingleContentPanel categoryListPanel = new CategoryListPanel();
		SingleContentPanel recipeListPanel = new RecipeListPanel();

		subSelectionPanel.add(categoryListPanel, BorderLayout.WEST);
		subSelectionPanel.add(recipeListPanel, BorderLayout.CENTER);
		// add the special component that simulates light holders
		selectionPanel.add(new JComponent() {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(10, 5);
			}

			@Override
			protected void paintComponent(Graphics g) {
				LinearGradientPaint lgp = new LinearGradientPaint(0, 0,
						getWidth(), 0, new float[] { 0.0f, 0.2f, 1.0f },
						new Color[] { new Color(228, 228, 228),
								new Color(144, 144, 144),
								new Color(228, 228, 228) });
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setPaint(lgp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				g2d.setColor(Color.black);
				g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

				LinearGradientPaint lgp2 = new LinearGradientPaint(0, 0,
						getWidth(), 0, new float[] { 0.0f, 0.2f, 0.7f, 1.0f },
						new Color[] { new Color(196, 196, 196),
								new Color(16, 16, 16), new Color(32, 32, 32),
								new Color(228, 228, 228) });
				g2d.setPaint(lgp2);
				g2d.drawLine(1, getHeight() - 1, getWidth() - 2,
						getHeight() - 1);

				g2d.dispose();
			}
		}, BorderLayout.NORTH);

		// mark the entire selection panel as GENERAL so that we have
		// continuous lights on the top
		SubstanceLookAndFeel.setDecorationType(subSelectionPanel,
				DecorationAreaType.GENERAL);
		selectionPanel.add(subSelectionPanel, BorderLayout.CENTER);

		SingleContentPanel recipePanel = new SingleContentPanel();

		// configure borders
		categoryListPanel.getMainPanel().setBorder(new CookbookBorderRight());
		categoryListPanel.getFooterPanel().setBorder(new CookbookBorderRight());
		recipeListPanel.getMainPanel().setBorder(new CookbookBorderLeft());
		recipeListPanel.getFooterPanel().setBorder(
				new CompoundBorder(new CookbookBorderLeft(),
						new CookbookBorderRight()));
		recipePanel.getFooterPanel().setBorder(new CookbookBorderLeft());

		this.add(selectionPanel, BorderLayout.WEST);
		this.add(recipePanel, BorderLayout.CENTER);

		this.add(new CookbookToolBar(), BorderLayout.NORTH);

		this.setIconImage(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(960, 536);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				SubstanceLookAndFeel.setSkin(new CookbookSkin());
				UIManager.put(SubstanceLookAndFeel.FOCUS_KIND, FocusKind.NONE);
				new CookbookFrame().setVisible(true);
			}
		});
	}
}
