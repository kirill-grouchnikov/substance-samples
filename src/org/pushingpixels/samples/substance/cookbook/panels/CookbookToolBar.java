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
package org.pushingpixels.samples.substance.cookbook.panels;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

import org.pushingpixels.flamingo.api.common.CommandToggleButtonGroup;
import org.pushingpixels.flamingo.api.common.JCommandButtonStrip;
import org.pushingpixels.flamingo.api.common.JCommandToggleButton;
import org.pushingpixels.samples.substance.cookbook.EchoResizableIcon;
import org.pushingpixels.samples.substance.cookbook.ScaledResizableIcon;
import org.pushingpixels.samples.substance.cookbook.svg.ic_format_size_white_24px;
import org.pushingpixels.samples.substance.cookbook.svg.ic_view_headline_white_24px;
import org.pushingpixels.samples.substance.cookbook.svg.ic_view_list_white_24px;
import org.pushingpixels.samples.substance.cookbook.svg.ic_view_stream_white_24px;

public class CookbookToolBar extends JToolBar {
    public CookbookToolBar() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setFloatable(false);

        JLabel smaller = new JLabel(new EchoResizableIcon(
                new ScaledResizableIcon(ic_format_size_white_24px.of(16, 16), 0.6f)));
        this.add(smaller);

        JSlider slider = new JSlider(0, 100, 80);
        this.add(slider);

        JLabel bigger = new JLabel(new EchoResizableIcon(
                new ScaledResizableIcon(ic_format_size_white_24px.of(16, 16), 0.8f)));
        this.add(bigger);

        this.add(Box.createHorizontalStrut(20));

        JCommandButtonStrip controlButtons = new JCommandButtonStrip();

        JCommandToggleButton headlineView = new JCommandToggleButton("", new EchoResizableIcon(
                new ScaledResizableIcon(ic_view_headline_white_24px.of(16, 16), 0.75f)));
        JCommandToggleButton listView = new JCommandToggleButton("", new EchoResizableIcon(
                new ScaledResizableIcon(ic_view_list_white_24px.of(16, 16), 0.75f)));
        JCommandToggleButton streamView = new JCommandToggleButton("", new EchoResizableIcon(
                new ScaledResizableIcon(ic_view_stream_white_24px.of(16, 16), 0.75f)));

        controlButtons.add(headlineView);
        controlButtons.add(listView);
        controlButtons.add(streamView);

        CommandToggleButtonGroup group = new CommandToggleButtonGroup();
        group.add(headlineView);
        group.add(listView);
        group.add(streamView);
        group.setSelected(headlineView, true);

        this.add(controlButtons);
    }
}
