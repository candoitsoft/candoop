package cdp.dao;

import cdp.comm.CdpParam;
import jm.com.JmProperties;
import jm.net.Dao;

public class PjDao {

	private static PjDao instance = null;
	private JmProperties property = null;
	Dao dao = null;
	
	private PjDao(){
		property = new JmProperties(CdpParam.property);
		dao = Dao.getInstance("maria");
	}
	
	public static PjDao getInstance() {
		if(instance == null){
			instance = new PjDao();
		}
		return instance;
	}
	

}
