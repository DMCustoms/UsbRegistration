package com.usbregistration.app.graphics;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JRootPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.usbregistration.app.handlers.ConfirmRemovalHandler;
import com.usbregistration.app.handlers.ConfirmUpdateHandler;
import com.usbregistration.app.interfaces.ConfirmHandler;
import com.usbregistration.app.items.RegisteredItem;
import com.usbregistration.app.types.ConfirmTypes;
import com.usbregistration.app.types.DialogMessages;

public class ConfirmDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private SearchDialog context;
	private RegisteredItem item;
	private JRootPane contentPane;
	private JLabel confirmation;
	private JButton confirm;
	private JButton cancel;
	private DialogMessages message;
	private ConfirmTypes type;
	private ConfirmHandler handler;

	public ConfirmDialog(JDialog context, RegisteredItem item, DialogMessages message, ConfirmTypes type) {
		super(context, "Подтверждение", ModalityType.APPLICATION_MODAL);

		this.context = (SearchDialog) context;
		this.message = message;
		this.item = item;
		this.type = type;
		
		setSize(new Dimension(335, 177));
		setResizable(false);
		initContentPane();
		setContentPane(contentPane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(context);
		setVisible(true);
	}
	
	private void initContentPane() {
		contentPane = new JRootPane();
		contentPane.setLayout(null);
		
		confirmation = new JLabel(message.value);
		confirmation.setHorizontalAlignment(SwingConstants.CENTER);
		confirmation.setBounds(23, 31, 300, 17);
		contentPane.add(confirmation);
		
		confirm = new JButton("Да");
		confirm.setBounds(48, 83, 105, 27);
		confirm.addActionListener((ae) -> {
			if (type == ConfirmTypes.CONFIRM_REMOVAL) handler = new ConfirmRemovalHandler(context, this, item);
			else if (type == ConfirmTypes.CONFIRM_UPDATE) handler = new ConfirmUpdateHandler(context, this, item);
			handler.confirm();
		});
		contentPane.add(confirm);
		
		cancel = new JButton("Отмена");
		cancel.addActionListener((ae) -> this.dispose());
		cancel.setBounds(181, 83, 105, 27);
		contentPane.add(cancel);
		
		contentPane.setDefaultButton(cancel);
	}

}
