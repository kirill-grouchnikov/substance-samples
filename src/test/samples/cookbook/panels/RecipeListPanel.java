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
package test.samples.cookbook.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BoundedRangeModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButtonStrip;
import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceConstants;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import test.samples.cookbook.EchoResizableIcon;
import test.samples.cookbook.icons.list_add;
import test.samples.cookbook.icons.list_remove;

public class RecipeListPanel extends SingleContentPanel {
	public RecipeListPanel() {
		JPanel mainPanel = this.getMainPanel();
		JPanel recipePanel = new JPanel();
		JScrollPane scroll = new JScrollPane(recipePanel);
		scroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// put the scroll buttons together
		scroll.putClientProperty(
				SubstanceLookAndFeel.SCROLL_PANE_BUTTONS_POLICY,
				SubstanceConstants.ScrollPaneButtonPolicyKind.ADJACENT);
		// remove the scroll pane border
		scroll.setBorder(new EmptyBorder(0, 0, 0, 0));
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(scroll, BorderLayout.CENTER);

		// set scroll mode to simple so that the light bar
		// does not have visual artifacts during scrolling
		// the contents
		scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		final JCommandButtonStrip controlButtons = new JCommandButtonStrip();

		JCommandButton addButton = new JCommandButton("",
				new EchoResizableIcon(new list_add()));
		JCommandButton removeButton = new JCommandButton("",
				new EchoResizableIcon(new list_remove()));
		controlButtons.add(addButton);
		controlButtons.add(removeButton);

		final JTextField searchTextField = new JTextField(15);
		// mark the search text field with NONE decoration so that it
		// has white background
		SubstanceLookAndFeel.setDecorationType(searchTextField,
				DecorationAreaType.NONE);

		LayoutManager layout = new LayoutManager() {
			@Override
			public void addLayoutComponent(String name, Component comp) {
			}

			@Override
			public void removeLayoutComponent(Component comp) {
			}

			@Override
			public Dimension minimumLayoutSize(Container parent) {
				return this.preferredLayoutSize(parent);
			}

			@Override
			public Dimension preferredLayoutSize(Container parent) {
				int width = controlButtons.getPreferredSize().width
						+ searchTextField.getPreferredSize().width;
				return new Dimension(width + 20, controlButtons
						.getPreferredSize().height);
			}

			@Override
			public void layoutContainer(Container parent) {
				Insets ins = parent.getInsets();

				Dimension prefButtons = controlButtons.getPreferredSize();
				Dimension prefText = searchTextField.getPreferredSize();

				int availableHeight = parent.getHeight() - ins.top - ins.bottom;
				controlButtons.setBounds(ins.left, ins.top
						+ (availableHeight - prefButtons.height) / 2,
						prefButtons.width, prefButtons.height);

				searchTextField.setBounds(parent.getWidth() - ins.left
						- ins.right - prefText.width, ins.top
						+ (availableHeight - prefText.height) / 2,
						prefText.width, prefText.height);
			}
		};

		JPanel panel = new JPanel(layout);
		panel.add(controlButtons);
		panel.add(searchTextField);

		JPanel statusBar = this.getFooterContentPanel();
		statusBar.add(panel);

		// scroll down. The preferred sizes are here to
		// make the scroll thumb appear
		recipePanel.setPreferredSize(new Dimension(280, 1800));
		scroll.setPreferredSize(new Dimension(280, 300));
		final BoundedRangeModel verticalScrollModel = scroll
				.getVerticalScrollBar().getModel();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				verticalScrollModel.setValue(800);
			}
		});
	}
}
