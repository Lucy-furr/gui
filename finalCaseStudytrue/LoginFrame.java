package finalCaseStudytrue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener{

	JLabel lbUsername = new JLabel();
	JLabel lbPassword = new JLabel();
	
	JTextField txtUsername = new JTextField();
	JPasswordField txtPassword = new JPasswordField();
	
	JButton btnLogin = new JButton();
	JButton btnCancel = new JButton();
	
	String username = "1";
	String password = "1";
	
	
	void LoginFrameDesign() {

		this.setTitle("TSU Canteen Log in");
		this.setSize(350, 230);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(183, 183, 183));
		
		ImageIcon image = new ImageIcon("tsuLogo.png");
		 this.setIconImage(image.getImage());
		
		
		lbUsername.setText("<html><i>Username: </i></html>");
		lbUsername.setBounds(50, 30, 150, 30);
		
		lbPassword.setText("<html><i>Password: </i></html>");
		lbPassword.setBounds(50, 70, 170, 30);
		
		txtUsername.setBounds(130, 30, 170, 30);
		
		txtPassword.setBounds(130, 70, 170, 30);
		txtPassword.setEchoChar('*');
		
		btnCancel.setBounds(130, 140, 80, 30);
		btnCancel.setText("Cancel");
		btnCancel.setFocusable(false);
		btnCancel.addActionListener(this);
		
		btnLogin.setBounds(220, 140, 80, 30);
		btnLogin.setText("Login");
		btnLogin.setFocusable(false);
		btnLogin.addActionListener(this);
		
		this.add(lbUsername);
		this.add(lbPassword);
		this.add(txtUsername);
		this.add(txtPassword);
		this.add(btnCancel);
		this.add(btnLogin);
		
		this.setLayout(null);
		this.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(btnLogin)) {
			if(username.equals(txtUsername.getText()) 
					&& password.equals(new String (txtPassword.getPassword()))) {
				JOptionPane.showMessageDialog(this, "Login Successfully!");
				this.dispose();
				new frame1();
			}
			else {
				JOptionPane.showMessageDialog(this, "Incorrect Username/Password!",
						"Incorrect Credentials", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource().equals(btnCancel)) {
			int res = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?",
					"Exit", JOptionPane.YES_NO_OPTION);
			if(res == JOptionPane.YES_OPTION) {
				this.dispose();
			}
		}
	}
}
