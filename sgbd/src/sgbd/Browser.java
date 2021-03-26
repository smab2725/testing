package sgbd;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class Browser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4372330298263558257L;
	private JLabel imageLabel;
	private JPanel PdfPanel;
	boolean scaled = false;
	JScrollPane scroll;

	public Browser() throws IOException {

		setLayout(new BorderLayout());
		imageLabel = new JLabel("Cliquez sur le bouton œil pour afficher 1", SwingConstants.CENTER);
		scroll = new JScrollPane(imageLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		imageLabel.setFont(new Font("Arial", Font.BOLD, 20));
		imageLabel.setOpaque(true);
		add(scroll, BorderLayout.CENTER);

	}

	public void load(URL file) {

		if (file.toExternalForm().endsWith(".pdf")) {

			SwingController controller = new SwingController();
			SwingViewBuilder factory = new SwingViewBuilder(controller);
			PdfPanel = factory.buildViewerPanel();
			ComponentKeyBinding.install(controller, this);
			System.getProperties().put("org.icepdf.core.scaleImages", "true");
			System.getProperties().put("org.icepdf.core.imageReference", "scaled");
			controller.openDocument(file);
			this.removeAll();
			this.add(PdfPanel, BorderLayout.CENTER);
			revalidate();
			repaint();

		} else {

			try {

				imageLabel.setText("");
				imageLabel.setIcon(new ImageIcon(
						ImageIO.read(file).getScaledInstance(631, 411, Image.SCALE_AREA_AVERAGING)));

				imageLabel.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						super.mouseClicked(e);

						if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
							try {
								if (scaled == false) {

									imageLabel.setIcon(new ImageIcon(ImageIO.read(file)));
								}

								else {

									imageLabel.setIcon(new ImageIcon(ImageIO.read(file).getScaledInstance(631,
											411, Image.SCALE_AREA_AVERAGING)));
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							scaled = !scaled;
						}
					}
				});
				this.removeAll();
				this.add(scroll, BorderLayout.CENTER);
				revalidate();
				repaint();

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
