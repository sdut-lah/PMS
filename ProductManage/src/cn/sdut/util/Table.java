package cn.sdut.util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import cn.sdut.dao.BaseInfoDao;
import cn.sdut.dao.GoodsOrderDao;
import cn.sdut.dao.OrderDao;
import cn.sdut.dao.ProductInfoDao;
import cn.sdut.entity.ClientBean;
import cn.sdut.entity.GoodsOrderBean;
import cn.sdut.entity.OrderBean;
import cn.sdut.entity.ProductBean;
import cn.sdut.entity.SupplierBean;

public class Table {
	
	/**
	 * �õ��ͻ���Ϣ���
	 * @return
	 */
	public JTable getClientTable(){
		
		BaseInfoDao bid = new BaseInfoDao();
		List<ClientBean> list = bid.searchClient();
		ClientBean cb = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		Object[][] data = new Object[list.size()][5];
		String[] colums = { "�ͻ����", "�ͻ�����", "��ϵ��ʽ", "��ַ", "�����˺�" };
		for (int i = 0; i < list.size(); i++) {

			cb = list.get(i);
			data[i][0] = cb.getClientId();
			data[i][1] = cb.getClientName();
			data[i][2] = cb.getPhoneNumber();
			data[i][3] = cb.getAddress();
			data[i][4] = cb.getBankAccount();

		}
		JTable table = new JTable(data, colums) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("�ͻ����").setCellRenderer(render);
		table.getColumn("�ͻ�����").setCellRenderer(render);
		table.getColumn("��ϵ��ʽ").setCellRenderer(render);
		table.getColumn("��ַ").setCellRenderer(render);
		table.getColumn("�����˺�").setCellRenderer(render);
		
		return table;
	}
	
	/**
	 * �õ���Ʒ��Ϣ�ı��
	 * @return
	 */
	public JTable getGoodsTable(){
		
		ProductInfoDao pid = new ProductInfoDao();
		List<ProductBean> list = pid.searchAll();
		System.out.println("hahahahhahahah"+list);
		ProductBean pb = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		Object[][] data = new Object[list.size()][6];
		String[] colums = { "��Ʒ��", "��Ʒ��", "����", "����", "��Ӧ��", "�������" };
		for (int i = 0; i < list.size(); i++) {

			pb = list.get(i);
			data[i][0] = pb.getP_id();
			data[i][1] = pb.getPname();
			data[i][2] = pb.getPnumber();
			data[i][3] = pb.getPrice();
			data[i][4] = pb.getSupplier();
			data[i][5] = pb.getStockInTime();

		}
		JTable table = new JTable(data, colums) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("��Ʒ��").setCellRenderer(render);
		table.getColumn("��Ʒ��").setCellRenderer(render);
		table.getColumn("����").setCellRenderer(render);
		table.getColumn("����").setCellRenderer(render);
		table.getColumn("��Ӧ��").setCellRenderer(render);
		table.getColumn("�������").setCellRenderer(render);
		
		return table;
	}
	/**
	 * �õ���Ӧ�����ݱ��
	 * @return
	 */
	public JTable getSupplierTable(){
		
		BaseInfoDao bid = new BaseInfoDao();
		List<SupplierBean> list = bid.searchSupplier();
		SupplierBean sb = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		Object[][] data = new Object[list.size()][5];
		String[] colums = { "��Ӧ�̱��", "��Ӧ������", "��ϵ��ʽ", "��ַ", "�����˺�" };
		for (int i = 0; i < list.size(); i++) {

			sb = list.get(i);
			data[i][0] = sb.getS_id();
			data[i][1] = sb.getSname();
			data[i][2] = sb.getSphone();
			data[i][3] = sb.getSaddress();
			data[i][4] = sb.getSbankAccount();

		}
		JTable table = new JTable(data, colums) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("��Ӧ�̱��").setCellRenderer(render);
		table.getColumn("��Ӧ������").setCellRenderer(render);
		table.getColumn("��ϵ��ʽ").setCellRenderer(render);
		table.getColumn("��ַ").setCellRenderer(render);
		table.getColumn("�����˺�").setCellRenderer(render);
		
		return table;
	}
	/**
	 * �õ�������Ϣ��
	 * @return
	 */
	public JTable getSellGoodsTable(){
		
		OrderDao od = new OrderDao();
		List<OrderBean> list = od.sortSellProduct();
		OrderBean ob = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		Object[][] data = new Object[list.size()][6];
		String[] colums = {  "��Ʒ��", "����", "����", "�ܼ�","�ͻ�", "�۳�����" };
		for (int i = 0; i < list.size(); i++) {

			ob = list.get(i);
			data[i][0] = ob.getName();
			data[i][1] = ob.getNumber();
			data[i][2] = ob.getPrice();
			data[i][3] = ob.getNumber()*ob.getPrice();
			data[i][4] = ob.getClient();
			data[i][5] = ob.getOrderTime();		

		}
		JTable table = new JTable(data, colums) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("��Ʒ��").setCellRenderer(render);
		table.getColumn("����").setCellRenderer(render);
		table.getColumn("����").setCellRenderer(render);
		table.getColumn("�ܼ�").setCellRenderer(render);
		table.getColumn("�ͻ�").setCellRenderer(render);
		table.getColumn("�۳�����").setCellRenderer(render);
		
		return table;
	}
	/**
	 * �õ������Ϣ���
	 * @return
	 */
	public JTable getStockSaveTable(){
		
		ProductInfoDao pid = new ProductInfoDao();
		List<ProductBean> list = pid.sortProductByNum();
		ProductBean pb = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		
		Object[][] data = new Object[list.size()][6];
		String[] colums = { "��Ʒ��", "��Ʒ��", "����", "����", "��Ӧ��", "�������" };
		for (int i = 0; i < list.size(); i++) {

			pb = list.get(i);
			data[i][0] = pb.getP_id();
			data[i][1] = pb.getPname();
			data[i][2] = pb.getPnumber();
			data[i][3] = pb.getPrice();
			data[i][4] = pb.getSupplier();
			data[i][5] = pb.getStockInTime();

		}
		JTable table = new JTable(data, colums) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("��Ʒ��").setCellRenderer(render);
		table.getColumn("��Ʒ��").setCellRenderer(render);
		table.getColumn("����").setCellRenderer(render);
		table.getColumn("����").setCellRenderer(render);
		table.getColumn("��Ӧ��").setCellRenderer(render);
		table.getColumn("�������").setCellRenderer(render);
		
		return table;
	}
	
	/**
	 * �õ������Ʒ�ı��
	 * @return
	 */
	public JTable getAddGoodTable(){
		
		GoodsOrderDao god = new GoodsOrderDao();
		ProductBean pb = null;
		final int [] orderId = new int[1000];
		List<GoodsOrderBean> list = god.searchAllOrder();
		 List<ProductBean> plist = new ArrayList<ProductBean>();
		GoodsOrderBean gob = new GoodsOrderBean();
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		 Object[][] data = new Object[list.size()][5];
		String[] colums = { "��Ʒ��", "����", "����", "��Ӧ��", "�������" };
		for (int i = 0; i < list.size(); i++) {
            
			pb = new ProductBean();
			gob = list.get(i);
			data[i][0] = gob.getGname();
			pb.setPname(gob.getGname());
			data[i][1] = gob.getGnumber();
			pb.setPnumber( gob.getGnumber());
			data[i][2] = gob.getGprice();
			pb.setPrice(gob.getGprice());
			data[i][3] = gob.getGsupplier();
			pb.setSupplier( gob.getGsupplier());
			data[i][4] = GetTime.getCurrenttime();
			pb.setStockInTime(GetTime.getCurrenttime());
            plist.add(pb);
            orderId[i] = gob.getG_id();
		}
		DefaultTableModel model = new DefaultTableModel(data,colums);
		JTable table = new JTable(model) {
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumn("��Ʒ��").setCellRenderer(render);
		table.getColumn("����").setCellRenderer(render);
		table.getColumn("����").setCellRenderer(render);
		table.getColumn("��Ӧ��").setCellRenderer(render);
		table.getColumn("�������").setCellRenderer(render);
		
		return table;
		
	}

}
