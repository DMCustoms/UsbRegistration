package com.usbregistration.app.graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ModalDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JRootPane contentPane;
	private JLabel message;
	private JButton buttonOK;

	public ModalDialog(JFrame owner, DialogTypes type) {
		super(owner, "", ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 300, 178);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(owner);
		initContentPane(type);
		setContentPane(contentPane);
		setVisible(true);
	}
	
	private void initContentPane(DialogTypes type) {
		contentPane = new JRootPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(12, 28, 271, 17);
		message.setText(type.value);
		contentPane.add(message);
		
		buttonOK = new JButton("ОК");
		buttonOK.setBounds(95, 73, 110, 27);
		buttonOK.addActionListener((ae) -> this.dispose());
		contentPane.add(buttonOK);
	}
}
