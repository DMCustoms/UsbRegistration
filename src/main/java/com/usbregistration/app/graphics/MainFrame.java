package com.usbregistration.app.graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.usbregistration.app.dbutils.DBConnector;
import com.usbregistration.app.dbutils.DBStates;
import com.usbregistration.app.listeners.FindUSBButtonListener;
import com.usbregistration.app.usb.USBItem;
import com.usbregistration.app.utils.CFReader;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar jMenuBar;
	private JMenu jmFile;
	private JMenuItem connectDBFile;
	private JMenuItem createDBFile;
	private JMenuItem exit;
	private JScrollPane jScrollPane;
	private JList<USBItem> findedDevices;
	private JButton findDevices;
	private JCheckBox filtrationByName;
	private JLabel dbState;

	public MainFrame() {
		setResizable(false);
		setTitle("UsbRegistration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(new Dimension(510, 370));
		initMenu();
		initContentPane();
		setJMenuBar(jMenuBar);
		setContentPane(contentPane);
		setVisible(true);
		preconnectDB();
	}
	
	private void initMenu() {
		jMenuBar = new JMenuBar();
		jmFile = new JMenu("Файл");
		jmFile.setMnemonic(KeyEvent.VK_F);
		
		connectDBFile = new JMenuItem("Подключить файл БД", KeyEvent.VK_O);
		connectDBFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		connectDBFile.addActionListener((_) -> new ConnectDBFileChooser(this));
		
		createDBFile = new JMenuItem("Создать файл БД", KeyEvent.VK_C);
		createDBFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		createDBFile.addActionListener((_) -> new CreateDBFileChooser(this));
		
		exit = new JMenuItem("Выйти", KeyEvent.VK_E);
		exit.addActionListener((_) -> System.exit(0));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));	
		
		jmFile.add(connectDBFile);
		jmFile.add(createDBFile);
		jmFile.addSeparator();
		jmFile.add(exit);
		
		jMenuBar.add(jmFile);
	}
	
	private void initContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		findedDevices = new JList<USBItem>();
		findedDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jScrollPane = new JScrollPane(findedDevices);
		jScrollPane.setBounds(27, 12, 454, 229);
		contentPane.add(jScrollPane);
		
		filtrationByName = new JCheckBox("Фильтровать по названию устройства");
		filtrationByName.setBounds(37, 254, 277, 25);
		filtrationByName.setSelected(true);
		contentPane.add(filtrationByName);
		
		findDevices = new JButton("Обнаружить ус-ва");
		findDevices.setMargin(new Insets(2, 6, 2, 6));
		findDevices.setBounds(322, 253, 147, 27);
		findDevices.setActionCommand("find_devices");
		findDevices.addActionListener(new FindUSBButtonListener(this, filtrationByName));
		contentPane.add(findDevices);
		
		dbState = new JLabel(DBStates.DATABASE_IS_DISCONNECTED.state);
		dbState.setBounds(27, 280, 170, 25);
		contentPane.add(dbState);
	}
	
	private void preconnectDB() {
		String path = CFReader.INSTANCE.readConfigureFile();
		if (!path.equals("")) {
			if (DBConnector.INSTANCE.connectDB(path)) {
				dbState.setText(DBStates.DATABASE_IS_CONNECTED.state);
				createDBFile.setEnabled(false);
				connectDBFile.setEnabled(false);
			}
		}
	}
	
	public void setUSBList(USBItem[] deviceList) {
		findedDevices.setListData(deviceList);
	}
	
	public void setDBState(DBStates state) {
		if (state == DBStates.DATABASE_IS_CONNECTED) dbState.setText(DBStates.DATABASE_IS_CONNECTED.state);
		else if (state == DBStates.DATABASE_IS_DISCONNECTED) dbState.setText(DBStates.DATABASE_IS_DISCONNECTED.state);
	}
	
	public void setDBButtonsDisabled() {
		createDBFile.setEnabled(false);
		connectDBFile.setEnabled(false);
	}
}











