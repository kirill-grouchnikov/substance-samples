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
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

import org.pushingpixels.flamingo.api.common.CommandToggleButtonGroup;
import org.pushingpixels.flamingo.api.common.JCommandButtonStrip;
import org.pushingpixels.flamingo.api.common.JCommandToggleButton;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import test.samples.cookbook.EchoResizableIcon;
import test.samples.cookbook.ScaledResizableIcon;
import test.samples.cookbook.icons.applications_other;
import test.samples.cookbook.icons.edit_redo;
import test.samples.cookbook.icons.weather_clear;

public class CookbookToolBar extends JToolBar {
	public CookbookToolBar() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setFloatable(false);

		ResizableIcon smallerIcon = new ScaledResizableIcon(
				new EchoResizableIcon(new applications_other()), 0.5);
		smallerIcon.setDimension(new Dimension(16, 16));
		JLabel smaller = new JLabel(smallerIcon);
		this.add(smaller);

		JSlider slider = new JSlider(0, 100, 80);
		this.add(slider);

		ResizableIcon biggerIcon = new EchoResizableIcon(
				new applications_other());
		biggerIcon.setDimension(new Dimension(16, 16));
		JLabel bigger = new JLabel(biggerIcon);
		this.add(bigger);

		this.add(Box.createHorizontalStrut(20));

		JCommandButtonStrip controlButtons = new JCommandButtonStrip();

		CommandToggleButtonGroup group = new CommandToggleButtonGroup();
		JCommandToggleButton button1 = new JCommandToggleButton("",
				new EchoResizableIcon(new edit_redo()));
		group.add(button1);
		JCommandToggleButton button2 = new JCommandToggleButton("",
				new EchoResizableIcon(new weather_clear()));
		group.add(button2);
		controlButtons.add(button1);
		controlButtons.add(button2);

		group.setSelected(button1, true);

		this.add(controlButtons);
	}
}
