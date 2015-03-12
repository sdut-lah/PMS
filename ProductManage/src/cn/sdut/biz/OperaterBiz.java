package cn.sdut.biz;

import cn.sdut.dao.LoginDao;
import cn.sdut.dao.OrderDao;
import cn.sdut.entity.OperaterBean;
import cn.sdut.entity.OrderBean;

public class OperaterBiz {

	private static OperaterBiz instance = null;

	private OperaterBiz() {

	}
    /**
     * 操作员单例
     * @return
     */
	public static  OperaterBiz getInstance() {
		if (instance == null) {
			instance = new OperaterBiz();
		}
		return instance;
	}
	
	/**
	 * 判断用户登录
	 * @param id
	 * @param psw
	 * @return
	 */

	public int OperaterLogin(String id, String psw) {

		LoginDao ld = new LoginDao();
		OperaterBean ob = ld.getOperaterByID(Integer.valueOf(id));
		if (ob == null) {
			return -1;// 账号不存在
		}
		if (!ob.getPsw().equals(psw)) {
			return 0;//密码错误
		}
		return 1;//登录成功
	}
	
	/**
	 * 客户下订单,得到订单号
	 * @param order
	 * @return
	 */
	public int makeOrder(OrderBean order){
		
		OrderDao od = new OrderDao();
		int id = od.getOderId(order);
		return id;
	}

}
