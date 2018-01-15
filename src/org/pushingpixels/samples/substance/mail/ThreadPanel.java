/*
 * Copyright (c) 2005-2018 Substance Kirill Grouchnikov. All Rights Reserved.
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
package org.pushingpixels.samples.substance.mail;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.samples.substance.mail.svg.ic_access_alarm_black_24px;
import org.pushingpixels.samples.substance.mail.svg.ic_archive_black_24px;
import org.pushingpixels.samples.substance.mail.svg.ic_close_black_24px;
import org.pushingpixels.samples.substance.mail.svg.ic_delete_black_24px;
import org.pushingpixels.samples.substance.mail.svg.ic_forward_black_24px;
import org.pushingpixels.samples.substance.mail.svg.ic_history_black_24px;
import org.pushingpixels.samples.substance.mail.svg.ic_more_horiz_black_24px;
import org.pushingpixels.samples.substance.mail.svg.ic_reply_black_24px;
import org.pushingpixels.samples.substance.mail.svg.ic_view_list_black_24px;
import org.pushingpixels.substance.api.ComponentState;
import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.SubstanceSlices.ColorSchemeAssociationKind;
import org.pushingpixels.substance.api.SubstanceSlices.DecorationAreaType;
import org.pushingpixels.substance.api.colorscheme.SubstanceColorScheme;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Main destinations panel (leftmost under LTR). In the real app this will probably be a JList
 * backed by an adapter and custom renderer(s).
 */
public class ThreadPanel extends JPanel {
    public ThreadPanel() {
        this.setLayout(new VerticalStackLayout());

        SubstanceSkin currentSkin = SubstanceCortex.GlobalScope.getCurrentSkin();
        SubstanceColorScheme fillScheme = currentSkin.getColorScheme(DecorationAreaType.NONE,
                ColorSchemeAssociationKind.FILL, ComponentState.ENABLED);
        Color iconColor = fillScheme.getForegroundColor();
        Color backgroundColor = fillScheme.getLightColor();
        Color innerBackgroundColor = fillScheme.getUltraLightColor();

        this.add(getHeaderActionsPanel(iconColor, backgroundColor));
        this.add(getMessageTitlePanel("Keys found", backgroundColor));

        this.add(getCollapsedMessagePanel("Reception desk",
                "If you lost your keys stop by the reception desk", "10:25am", innerBackgroundColor,
                backgroundColor));
        this.add(getCollapsedMessagePanel("Bryce Dunwood", "I think those might be Grayson's",
                "10:28am", innerBackgroundColor, backgroundColor));
        this.add(getFullMessagePanel("Reception desk", "Today, 4:15pm",
                "Bryce Dunwood, Grayson Flay",
                "Thanks, Bryce.\n\nGrayson, can you check if you still have your keys?\n\n"
                        + "It's a silver keychain with five keys and a small elephant. "
                        + "If these are yours, please stop by. We'll be here until six today.\n\n"
                        + "Morgan from reception.",
                innerBackgroundColor, backgroundColor, iconColor));

        this.add(getFooterActionsPanel(backgroundColor, iconColor));

        this.setBackground(backgroundColor);

        this.setPreferredSize(new Dimension(400, 0));
    }

    private JPanel getHeaderActionsPanel(Color iconColor, Color backgroundColor) {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.LEADING, 24, 0));
        result.setBorder(new EmptyBorder(14, 4, 14, 4));

        result.add(new JLabel(ic_close_black_24px.of(16, 16).colorize(iconColor, 0.8f)));
        result.add(new JLabel(ic_view_list_black_24px.of(16, 16).colorize(iconColor, 0.8f)));
        result.add(new JLabel(ic_access_alarm_black_24px.of(16, 16).colorize(iconColor, 0.8f)));
        result.add(new JLabel(ic_archive_black_24px.of(16, 16).colorize(iconColor, 0.8f)));
        result.add(new JLabel(ic_delete_black_24px.of(16, 16).colorize(iconColor, 0.8f)));

        result.setBackground(backgroundColor);

        return result;
    }

    private JPanel getMessageTitlePanel(String title, Color backgroundColor) {
        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(new EmptyBorder(6, 12, 16, 12));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(SubstanceCortex.GlobalScope.getFontPolicy().getFontSet("Substance", null)
                .getControlFont().deriveFont(Font.BOLD, 19.0f));
        result.add(titleLabel);

        result.setBackground(backgroundColor);

        return result;
    }

    private JPanel getCollapsedMessagePanel(String from, String summary, String date,
            Color innerBackground, Color outerBackground) {
        DefaultFormBuilder builder = new DefaultFormBuilder(
                new FormLayout("center:pref, 8dlu, 0dlu:grow, 8dlu, right:pref", ""))
                        .border(new EmptyBorder(8, 16, 8, 16));
        Font baseFont = SubstanceCortex.GlobalScope.getFontPolicy().getFontSet("Substance", null)
                .getControlFont();

        JLabel senderLabel = new JLabel(from);
        senderLabel.setFont(baseFont.deriveFont(Font.BOLD));
        builder.append(senderLabel);

        builder.append(new JLabel(summary));

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(baseFont.deriveFont(baseFont.getSize() - 2.0f));
        builder.append(dateLabel);

        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(new EmptyBorder(2, 8, 2, 8));
        result.setBackground(outerBackground);

        JPanel inner = builder.build();
        inner.setOpaque(true);
        inner.setBackground(innerBackground);
        result.add(inner, BorderLayout.CENTER);

        return result;
    }

    private JPanel getFullMessagePanel(String from, String date, String to, String message,
            Color innerBackground, Color outerBackground, Color iconColor) {
        DefaultFormBuilder firstRow = new DefaultFormBuilder(new FormLayout(
                "0dlu:grow, 8dlu, right:pref, 8dlu, center:pref, 8dlu, center:pref", ""))
                        .border(new EmptyBorder(8, 16, 2, 16));
        Font baseFont = SubstanceCortex.GlobalScope.getFontPolicy().getFontSet("Substance", null)
                .getControlFont();

        JLabel senderLabel = new JLabel(from);
        senderLabel.setFont(baseFont.deriveFont(Font.BOLD));
        firstRow.append(senderLabel);

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(baseFont.deriveFont(baseFont.getSize() - 2.0f));
        firstRow.append(dateLabel);

        firstRow.append(new JLabel(ic_reply_black_24px.of(14, 14).colorize(iconColor, 0.8f)));
        firstRow.append(new JLabel(ic_more_horiz_black_24px.of(14, 14).colorize(iconColor, 0.8f)));

        JLabel toLabel = new JLabel("To: " + to);
        toLabel.setBorder(new EmptyBorder(0, 16, 24, 16));

        JEditorPane messagePane = new JEditorPane();
        messagePane.setContentType("text/plain");
        messagePane.setBorder(new EmptyBorder(0, 16, 0, 16));
        messagePane.setBackground(innerBackground);
        messagePane.setText(message);

        Color historyColor = new Color(32, 96, 148);
        JLabel historyLabel = new JLabel("Show History",
                ic_history_black_24px.of(12, 12).colorize(historyColor), JLabel.LEADING);
        historyLabel.setBorder(new EmptyBorder(24, 16, 16, 16));
        historyLabel.setForeground(historyColor);
        SubstanceCortex.ComponentOrParentChainScope.setColorizationFactor(historyLabel, 1.0);

        JPanel result = new JPanel(new BorderLayout());
        result.setBorder(new EmptyBorder(2, 8, 2, 8));
        result.setBackground(outerBackground);

        JPanel inner = new JPanel(new VerticalStackLayout());
        inner.setOpaque(true);
        inner.setBackground(innerBackground);
        inner.add(firstRow.build());
        inner.add(toLabel);
        inner.add(messagePane);
        inner.add(historyLabel);

        result.add(inner, BorderLayout.CENTER);

        return result;
    }

    private JPanel getFooterActionsPanel(Color backgroundColor, Color iconColor) {
        JPanel result = new JPanel(new FlowLayout(FlowLayout.TRAILING, 8, 0));
        result.setBorder(new EmptyBorder(16, 24, 16, 0));

        JButton reply = new JButton("Reply",
                ic_reply_black_24px.of(14, 14).colorize(iconColor, 0.8f));
        JButton forward = new JButton("Forward",
                ic_forward_black_24px.of(14, 14).colorize(iconColor, 0.8f));
        // Mark the button panel to be flat - effectively marking both action buttons as flat
        SubstanceCortex.ComponentOrParentScope.setFlatBackground(result, true);

        result.add(reply);
        result.add(forward);

        result.setBackground(backgroundColor);

        return result;
    }
}
