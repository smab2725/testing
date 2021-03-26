package sgbd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class DataPanel extends JPanel {

	JLabel xml, json;
	JProgressBar xmlp, jsonp;
	JLabel nommois, nomJour;
	int jsonval, xmlval;
	JButton telecharger, voir, extraire;

	public DataPanel() {
		setLayout(new BorderLayout(10, 10));
		nommois = new JLabel("", JLabel.CENTER);
		nommois.setFont(new Font("Arial", Font.BOLD, 14));

		nomJour = new JLabel("2021 / mars/ 01", JLabel.CENTER);
		nomJour.setFont(new Font("Arial", Font.BOLD, 14));

		xml = new JLabel("XML");
		json = new JLabel("JSON");
		xmlp = new JProgressBar(0, 100);
		jsonp = new JProgressBar(0, 100);
		telecharger = new JButton();
		voir = new JButton();
		extraire = new JButton();
		JPanel pn = new JPanel();
		pn.setLayout(null);
		nommois.setBounds(50, 10, 150, 20);
		json.setBounds(30, 40, 80, 20);
		jsonp.setBounds(75, 40, 150, 20);
		xml.setBounds(30, 75, 80, 20);
		xmlp.setBounds(75, 75, 150, 20);
		nomJour.setBounds(50, 150, 150, 20);
		telecharger.setBounds(60, 180, 40, 30);
		voir.setBounds(106, 180, 40, 30);
		extraire.setBounds(150, 180, 40, 30);

		telecharger.setToolTipText("Telecharger");
		voir.setToolTipText("Voir");
		extraire.setToolTipText("Extraire");

		xml.setToolTipText("XML");
		json.setToolTipText("JSON");

		xml.setFont(new Font("Arial", Font.BOLD, 12));
		json.setFont(new Font("Arial", Font.BOLD, 12));

		try {
			telecharger.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("download.png")).getScaledInstance(
					telecharger.getWidth() / 2, telecharger.getHeight() / 2, Image.SCALE_AREA_AVERAGING)));
			voir.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("voir.png")).getScaledInstance(
					telecharger.getWidth() / 2, telecharger.getHeight() / 2, Image.SCALE_AREA_AVERAGING)));
			extraire.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("agre.png")).getScaledInstance(
					telecharger.getWidth() / 2, telecharger.getHeight() / 2, Image.SCALE_AREA_AVERAGING)));
		} catch (Exception e) {
		}

		pn.add(nommois);
		pn.add(json);
		pn.add(jsonp);
		pn.add(xml);
		pn.add(xmlp);
		pn.add(nomJour);
		pn.add(telecharger);
		pn.add(voir);
		pn.add(extraire);

		add(pn, BorderLayout.CENTER);

		xmlp.setStringPainted(true);
		jsonp.setStringPainted(true);
		jsonp.setFont(new Font("Arial", Font.BOLD, 10));
		xmlp.setFont(new Font("Arial", Font.BOLD, 10));

		voir.addActionListener((e) -> {

			int res = new Dialog(voir, "Ouvrir", "Voulez vous ouvrir Image ou PDF", "Image", "PDF").openDialog();
			if (res == 0)
				Window.browser.load(getClass().getResource("covidmars.jpg"));
			if (res == 1)
				Window.browser.load(getClass().getResource("covidmars.pdf"));
			else
				;

		});

	}

	public void setData(String name, int jsonval, int xmlval) {
		this.xmlval = xmlval;
		this.jsonval = jsonval;
		this.nommois.setText(name);
		this.xmlp.setValue(this.xmlval);
		this.jsonp.setValue(this.jsonval);

		if (this.jsonval == 100)
			jsonp.setForeground(new Color(99, 166, 105));
		else
			jsonp.setForeground(Color.red);
		if (this.xmlval == 100)
			xmlp.setForeground(new Color(99, 166, 105));
		else
			xmlp.setForeground(Color.red);
	}

}
