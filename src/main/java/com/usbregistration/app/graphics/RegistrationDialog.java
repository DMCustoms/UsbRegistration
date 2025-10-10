package com.usbregistration.app.graphics;

import java.awt.Dimension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;

import com.usbregistration.app.handlers.ConfirmRegistrationButtonHandler;
import com.usbregistration.app.handlers.ReRegistrationHandler;
import com.usbregistration.app.interfaces.ButtonHandler;
import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.items.USBItem;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class RegistrationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private MainFrame context;
	private JRootPane contentPane;
	private JTextField serialNumberField;
	private JTextField productNameField;
	private JTextField VIDField;
	private JTextField PIDField;
	private JTextField ownerSurnameField;
	private JTextField ownerNameField;
	private JTextField ownerLastNameField;
	private JTextField departamentField;
	private JTextField protectedField;
	private JLabel serialNumberLabel;
	private JLabel productNameLabel;
	private JLabel VIDLabel;
	private JLabel PIDLabel;
	private JLabel ownerSurnameLabel;
	private JLabel ownerNameLabel;
	private JLabel ownerLastNameLabel;
	private JLabel departamentLabel;
	private JLabel protectedLabel;
	private JButton confirmRegistration;
	private JCheckBox reRegistration;
	private USBItem usbItem;
	private ButtonHandler handler;

	public RegistrationDialog(JFrame context, USBItem usbItem) {
		super(context, "Регистрация", ModalityType.APPLICATION_MODAL);
		this.usbItem = usbItem;
		this.context = (MainFrame) context;
		setSize(new Dimension(377, 230));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(context);
		setResizable(false);
		initContentPane();
		presetFieldsValues();
		checkEditingPermission();
		setContentPane(contentPane);
		setVisible(true);
	}
	
	private void initContentPane() {
		contentPane = new JRootPane();
		contentPane.setLayout(null);
		
		serialNumberField = new JTextField();
		serialNumberField.setBounds(115, 10, 234, 21);
		contentPane.add(serialNumberField);
		serialNumberField.setColumns(10);
		
		productNameField = new JTextField();
		productNameField.setBounds(125, 35, 124, 21);
		contentPane.add(productNameField);
		productNameField.setColumns(10);
		
		ownerSurnameField = new JTextField();
		ownerSurnameField.setBounds(201, 60, 148, 21);
		contentPane.add(ownerSurnameField);
		ownerSurnameField.setColumns(10);
		
		PIDField = new JTextField();
		PIDField.setBounds(54, 60, 67, 21);
		contentPane.add(PIDField);
		PIDField.setColumns(10);
		
		VIDField = new JTextField();
		VIDField.setBounds(280, 35, 69, 21);
		contentPane.add(VIDField);
		VIDField.setColumns(10);
			
		ownerNameField = new JTextField();
		ownerNameField.setBounds(62, 85, 101, 21);
		contentPane.add(ownerNameField);
		ownerNameField.setColumns(10);
		
		ownerLastNameField = new JTextField();
		ownerLastNameField.setBounds(235, 85, 114, 21);
		contentPane.add(ownerLastNameField);
		ownerLastNameField.setColumns(10);
		
		departamentField = new JTextField();
		departamentField.setBounds(132, 110, 217, 21);
		contentPane.add(departamentField);
		departamentField.setColumns(10);
		
		protectedField = new JTextField();
		protectedField.setBounds(142, 135, 101, 21);
		contentPane.add(protectedField);
		protectedField.setColumns(10);

		serialNumberLabel = new JLabel("Серийный №");
		serialNumberLabel.setBounds(26, 12, 124, 17);
		contentPane.add(serialNumberLabel);
		
		productNameLabel = new JLabel("Наименование");
		productNameLabel.setBounds(26, 36, 108, 17);
		contentPane.add(productNameLabel);
		
		VIDLabel = new JLabel("VID");
		VIDLabel.setBounds(252, 36, 22, 17);
		contentPane.add(VIDLabel);
		
		PIDLabel = new JLabel("PID");
		PIDLabel.setBounds(26, 61, 31, 17);
		contentPane.add(PIDLabel);
		
		ownerSurnameLabel = new JLabel("Фамилия");
		ownerSurnameLabel.setBounds(132, 61, 60, 17);
		contentPane.add(ownerSurnameLabel);
		
		ownerNameLabel = new JLabel("Имя");
		ownerNameLabel.setBounds(26, 86, 38, 17);
		contentPane.add(ownerNameLabel);
		
		ownerLastNameLabel = new JLabel("Отчество");
		ownerLastNameLabel.setBounds(169, 86, 60, 17);
		contentPane.add(ownerLastNameLabel);
		
		departamentLabel = new JLabel("Подразделение");
		departamentLabel.setBounds(26, 112, 108, 17);
		contentPane.add(departamentLabel);
		
		protectedLabel = new JLabel("Защитная марка");
		protectedLabel.setBounds(26, 136, 114, 17);
		contentPane.add(protectedLabel);
		
		reRegistration = new JCheckBox("Перерегистрация");
		reRegistration.setBounds(26, 155, 150, 25);
		contentPane.add(reRegistration);
		
		confirmRegistration = new JButton("ОК");
		confirmRegistration.setBounds(252, 145, 86, 27);
		confirmRegistration.addActionListener((ae) -> {
			if (reRegistration.isSelected()) handler = new ReRegistrationHandler(this);
			else handler = new ConfirmRegistrationButtonHandler(this);
			handler.handle();
		});
		contentPane.add(confirmRegistration);
		
		contentPane.setDefaultButton(confirmRegistration);
	}
	
	private void presetFieldsValues() {
		if (usbItem != null) {
			serialNumberField.setText(usbItem.serialNumber());
			productNameField.setText(usbItem.productName());
			VIDField.setText(Integer.toString(usbItem.vendorID()));
			PIDField.setText(Integer.toString(usbItem.productID()));
		}
	}
	
	public void checkEditingPermission() {
		if (context.isPermittedEditing()) {
			serialNumberField.setEditable(false);
			VIDField.setEditable(false);
			PIDField.setEditable(false);
			productNameField.setEditable(false);
		}
	}
	
	public RegisteredItem getRegisteredItem() {
		JTextField[] checkOnIsEmptyArray = new JTextField[] { serialNumberField, productNameField, VIDField, PIDField, ownerSurnameField, ownerNameField, ownerLastNameField, departamentField, protectedField };
		for (JTextField item : checkOnIsEmptyArray) {
			if (item.getText().trim().isBlank()) return null;
		}
		LocalDateTime ldm = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
		String date = ldm.format(format);
		return new RegisteredItem(serialNumberField.getText().trim(), productNameField.getText().trim(), VIDField.getText().trim(), PIDField.getText().trim(), ownerSurnameField.getText().trim(), ownerNameField.getText().trim(), ownerLastNameField.getText().trim(), departamentField.getText().trim(), protectedField.getText().trim(), date);
	}
}






