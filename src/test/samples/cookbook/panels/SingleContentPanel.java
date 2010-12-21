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
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.Box;
import javax.swing.JPanel;

import org.pushingpixels.substance.api.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

/**
 * This class makes sure that the three application panels have the
 * continuously-looking footer bar at the bottom. The custom layout manager
 * handles the height consistency.
 * 
 * @author Kirill Grouchnikov
 */
public class SingleContentPanel extends JPanel {
	protected JPanel mainPanel;

	protected JPanel footerContentPanel;

	private JPanel footerPanel;

	public SingleContentPanel() {
		this.mainPanel = new JPanel();
		this.footerContentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		footerPanel = new JPanel(new BorderLayout(1, 1));
		footerPanel.add(Box.createHorizontalStrut(5), BorderLayout.EAST);
		footerPanel.add(this.footerContentPanel, BorderLayout.CENTER);
		footerPanel.add(Box.createHorizontalStrut(5), BorderLayout.WEST);

		this.setLayout(new ContentPanelLayoutManager());
		this.add(this.mainPanel);
		this.add(footerPanel);

		SubstanceLookAndFeel.setDecorationType(this.footerPanel,
				DecorationAreaType.FOOTER);
	}

	public JPanel getMainPanel() {
		return this.mainPanel;
	}

	public JPanel getFooterPanel() {
		return this.footerPanel;
	}

	public JPanel getFooterContentPanel() {
		return this.footerContentPanel;
	}

	protected class ContentPanelLayoutManager implements LayoutManager {
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
			int w = Math.max(mainPanel.getPreferredSize().width,
					footerContentPanel.getPreferredSize().width);
			int h = mainPanel.getPreferredSize().height
					+ footerPanel.getPreferredSize().height;
			return new Dimension(w, h);
		}

		@Override
		public void layoutContainer(Container parent) {
			Insets ins = parent.getInsets();
			mainPanel
					.setBounds(ins.left, ins.top, parent.getWidth() - ins.left
							- ins.right, parent.getHeight() - ins.top
							- ins.bottom - 32);
			footerPanel.setBounds(ins.left, parent.getHeight() - ins.top
					- ins.bottom - 32,
					parent.getWidth() - ins.left - ins.right, 32);
		}
	}
}
