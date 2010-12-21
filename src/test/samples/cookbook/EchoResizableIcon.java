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

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImageOp;

import org.pushingpixels.flamingo.api.common.icon.FilteredResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import com.jhlabs.image.CompoundFilter;
import com.jhlabs.image.Gradient;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.HSBAdjustFilter;
import com.jhlabs.image.LookupFilter;

public class EchoResizableIcon implements ResizableIcon {
	private ResizableIcon echo;

	private ResizableIcon main;

	static BufferedImageOp iconInvertFilter = new CompoundFilter(
			new HSBAdjustFilter(0, 0, 1.0f), new CompoundFilter(
					new GrayscaleFilter(), new HSBAdjustFilter(0, 0, 1.0f)));
	static BufferedImageOp iconShadowFilter = new CompoundFilter(
			new GrayscaleFilter(), new LookupFilter(new Gradient(new int[] { 0,
					240, 255 },
					new int[] { 0x00000000, 0x88000000, 0x88000000 })));

	public EchoResizableIcon(ResizableIcon original) {
		this.echo = new FilteredResizableIcon(original, iconShadowFilter);
		this.main = new FilteredResizableIcon(original, iconInvertFilter);
	}

	@Override
	public void setDimension(Dimension newDimension) {
		this.echo.setDimension(newDimension);
		this.main.setDimension(newDimension);
	}

	@Override
	public int getIconHeight() {
		return this.main.getIconHeight();
	}

	@Override
	public int getIconWidth() {
		return this.main.getIconWidth();
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		this.echo.paintIcon(c, g, x, y - 1);
		this.main.paintIcon(c, g, x, y);
	}

}
