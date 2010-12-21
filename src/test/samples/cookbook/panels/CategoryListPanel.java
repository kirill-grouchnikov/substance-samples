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

import java.awt.Dimension;

import javax.swing.JPanel;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.JCommandButton.CommandButtonKind;
import org.pushingpixels.flamingo.api.common.JCommandButtonStrip;

import test.samples.cookbook.EchoResizableIcon;
import test.samples.cookbook.ScaledResizableIcon;
import test.samples.cookbook.icons.applications_system;
import test.samples.cookbook.icons.list_add;
import test.samples.cookbook.icons.list_remove;

public class CategoryListPanel extends SingleContentPanel {
	public CategoryListPanel() {
		JCommandButtonStrip controlButtons = new JCommandButtonStrip();

		JCommandButton addButton = new JCommandButton("",
				new EchoResizableIcon(new list_add()));
		JCommandButton removeButton = new JCommandButton("",
				new EchoResizableIcon(new list_remove()));
		JCommandButton confButton = new JCommandButton("",
				new EchoResizableIcon(new ScaledResizableIcon(
						new applications_system(), 0.8)));
		confButton.setCommandButtonKind(CommandButtonKind.POPUP_ONLY);
		controlButtons.add(addButton);
		controlButtons.add(removeButton);
		controlButtons.add(confButton);

		JPanel statusBar = this.getFooterContentPanel();
		statusBar.add(controlButtons);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, 200);
	}
}
