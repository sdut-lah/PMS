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
import cn.sdut.dao.GoodsOrderDao;
import cn.sdut.entity.GoodsOrderBean;
import cn.sdut.util.GetTime;

public class StockInSubMenu {

	static JPanel listIn = new JPanel();
	static JPanel listOut = new JPanel();
	static GoodsOrderBean goodsOrder = new GoodsOrderBean();
	static GoodsOrderDao god = new GoodsOrderDao();

	/**
	 * 入库账单列表面板
	 * 
	 * @return
	 */
	public static JPanel listInPanel() {

		listIn.setLayout(null);

		/**
		 * 货物名称
		 */
		JLabel name = new JLabel("货物名称：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160, 74, 80, 60);
		listIn.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		listIn.add(textName);

		/**
		 * 订货 数量
		 */
		JLabel number = new JLabel("订货数量：");
		number.setFont(new Font("黑体", Font.PLAIN, 15));
		number.setBounds(160, 110, 80, 60);
		listIn.add(number);
		final JTextField textNumber = new JTextField(10);
		textNumber.setFont(new Font("黑体", Font.PLAIN, 15));
		textNumber.setBounds(245, 130, 140, 25);
		textNumber.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char ch = e.getKeyChar();
				if ((ch >= '0' && ch <= '9')) {

				} else {
					e.consume();
					return;
				}
			}

		});
		listIn.add(textNumber);

		/**
		 * 货品单价
		 */
		JLabel price = new JLabel("货品单价：");
		price.setFont(new Font("黑体", Font.PLAIN, 15));
		price.setBounds(160, 148, 80, 60);
		listIn.add(price);
		final JTextField textPrice = new JTextField(10);
		textPrice.setFont(new Font("黑体", Font.PLAIN, 15));
		textPrice.setBounds(245, 168, 140, 25);
		textPrice.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char ch = e.getKeyChar();
				if ((ch >= '0' && ch <= '9') || ch == '.') {

				} else {
					e.consume();
					return;
				}
			}

		});
		listIn.add(textPrice);

		/**
		 * 供应来源
		 */
		JLabel supplier = new JLabel("供应来源：");
		supplier.setFont(new Font("黑体", Font.PLAIN, 15));
		supplier.setBounds(160, 187, 80, 60);
		listIn.add(supplier);
		final JTextField textSupplier = new JTextField(10);
		textSupplier.setFont(new Font("黑体", Font.PLAIN, 15));
		textSupplier.setBounds(245, 206, 140, 25);
		listIn.add(textSupplier);

		/**
		 * 确认和重置按钮
		 */
		JButton confirm = new JButton("确认");
		confirm.setFont(new Font("黑体", Font.PLAIN, 15));
		confirm.setBackground(Color.GREEN);
		confirm.setBounds(190, 270, 80, 25);
		listIn.add(confirm);
		confirm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText().trim();
				int num = Integer.parseInt(textNumber.getText().trim());
				double price = Double.parseDouble(textPrice.getText().trim());
				String supplier = textSupplier.getText().trim();
				goodsOrder.setGname(name);
				goodsOrder.setGnumber(num);
				goodsOrder.setGprice(price);
				goodsOrder.setGsupplier(supplier);
				goodsOrder.setOperater(LoginWindow.id);
				goodsOrder.setGtime(GetTime.getCurrenttime());
				goodsOrder.setState(0);
				int id = god.makeGoodsOrder(goodsOrder);
				OrderInfo.list.add(id);
				JOptionPane.showMessageDialog(null, "下单成功，订单号为：" + id);

			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(305, 270, 80, 25);
		listIn.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textNumber.setText("");
				textPrice.setText("");
				textSupplier.setText("");
			}
		});
		listIn.setOpaque(false);

		return listIn;
	}

}
