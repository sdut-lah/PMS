package cn.sdut.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import cn.sdut.entity.ProductBean;


public class Data {
	
	static List<ProductBean> productList = null;
	
	/**
	 * 得到产品信息表中的数据
	 * @author 
	 *
	 */
	public static List<ProductBean> getTabelData(JTable table){
		 
		productList = new ArrayList<ProductBean>();
		TableModel model = table.getModel();
		int rowCount = model.getRowCount();
		System.out.println("lieshu"+rowCount);
		for (int i = 0;i < rowCount; i++){
			ProductBean product = new ProductBean();
			product.setPname((String) model.getValueAt(i, 0));
			product.setPnumber((int) model.getValueAt(i, 1));
			product.setPrice((double) model.getValueAt(i, 2));
			product.setSupplier((String) model.getValueAt(i, 3));
			product.setStockInTime(GetTime.getCurrenttime());

			productList.add(product);
		}
       System.out.println(productList);
		return productList;
		
	}
}
