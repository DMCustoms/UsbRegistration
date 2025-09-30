package com.usbregistration.app.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.types.ConfirmTypes;
import com.usbregistration.app.types.DialogMessages;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class SearchDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JRootPane contentPane;
	private JTextField searchBySN;
	private JTextField searchBySurname;
	private JTextField searchByName;
	private JTextField searchByLastname;
	private JTextField searchByDepartament;
	private JTextField searchByProtectedlabel;
	private JTextField searchByDate;
	private JLabel serialNumber;
	private JLabel surname;
	private JLabel name;
	private JLabel lastname;
	private JLabel departament;
	private JLabel protectedLabel;
	private JLabel date;
	private JScrollPane jScrollPane;
	private JList<RegisteredItem> registeredDevices;
	private JButton remove;
	private JButton update;

	public SearchDialog(JFrame context) {
		super(context, "Поиск по БД", ModalityType.APPLICATION_MODAL);
		setSize(new Dimension(720, 411));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(context);
		initContentPane();
		setContentPane(contentPane);
		setRegisteredItemsList();
		setVisible(true);
	}
	
	private void initContentPane() {
		contentPane = new JRootPane();
		contentPane.setLayout(null);
		
		registeredDevices = new JList<RegisteredItem>();
		registeredDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		registeredDevices.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (!registeredDevices.isSelectionEmpty()) {
					remove.setEnabled(true);
					update.setEnabled(true);
				}
			}
		});
		
		jScrollPane = new JScrollPane(registeredDevices);
		jScrollPane.setBounds(12, 12, 449, 298);
		contentPane.add(jScrollPane);
		
		serialNumber = new JLabel("Серийный номер");
		serialNumber.setFont(new Font("Dialog", Font.BOLD, 12));
		serialNumber.setHorizontalAlignment(SwingConstants.CENTER);
		serialNumber.setBounds(473, 14, 215, 17);
		contentPane.add(serialNumber);
		
		searchBySN = new JTextField();
		searchBySN.setBounds(473, 32, 215, 21);
		searchBySN.addCaretListener((ce) -> setRegisteredItemsList());
		contentPane.add(searchBySN);
		
		surname = new JLabel("Фамилия");
		surname.setHorizontalAlignment(SwingConstants.CENTER);
		surname.setFont(new Font("Dialog", Font.BOLD, 12));
		surname.setBounds(473, 56, 215, 17);
		contentPane.add(surname);
		
		searchBySurname = new JTextField();
		searchBySurname.setBounds(473, 75, 215, 21);
		searchBySurname.addCaretListener((ce) -> setRegisteredItemsList());
		contentPane.add(searchBySurname);
		
		name = new JLabel("Имя");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Dialog", Font.BOLD, 12));
		name.setBounds(473, 99, 215, 17);
		contentPane.add(name);
		
		searchByName = new JTextField();
		searchByName.setBounds(473, 119, 215, 21);
		searchByName.addCaretListener((ce) -> setRegisteredItemsList());
		contentPane.add(searchByName);
		
		lastname = new JLabel("Отчество");
		lastname.setHorizontalAlignment(SwingConstants.CENTER);
		lastname.setFont(new Font("Dialog", Font.BOLD, 12));
		lastname.setBounds(473, 141, 215, 17);
		contentPane.add(lastname);
		
		searchByLastname = new JTextField();
		searchByLastname.addCaretListener((ce) -> setRegisteredItemsList());
		searchByLastname.setBounds(473, 161, 215, 21);
		contentPane.add(searchByLastname);
		
		departament = new JLabel("Подразделение");
		departament.setHorizontalAlignment(SwingConstants.CENTER);
		departament.setBounds(473, 185, 215, 17);
		contentPane.add(departament);
		
		searchByDepartament = new JTextField();
		searchByDepartament.addCaretListener((ce) -> setRegisteredItemsList());
		searchByDepartament.setBounds(473, 206, 215, 21);
		contentPane.add(searchByDepartament);
		
		protectedLabel = new JLabel("Защитная марка");
		protectedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		protectedLabel.setBounds(473, 229, 215, 17);
		contentPane.add(protectedLabel);
		
		searchByProtectedlabel = new JTextField();
		searchByProtectedlabel.setBounds(473, 249, 215, 21);
		searchByProtectedlabel.addCaretListener((ce) -> setRegisteredItemsList());
		contentPane.add(searchByProtectedlabel);
		
		date = new JLabel("Дата");
		date.setHorizontalAlignment(SwingConstants.CENTER);
		date.setBounds(473, 271, 215, 17);
		contentPane.add(date);
		
		searchByDate = new JTextField();
		searchByDate.setBounds(473, 289, 215, 21);
		searchByDate.addCaretListener((ce) -> setRegisteredItemsList());
		contentPane.add(searchByDate);
		
		remove = new JButton("Удалить запись");
		remove.setEnabled(false);
		remove.setBounds(506, 324, 146, 27);
		remove.addActionListener((ae) -> new ConfirmDialog(this, registeredDevices.getSelectedValue(), DialogMessages.CONFIRM_REMOVAL, ConfirmTypes.CONFIRM_REMOVAL));
		contentPane.add(remove);
		
		update = new JButton("Изменить запись");
		update.setEnabled(false);
		update.setBounds(340, 324, 146, 27);
		update.addActionListener((ae) -> new UpdateDialog(this, registeredDevices.getSelectedValue()));
		contentPane.add(update);
	}
	
	public void setRegisteredItemsList() {
		try {
			RegisteredItem[] items = Queries.selectAllQuery(DBConnection.INSTANCE.getStatement(), searchBySN.getText(), searchBySurname.getText(), searchByName.getText(), searchByLastname.getText(), searchByDepartament.getText(), searchByProtectedlabel.getText(), searchByDate.getText());                             
			registeredDevices.setListData(items);
			registeredDevices.repaint();
			this.repaint();
			remove.setEnabled(false);
			update.setEnabled(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}




