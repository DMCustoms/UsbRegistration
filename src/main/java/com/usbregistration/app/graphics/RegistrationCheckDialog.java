package com.usbregistration.app.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import com.usbregistration.app.items.RegisteredItem;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class RegistrationCheckDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JRootPane contentPane;
	private JLabel deviceIsRegistred;
	private JLabel serialNumber;
	private JLabel ownerSurname;
	private JLabel ownerName;
	private JLabel ownerLastname;
	private JLabel ownerDepartament;
	private RegisteredItem item;

	public RegistrationCheckDialog(JFrame owner, RegisteredItem item) {
		super(owner, "Проверка", ModalityType.APPLICATION_MODAL);
		this.item = item;
		setSize(new Dimension(270, 245));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		initContentPane();
		setContentPane(contentPane);
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	private void initContentPane() {
		contentPane = new JRootPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		deviceIsRegistred = new JLabel("Устройство зарегистрировано");
		deviceIsRegistred.setBounds(30, 25, 200, 17);
		contentPane.add(deviceIsRegistred);
		
		serialNumber = new JLabel(item.serialNumber());
		serialNumber.setHorizontalAlignment(SwingConstants.CENTER);
		serialNumber.setBounds(30, 54, 200, 17);
		contentPane.add(serialNumber);
		
		ownerSurname = new JLabel(item.ownerSurname());
		ownerSurname.setHorizontalAlignment(SwingConstants.CENTER);
		ownerSurname.setBounds(0, 78, 130, 17);
		contentPane.add(ownerSurname);
		
		ownerName = new JLabel(item.ownerName());
		ownerName.setHorizontalAlignment(SwingConstants.CENTER);
		ownerName.setBounds(131, 78, 130, 17);
		contentPane.add(ownerName);
		
		ownerLastname = new JLabel(item.ownerLastname());
		ownerLastname.setHorizontalAlignment(SwingConstants.CENTER);
		ownerLastname.setBounds(65, 100, 130, 17);
		contentPane.add(ownerLastname);
		
		ownerDepartament = new JLabel(item.ownerDepartament());
		ownerDepartament.setHorizontalAlignment(SwingConstants.CENTER);
		ownerDepartament.setBounds(55, 131, 150, 17);
		contentPane.add(ownerDepartament);
		
		JButton buttonOK = new JButton("ОК");
		buttonOK.addActionListener((ae) -> this.dispose());
		buttonOK.setBounds(79, 160, 105, 27);
		contentPane.add(buttonOK);
		
		contentPane.setDefaultButton(buttonOK);
	}
}
