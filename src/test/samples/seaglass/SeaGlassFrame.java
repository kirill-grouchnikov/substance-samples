package test.samples.seaglass;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import test.check.SampleFrame;
import test.samples.seaglass.skin.SeaGlassSkin;

public class SeaGlassFrame extends SampleFrame {
	public SeaGlassFrame() {
		super();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				SubstanceLookAndFeel.setSkin(new SeaGlassSkin());
				JFrame.setDefaultLookAndFeelDecorated(true);
				SeaGlassFrame sf = new SeaGlassFrame();
				sf.setSize(338, 245);
				sf.setLocationRelativeTo(null);
				sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				sf.setVisible(true);
			}
		});
	}

}
