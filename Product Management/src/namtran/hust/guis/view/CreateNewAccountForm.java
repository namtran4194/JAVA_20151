package namtran.hust.guis.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import namtran.hust.guis.controller.CreateAccountController;

public class CreateNewAccountForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private JRadioButton rdYes;
	private JRadioButton rdNo;

	public static void main(String[] args) {
		new CreateNewAccountForm().setVisible(true);
	}

	public CreateNewAccountForm() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 250, 386, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// create label
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setForeground(Color.BLACK);
		lblUsername.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblUsername.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		lblUsername.setBounds(10, 11, 105, 26);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setForeground(Color.BLACK);
		lblPassword.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblPassword.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		lblPassword.setBounds(10, 58, 105, 26);

		// create textfield
		tfUsername = new JTextField();
		tfUsername.setForeground(Color.BLACK);
		tfUsername.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		tfUsername.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		tfUsername.setBounds(145, 11, 211, 26);
		tfUsername.setColumns(10);

		tfPassword = new JPasswordField();
		tfPassword.setForeground(Color.BLACK);
		tfPassword.setFont(new Font("Lucida Sans Unicode", Font.PLAIN, 15));
		tfPassword.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		tfPassword.setBounds(145, 58, 211, 26);
		tfPassword.setEchoChar('⊹');
		tfPassword.setColumns(10);

		// create panel contain radio button
		JPanel panelAdm = new JPanel();
		panelAdm.setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)),
				"Administrator account", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		panelAdm.setBounds(38, 95, 283, 59);
		panelAdm.setLayout(null);

		rdYes = new JRadioButton("Yes");
		rdYes.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		rdYes.setBounds(101, 26, 51, 23);
		panelAdm.add(rdYes);

		rdNo = new JRadioButton("No");
		rdNo.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		rdNo.setBounds(200, 26, 51, 23);
		panelAdm.add(rdNo);

		// create button
		JButton btnCreate = new JButton("Create");
		btnCreate.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnCreate.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnCreate.setBounds(58, 165, 105, 26);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		btnCancel.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.RED));
		btnCancel.setBounds(190, 165, 105, 26);

		// add event
		btnCreate.addActionListener(new EventHandler());
		btnCancel.addActionListener(new EventHandler());
		rdYes.addActionListener(new EventHandler());
		rdNo.addActionListener(new EventHandler());

		// add to panel
		contentPane.add(lblUsername);
		contentPane.add(lblPassword);
		contentPane.add(tfUsername);
		contentPane.add(tfPassword);
		contentPane.add(panelAdm);
		contentPane.add(btnCreate);
		contentPane.add(btnCancel);
	}

	// event handler
	private class EventHandler implements ActionListener {
		String s;
		private int permission;

		@Override
		public void actionPerformed(ActionEvent e) {
			s = e.getActionCommand();
			// click create button
			if (s.equals("Create")) {
				String userName = tfUsername.getText();
				String password = String.valueOf(tfPassword.getPassword());
				// check radio button
				if(rdYes.isSelected())
					permission = 1;
				else if(rdNo.isSelected())
					permission = 0;
				else {
					JOptionPane.showMessageDialog(CreateNewAccountForm.this, "you don't select administrator account!",
							"Invalid", JOptionPane.ERROR_MESSAGE);
					return;
					}
				// create account
				CreateAccountController cAC = new CreateAccountController(userName, password, permission);
				// check status
				int check = cAC.checkAccount();
				if (check == 0) {
					CreateNewAccountForm.this.setVisible(false);
					JOptionPane.showMessageDialog(CreateNewAccountForm.this, "Create account successfully",
							"Infomation", JOptionPane.INFORMATION_MESSAGE);
				} else
					JOptionPane.showMessageDialog(CreateNewAccountForm.this, "Account has already used!",
							"Invalid account", JOptionPane.ERROR_MESSAGE);
			}
			// button cancel
			else if (s.equals("Cancel"))
				System.exit(0);
			// radio button
			else if (s.equals("Yes"))
				rdNo.setSelected(false);
			else if (s.equals("No"))
				rdYes.setSelected(false);
		}
	}
}
