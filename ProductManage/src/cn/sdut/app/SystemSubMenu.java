package cn.sdut.app;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.sdut.dao.LoginDao;
import cn.sdut.dao.SystemMangeDao;
import cn.sdut.entity.OperaterBean;

public class SystemSubMenu {

	static JPanel addOperator = new JPanel();
	static JPanel deleOperator = new JPanel();
	static JPanel newPsw = new JPanel();
	static SystemMangeDao smd = new SystemMangeDao();

	/**
	 * 添加操作员面板
	 * 
	 * @return
	 */
	public static JPanel addOperaterPanel() {

		addOperator.setLayout(null);

		/**
		 * 操作员姓名
		 */
		JLabel name = new JLabel("操作员姓名：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160, 74, 100, 60);
		addOperator.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		addOperator.add(textName);

		/**
		 * 操作员密码
		 */
		JLabel password = new JLabel("输入密码：");
		password.setFont(new Font("黑体", Font.PLAIN, 15));
		password.setBounds(160, 110, 80, 60);
		addOperator.add(password);
		final JPasswordField textPassword = new JPasswordField(10);
		textPassword.setFont(new Font("黑体", Font.PLAIN, 15));
		textPassword.setBounds(245, 130, 140, 25);
		textPassword.setEchoChar('*');
		addOperator.add(textPassword);

		/**
		 * 确认密码
		 */
		JLabel confirmPwd = new JLabel("确认密码：");
		confirmPwd.setFont(new Font("黑体", Font.PLAIN, 15));
		confirmPwd.setBounds(160, 148, 80, 60);
		addOperator.add(confirmPwd);
		final JPasswordField textConfirmPwd = new JPasswordField(10);
		textConfirmPwd.setFont(new Font("黑体", Font.PLAIN, 15));
		textConfirmPwd.setBounds(245, 168, 140, 25);
		textConfirmPwd.setEchoChar('*');
		addOperator.add(textConfirmPwd);

		/**
		 * 添加和重置按钮
		 */
		JButton add = new JButton("添加");
		add.setFont(new Font("黑体", Font.PLAIN, 15));
		add.setBackground(Color.GREEN);
		add.setBounds(200, 270, 80, 25);
		addOperator.add(add);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String name = textName.getText().trim();
				String pwd = new String(textPassword.getPassword()).trim();
				String newPwd = new String(textConfirmPwd.getPassword()).trim();

				if (!pwd.equals(newPwd)) {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
					textPassword.setText("");
					textConfirmPwd.setText("");
				} else {
					OperaterBean operater = new OperaterBean();
					operater.setName(name);
					operater.setPsw(pwd);
					if(LoginWindow.flag==0){
						JOptionPane.showMessageDialog(null, "对不起，您没有权限修改!");
					}
					int id = smd.addOperater(operater);
					if (id != 0) {
						JOptionPane.showMessageDialog(null, "添加成功,账号为：" + id);
					} else {
						JOptionPane.showMessageDialog(null, "添加失败");
					}
				}

			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(315, 270, 80, 25);
		addOperator.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textPassword.setText("");
				textConfirmPwd.setText("");
				
			}
		});
		addOperator.setOpaque(false);
		return addOperator;
	}

	/**
	 * 删除操作员面板
	 * 
	 * @return
	 */
	public static JPanel deleOperaterPanel() {

		deleOperator.setLayout(null);
		/**
		 * 操作员姓名
		 */
		JLabel name = new JLabel("操作员姓名：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160, 74, 100, 60);
		deleOperator.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		deleOperator.add(textName);

		/**
		 * 操作员账号
		 */
		JLabel account = new JLabel("操作员账号：");
		account.setFont(new Font("黑体", Font.PLAIN, 15));
		account.setBounds(160, 110, 100, 60);
		deleOperator.add(account);
		final JTextField textAccount = new JTextField(10);
		textAccount.setFont(new Font("黑体", Font.PLAIN, 15));
		textAccount.setBounds(245, 130, 140, 25);
		deleOperator.add(textAccount);
		textAccount.addKeyListener(new KeyAdapter() {

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
		deleOperator.setOpaque(false);

		/**
		 * 删除和重置按钮
		 */
		JButton delete = new JButton("删除");
		delete.setFont(new Font("黑体", Font.PLAIN, 15));
		delete.setBackground(Color.GREEN);
		delete.setBounds(190, 250, 80, 25);
		deleOperator.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (LoginWindow.flag == 0) {
					JOptionPane.showMessageDialog(null, "对不起，您没有删除权限");
				} else {
					LoginDao ld = new LoginDao();
					String account = textAccount.getText().trim();
					OperaterBean operater = ld.getOperaterByID(Integer
							.valueOf(account));
					if (operater == null) {
						JOptionPane.showMessageDialog(null, "账号不存在");
					} else {

						String name = textName.getText().trim();
						String id = textAccount.getText().trim();
						if (smd.deleOperater(name, id)) {
							JOptionPane.showMessageDialog(null, "成功删除");
						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
					}
				}

			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(305, 250, 80, 25);
		deleOperator.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textAccount.setText("");

			}
		});

		return deleOperator;

	}

	/**
	 * 更改密码面板
	 * 
	 * @return
	 */
	public static JPanel newPswPanel() {

		newPsw.setLayout(null);
		/**
		 * 操作员旧密码
		 */
		JLabel oldPwd = new JLabel("输入旧密码：");
		oldPwd.setFont(new Font("黑体", Font.PLAIN, 15));
		oldPwd.setBounds(160, 74, 100, 60);
		newPsw.add(oldPwd);
		final JTextField textOldPwd = new JTextField(10);
		textOldPwd.setBounds(245, 92, 140, 25);
		newPsw.add(textOldPwd);
		textOldPwd.addKeyListener(new KeyAdapter() {

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

		/**
		 * 操作员新密码
		 */
		JLabel password = new JLabel("输入新密码：");
		password.setFont(new Font("黑体", Font.PLAIN, 15));
		password.setBounds(160, 110, 100, 60);
		newPsw.add(password);
		final JPasswordField textPassword = new JPasswordField(10);
		textPassword.setFont(new Font("黑体", Font.PLAIN, 15));
		textPassword.setBounds(245, 130, 140, 25);
		newPsw.add(textPassword);
		textPassword.addKeyListener(new KeyAdapter() {

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

		/**
		 * 确认新密码
		 */
		JLabel confirmPwd = new JLabel("确认新密码：");
		confirmPwd.setFont(new Font("黑体", Font.PLAIN, 15));
		confirmPwd.setBounds(160, 148, 100, 60);
		newPsw.add(confirmPwd);
		final JPasswordField textConfirmPwd = new JPasswordField(10);
		textConfirmPwd.setFont(new Font("黑体", Font.PLAIN, 15));
		textConfirmPwd.setBounds(245, 168, 140, 25);
		newPsw.add(textConfirmPwd);
		textConfirmPwd.addKeyListener(new KeyAdapter() {

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

		/**
		 * 确认和重置按钮
		 */
		JButton delete = new JButton("确认");
		delete.setFont(new Font("黑体", Font.PLAIN, 15));
		delete.setBackground(Color.GREEN);
		delete.setBounds(190, 250, 80, 25);
		newPsw.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String old = textOldPwd.getText().trim();
				String newPwd = new String(textPassword.getPassword()).trim();
				String confirmPwd = new String(textConfirmPwd.getPassword())
						.trim();
				if (!newPwd.equals(confirmPwd)) {
					JOptionPane.showMessageDialog(null, "两次输入的密码不一致！");
				} else {
					if (!old.equals(LoginWindow.password)) {
						JOptionPane.showMessageDialog(null, "输入的原始密码有误！");
					} else {
						String newPwd1 = new String(textPassword.getPassword())
								.trim();
						int id = Integer.valueOf(LoginWindow.id);
						if (smd.changePwd(id, newPwd1, LoginWindow.flag)) {
							JOptionPane.showMessageDialog(null, "密码更改成功！");
						} else {
							JOptionPane.showMessageDialog(null, "密码更改失败！");
						}
					}
				}
			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(305, 250, 80, 25);
		newPsw.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				textOldPwd.setText("");
				textPassword.setText("");
				textConfirmPwd.setText("");

			}
		});
		newPsw.setOpaque(false);
		return newPsw;

	}

}
