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
	 * 得到客户信息表格
	 * @return
	 */
	public JTable getClientTable(){
		
		BaseInfoDao bid = new BaseInfoDao();
		List<ClientBean> list = bid.searchClient();
		ClientBean cb = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		Object[][] data = new Object[list.size()][5];
		String[] colums = { "客户编号", "客户名称", "联系方式", "地址", "银行账号" };
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
		table.getColumn("客户编号").setCellRenderer(render);
		table.getColumn("客户名称").setCellRenderer(render);
		table.getColumn("联系方式").setCellRenderer(render);
		table.getColumn("地址").setCellRenderer(render);
		table.getColumn("银行账号").setCellRenderer(render);
		
		return table;
	}
	
	/**
	 * 得到商品信息的表格
	 * @return
	 */
	public JTable getGoodsTable(){
		
		ProductInfoDao pid = new ProductInfoDao();
		List<ProductBean> list = pid.searchAll();
		System.out.println("hahahahhahahah"+list);
		ProductBean pb = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		Object[][] data = new Object[list.size()][6];
		String[] colums = { "商品号", "商品名", "数量", "单价", "供应商", "入库日期" };
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
		table.getColumn("商品号").setCellRenderer(render);
		table.getColumn("商品名").setCellRenderer(render);
		table.getColumn("数量").setCellRenderer(render);
		table.getColumn("单价").setCellRenderer(render);
		table.getColumn("供应商").setCellRenderer(render);
		table.getColumn("入库日期").setCellRenderer(render);
		
		return table;
	}
	/**
	 * 得到供应商数据表格
	 * @return
	 */
	public JTable getSupplierTable(){
		
		BaseInfoDao bid = new BaseInfoDao();
		List<SupplierBean> list = bid.searchSupplier();
		SupplierBean sb = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		Object[][] data = new Object[list.size()][5];
		String[] colums = { "供应商编号", "供应商名称", "联系方式", "地址", "银行账号" };
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
		table.getColumn("供应商编号").setCellRenderer(render);
		table.getColumn("供应商名称").setCellRenderer(render);
		table.getColumn("联系方式").setCellRenderer(render);
		table.getColumn("地址").setCellRenderer(render);
		table.getColumn("银行账号").setCellRenderer(render);
		
		return table;
	}
	/**
	 * 得到销售信息表
	 * @return
	 */
	public JTable getSellGoodsTable(){
		
		OrderDao od = new OrderDao();
		List<OrderBean> list = od.sortSellProduct();
		OrderBean ob = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();

		Object[][] data = new Object[list.size()][6];
		String[] colums = {  "商品名", "数量", "单价", "总价","客户", "售出日期" };
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
		table.getColumn("商品名").setCellRenderer(render);
		table.getColumn("数量").setCellRenderer(render);
		table.getColumn("单价").setCellRenderer(render);
		table.getColumn("总价").setCellRenderer(render);
		table.getColumn("客户").setCellRenderer(render);
		table.getColumn("售出日期").setCellRenderer(render);
		
		return table;
	}
	/**
	 * 得到库存信息表格
	 * @return
	 */
	public JTable getStockSaveTable(){
		
		ProductInfoDao pid = new ProductInfoDao();
		List<ProductBean> list = pid.sortProductByNum();
		ProductBean pb = null;
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		
		Object[][] data = new Object[list.size()][6];
		String[] colums = { "商品号", "商品名", "数量", "单价", "供应商", "入库日期" };
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
		table.getColumn("商品号").setCellRenderer(render);
		table.getColumn("商品名").setCellRenderer(render);
		table.getColumn("数量").setCellRenderer(render);
		table.getColumn("单价").setCellRenderer(render);
		table.getColumn("供应商").setCellRenderer(render);
		table.getColumn("入库日期").setCellRenderer(render);
		
		return table;
	}
	
	/**
	 * 得到添加商品的表格
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
		String[] colums = { "商品名", "数量", "单价", "供应商", "入库日期" };
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
		table.getColumn("商品名").setCellRenderer(render);
		table.getColumn("数量").setCellRenderer(render);
		table.getColumn("单价").setCellRenderer(render);
		table.getColumn("供应商").setCellRenderer(render);
		table.getColumn("入库日期").setCellRenderer(render);
		
		return table;
		
	}

}
