package cn.sdut.app;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cn.sdut.biz.AdminBiz;
import cn.sdut.biz.OperaterBiz;

public class LoginWindow extends JFrame {

	static final long serialVersionUID = 1L;
	static int flag = 1;
	static String id = null;
	static String password = null;
	AdminBiz ab = AdminBiz.getInstance();
	OperaterBiz ub = OperaterBiz.getInstance();
	JFrame win;
	JPanel p;
	JLabel title, lb1, lb2;
	JTextField account;
	JPasswordField psw;
	JButton login, exit;
	JRadioButton userLogin, adminLogin;
	ButtonGroup group;

	public LoginWindow() {
		super("登录");
		init();
		setBounds(500, 200, 400, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(getOwner());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/**
	 * 登录界面布局
	 */
	public void init() {

		final JPanel p = new AppBackPanel("logo.jpg");
		p.setLayout(null);
		title = new JLabel("产品管理系统/LOGIN", JLabel.CENTER);
		title.setFont(new Font("宋体", Font.BOLD, 16));
		title.setBounds(10, 5, 350, 50);
		p.add(title);

		lb1 = new JLabel("账号：");
		lb1.setBounds(50, 80, 50, 20);
		p.add(lb1);

		account = new JTextField();
		account.setBounds(110, 75, 200, 30);
		account.setOpaque(false);
		p.add(account);
		account.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (ch >= '0' && ch <= '9') {

				} else {
					e.consume();
					return;
				}
			}

		});

		lb2 = new JLabel("密码：", JLabel.CENTER);
		lb2.setBounds(50, 120, 50, 20);
		p.add(lb2);

		psw = new JPasswordField();
		psw.setBounds(110, 115, 200, 30);
		psw.setOpaque(false);
		p.add(psw);

		group = new ButtonGroup();
		userLogin = new JRadioButton("操作员", true);
		group.add(userLogin);
		userLogin.setBounds(120, 160, 80, 20);
		userLogin.setSelected(true);
		p.add(userLogin);

		adminLogin = new JRadioButton("管理员", false);
		group.add(adminLogin);
		adminLogin.setBounds(200, 160, 80, 20);
		p.add(adminLogin);

		login = new JButton("登录");
		login.setBounds(120, 190, 70, 30);
		p.add(login);

		exit = new JButton("退出");
		exit.setBounds(200, 190, 70, 30);
		p.add(exit);
		getContentPane().add(p, BorderLayout.CENTER);

		login.addActionListener(new LoginListener());
		exit.addActionListener(new ExitListener());
	}

	/*
	 * 设置登录监听事件
	 */
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			id = account.getText();
			password = new String(psw.getPassword());
			if (userLogin.isSelected() == true) {
				flag = 0;
				int Case = ub.OperaterLogin(id, password);
				switch (Case) {
				case -1:
					JOptionPane.showMessageDialog(null, "您输入的账号不存在,请重新输入");
					break;
				case 0:
					JOptionPane.showMessageDialog(null, "您输入的密码有误，请重新输入");
					break;
				case 1:
					new AdminWindow("操作员界面");
					dispose();
					break;
				}
			} else if (adminLogin.isSelected() == true) {

				flag = 1;
				int Case = ab.adminLogin(id, password);
				switch (Case) {
				case -1:
					JOptionPane.showMessageDialog(null, "您输入的账号不存在,请重新输入");
					break;
				case 0:
					JOptionPane.showMessageDialog(null, "您输入的密码有误，请重新输入");
					break;
				case 1:
					new AdminWindow("管理员界面");
					dispose();
					break;
				}
			}

		}
	}

    /**
     * 设置退出的监听事件
     * @author lah
     *
     */
	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int option = JOptionPane.showConfirmDialog(p, "确定退出本系统？", "提示",
					JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION)
				System.exit(0);
		}
	}
}
