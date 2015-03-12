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
	 * 销售列表面板
	 * @return
	 */
	public static JPanel sellListPanel(){
		
		sellList.setLayout(null);
		/**
		 * 货物名称
		 */
		JLabel name = new JLabel("货物名称：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160,74,80,60);
		sellList.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		sellList.add(textName);
		
		/**
		 * 订货数量
		 */
		JLabel number = new JLabel("订货数量：");
		number.setFont(new Font("黑体", Font.PLAIN, 15));
		number.setBounds(160, 110, 80, 60);
		sellList.add(number);
		final JTextField textNumber = new JTextField(10);
		textNumber.setFont(new Font("黑体", Font.PLAIN, 15));
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
		 * 货品单价
		 */
		JLabel price = new JLabel("货品单价：");
		price.setFont(new Font("黑体", Font.PLAIN, 15));
		price.setBounds(160, 148, 80, 60);
		sellList.add(price);
		final JTextField textPrice = new JTextField(10);
		textPrice.setFont(new Font("黑体", Font.PLAIN, 15));
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
		 * 订单客户
		 */
		JLabel client = new JLabel("订单客户：");
		client.setFont(new Font("黑体", Font.PLAIN, 15));
		client.setBounds(160, 187, 80, 60);
		sellList.add(client);
		final JTextField textClient = new JTextField(10);
		textClient.setFont(new Font("黑体", Font.PLAIN, 15));
		textClient.setBounds(245, 206, 140, 25);
		sellList.add(textClient);
		
		/**
		 * 确认和重置按钮
		 */
		JButton confirm = new JButton("确认");
		confirm.setFont(new Font("黑体", Font.PLAIN, 15));
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
					JOptionPane.showMessageDialog(null,"订单未成功，仓库中无此产品");
					break;
				case -1:
					JOptionPane.showMessageDialog(null, "订单未成功，该产品库存不足");
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "订单成功！");
					OrderDao od = new OrderDao();
					od.getOderId(order);
					break;
				}
				
			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
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
