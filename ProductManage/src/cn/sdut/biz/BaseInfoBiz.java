package cn.sdut.biz;

import java.util.List;

import cn.sdut.dao.BaseInfoDao;
import cn.sdut.dao.ProductInfoDao;
import cn.sdut.entity.ProductBean;
import cn.sdut.entity.SupplierBean;

public class BaseInfoBiz {

	/**
	 * 添加产品信息
	 * @param list
	 * @return
	 */
	public boolean addProducts(List<ProductBean> list){
		
		ProductInfoDao pid = new ProductInfoDao();
		ProductBean product = null;
		for (int i = 0;i < list.size(); i++){
			product = list.get(i);
			pid.addProduct(product);
		}
		return true;
	}
	/**
	 * 添加供应商
	 * @param supplier
	 * @return
	 */
	public boolean addSupplier(SupplierBean supplier){
		BaseInfoDao bid = new BaseInfoDao();
		if(bid.addSupplier(supplier)){
			return true;
		}
		return false;
	}
}
