package cn.sdut.app;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import cn.sdut.biz.BaseInfoBiz;
import cn.sdut.biz.OrderInfo;
import cn.sdut.dao.GoodsOrderDao;
import cn.sdut.dao.ProductInfoDao;
import cn.sdut.util.Data;
import cn.sdut.util.Table;

public class AdminWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	ProductInfoDao pid = new ProductInfoDao();

	public static JScrollPane tablePanel = new JScrollPane();
	public static JScrollPane tablePanel6 = new JScrollPane();
	JScrollPane tablePanel2 = new JScrollPane();
	JScrollPane tablePanel3 = new JScrollPane();
	JScrollPane tablePanel4 = new JScrollPane();
	JScrollPane tablePanel5 = new JScrollPane();

	static CardLayout card = new CardLayout();
	JPanel p = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel addGoodsPanel = new JPanel(new BorderLayout());

	static JPanel cardPanel = new JPanel();
	JTabbedPane tabbed_customer = new JTabbedPane();
	JTabbedPane tabbed_supplier = new JTabbedPane();
	JTabbedPane tabbed_goods = new JTabbedPane();
	JTabbedPane tabbed_listIn = new JTabbedPane();
	JTabbedPane tabbed_listSell = new JTabbedPane();
	JTabbedPane tabbed_operator = new JTabbedPane();
	JTabbedPane tabbed_newPsd = new JTabbedPane();
	JTabbedPane tabbed_searchWay = new JTabbedPane();
	JTabbedPane tabbed_searchGoods = new JTabbedPane();
	JTabbedPane tabbed_searchClient = new JTabbedPane();
	JTabbedPane tabbed_searchSupplier = new JTabbedPane();
	JTabbedPane tabbed_addGoods = new JTabbedPane();
	JTabbedPane tabbed_sortGoods = new JTabbedPane();
	JTabbedPane tabbed_sortSellGoods = new JTabbedPane();

	JScrollPane container = new JScrollPane();
	JPanel centerPanel = new AppBackPanel("center.png");

	JButton bt1, bt2, bt3, bt4, bt5, bt6;
	JLabel lp11, lp12, lp13, lp21, lp22, lp31, lp32, lp41, lp42, lp51, lp52,
			lp53, lp54, lp61, lp62, lp63;

	JToolBar jtb;

	public AdminWindow(String title) {
		super(title);
		init();
		setResizable(true);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    /**
     * 初始化界面
     */
	public void init() {

		setBounds(100, 100, 800, 600);
		centerPanel.setLayout(null);
		leftPanel.setLayout(new GridLayout(9, 1));
		leftPanel.setBorder(new TitledBorder(new EtchedBorder(), "子菜单"));
		jtb = new JToolBar(JToolBar.HORIZONTAL);
		add(jtb, BorderLayout.NORTH);

		/**
		 * 卡片布局
		 */
		cardPanel.setLayout(card);
		cardPanel.add("imag", centerPanel);

		tabbed_customer.addTab("添加客户", BaseInfoSubMenu.addClientPanel());
		tabbed_customer.addTab("删除客户", BaseInfoSubMenu.deleClientPanel());
		cardPanel.add("客户", tabbed_customer);

		tabbed_supplier.addTab("添加供应商", BaseInfoSubMenu.addSupplierPanel());
		tabbed_supplier.addTab("删除供应商", BaseInfoSubMenu.deleSupplierPanel());
		cardPanel.add("供应商", tabbed_supplier);

		tabbed_listIn.addTab("进货单", StockInSubMenu.listInPanel());
		cardPanel.add("进货单", tabbed_listIn);

		tabbed_listSell.addTab("销售单", SellSubMenu.sellListPanel());
		cardPanel.add("销售单", tabbed_listSell);

		tabbed_operator.addTab("添加操作员", SystemSubMenu.addOperaterPanel());
		tabbed_operator.addTab("删除操作员", SystemSubMenu.deleOperaterPanel());
		cardPanel.add("操作员", tabbed_operator);

		tabbed_newPsd.addTab("更改密码", SystemSubMenu.newPswPanel());
		cardPanel.add("密码", tabbed_newPsd);

		tabbed_searchGoods.addTab("商品查询", tablePanel);
		cardPanel.add("所有商品显示", tabbed_searchGoods);

		tabbed_searchClient.addTab("客户信息", tablePanel2);
		cardPanel.add("所有客户显示", tabbed_searchClient);

		tabbed_searchSupplier.addTab("供应商信息", tablePanel3);
		cardPanel.add("所有供应商显示", tabbed_searchSupplier);

		addGoodsPanel.add(tablePanel6, BorderLayout.CENTER);
		JButton Add = new JButton("添加商品");
		Add.setFont(new Font("黑体", Font.PLAIN, 15));
		Add.setBackground(Color.GREEN);
		addGoodsPanel.add(Add, BorderLayout.SOUTH);
		Add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				BaseInfoBiz bib = new BaseInfoBiz();
				GoodsOrderDao god = new GoodsOrderDao();
				JTable table = new Table().getAddGoodTable();
				boolean flag = bib.addProducts(Data.getTabelData(table));
				if (flag) {
					JOptionPane.showMessageDialog(null, "产品成功入库！");
					for (int i = 0;i < OrderInfo.list.size(); i++){
						int id = OrderInfo.list.get(i);
						god.changState(id);
					}
					tablePanel6.setViewportView(new JTable());
				} else {
					JOptionPane.showMessageDialog(null, "产品录入失败！");
				}
			}
		});

		tabbed_addGoods.addTab("添加商品", addGoodsPanel);
		tabbed_addGoods.addTab("修改/删除商品", BaseInfoSubMenu.deleGoodsPanel());
		cardPanel.add("商品信息", tabbed_addGoods);

		tabbed_sortGoods.addTab("按数量排序", tablePanel5);
		cardPanel.add("库存盘点", tabbed_sortGoods);

		tabbed_sortSellGoods.addTab("销售排行", tablePanel4);
		cardPanel.add("销售排行", tabbed_sortSellGoods);

		getContentPane().add(cardPanel, BorderLayout.CENTER);

		add(leftPanel, BorderLayout.WEST);

		bt1 = new JButton(new ImageIcon("src//images//信息管理.png"));
		bt2 = new JButton(new ImageIcon("src//images//入库管理.png"));
		bt3 = new JButton(new ImageIcon("src//images//出库管理.png"));
		bt4 = new JButton(new ImageIcon("src//images//库存管理.png"));
		bt5 = new JButton(new ImageIcon("src//images//查询统计.png"));
		bt6 = new JButton(new ImageIcon("src//images//系统管理.png"));

		bt1.setPressedIcon(new ImageIcon("src//images//信息管理1.png"));
		bt2.setPressedIcon(new ImageIcon("src//images//入库管理1.png"));
		bt3.setPressedIcon(new ImageIcon("src//images//出库管理1.png"));
		bt4.setPressedIcon(new ImageIcon("src//images//库存管理1.png"));
		bt5.setPressedIcon(new ImageIcon("src//images//查询统计1.png"));
		bt6.setPressedIcon(new ImageIcon("src//images//系统管理1.png"));
		jtb.add(bt1);
		jtb.addSeparator(new Dimension(10, 50));

		jtb.add(bt2);
		jtb.addSeparator(new Dimension(10, 50));

		jtb.add(bt3);
		jtb.addSeparator(new Dimension(10, 50));

		jtb.add(bt4);
		jtb.addSeparator(new Dimension(10, 50));

		jtb.add(bt5);
		jtb.addSeparator(new Dimension(10, 50));

		jtb.add(bt6);
		jtb.addSeparator(new Dimension(10, 50));

		baseInfoShow();
		centerPanel.add(new JLabel("中间"));

		/**
		 * 信息管理菜单监听
		 */
		bt1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("bt1");
				card.show(cardPanel, "imag");
				leftPanel.removeAll();
				leftPanel.validate();
				baseInfoShow();
				leftPanel.updateUI();

			}
		});

		/**
		 * 入库管理菜单监听
		 */

		bt2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				card.show(cardPanel, "imag");
				leftPanel.removeAll();
				leftPanel.validate();
				stockIn();
				leftPanel.updateUI();
			}
		});

		/**
		 * 出库管理菜单
		 */
		bt3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("bt3");
				card.show(cardPanel, "imag");
				leftPanel.removeAll();
				leftPanel.validate();
				stockOut();
				leftPanel.updateUI();
			}
		});

		/**
		 * 库存管理菜单
		 */
		bt4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("bt4");
				card.show(cardPanel, "imag");
				leftPanel.removeAll();
				leftPanel.validate();
				stockSave();
				leftPanel.updateUI();
			}
		});

		/**
		 * 查询统计菜单
		 */
		bt5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("bt5");
				card.show(cardPanel, "imag");
				leftPanel.removeAll();
				leftPanel.validate();
				query();
				leftPanel.updateUI();
			}
		});

		/**
		 * 系统管理菜单
		 */
		bt6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("bt5");
				card.show(cardPanel, "imag");
				leftPanel.removeAll();
				leftPanel.validate();
				systemManage();
				leftPanel.updateUI();
			}
		});

	}

	/**
	 * 基本信息子菜单
	 */
	public void baseInfoShow() {

		lp11 = new JLabel(new ImageIcon("src//images//客户信息管理.png"));
		leftPanel.add(lp11);

		lp11.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(cardPanel, "客户");

			}

		});
		lp12 = new JLabel(new ImageIcon("src//images//商品信息管理.png"));
		leftPanel.add(lp12);
		lp12.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel6.setViewportView(new Table().getAddGoodTable());
				card.show(cardPanel, "商品信息");
			}

		});
		lp13 = new JLabel(new ImageIcon("src//images//供应商信息管理.png"));
		leftPanel.add(lp13);

		lp13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "供应商");
			}

		});
	}

	/**
	 * 入库管理子菜单
	 */
	public void stockIn() {

		lp21 = new JLabel(new ImageIcon("src//images//进货单.png"));
		leftPanel.add(lp21);
		lp21.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "进货单");
			}

		});
		/*lp22 = new JLabel(new ImageIcon("src//images//进货退货.png"));
		leftPanel.add(lp22);*/

	}

	/**
	 * 出库管理子菜单
	 */
	public void stockOut() {
		lp31 = new JLabel(new ImageIcon("src//images//销售单.png"));
		leftPanel.add(lp31);
		lp31.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "销售单");
			}

		});
		/*lp32 = new JLabel(new ImageIcon("src//images//销售退货.png"));
		leftPanel.add(lp32);*/
	}

	/**
	 * 库存管理子菜单
	 */
	public void stockSave() {
		lp41 = new JLabel(new ImageIcon("src//images//库存盘点.png"));
		leftPanel.add(lp41);
		lp41.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel5.setViewportView(new Table().getStockSaveTable());
				card.show(cardPanel, "库存盘点");
			}

		});
		lp42 = new JLabel(new ImageIcon("src//images//价格调整.png"));
		leftPanel.add(lp42);
		lp42.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				double price = 0;
				String textPrice = JOptionPane
						.showInputDialog("请输入要调整的价格增(减)量");

				if (textPrice == null) {
					return;
				}
				else if(textPrice.equals("")){
					JOptionPane.showMessageDialog(null, "请输入要调整的价格增(减)量","提示信息",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				else{
					try {
						price = Double.parseDouble(textPrice);
						if (pid.changePrice(price)) {
							JOptionPane.showMessageDialog(null, "价格调整完成！");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "输入有误！");
					}

				}
			}

		});
	}

	/**
	 * 查询统计子菜单
	 */
	public void query() {

		lp51 = new JLabel(new ImageIcon("src//images//商品信息查询.png"));
		leftPanel.add(lp51);
		lp51.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel.setViewportView(new Table().getGoodsTable());
				System.out.println("商品查询");
				card.show(cardPanel, "所有商品显示");
			}

		});
		lp52 = new JLabel(new ImageIcon("src//images//客户信息查询.png"));
		leftPanel.add(lp52);
		lp52.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel2.setViewportView(new Table().getClientTable());
				card.show(cardPanel, "所有客户显示");
			}

		});
		lp53 = new JLabel(new ImageIcon("src//images//供应商信息查询.png"));
		leftPanel.add(lp53);
		lp53.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel3.setViewportView(new Table().getSupplierTable());
				card.show(cardPanel, "所有供应商显示");
			}

		});
		lp54 = new JLabel(new ImageIcon("src//images//销售排行.png"));
		leftPanel.add(lp54);
		lp54.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel4.setViewportView(new Table().getSellGoodsTable());
				card.show(cardPanel, "销售排行");
			}

		});
	}

	/**
	 * 系统管理子菜单
	 */
	public void systemManage() {

		lp61 = new JLabel(new ImageIcon("src//images//操作员管理.png"));
		leftPanel.add(lp61);
		lp61.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "操作员");
			}

		});
		lp62 = new JLabel(new ImageIcon("src//images//更改密码.png"));
		leftPanel.add(lp62);
		lp62.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "密码");
			}

		});
		lp63 = new JLabel(new ImageIcon("src//images//退出系统.png"));
		leftPanel.add(lp63);
		lp63.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int option = JOptionPane.showConfirmDialog(null, "确定退出本系统？", "提示",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
		});

	}

}
