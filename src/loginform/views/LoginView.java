package loginform.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import loginform.interfaces.*;

import javax.swing.JPasswordField;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class LoginView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel bottomPanel, middlePanel;
	private JButton btnLogin, btnRegister;
	
	private JLabel lblEmail, lblPassword;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	
	private List <IEvent> subscribers;
	
	
	private void config() {
		this.setTitle("Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(400, 300));
		this.setSize(getPreferredSize());
		this.setLocationRelativeTo(null);
	}
	
	private void initComponents() {
		var panel = this.getContentPane();
    panel.setLayout(new BorderLayout());
		
    // Middle panel
    
    middlePanel = new JPanel(new GridBagLayout());
    middlePanel.setBackground(new Color(0xDDDDFF));
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 10, 5, 10);

    // Email
    lblEmail = new JLabel("Email:");
    txtEmail = new JTextField(20);
    middlePanel.add(lblEmail, gbc);
    middlePanel.add(txtEmail, gbc);
   

    // Password
    lblPassword = new JLabel("Password:");
    txtPassword = new JPasswordField(20);
    txtPassword.setEchoChar('*');
    middlePanel.add(lblPassword, gbc);
    middlePanel.add(txtPassword, gbc);    
    
    panel.add(middlePanel, BorderLayout.CENTER);
	
		// Bottom panel

    btnLogin = new JButton("Login");
    btnLogin.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        subscribers.stream().forEach((IEvent ev)->{
          ev.Click("login", getData());
        });
      }
    });
    
    btnRegister = new JButton("Register");
    btnRegister.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        subscribers.stream().forEach((IEvent ev)->{
          ev.Click("register", getData());
        });
      }
    });
    
		bottomPanel = new JPanel();
		bottomPanel.setBackground(new Color(0xCCCCCC));
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(btnLogin);
		bottomPanel.add(btnRegister);
		
		panel.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public LoginView() {
		config();
		initComponents();
		subscribers = new ArrayList<IEvent>();
	}
	
	public void showMe() {
	  pack();
		setVisible(true);
	}
	
	public void subscribe(IEvent sub)
	{
		if (subscribers != null)
			subscribers.add(sub);
	}
	
	private List<String> getData()
	{
	  List<String> list = new ArrayList<>();
	  list.add(txtEmail.getText());
	  list.add(new String(txtPassword.getPassword()));
	  return list;
	}
	
}
