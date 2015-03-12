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
     * ��ʼ������
     */
	public void init() {

		setBounds(100, 100, 800, 600);
		centerPanel.setLayout(null);
		leftPanel.setLayout(new GridLayout(9, 1));
		leftPanel.setBorder(new TitledBorder(new EtchedBorder(), "�Ӳ˵�"));
		jtb = new JToolBar(JToolBar.HORIZONTAL);
		add(jtb, BorderLayout.NORTH);

		/**
		 * ��Ƭ����
		 */
		cardPanel.setLayout(card);
		cardPanel.add("imag", centerPanel);

		tabbed_customer.addTab("��ӿͻ�", BaseInfoSubMenu.addClientPanel());
		tabbed_customer.addTab("ɾ���ͻ�", BaseInfoSubMenu.deleClientPanel());
		cardPanel.add("�ͻ�", tabbed_customer);

		tabbed_supplier.addTab("��ӹ�Ӧ��", BaseInfoSubMenu.addSupplierPanel());
		tabbed_supplier.addTab("ɾ����Ӧ��", BaseInfoSubMenu.deleSupplierPanel());
		cardPanel.add("��Ӧ��", tabbed_supplier);

		tabbed_listIn.addTab("������", StockInSubMenu.listInPanel());
		cardPanel.add("������", tabbed_listIn);

		tabbed_listSell.addTab("���۵�", SellSubMenu.sellListPanel());
		cardPanel.add("���۵�", tabbed_listSell);

		tabbed_operator.addTab("��Ӳ���Ա", SystemSubMenu.addOperaterPanel());
		tabbed_operator.addTab("ɾ������Ա", SystemSubMenu.deleOperaterPanel());
		cardPanel.add("����Ա", tabbed_operator);

		tabbed_newPsd.addTab("��������", SystemSubMenu.newPswPanel());
		cardPanel.add("����", tabbed_newPsd);

		tabbed_searchGoods.addTab("��Ʒ��ѯ", tablePanel);
		cardPanel.add("������Ʒ��ʾ", tabbed_searchGoods);

		tabbed_searchClient.addTab("�ͻ���Ϣ", tablePanel2);
		cardPanel.add("���пͻ���ʾ", tabbed_searchClient);

		tabbed_searchSupplier.addTab("��Ӧ����Ϣ", tablePanel3);
		cardPanel.add("���й�Ӧ����ʾ", tabbed_searchSupplier);

		addGoodsPanel.add(tablePanel6, BorderLayout.CENTER);
		JButton Add = new JButton("�����Ʒ");
		Add.setFont(new Font("����", Font.PLAIN, 15));
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
					JOptionPane.showMessageDialog(null, "��Ʒ�ɹ���⣡");
					for (int i = 0;i < OrderInfo.list.size(); i++){
						int id = OrderInfo.list.get(i);
						god.changState(id);
					}
					tablePanel6.setViewportView(new JTable());
				} else {
					JOptionPane.showMessageDialog(null, "��Ʒ¼��ʧ�ܣ�");
				}
			}
		});

		tabbed_addGoods.addTab("�����Ʒ", addGoodsPanel);
		tabbed_addGoods.addTab("�޸�/ɾ����Ʒ", BaseInfoSubMenu.deleGoodsPanel());
		cardPanel.add("��Ʒ��Ϣ", tabbed_addGoods);

		tabbed_sortGoods.addTab("����������", tablePanel5);
		cardPanel.add("����̵�", tabbed_sortGoods);

		tabbed_sortSellGoods.addTab("��������", tablePanel4);
		cardPanel.add("��������", tabbed_sortSellGoods);

		getContentPane().add(cardPanel, BorderLayout.CENTER);

		add(leftPanel, BorderLayout.WEST);

		bt1 = new JButton(new ImageIcon("src//images//��Ϣ����.png"));
		bt2 = new JButton(new ImageIcon("src//images//������.png"));
		bt3 = new JButton(new ImageIcon("src//images//�������.png"));
		bt4 = new JButton(new ImageIcon("src//images//������.png"));
		bt5 = new JButton(new ImageIcon("src//images//��ѯͳ��.png"));
		bt6 = new JButton(new ImageIcon("src//images//ϵͳ����.png"));

		bt1.setPressedIcon(new ImageIcon("src//images//��Ϣ����1.png"));
		bt2.setPressedIcon(new ImageIcon("src//images//������1.png"));
		bt3.setPressedIcon(new ImageIcon("src//images//�������1.png"));
		bt4.setPressedIcon(new ImageIcon("src//images//������1.png"));
		bt5.setPressedIcon(new ImageIcon("src//images//��ѯͳ��1.png"));
		bt6.setPressedIcon(new ImageIcon("src//images//ϵͳ����1.png"));
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
		centerPanel.add(new JLabel("�м�"));

		/**
		 * ��Ϣ����˵�����
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
		 * ������˵�����
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
		 * �������˵�
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
		 * ������˵�
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
		 * ��ѯͳ�Ʋ˵�
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
		 * ϵͳ����˵�
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
	 * ������Ϣ�Ӳ˵�
	 */
	public void baseInfoShow() {

		lp11 = new JLabel(new ImageIcon("src//images//�ͻ���Ϣ����.png"));
		leftPanel.add(lp11);

		lp11.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				card.show(cardPanel, "�ͻ�");

			}

		});
		lp12 = new JLabel(new ImageIcon("src//images//��Ʒ��Ϣ����.png"));
		leftPanel.add(lp12);
		lp12.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel6.setViewportView(new Table().getAddGoodTable());
				card.show(cardPanel, "��Ʒ��Ϣ");
			}

		});
		lp13 = new JLabel(new ImageIcon("src//images//��Ӧ����Ϣ����.png"));
		leftPanel.add(lp13);

		lp13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "��Ӧ��");
			}

		});
	}

	/**
	 * �������Ӳ˵�
	 */
	public void stockIn() {

		lp21 = new JLabel(new ImageIcon("src//images//������.png"));
		leftPanel.add(lp21);
		lp21.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "������");
			}

		});
		/*lp22 = new JLabel(new ImageIcon("src//images//�����˻�.png"));
		leftPanel.add(lp22);*/

	}

	/**
	 * ��������Ӳ˵�
	 */
	public void stockOut() {
		lp31 = new JLabel(new ImageIcon("src//images//���۵�.png"));
		leftPanel.add(lp31);
		lp31.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "���۵�");
			}

		});
		/*lp32 = new JLabel(new ImageIcon("src//images//�����˻�.png"));
		leftPanel.add(lp32);*/
	}

	/**
	 * �������Ӳ˵�
	 */
	public void stockSave() {
		lp41 = new JLabel(new ImageIcon("src//images//����̵�.png"));
		leftPanel.add(lp41);
		lp41.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel5.setViewportView(new Table().getStockSaveTable());
				card.show(cardPanel, "����̵�");
			}

		});
		lp42 = new JLabel(new ImageIcon("src//images//�۸����.png"));
		leftPanel.add(lp42);
		lp42.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				double price = 0;
				String textPrice = JOptionPane
						.showInputDialog("������Ҫ�����ļ۸���(��)��");

				if (textPrice == null) {
					return;
				}
				else if(textPrice.equals("")){
					JOptionPane.showMessageDialog(null, "������Ҫ�����ļ۸���(��)��","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
					return ;
				}
				else{
					try {
						price = Double.parseDouble(textPrice);
						if (pid.changePrice(price)) {
							JOptionPane.showMessageDialog(null, "�۸������ɣ�");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "��������");
					}

				}
			}

		});
	}

	/**
	 * ��ѯͳ���Ӳ˵�
	 */
	public void query() {

		lp51 = new JLabel(new ImageIcon("src//images//��Ʒ��Ϣ��ѯ.png"));
		leftPanel.add(lp51);
		lp51.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel.setViewportView(new Table().getGoodsTable());
				System.out.println("��Ʒ��ѯ");
				card.show(cardPanel, "������Ʒ��ʾ");
			}

		});
		lp52 = new JLabel(new ImageIcon("src//images//�ͻ���Ϣ��ѯ.png"));
		leftPanel.add(lp52);
		lp52.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel2.setViewportView(new Table().getClientTable());
				card.show(cardPanel, "���пͻ���ʾ");
			}

		});
		lp53 = new JLabel(new ImageIcon("src//images//��Ӧ����Ϣ��ѯ.png"));
		leftPanel.add(lp53);
		lp53.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel3.setViewportView(new Table().getSupplierTable());
				card.show(cardPanel, "���й�Ӧ����ʾ");
			}

		});
		lp54 = new JLabel(new ImageIcon("src//images//��������.png"));
		leftPanel.add(lp54);
		lp54.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				tablePanel4.setViewportView(new Table().getSellGoodsTable());
				card.show(cardPanel, "��������");
			}

		});
	}

	/**
	 * ϵͳ�����Ӳ˵�
	 */
	public void systemManage() {

		lp61 = new JLabel(new ImageIcon("src//images//����Ա����.png"));
		leftPanel.add(lp61);
		lp61.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "����Ա");
			}

		});
		lp62 = new JLabel(new ImageIcon("src//images//��������.png"));
		leftPanel.add(lp62);
		lp62.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				card.show(cardPanel, "����");
			}

		});
		lp63 = new JLabel(new ImageIcon("src//images//�˳�ϵͳ.png"));
		leftPanel.add(lp63);
		lp63.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				int option = JOptionPane.showConfirmDialog(null, "ȷ���˳���ϵͳ��", "��ʾ",
						JOptionPane.YES_NO_OPTION);
				if (option == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
			
		});

	}

}
