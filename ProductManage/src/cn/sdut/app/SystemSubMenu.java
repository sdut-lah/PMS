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
	 * ��Ӳ���Ա���
	 * 
	 * @return
	 */
	public static JPanel addOperaterPanel() {

		addOperator.setLayout(null);

		/**
		 * ����Ա����
		 */
		JLabel name = new JLabel("����Ա������");
		name.setFont(new Font("����", Font.PLAIN, 15));
		name.setBounds(160, 74, 100, 60);
		addOperator.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		addOperator.add(textName);

		/**
		 * ����Ա����
		 */
		JLabel password = new JLabel("�������룺");
		password.setFont(new Font("����", Font.PLAIN, 15));
		password.setBounds(160, 110, 80, 60);
		addOperator.add(password);
		final JPasswordField textPassword = new JPasswordField(10);
		textPassword.setFont(new Font("����", Font.PLAIN, 15));
		textPassword.setBounds(245, 130, 140, 25);
		textPassword.setEchoChar('*');
		addOperator.add(textPassword);

		/**
		 * ȷ������
		 */
		JLabel confirmPwd = new JLabel("ȷ�����룺");
		confirmPwd.setFont(new Font("����", Font.PLAIN, 15));
		confirmPwd.setBounds(160, 148, 80, 60);
		addOperator.add(confirmPwd);
		final JPasswordField textConfirmPwd = new JPasswordField(10);
		textConfirmPwd.setFont(new Font("����", Font.PLAIN, 15));
		textConfirmPwd.setBounds(245, 168, 140, 25);
		textConfirmPwd.setEchoChar('*');
		addOperator.add(textConfirmPwd);

		/**
		 * ��Ӻ����ð�ť
		 */
		JButton add = new JButton("���");
		add.setFont(new Font("����", Font.PLAIN, 15));
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
					JOptionPane.showMessageDialog(null, "������������벻һ�£�");
					textPassword.setText("");
					textConfirmPwd.setText("");
				} else {
					OperaterBean operater = new OperaterBean();
					operater.setName(name);
					operater.setPsw(pwd);
					if(LoginWindow.flag==0){
						JOptionPane.showMessageDialog(null, "�Բ�����û��Ȩ���޸�!");
					}
					int id = smd.addOperater(operater);
					if (id != 0) {
						JOptionPane.showMessageDialog(null, "��ӳɹ�,�˺�Ϊ��" + id);
					} else {
						JOptionPane.showMessageDialog(null, "���ʧ��");
					}
				}

			}
		});
		JButton reset = new JButton("����");
		reset.setFont(new Font("����", Font.PLAIN, 15));
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
	 * ɾ������Ա���
	 * 
	 * @return
	 */
	public static JPanel deleOperaterPanel() {

		deleOperator.setLayout(null);
		/**
		 * ����Ա����
		 */
		JLabel name = new JLabel("����Ա������");
		name.setFont(new Font("����", Font.PLAIN, 15));
		name.setBounds(160, 74, 100, 60);
		deleOperator.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		deleOperator.add(textName);

		/**
		 * ����Ա�˺�
		 */
		JLabel account = new JLabel("����Ա�˺ţ�");
		account.setFont(new Font("����", Font.PLAIN, 15));
		account.setBounds(160, 110, 100, 60);
		deleOperator.add(account);
		final JTextField textAccount = new JTextField(10);
		textAccount.setFont(new Font("����", Font.PLAIN, 15));
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
		 * ɾ�������ð�ť
		 */
		JButton delete = new JButton("ɾ��");
		delete.setFont(new Font("����", Font.PLAIN, 15));
		delete.setBackground(Color.GREEN);
		delete.setBounds(190, 250, 80, 25);
		deleOperator.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (LoginWindow.flag == 0) {
					JOptionPane.showMessageDialog(null, "�Բ�����û��ɾ��Ȩ��");
				} else {
					LoginDao ld = new LoginDao();
					String account = textAccount.getText().trim();
					OperaterBean operater = ld.getOperaterByID(Integer
							.valueOf(account));
					if (operater == null) {
						JOptionPane.showMessageDialog(null, "�˺Ų�����");
					} else {

						String name = textName.getText().trim();
						String id = textAccount.getText().trim();
						if (smd.deleOperater(name, id)) {
							JOptionPane.showMessageDialog(null, "�ɹ�ɾ��");
						} else {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
						}
					}
				}

			}
		});
		JButton reset = new JButton("����");
		reset.setFont(new Font("����", Font.PLAIN, 15));
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
	 * �����������
	 * 
	 * @return
	 */
	public static JPanel newPswPanel() {

		newPsw.setLayout(null);
		/**
		 * ����Ա������
		 */
		JLabel oldPwd = new JLabel("��������룺");
		oldPwd.setFont(new Font("����", Font.PLAIN, 15));
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
		 * ����Ա������
		 */
		JLabel password = new JLabel("���������룺");
		password.setFont(new Font("����", Font.PLAIN, 15));
		password.setBounds(160, 110, 100, 60);
		newPsw.add(password);
		final JPasswordField textPassword = new JPasswordField(10);
		textPassword.setFont(new Font("����", Font.PLAIN, 15));
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
		 * ȷ��������
		 */
		JLabel confirmPwd = new JLabel("ȷ�������룺");
		confirmPwd.setFont(new Font("����", Font.PLAIN, 15));
		confirmPwd.setBounds(160, 148, 100, 60);
		newPsw.add(confirmPwd);
		final JPasswordField textConfirmPwd = new JPasswordField(10);
		textConfirmPwd.setFont(new Font("����", Font.PLAIN, 15));
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
		 * ȷ�Ϻ����ð�ť
		 */
		JButton delete = new JButton("ȷ��");
		delete.setFont(new Font("����", Font.PLAIN, 15));
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
					JOptionPane.showMessageDialog(null, "������������벻һ�£�");
				} else {
					if (!old.equals(LoginWindow.password)) {
						JOptionPane.showMessageDialog(null, "�����ԭʼ��������");
					} else {
						String newPwd1 = new String(textPassword.getPassword())
								.trim();
						int id = Integer.valueOf(LoginWindow.id);
						if (smd.changePwd(id, newPwd1, LoginWindow.flag)) {
							JOptionPane.showMessageDialog(null, "������ĳɹ���");
						} else {
							JOptionPane.showMessageDialog(null, "�������ʧ�ܣ�");
						}
					}
				}
			}
		});
		JButton reset = new JButton("����");
		reset.setFont(new Font("����", Font.PLAIN, 15));
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
