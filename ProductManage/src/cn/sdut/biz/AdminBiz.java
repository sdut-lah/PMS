package cn.sdut.biz;

import cn.sdut.dao.LoginDao;
import cn.sdut.entity.AdminBean;

public class AdminBiz {

	private static AdminBiz instance = null;

	private AdminBiz() {

	}
    /**
     * 得到管理员单例
     * @return
     */
	public static AdminBiz getInstance() {
		if (instance == null) {
			instance = new AdminBiz();
		}
		return instance;
	}

	/**
	 * 管理员登录
	 * 
	 * @param id
	 * @param psw
	 * @return
	 */
	public int adminLogin(String id, String psw) {

		LoginDao ld = new LoginDao();
		AdminBean ab = ld.getAdminByID(Integer.valueOf(id));
		if (ab == null) {
			return -1; //账号不存在
		}
		if (!ab.getPsw().equals(psw)) {
			return 0; //密码有误
		}
		return 1; //登录成功
	}
      

}
