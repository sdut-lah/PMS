package cn.sdut.biz;

import cn.sdut.dao.LoginDao;
import cn.sdut.entity.AdminBean;

public class AdminBiz {

	private static AdminBiz instance = null;

	private AdminBiz() {

	}
    /**
     * �õ�����Ա����
     * @return
     */
	public static AdminBiz getInstance() {
		if (instance == null) {
			instance = new AdminBiz();
		}
		return instance;
	}

	/**
	 * ����Ա��¼
	 * 
	 * @param id
	 * @param psw
	 * @return
	 */
	public int adminLogin(String id, String psw) {

		LoginDao ld = new LoginDao();
		AdminBean ab = ld.getAdminByID(Integer.valueOf(id));
		if (ab == null) {
			return -1; //�˺Ų�����
		}
		if (!ab.getPsw().equals(psw)) {
			return 0; //��������
		}
		return 1; //��¼�ɹ�
	}
      

}
