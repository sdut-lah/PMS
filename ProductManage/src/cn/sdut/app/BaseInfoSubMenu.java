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

import cn.sdut.biz.BaseInfoBiz;
import cn.sdut.dao.BaseInfoDao;
import cn.sdut.dao.GoodsOrderDao;
import cn.sdut.dao.ProductInfoDao;
import cn.sdut.entity.ClientBean;
import cn.sdut.entity.ProductBean;
import cn.sdut.entity.SupplierBean;
import cn.sdut.util.GetTime;

public class BaseInfoSubMenu {

	static JPanel addClient = new JPanel();
	static JPanel deleClient = new JPanel();
	static JPanel addGoods = new JPanel();
	static JPanel deleGoods = new JPanel();
	static JPanel addSupplier = new JPanel();
	static JPanel deleSupplier = new JPanel();
	static ClientBean client = new ClientBean();
	static SupplierBean supplier = new SupplierBean();
	static BaseInfoDao bid = new BaseInfoDao();
	static GoodsOrderDao god = new GoodsOrderDao();
	static ProductInfoDao pid = new ProductInfoDao();

	/**
	 * 添加客户面板
	 * 
	 * @return
	 */
	public static JPanel addClientPanel() {

		addClient.setLayout(null);

		/**
		 * 客户姓名
		 */
		JLabel name = new JLabel("客户姓名：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160, 74, 80, 60);
		addClient.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		addClient.add(textName);

		/**
		 * 客户地址
		 */
		JLabel address = new JLabel("客户地址：");
		address.setFont(new Font("黑体", Font.PLAIN, 15));
		address.setBounds(160, 110, 80, 60);
		addClient.add(address);
		final JTextField textAddress = new JTextField(10);
		textAddress.setFont(new Font("黑体", Font.PLAIN, 15));
		textAddress.setBounds(245, 130, 140, 25);
		addClient.add(textAddress);

		/**
		 * 联系方式
		 */
		JLabel phone = new JLabel("联系方式：");
		phone.setFont(new Font("黑体", Font.PLAIN, 15));
		phone.setBounds(160, 148, 80, 60);
		addClient.add(phone);
		final JTextField textPhone = new JTextField(10);
		textPhone.setFont(new Font("黑体", Font.PLAIN, 15));
		textPhone.setBounds(245, 168, 140, 25);
		addClient.add(textPhone);
		textPhone.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char ch = e.getKeyChar();
				if (ch >= '0' && ch <= '9') {

				} else {
					e.consume();
					return;
				}
			}

		});

		/**
		 * 银行账号
		 */
		JLabel account = new JLabel("银行账号：");
		account.setFont(new Font("黑体", Font.PLAIN, 15));
		account.setBounds(160, 187, 80, 60);
		addClient.add(account);
		final JTextField textAccount = new JTextField(10);
		textAccount.setFont(new Font("黑体", Font.PLAIN, 15));
		textAccount.setBounds(245, 206, 140, 25);
		addClient.add(textAccount);
		textAccount.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char ch = e.getKeyChar();
				if (ch <= '9' && ch >= '0') {

				} else {
					e.consume();
					return;
				}

			}

		});

		/**
		 * 添加和重置按钮
		 */
		JButton save = new JButton("添加");
		save.setFont(new Font("黑体", Font.PLAIN, 15));
		save.setBackground(Color.GREEN);
		save.setBounds(190, 270, 80, 25);
		addClient.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				client.setClientName(textName.getText().trim());
				client.setAddress(textAddress.getText().trim());
				client.setPhoneNumber(textPhone.getText().trim());
				client.setBankAccount(textAccount.getText().trim());
				System.out.println(client.getClientName());
				if (bid.addClient(client)) {
					JOptionPane.showMessageDialog(null, "客户信息添加成功！");
					textName.setText("");
					textAddress.setText("");
					textPhone.setText("");
					textAccount.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "客户信息添加失败！");
				}

			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(305, 270, 80, 25);
		addClient.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textAddress.setText("");
				textPhone.setText("");
				textAccount.setText("");

			}
		});
		addClient.setOpaque(false);
		return addClient;

	}

	/**
	 * 删除客户面板
	 * 
	 * @return
	 */
	public static JPanel deleClientPanel() {

		deleClient.setLayout(null);

		/**
		 * 客户姓名
		 */
		JLabel name = new JLabel("客户姓名：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160, 74, 80, 60);
		deleClient.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		deleClient.add(textName);

		/**
		 * 联系方式
		 */
		JLabel phone = new JLabel("联系方式：");
		phone.setFont(new Font("黑体", Font.PLAIN, 15));
		phone.setBounds(160, 110, 80, 60);
		deleClient.add(phone);
		final JTextField textPhone = new JTextField(10);
		textPhone.setFont(new Font("黑体", Font.PLAIN, 15));
		textPhone.setBounds(245, 130, 140, 25);
		deleClient.add(textPhone);
		textPhone.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (ch <= '9' && ch >= '0') {

				} else {
					e.consume();
					return;
				}

			}

		});

		/**
		 * 删除和重置按钮
		 */
		JButton dele = new JButton("删除");
		dele.setFont(new Font("黑体", Font.PLAIN, 15));
		dele.setBackground(Color.GREEN);
		dele.setBounds(205, 180, 80, 25);
		deleClient.add(dele);
		dele.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText().trim();
				String phone = textPhone.getText().trim();

				if (bid.deleClient(name, phone)) {
					JOptionPane.showMessageDialog(null, "删除成功！");
				} else {
					JOptionPane.showMessageDialog(null, "删除失败，请核对输入信息！");
				}
			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(310, 180, 80, 25);
		deleClient.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textPhone.setText("");

			}
		});
		deleClient.setOpaque(false);
		return deleClient;

	}

	/**
	 * 删除产品面板
	 * 
	 * @return
	 */
	public static JPanel deleGoodsPanel() {

		deleGoods.setLayout(null);

		/**
		 * 产品编号
		 */
		JLabel num = new JLabel("产品编号：");
		System.out.println("deleGoodsPanel 执行");
		num.setFont(new Font("黑体", Font.PLAIN, 15));
		num.setBounds(160, 74, 80, 60);
		deleGoods.add(num);
		final JTextField textNum = new JTextField(10);
		textNum.setBounds(245, 92, 140, 25);
		deleGoods.add(textNum);
		textNum.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char ch = e.getKeyChar();
				if (ch >= '0' && ch <= '9') {

				} else {
					e.consume();
					return;
				}
			}

		});

		/**
		 * 产品名称
		 */
		JLabel name = new JLabel("产品名称：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160, 110, 80, 60);
		deleGoods.add(name);
		final JTextField textName = new JTextField(10);
		textName.setFont(new Font("黑体", Font.PLAIN, 15));
		textName.setBounds(245, 130, 140, 25);
		deleGoods.add(textName);

		/**
		 * 产品数量
		 */
		JLabel number = new JLabel("产品数量：");
		number.setFont(new Font("黑体", Font.PLAIN, 15));
		number.setBounds(160, 148, 80, 60);
		deleGoods.add(number);
		final JTextField textNumber = new JTextField(10);
		textNumber.setFont(new Font("黑体", Font.PLAIN, 15));
		textNumber.setBounds(245, 168, 140, 25);
		deleGoods.add(textNumber);
		textNumber.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char ch = e.getKeyChar();
				if (ch >= '0' && ch <= '9') {

				} else {
					e.consume();
					return;
				}
			}

		});

		/**
		 * 产品价格
		 */
		JLabel price = new JLabel("产品价格：");
		price.setFont(new Font("黑体", Font.PLAIN, 15));
		price.setBounds(160, 187, 80, 60);
		deleGoods.add(price);
		final JTextField textPrice = new JTextField(10);
		textPrice.setFont(new Font("黑体", Font.PLAIN, 15));
		textPrice.setBounds(245, 206, 140, 25);
		deleGoods.add(textPrice);
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
		/**
		 * 供应来源
		 */
		JLabel supplier = new JLabel("供应来源：");
		supplier.setFont(new Font("黑体", Font.PLAIN, 15));
		supplier.setBounds(160, 226, 80, 60);
		deleGoods.add(supplier);
		final JTextField textSupplier = new JTextField(10);
		textSupplier.setFont(new Font("黑体", Font.PLAIN, 15));
		textSupplier.setBounds(245, 245, 140, 25);
		deleGoods.add(textSupplier);

		/**
		 * 修改和删除按钮
		 */
		JButton save = new JButton("修改");
		save.setFont(new Font("黑体", Font.PLAIN, 15));
		save.setBackground(Color.GREEN);
		save.setBounds(190, 300, 80, 25);
		deleGoods.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ProductBean pb = new ProductBean();

				String id = textNum.getText().trim();
				String name = textName.getText().trim();
				String number = textNumber.getText().trim();
				String price = textPrice.getText().trim();
				String supplier = textSupplier.getText().trim();

				pb.setP_id(Integer.valueOf(id));
				pb.setPname(name);
				pb.setPnumber(Integer.valueOf(number));
				pb.setPrice(Integer.valueOf(price));
				pb.setSupplier(supplier);
				pb.setStockInTime(GetTime.getCurrenttime());
				if (pid.updateProduct(pb)) {
					JOptionPane.showMessageDialog(null, "修改成功！");
				} else {
					JOptionPane.showMessageDialog(null, "修改失败！");
				}

			}
		});

		JButton reset = new JButton("删除");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(305, 300, 80, 25);
		deleGoods.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String id = textNum.getText().trim();
				if (id.equals("")) {
					JOptionPane.showMessageDialog(null, "产品编号不能为空！");
				} else {
					if (pid.deleProductById(Integer.valueOf(id))) {
						JOptionPane.showMessageDialog(null, "删除成功！");
					} else {
						JOptionPane.showMessageDialog(null, "删除失败！，请核对产品编号！");
					}
				}

			}
		});
		deleGoods.setOpaque(false);

		return deleGoods;
	}

	/**
	 * 添加供应商面板
	 * 
	 * @return
	 */
	public static JPanel addSupplierPanel() {

		addSupplier.setLayout(null);
		/**
		 * 供应商姓名
		 */
		JLabel name = new JLabel("供应商名称：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160, 74, 100, 60);
		addSupplier.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		addSupplier.add(textName);

		/**
		 * 客户地址
		 */
		JLabel address = new JLabel("供应商地址：");
		address.setFont(new Font("黑体", Font.PLAIN, 15));
		address.setBounds(160, 110, 100, 60);
		addSupplier.add(address);
		final JTextField textAddress = new JTextField(10);
		textAddress.setFont(new Font("黑体", Font.PLAIN, 15));
		textAddress.setBounds(245, 130, 140, 25);
		addSupplier.add(textAddress);

		/**
		 * 联系方式
		 */
		JLabel phone = new JLabel("供应商电话：");
		phone.setFont(new Font("黑体", Font.PLAIN, 15));
		phone.setBounds(160, 148, 100, 60);
		addSupplier.add(phone);
		final JTextField textPhone = new JTextField(10);
		textPhone.setFont(new Font("黑体", Font.PLAIN, 15));
		textPhone.setBounds(245, 168, 140, 25);
		addSupplier.add(textPhone);
		textPhone.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				char ch = e.getKeyChar();
				if (ch >= '0' && ch <= '9') {

				} else {
					e.consume();
					return;
				}
			}

		});
		/**
		 * 银行账号
		 */
		JLabel account = new JLabel("银行账号 ：");
		account.setFont(new Font("黑体", Font.PLAIN, 15));
		account.setBounds(160, 187, 100, 60);
		addSupplier.add(account);
		final JTextField textAccount = new JTextField(10);
		textAccount.setFont(new Font("黑体", Font.PLAIN, 15));
		textAccount.setBounds(245, 206, 140, 25);
		addSupplier.add(textAccount);
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

		/**
		 * 保存和重置按钮
		 */
		JButton save = new JButton("添加");
		save.setFont(new Font("黑体", Font.PLAIN, 15));
		save.setBackground(Color.GREEN);
		save.setBounds(190, 270, 100, 25);
		addSupplier.add(save);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				supplier.setSname(textName.getText().trim());
				supplier.setSphone(textPhone.getText().trim());
				supplier.setSaddress(textAddress.getText().trim());
				supplier.setSbankAccount(textAccount.getText().trim());
                BaseInfoBiz biz = new BaseInfoBiz();
                if(biz.addSupplier(supplier)){
                	JOptionPane.showMessageDialog(null,"添加成功!");
                }else{
                	JOptionPane.showMessageDialog(null,"添加失败!");
                }
			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(305, 270, 80, 25);
		addSupplier.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textAccount.setText("");
				textAddress.setText("");
				textPhone.setText("");
			}
		});
		addSupplier.setOpaque(false);
		return addSupplier;
	}

	/**
	 * 删除供应商面板
	 * 
	 * @return
	 */
	public static JPanel deleSupplierPanel() {

		deleSupplier.setLayout(null);

		/**
		 * 供应商姓名
		 */
		JLabel name = new JLabel("供应商名称：");
		name.setFont(new Font("黑体", Font.PLAIN, 15));
		name.setBounds(160, 74, 100, 60);
		deleSupplier.add(name);
		final JTextField textName = new JTextField(10);
		textName.setBounds(245, 92, 140, 25);
		deleSupplier.add(textName);

		/**
		 * 联系方式
		 */
		JLabel phone = new JLabel("供应商电话：");
		phone.setFont(new Font("黑体", Font.PLAIN, 15));
		phone.setBounds(160, 110, 100, 60);
		deleSupplier.add(phone);
		final JTextField textPhone = new JTextField(10);
		textPhone.setFont(new Font("黑体", Font.PLAIN, 15));
		textPhone.setBounds(245, 130, 140, 25);
		deleSupplier.add(textPhone);
		textPhone.addKeyListener(new KeyAdapter() {

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
		 * 删除和重置按钮
		 */
		JButton dele = new JButton("删除");
		dele.setFont(new Font("黑体", Font.PLAIN, 15));
		dele.setBackground(Color.GREEN);
		dele.setBounds(205, 180, 80, 25);
		deleSupplier.add(dele);
		dele.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textName.getText().trim();
				String phone = textPhone.getText().trim();
				if (bid.deleClient(name, phone)) {
					JOptionPane.showMessageDialog(null, "删除成功！");
				} else {
					JOptionPane.showMessageDialog(null, "删除失败！，请核对输入信息！");
				}

			}
		});
		JButton reset = new JButton("重置");
		reset.setFont(new Font("黑体", Font.PLAIN, 15));
		reset.setBackground(Color.GREEN);
		reset.setBounds(310, 180, 80, 25);
		deleSupplier.add(reset);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textPhone.setText("");

			}
		});
		deleSupplier.setOpaque(false);

		return deleSupplier;
	}

}
