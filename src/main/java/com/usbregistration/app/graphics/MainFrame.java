package com.usbregistration.app.graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.usbregistration.app.listeners.FindUSBButtonListener;
import com.usbregistration.app.usb.USBItem;

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
	private static JPanel contentPane;
	private static JMenuBar jMenuBar;
	private static JMenu jmFile;
	private static JMenuItem openDBFile;
	private static JMenuItem createDBFile;
	private static JMenuItem exit;
	private static JScrollPane jScrollPane;
	private static JList<USBItem> findedDevices;
	private static JButton findDevices;
	private static JCheckBox filtrationByName;

	public MainFrame() {
		setResizable(false);
		setTitle("UsbRegistration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(new Dimension(510, 370));
		initMenu();
		init();
		setJMenuBar(jMenuBar);
		setContentPane(contentPane);
		setVisible(true);
	}
	
	private void initMenu() {
		jMenuBar = new JMenuBar();
		jmFile = new JMenu("Файл");
		jmFile.setMnemonic(KeyEvent.VK_F);
		openDBFile = new JMenuItem("Открыть файл БД", KeyEvent.VK_O);
		openDBFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		createDBFile = new JMenuItem("Создать файл БД", KeyEvent.VK_C);
		createDBFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		exit = new JMenuItem("Выйти", KeyEvent.VK_E);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));		
		jmFile.add(openDBFile);
		jmFile.add(createDBFile);
		jmFile.addSeparator();
		jmFile.add(exit);
		jMenuBar.add(jmFile);
	}
	
	private void init() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		findedDevices = new JList<USBItem>();
		findedDevices.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jScrollPane = new JScrollPane(findedDevices);
		jScrollPane.setBounds(27, 12, 454, 229);
		contentPane.add(jScrollPane);
		
		findDevices = new JButton("Обнаружить ус-ва");
		findDevices.setMargin(new Insets(2, 6, 2, 6));
		findDevices.setBounds(322, 253, 147, 27);
		findDevices.setActionCommand("find_devices");
		findDevices.addActionListener(new FindUSBButtonListener());
		contentPane.add(findDevices);
		
		filtrationByName = new JCheckBox("Фильтровать по названию устройства");
		filtrationByName.setBounds(37, 254, 277, 25);
		filtrationByName.setSelected(true);
		contentPane.add(filtrationByName);
	}
	
	public static void setUSBList(USBItem[] deviceList) {
		if (filtrationByName.isSelected()) {
			
		} else {
			findedDevices.setListData(deviceList);
		}
	}
}











