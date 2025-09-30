package com.usbregistration.app.graphics;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRootPane;

import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.types.ConfirmTypes;
import com.usbregistration.app.types.DialogMessages;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class UpdateDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private SearchDialog context;
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
	private JTextField dateField;
	private JLabel serialNumberLabel;
	private JLabel productNameLabel;
	private JLabel VIDLabel;
	private JLabel PIDLabel;
	private JLabel ownerSurnameLabel;
	private JLabel ownerNameLabel;
	private JLabel ownerLastNameLabel;
	private JLabel departamentLabel;
	private JLabel protectedLabel;
	private JLabel dateLabel;
	private JButton confirmUpdating;
	private RegisteredItem item;

	public UpdateDialog(JDialog context, RegisteredItem item) {
		super(context, "Изменение", ModalityType.APPLICATION_MODAL);
		this.item = item;
		this.context = (SearchDialog) context;
		setBounds(100, 100, 377, 230);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(context);
		setResizable(false);
		initContentPane();
		presetFieldsValues();
		setContentPane(contentPane);
		setVisible(true);
	}
	
	private void initContentPane() {
		contentPane = new JRootPane();
		contentPane.setLayout(null);
		
		serialNumberField = new JTextField();
		serialNumberField.setBounds(115, 10, 234, 21);
		serialNumberField.setEditable(false);
		contentPane.add(serialNumberField);
		serialNumberField.setColumns(10);
		
		productNameField = new JTextField();
		productNameField.setBounds(125, 35, 124, 21);
		productNameField.setEditable(false);
		contentPane.add(productNameField);
		productNameField.setColumns(10);
		
		ownerSurnameField = new JTextField();
		ownerSurnameField.setBounds(201, 60, 148, 21);
		contentPane.add(ownerSurnameField);
		ownerSurnameField.setColumns(10);
		
		PIDField = new JTextField();
		PIDField.setBounds(54, 60, 67, 21);
		PIDField.setEditable(false);
		contentPane.add(PIDField);
		PIDField.setColumns(10);
		
		VIDField = new JTextField();
		VIDField.setBounds(280, 35, 69, 21);
		VIDField.setEditable(false);
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
		
		dateField = new JTextField();
		dateField.setBounds(62, 160, 140, 21);
		dateField.setEditable(false);
		contentPane.add(dateField);

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
		
		dateLabel = new JLabel("Дата");
		dateLabel.setBounds(26, 160, 50, 17);
		contentPane.add(dateLabel);
		
		confirmUpdating = new JButton("ОК");
		confirmUpdating.setBounds(252, 145, 86, 27);
		confirmUpdating.addActionListener((ae) -> {
			new ConfirmDialog(context, new RegisteredItem(serialNumberField.getText(), productNameField.getText(), VIDField.getText(), PIDField.getText(), ownerSurnameField.getText().trim(), ownerNameField.getText().trim(), ownerLastNameField.getText().trim(), departamentField.getText().trim(), protectedField.getText().trim(), dateField.getText()), DialogMessages.CONFIRM_UPDATE, ConfirmTypes.CONFIRM_UPDATE);
			this.dispose();
		});
		contentPane.add(confirmUpdating);
		
		contentPane.setDefaultButton(confirmUpdating);
	}
	
	private void presetFieldsValues() {
		if (item != null) {
			serialNumberField.setText(item.serialNumber());
			productNameField.setText(item.productName());
			VIDField.setText(item.vid());
			PIDField.setText(item.pid());
			ownerSurnameField.setText(item.ownerSurname());
			ownerNameField.setText(item.ownerName());
			ownerLastNameField.setText(item.ownerLastname());
			departamentField.setText(item.ownerDepartament());
			protectedField.setText(item.protectedLabel());
			dateField.setText(item.date());
		}
	}
	
}






