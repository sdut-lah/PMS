package cn.sdut.biz;

import java.util.ArrayList;
import java.util.List;
import cn.sdut.dao.ProductInfoDao;
import cn.sdut.entity.OrderBean;
import cn.sdut.entity.ProductBean;

public class OrderInfo {

	/**
	 * 判定订单是否有效
	 * 
	 * @param order
	 * @return
	 */

	public static List<Integer> list = new ArrayList<Integer>();

	/**
	 * 确认订单
	 * 
	 * @param order
	 * @return
	 */
	public static int confirmOrder(OrderBean order) {

		ProductInfoDao pi = new ProductInfoDao();
		ProductBean pb = pi.searchByName(order.getName());
		if (pb == null) {
			return 0;// 数据库中无该商品
		} else {
			if (pb.getPnumber() < order.getNumber()) {
				return -1;// 数据库中商品数量不足
			} else {
				pb.setPnumber(pb.getPnumber() - order.getNumber());
				pi.updateProduct(pb);
				return 1;// 订单有效
			}
		}
	}

}
