package com.usbregistration.app.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.listeners.ConfirmRemovingButtonListener;

public class ConfirmRemovalDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JFrame context;
	private SearchDialog searchDialogContext;
	private RegisteredItem item;
	private JRootPane contentPane;
	private JLabel confirmation;
	private JButton confirm;
	private JButton cancel;

	public ConfirmRemovalDialog(JFrame context, SearchDialog searchDialogContext, RegisteredItem item) {
		super(context, "Подтверждение", ModalityType.APPLICATION_MODAL);
		this.context = context;
		this.searchDialogContext = searchDialogContext;
		this.item = item;
		setSize(new Dimension(335, 177));
		setResizable(false);
		initContentPane();
		setRootPane(contentPane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(context);
		setVisible(true);
	}
	
	private void initContentPane() {
		contentPane = new JRootPane();
		contentPane.setLayout(null);
		
		confirmation = new JLabel("Вы действительно хотите удалить запись?");
		confirmation.setHorizontalAlignment(SwingConstants.CENTER);
		confirmation.setBounds(23, 31, 300, 17);
		contentPane.add(confirmation);
		
		confirm = new JButton("Да");
		confirm.setBounds(48, 83, 105, 27);
		confirm.addActionListener(new ConfirmRemovingButtonListener(context, this, searchDialogContext, item));
		contentPane.add(confirm);
		
		cancel = new JButton("Отмена");
		cancel.addActionListener((ae) -> this.dispose());
		cancel.setBounds(181, 83, 105, 27);
		contentPane.add(cancel);
		
		contentPane.setDefaultButton(cancel);
	}

}
