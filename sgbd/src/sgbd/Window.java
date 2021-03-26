package sgbd;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.icons.FlatTreeOpenIcon;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel parent, login, dataAcquisition, logo, connexion, header, body, pnelmin, panelclose;
	static Browser browser;
	JLabel applogo, register, usernameIcon, passwordIcon, photo, nom, fonction, vide1, vide2, background, min, close,
			timer;
	JTextField username;
	JPasswordField password;
	JButton submit, reset, load, deconnextion;
	JRadioButton dark;
	JCheckBox show;
	JTree tree;
	JScrollPane sp;
	JFileChooser fc;

	Window() throws IOException {

		setSize(1200, 750);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Page De Connexion");
		try {
			setIconImage(ImageIO.read(Window.class.getResource("icon.png")));
		} catch (Exception e) {
		}

		setUndecorated(true);

		setLayout(null);
		parent = new JPanel();
		parent.setBounds(40, 60, getWidth() - 100, getHeight() - 100);
		parent.setOpaque(true);
		add(parent);
		CardLayout layout = new CardLayout();

		parent.setLayout(layout);

		timer = new JLabel("2020 / 10 / 3 15:22:30");
		timer.setFont(new Font("Arial", Font.BOLD, 15));
		timer.setForeground(Color.lightGray);
		timer.setBounds(470, 7, 300, 30);
		add(timer);

		Timer tm = new Timer(true);
		tm.schedule(new TimerTask() {

			@Override
			public void run() {
				Date dt = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("EEEE  'le' dd/MM/yy  HH:mm:ss a z", Locale.FRANCE);
				sdf.setLenient(true);
				timer.setText(sdf.format(dt));
				repaint();
			}
		}, 1000, 1000);

		min = new JLabel("—", JLabel.CENTER);
		close = new JLabel("X", JLabel.CENTER);
		min.setBounds(1130, 0, 35, 35);
		close.setBounds(1165, 0, 35, 35);
		min.setFont(new Font("Arial", Font.BOLD, 14));
		close.setFont(new Font("Arial", Font.BOLD, 14));
		add(min);
		add(close);
		min.setOpaque(true);
		close.setOpaque(true);

		min.setBackground(new Color(100, 100, 100, 0));
		close.setBackground(new Color(100, 100, 100, 0));
		min.setForeground(Color.white);
		close.setForeground(Color.white);

		min.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				super.mouseExited(e);

				min.setBackground(new Color(100, 100, 100, 0));
				repaint();

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				super.mouseEntered(e);
				min.setBackground(new Color(198, 200, 207, 50));
				repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				super.mouseClicked(e);
				Window.this.setState(1);
			}
		});
		close.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {

				super.mouseExited(e);
				close.setBackground(new Color(100, 100, 100, 0));
				repaint();

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				super.mouseEntered(e);
				close.setBackground(new Color(232, 17, 35));
				repaint();

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				super.mouseClicked(e);
				System.exit(0);
			}
		});

		login = new JPanel();
		dataAcquisition = new JPanel();
		dark = new JRadioButton();
		dark.setFocusable(false);
		dark.setFont(new Font("Arial", Font.BOLD, 12));
		logo = new JPanel();
		connexion = new JPanel();
		parent.add(login);
		parent.add(dataAcquisition);
		login.setLayout(null);

		login.setBackground(new Color(255, 255, 255, 0));
		dataAcquisition.setBackground(new Color(255, 255, 255, 0));
		logo.setBounds(150, 150, 400, 350);
		connexion.setBounds(550, 150, 400, 350);
		dark.setBounds(60, 20, 100, 20);
		login.add(logo);
		login.add(connexion);
		add(dark);

		background = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("covid.png"))
				.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING)));

		background.setBounds(0, 0, this.getWidth(), this.getHeight());
		add(background);

		background.addMouseMotionListener(new MouseMotionListener() {

			int xx = 0;
			int yy = 0;

			@Override
			public void mouseMoved(MouseEvent e) {

				xx = e.getX();
				yy = e.getY();
			}

			@Override
			public void mouseDragged(MouseEvent e) {

				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				Window.this.setLocation(x - xx, y - yy);

			}
		});

		// darkmode

		dark.setText("Dark Mode");
		dark.addActionListener((e) -> {

			if (e.getSource() == dark) {

				if (dark.isSelected()) {
					try {
						// browser.engine.loadContent("<body style='background : #3C3F41;'></body>");
						background.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("covid-dark.png"))
								.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING)));
						applogo.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("icon-dark.png"))
								.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_AREA_AVERAGING)));
						UIManager.setLookAndFeel(new FlatDarculaLaf());
						browser.setBackground(Color.decode("#3C3F41"));
						for (java.awt.Window window : this.getWindows()) {
							SwingUtilities.updateComponentTreeUI(window);
						}
						this.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} else {
					try {
						// browser.engine.loadContent("<body style='background : #FFFFFF;'></body>");
						background.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("covid.png"))
								.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_AREA_AVERAGING)));
						applogo.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("icon.png"))
								.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_AREA_AVERAGING)));
						UIManager.setLookAndFeel(new FlatIntelliJLaf());
						for (java.awt.Window window : this.getWindows()) {
							SwingUtilities.updateComponentTreeUI(window);
						}
						this.repaint();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			}
		});

		// connexion.setBorder(new LineBorder(Color.decode("#A2B6B6")));

		// logo
		applogo = new JLabel();
		logo.setLayout(new BorderLayout());
		logo.add(applogo, BorderLayout.CENTER);
		try {

			applogo.setIcon(new ImageIcon(ImageIO.read(getClass().getResource("icon.png"))
					.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_AREA_AVERAGING)));

		} catch (Exception e) {

		}

		// connexion

		register = new JLabel("Connexion", SwingConstants.CENTER);
		register.setFont(new Font("Arial", Font.BOLD, 20));
		username = new JTextField();
		password = new JPasswordField();
		password.setEchoChar('\u25CF');
		show = new JCheckBox("Afficher", new ImageIcon(getClass().getResource("invisible.png")));

		usernameIcon = new JLabel("Identifiant: ", new ImageIcon(getClass().getResource("user.png")),
				SwingConstants.LEFT);
		passwordIcon = new JLabel("Mot de passe: ", new ImageIcon(getClass().getResource("lock.png")),
				SwingConstants.LEFT);

		submit = new JButton("Connexion", new ImageIcon(getClass().getResource("login.png")));
		reset = new JButton("Effacer", new ImageIcon(getClass().getResource("reset.png")));

		// voir le password
		show.addActionListener((e) -> {

			if (show.isSelected()) {
				password.setEchoChar('\0');
				show.setIcon(new ImageIcon(getClass().getResource("visible.png")));
			} else {
				password.setEchoChar('\u25CF');
				show.setIcon(new ImageIcon(getClass().getResource("invisible.png")));
			}
		});

		connexion.setLayout(null);

		register.setBounds(100, 20, 200, 40);
		usernameIcon.setBounds(20, 100, 120, 30);
		username.setBounds(150, 100, 220, 30);
		passwordIcon.setBounds(20, 150, 120, 30);
		password.setBounds(150, 150, 220, 30);
		show.setBounds(145, 190, 100, 30);
		submit.setBounds(30, 240, 160, 30);
		reset.setBounds(207, 240, 160, 30);

		connexion.add(register);
		connexion.add(usernameIcon);
		connexion.add(username);
		connexion.add(passwordIcon);
		connexion.add(password);
		connexion.add(show);
		connexion.add(submit);
		connexion.add(reset);

		// actions
		ActionListener ac = ((e) -> {

			if (e.getSource() == reset) {
				password.setText("");
				username.setText("");
			}

			if (e.getSource() == submit) {

				/*
				 * if (username.getText().equals("i") && new
				 * String(password.getPassword()).equals("20")) { layout.next(container); }
				 */

				this.setTitle("Data Aquisition");
				layout.next(parent);
				this.repaint();
			}
		});

		reset.addActionListener(ac);
		submit.addActionListener(ac);

		connexion.setFocusable(true);
		connexion.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					layout.next(parent);
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					layout.next(parent);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					layout.next(parent);
				}

			}
		});

		// dataquisition
		dataAcquisition.setLayout(null);

		header = new JPanel();
		body = new JPanel();
		photo = new JLabel();
		nom = new JLabel("Nom: Sidi Brahim");
		fonction = new JLabel("Fonction: Etudiant");
		vide1 = new JLabel("Vide1: ya rien");
		vide2 = new JLabel("Vide2: ya rien");
		load = new JButton("Charger");
		deconnextion = new JButton("Deconnextion");

		header.setBounds(20, 0, this.getWidth(), this.getHeight() / 4);
		dataAcquisition.add(header);

		header.setLayout(null);
		photo.setBounds(20, 20, 150, 150);
		header.add(photo);

		Image im = null;
		try {

			im = ImageIO.read(getClass().getResource("photo.png")).getScaledInstance(photo.getWidth(),
					photo.getHeight(), Image.SCALE_AREA_AVERAGING);
		} catch (Exception e2) {
		}

		photo.setIcon(new ImageIcon(im));
		photo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		photo.setToolTipText("Clicker ici pour changer la photo");

		fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new FileNameExtensionFilter("images", "PNG", "JPG", "JPEG"));
		photo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int rs = fc.showDialog(null, "Choisir");
				if (rs == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						photo.setIcon(new ImageIcon(ImageIO.read(file).getScaledInstance(photo.getWidth(),
								photo.getHeight(), Image.SCALE_AREA_AVERAGING)));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		nom.setBounds(220, 20, 150, 30);
		fonction.setBounds(220, 60, 150, 30);
		vide1.setBounds(220, 100, 150, 30);
		vide2.setBounds(220, 140, 150, 30);
		load.setBounds(940, 60, 120, 90);
		deconnextion.setBounds(940, 25, 120, 30);
		header.add(nom);
		header.add(fonction);
		header.add(vide1);
		header.add(vide2);
		header.add(load);
		header.add(deconnextion);

		deconnextion.addActionListener((e) -> {
			layout.first(parent);
			repaint();
		});

		// confirmation
		load.addActionListener((e) -> {

			String passer = JOptionPane.showInputDialog("Confirmer votre mot de passe");
			String res = "Nom Confirme !";
			if (passer.equals("2020")) {
				res = "Confirme";
			}

			int option = JOptionPane.showConfirmDialog(this, res, "Message", JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.YES_OPTION) {

				passer = JOptionPane.showInputDialog("Confirmer votre mot de passe");
				res = "Nom Confirme !";
				if (passer.equals("2020")) {
					res = "Confirme";
				}

			} else {

			}

		});

		load.setIcon(new ImageIcon(ImageIO.read(Window.class.getResource("data.png"))
				.getScaledInstance(load.getWidth() / 3, load.getHeight() / 2, Image.SCALE_AREA_AVERAGING)));

		// tree

		String[] monthnames = { "janvier", "février", "mars", "avril", "mai", "juin", "juillet", "août", "septembre",
				"octobre", "novembre", "décembre" };

		int[] daynames = new int[30];

		for (int i = 0; i < 30; i++)
			daynames[i] = i + 1;
		DefaultMutableTreeNode[] moths2020 = new DefaultMutableTreeNode[monthnames.length];
		DefaultMutableTreeNode[] moths2021 = new DefaultMutableTreeNode[monthnames.length];
		DefaultMutableTreeNode[] days2020 = new DefaultMutableTreeNode[daynames.length];
		DefaultMutableTreeNode[] days2021 = new DefaultMutableTreeNode[daynames.length];

		DefaultMutableTreeNode year = new DefaultMutableTreeNode("Annees");
		DefaultMutableTreeNode year2020 = new DefaultMutableTreeNode("2020");
		DefaultMutableTreeNode year2021 = new DefaultMutableTreeNode("2021");
		year.add(year2020);
		year.add(year2021);

		for (int i = 0; i < monthnames.length; i++) {

			moths2020[i] = new DefaultMutableTreeNode(monthnames[i]);
			moths2021[i] = new DefaultMutableTreeNode(monthnames[i]);
			year2020.add(moths2020[i]);
			year2021.add(moths2021[i]);

		}

		for (int i = 0; i < moths2020.length; i++) {

			for (int j = 0; j < days2020.length; j++) {
				days2020[j] = new DefaultMutableTreeNode(daynames[j]);
				days2021[j] = new DefaultMutableTreeNode(daynames[j]);
				moths2020[i].add(days2020[j]);
				moths2021[i].add(days2021[j]);
			}
		}
		
		

		tree = new JTree(year);

		sp = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// sp.getViewport().setPreferredSize(new Dimension(150, 350));
		sp.setBounds(20, 230, 170, 410);

		dataAcquisition.add(sp);

		DataPanel data = new DataPanel();
		dataAcquisition.add(data);
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {

				// System.out.println(e.getPath().getPathComponent(2).toString());

				if (e.getPath().getPathCount() == 3) {
					data.setBounds(200, 230, 250, 410);
					data.setData(e.getPath().getPathComponent(1).toString() + " / "
							+ e.getPath().getPathComponent(2).toString(), 30, 100);
					data.repaint();
					data.revalidate();
					Window.this.repaint();
				}

			}
		});

		data.setBounds(203, 230, 250, 410);
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy '/' MMMM", Locale.FRANCE);
		data.setData(date.format(format), 30, 100);

		browser = new Browser();
		
		
			
		browser.setBounds(467, 230, 631, 411);
		dataAcquisition.add(browser);
	}

	// main
	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> {

			try {
				UIManager.put("Tree.leafIcon", new ImageIcon("leaf.png"));
				UIManager.put("Tree.openIcon", new ImageIcon("plus.png"));
				UIManager.put("Tree.closedIcon", new ImageIcon("minus.png"));
				UIManager.setLookAndFeel(new FlatIntelliJLaf());
				Window win = new Window();
				for (java.awt.Window window : win.getWindows()) {
					SwingUtilities.updateComponentTreeUI(window);
				}
				win.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		});

	}

}
