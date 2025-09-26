package com.usbregistration.app.graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.usbregistration.app.listeners.ConfirmRegistrationButtonListener;
import com.usbregistration.app.usb.USBItem;
import com.usbregistration.app.utils.RegisteredItem;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class RegistrationDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JFrame owner;
	private JPanel contentPane;
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
	private USBItem usbItem;

	public RegistrationDialog(JFrame owner, USBItem usbItem) {
		super(owner, "Регистрация", ModalityType.APPLICATION_MODAL);
		this.usbItem = usbItem;
		this.owner = owner;
		setBounds(100, 100, 377, 216);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		setResizable(false);
		initContentPane();
		presetFieldsValues();
		setContentPane(contentPane);
		setVisible(true);
	}
	
	private void initContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		
		confirmRegistration = new JButton("ОК");
		confirmRegistration.setBounds(252, 133, 86, 27);
		confirmRegistration.addActionListener(new ConfirmRegistrationButtonListener(this, owner));
		contentPane.add(confirmRegistration);
	}
	
	private void presetFieldsValues() {
		if (usbItem != null) {
			serialNumberField.setText(usbItem.serialNumber());
			productNameField.setText(usbItem.productName());
			VIDField.setText(usbItem.vendorID());
			PIDField.setText(usbItem.productID());
		}
	}
	
	public RegisteredItem getRegisteredItemInstance() {
		return new RegisteredItem(serialNumberField.getText(), productNameField.getText(), VIDField.getText(), PIDField.getText(), ownerSurnameField.getText(), ownerNameField.getText(), ownerLastNameField.getText(), departamentField.getText(), protectedField.getText());
	}
}






