package sgbd;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class Dialog extends JDialog {
	

	int res = -1;

	public Dialog(JComponent comp, String titre, String ques, String button1, String button2) {
		setSize(300, 150);
		setResizable(false);
		setLocationRelativeTo(comp);
		setTitle(titre);
		setLayout(null);
		setModal(true);
		JLabel question = new JLabel(ques, SwingConstants.CENTER);
		JButton bimage = new JButton(button1);
		bimage.setFocusable(true);
		JButton bpdf = new JButton(button2);
		question.setBounds(50, 30, 200, 30);
		bimage.setBounds(50, 70, 80, 30);
		bpdf.setBounds(150, 70, 80, 30);
		add(question);
		add(bimage);
		add(bpdf);
		ActionListener act = (e) -> {
			if (e.getSource() == bimage)
				this.res = 0;
			if (e.getSource() == bpdf)
				this.res = 1;
			this.setVisible(false);
		};
		bimage.addActionListener(act);
		bpdf.addActionListener(act);
	}

	public int openDialog() {

		this.setVisible(true);
		return this.res;
	}

}
