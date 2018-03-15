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
package org.pushingpixels.samples.substance.chat;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.samples.substance.chat.svg.ic_chat_black_24px;
import org.pushingpixels.samples.substance.chat.svg.ic_help_outline_black_24px;
import org.pushingpixels.samples.substance.chat.svg.ic_person_black_24px;
import org.pushingpixels.substance.api.SubstanceCortex;
import org.pushingpixels.substance.api.SubstanceCortex.ComponentOrParentChainScope;
import org.pushingpixels.substance.api.SubstanceSlices;
import org.pushingpixels.substance.api.SubstanceSlices.DecorationAreaType;
import org.pushingpixels.substance.api.SubstanceSlices.FocusKind;
import org.pushingpixels.substance.api.skin.ModerateSkin;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class ChatFrame extends JFrame {
    public ChatFrame() {
        this.getContentPane().setLayout(new BorderLayout());

        SubstanceCortex.WindowScope.extendContentIntoTitlePane(this,
                SubstanceSlices.HorizontalGravity.LEADING,
                SubstanceSlices.VerticalGravity.CENTERED);
        SubstanceCortex.WindowScope.setPreferredTitlePaneHeight(this, 40);

        DefaultFormBuilder titlePaneBuilder = new DefaultFormBuilder(
                new FormLayout("pref, 8dlu, center:0dlu:grow, 8dlu, pref, 8dlu, pref, 8dlu, pref", ""))
                        .border(new EmptyBorder(8, 12
                                + SubstanceCortex.WindowScope.getTitlePaneControlInsets(this).left,
                                0, 12));

        titlePaneBuilder.append(new JButton("New chat"));

        JLabel titleLabel = new JLabel("Chat", ic_chat_black_24px.of(16, 16), JLabel.CENTER);
        titleLabel.setIconTextGap(6);
        titleLabel.setFont(SubstanceCortex.GlobalScope.getFontPolicy().getFontSet("Substance", null)
                .getWindowTitleFont().deriveFont(16.0f));
        titlePaneBuilder.append(titleLabel);
        
        titlePaneBuilder.append(new JTextField(12));
        titlePaneBuilder.append(new JLabel(ic_help_outline_black_24px.of(14, 14)));
        titlePaneBuilder.append(new JLabel(ic_person_black_24px.of(12, 12)));

        JPanel titlePane = titlePaneBuilder.build();
        titlePane.setPreferredSize(new Dimension(100, 40));
        titlePane.setOpaque(true);
        ComponentOrParentChainScope.setDecorationType(titlePane,
                DecorationAreaType.PRIMARY_TITLE_PANE);

        this.getContentPane().add(titlePane, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Configure the main skin
            SubstanceCortex.GlobalScope.setSkin(new ModerateSkin());
            SubstanceCortex.GlobalScope.setFocusKind(FocusKind.NONE);
            JFrame.setDefaultLookAndFeelDecorated(true);

            // Create the main frame
            ChatFrame chatFrame = new ChatFrame();

            // Set initial size, center in screen, configure to exit the app on clicking the
            // close button
            chatFrame.setSize(900, 600);
            chatFrame.setLocationRelativeTo(null);
            chatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // And finally make the main frame visible
            chatFrame.setVisible(true);
        });
    }

}
