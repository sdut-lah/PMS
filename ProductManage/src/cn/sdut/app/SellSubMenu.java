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
import javax.swing.JTextField;

import cn.sdut.biz.OrderInfo;
import cn.sdut.dao.OrderDao;
import cn.sdut.entity.OrderBean;
import cn.sdut.util.GetTime;

public class SellSubMenu {
	
	static JPanel sellList = new JPanel();
	/**
	 * �����б����
	 * @return
	 */
	public static JPanel sellListPanel(){
		
		sellList.setLayout(null);
		/**
		 * ��������
		 */
		JLabel name = new JLabel("�������ƣ�");
		name.setFont(new Font("����", Font.PLAIN, 15));
		name.setBounds(160,74,80,60);
		sellList.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		sellList.add(textName);
		
		/**
		 * ��������
		 */
		JLabel number = new JLabel("����������");
		number.setFont(new Font("����", Font.PLAIN, 15));
		number.setBounds(160, 110, 80, 60);
		sellList.add(number);
		final JTextField textNumber = new JTextField(10);
		textNumber.setFont(new Font("����", Font.PLAIN, 15));
		textNumber.setBounds(245, 130, 140, 25);
		sellList.add(textNumber);
		textNumber.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(ch>='0'&&ch<='9'){
					
				}else{
					e.consume();
					return ;
				}
			}
			
		});
		
		/**
		 * ��Ʒ����
		 */
		JLabel price = new JLabel("��Ʒ���ۣ�");
		price.setFont(new Font("����", Font.PLAIN, 15));
		price.setBounds(160, 148, 80, 60);
		sellList.add(price);
		final JTextField textPrice = new JTextField(10);
		textPrice.setFont(new Font("����", Font.PLAIN, 15));
		textPrice.setBounds(245, 168, 140, 25);
		sellList.add(textPrice);
		textPrice.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if((ch>='0'&&ch<='9')||ch=='.'){
					
				}else{
					e.consume();
					return ;
				}
			}
			
		});
		
		/**
		 * �����ͻ�
		 */
		JLabel client = new JLabel("�����ͻ���");
		client.setFont(new Font("����", Font.PLAIN, 15));
		client.setBounds(160, 187, 80, 60);
		sellList.add(client);
		final JTextField textClient = new JTextField(10);
		textClient.setFont(new Font("����", Font.PLAIN, 15));
		textClient.setBounds(245, 206, 140, 25);
		sellList.add(textClient);
		
		/**
		 * ȷ�Ϻ����ð�ť
		 */
		JButton confirm = new JButton("ȷ��");
		confirm.setFont(new Font("����", Font.PLAIN, 15));
		confirm.setBackground(Color.GREEN);
		confirm.setBounds(190, 270, 80, 25);
		sellList.add(confirm);
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				OrderBean order = new OrderBean();
				String name = textName.getText().trim();
				String num = textNumber.getText().trim();
				String price = textPrice.getText().trim();
				String client = textClient.getText().trim();
				order.setName(name);
				order.setNumber(Integer.valueOf(num));
				order.setPrice(Double.parseDouble(price));
				order.setClient(client);
				order.setOrderTime(GetTime.getCurrenttime());
				int Case = OrderInfo.confirmOrder(order);
				switch(Case){
				case 0:
					JOptionPane.showMessageDialog(null,"����δ�ɹ����ֿ����޴˲�Ʒ");
					break;
				case -1:
					JOptionPane.showMessageDialog(null, "����δ�ɹ����ò�Ʒ��治��");
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "�����ɹ���");
					OrderDao od = new OrderDao();
					od.getOderId(order);
					break;
				}
				
			}
		});
		JButton reset = new JButton("����");
		reset.setFont(new Font("����", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(305, 270, 80, 25);
		sellList.add(reset);
		reset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textNumber.setText("");
				textClient.setText("");
				textPrice.setText("");
			}
		});
		sellList.setOpaque(false);
		return sellList;
	}

}
