package com.usbregistration.app.graphics;

import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.usbregistration.app.dbutils.DBConnection;
import com.usbregistration.app.dbutils.Queries;
import com.usbregistration.app.items.RegisteredItem;

public class SearchDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JRootPane contentPane;
	private JScrollPane jScrollPane;
	private JList<RegisteredItem> registeredDevices;
	private JTextField searchBySerialNumber;
	private JTextField searchByOwnerSurname;
	private JTextField searchByOwnerName;
	private JTextField searchByOwnerLastname;
	private JTextField searchByOwnerDepartament;
	private JTextField searchByProtectedLabel;
	private JTextField searchByDate;
	private JPanel searchingPane;
	
	public SearchDialog(JFrame owner) {
		super(owner, "Поиск в БД", ModalityType.APPLICATION_MODAL);
		setSize(new Dimension(850, 430));
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		initContentPane();
		setContentPane(contentPane);
		setVisible(true);
	}

	private void initContentPane() {
		contentPane = new JRootPane();
		contentPane.setLayout(null);
		
		searchingPane = new JPanel();
		searchBySerialNumber = new JTextField();
		searchBySerialNumber.setPreferredSize(new Dimension(105, 20));
		searchBySerialNumber.setToolTipText("Serial");
		searchByOwnerSurname = new JTextField();
		searchByOwnerSurname.setPreferredSize(new Dimension(105, 20));
		searchByOwnerName = new JTextField();
		searchByOwnerName.setPreferredSize(new Dimension(105, 20));
		searchByOwnerLastname = new JTextField();
		searchByOwnerLastname.setPreferredSize(new Dimension(105, 20));
		searchByOwnerDepartament = new JTextField();
		searchByOwnerDepartament.setPreferredSize(new Dimension(105, 20));
		searchByProtectedLabel = new JTextField();
		searchByProtectedLabel.setPreferredSize(new Dimension(105, 20));
		searchByDate = new JTextField();
		searchByDate.setPreferredSize(new Dimension(105, 20));
		searchingPane.add(searchBySerialNumber);
		searchingPane.add(searchByOwnerSurname);
		searchingPane.add(searchByOwnerName);
		searchingPane.add(searchByOwnerLastname);
		searchingPane.add(searchByOwnerDepartament);
		searchingPane.add(searchByProtectedLabel);
		searchingPane.add(searchByDate);
	
		registeredDevices = new JList<RegisteredItem>();
		registeredDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jScrollPane = new JScrollPane(registeredDevices);
		jScrollPane.setBounds(25, 25, 800, 300);
		jScrollPane.setColumnHeaderView(searchingPane);
		contentPane.add(jScrollPane);
		
		try {
			RegisteredItem [] items = Queries.selectAllQuery(DBConnection.INSTANCE.getStatement());
			registeredDevices.setListData(items);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}



















