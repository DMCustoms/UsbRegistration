package com.usbregistration.app.graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

import com.usbregistration.app.dbutils.DBUtils;
import com.usbregistration.app.items.USBItem;
import com.usbregistration.app.dbutils.DBStates;
import com.usbregistration.app.listeners.CheckButtonListener;
import com.usbregistration.app.listeners.FindUSBButtonListener;
import com.usbregistration.app.utils.CFUtils;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JRootPane contentPane;
	private JMenuBar jMenuBar;
	private JMenu jmFile;
	private JMenu jmEdit;
	private JCheckBoxMenuItem permitEditing;
	private JMenu jmSearch;
	private JMenuItem connectDBFile;
	private JMenuItem createDBFile;
	private JMenuItem exit;
	private JMenuItem openDB;
	private JScrollPane jScrollPane;
	private JList<USBItem> findedDevices;
	private JButton findDevices;
	private JButton registerDevice;
	private JButton checkDevice;
	private JCheckBox filtrationByName;
	private JLabel dbState;

	public MainFrame() {
		setResizable(false);
		setTitle("Регистрация USB-носителя");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(new Dimension(663, 248));
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
		jmEdit = new JMenu("Правка");
		jmSearch = new JMenu("Поиск");
		
		connectDBFile = new JMenuItem("Подключить файл БД", KeyEvent.VK_O);
		connectDBFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		connectDBFile.addActionListener((ae) -> new DBFileChooser(this, JFileChooser.OPEN_DIALOG));
		
		createDBFile = new JMenuItem("Создать файл БД", KeyEvent.VK_C);
		createDBFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		createDBFile.addActionListener((ae) -> new DBFileChooser(this, JFileChooser.SAVE_DIALOG));
		
		exit = new JMenuItem("Выйти", KeyEvent.VK_E);
		exit.addActionListener((ae) -> System.exit(0));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));	
		
		permitEditing = new JCheckBoxMenuItem("Запретить редактирование полей");
		permitEditing.setSelected(true);
		
		openDB = new JMenuItem("Открыть БД", KeyEvent.VK_B);
		openDB.addActionListener((ae) -> new SearchDialog(this));
		openDB.setEnabled(false);
		openDB.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
		
		jmFile.add(connectDBFile);
		jmFile.add(createDBFile);
		jmFile.addSeparator();
		jmFile.add(exit);
		
		jmSearch.add(openDB);
		
		jmEdit.add(permitEditing);
		
		jMenuBar.add(jmFile);
		jMenuBar.add(jmEdit);
		jMenuBar.add(jmSearch);
	}
	
	private void initContentPane() {
		contentPane = new JRootPane();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		findedDevices = new JList<USBItem>();
		findedDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		findedDevices.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (!findedDevices.isSelectionEmpty()) {
					registerDevice.setEnabled(true);
					checkDevice.setEnabled(true);
				}
			}
		});

		jScrollPane = new JScrollPane(findedDevices);
		jScrollPane.setBounds(27, 12, 454, 131);
		contentPane.add(jScrollPane);
		
		filtrationByName = new JCheckBox("Фильтровать по названию устройства");
		filtrationByName.setBounds(37, 151, 277, 25);
		filtrationByName.setSelected(true);
		contentPane.add(filtrationByName);
		
		findDevices = new JButton("Обнаружить");
		findDevices.setEnabled(false);
		findDevices.setBounds(493, 25, 147, 27);
		findDevices.setActionCommand("find_devices");
		findDevices.addActionListener(new FindUSBButtonListener(this, filtrationByName));
		contentPane.add(findDevices);
		
		registerDevice = new JButton("Регистрировать");
		registerDevice.setBounds(493, 64, 147, 27);
		registerDevice.setEnabled(false);
		registerDevice.addActionListener((ae) -> new RegistrationDialog(this, findedDevices.getSelectedValue()));
		contentPane.add(registerDevice);
		
		checkDevice = new JButton("Проверить");
		checkDevice.setBounds(493, 103, 147, 27);
		checkDevice.setEnabled(false);
		checkDevice.addActionListener(new CheckButtonListener(this, findedDevices));
		contentPane.add(checkDevice);
		
		dbState = new JLabel(DBStates.DATABASE_IS_DISCONNECTED.state);
		dbState.setBounds(438, 155, 170, 25);
		contentPane.add(dbState);
	}
	
	private void preconnectDB() {
		String path = CFUtils.readConfigureFile(this);
		if (!path.equals("")) {
			if (DBUtils.connectDB(path, this)) {
				setDBState(DBStates.DATABASE_IS_CONNECTED);
			}
		}
	}
	
	public void setUSBList(USBItem[] deviceList) {
		findedDevices.setListData(deviceList);
		registerDevice.setEnabled(false);
		checkDevice.setEnabled(false);
	}
	
	public void setDBState(DBStates state) {
		if (state == DBStates.DATABASE_IS_CONNECTED) { 
			dbState.setText(DBStates.DATABASE_IS_CONNECTED.state); 
			createDBFile.setEnabled(false);
			connectDBFile.setEnabled(false);
			openDB.setEnabled(true);
			findDevices.setEnabled(true);
		}
		else if (state == DBStates.DATABASE_IS_DISCONNECTED) {
			dbState.setText(DBStates.DATABASE_IS_DISCONNECTED.state);
			createDBFile.setEnabled(true);
			connectDBFile.setEnabled(true);
			openDB.setEnabled(false);
			findDevices.setEnabled(false);
		}
	}
	
	public boolean isPermittedEditing() {
		return permitEditing.isSelected();
	}
}











