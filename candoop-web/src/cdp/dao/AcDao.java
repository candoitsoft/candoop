package cdp.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import cdp.comm.CdpParam;
import cdp.comm.CdpUserObj;
import jm.com.Encrypt;
import jm.com.JmProperties;
import jm.net.Dao;
import jm.net.DataEntity;

public class AcDao {

	private static AcDao instance = null;
	private JmProperties property = null;
	Dao dao = null;
	
	private AcDao(){
		property = new JmProperties(CdpParam.property);
		dao = Dao.getInstance("maria");
	}
	
	public static AcDao getInstance() {
		if(instance == null){
			instance = new AcDao();
		}
		return instance;
	}
	

	/**
	 *  request 로 부터 CdpUserObj 객체를 생성하는 메서드.
	 * @param req
	 * @return
	 */
	public CdpUserObj getUserObj(HttpServletRequest req){
		return getUserObj(req, new CdpUserObj());
	}
	
	/**
	 * request 로 부터 CdpUserObj 객체를 생성하는 메서드.
	 * @param req
	 * @param obj
	 * @return
	 */
	public CdpUserObj getUserObj(HttpServletRequest req, CdpUserObj obj){
		try {
			if(req.getParameter("grpId") != null)
				obj.setGrpId(req.getParameter("grpId"));
			if(req.getParameter("usrType") != null)
				obj.setUsrType(req.getParameter("usrType"));
			if(req.getParameter("email") != null)
				obj.setEmail(req.getParameter("email"));
			if(req.getParameter("passwd") != null)
				obj.setPasswd(req.getParameter("passwd"));
			if(req.getParameter("name") != null)
				obj.setName(req.getParameter("name"));
			if(req.getParameter("nicname") != null)
				obj.setNicname(req.getParameter("nicname"));
			if(req.getParameter("birthY") != null && req.getParameter("birthM") != null && req.getParameter("birthD") != null){
				Date fullDate = new Date((new SimpleDateFormat("yyyyMMdd").parse(req.getParameter("birthY")+req.getParameter("birthM")+req.getParameter("birthD"))).getTime());
				obj.setBirthday(fullDate);
			}
			if(req.getParameter("phone") != null)
				obj.setPhone(req.getParameter("phone"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public CdpUserObj getUserObj(String email){
		CdpUserObj userObj  = null;
		DataEntity[] datas = null;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT \n");
		sql.append("A.* \n");
		sql.append(",B.grp_name \n");
		sql.append(",B.grp_text \n");
		sql.append(",B.grp_dept_id \n");
		sql.append("FROM \n");
		sql.append("ac_user A, ac_grp B \n");
		sql.append("WHERE A.grp_id = B.grp_id \n");
		sql.append("AND A.email = ? \n");
		
		String[] params = { email };
		datas = dao.getResult(property, sql.toString(), params);
		if(datas.length == 1){
			userObj = new CdpUserObj(datas[0]);
		}
		return userObj;
	}
	
	/**
	 * CdpUserObj 객체를 DB에 저장.
	 * @param userObj
	 * @return
	 */
	public int insertUserObj(CdpUserObj userObj){
		int result = 0;
		DataEntity data = new DataEntity();
		
		String passwd = Encrypt.getSha256(userObj.getPasswd());
		if(passwd != null && !"".equals(passwd)){
			data.put("grp_id", userObj.getGrpId());
			data.put("user_type", userObj.getUsrType());
			data.put("email", userObj.getEmail());
			data.put("passwd", passwd);
			data.put("name", userObj.getName());
			data.put("nicname", userObj.getNicname());
			data.put("birthday", userObj.getBirthday());
			if(userObj.getPhone() != null && !"".equals(userObj.getPhone())){
				data.put("phone", userObj.getPhone());
			}
			data.put("reg_time", new Date());
			result = dao.inertData(property, "ac_user", data);
		}
		return result;
	}
	
	/**
	 * CdpUserObj 객체를 DB에 업데이트.
	 * @param userObj
	 * @return
	 */
	public int updateUserObj(CdpUserObj userObj){
		int result = 0;
		
		String passwd = Encrypt.getSha256(userObj.getPasswd());
		DataEntity setData = new DataEntity();
		setData.put("email", userObj.getEmail());
		setData.put("passwd", passwd);
		setData.put("name", userObj.getName());
		setData.put("nicname", userObj.getNicname());
		setData.put("birthday", userObj.getBirthday());
		if(userObj.getPhone() != null && !"".equals(userObj.getPhone())){
			setData.put("phone", userObj.getPhone());
		}
		DataEntity whereData = new DataEntity();
		whereData.put("email", userObj.getEmail());
		
		result = dao.updateData(property, "mp_user", setData, whereData);
		
		return result;
	}
	
	/**
	 * 로그인 확인 메서드.
	 * 0:ID 없음, 1:PW오류, 2:로그인, 9:오류
	 * @param id
	 * @param passwd
	 * @return
	 */
	public int login(String email, String rawPasswd) {
		String passwd = Encrypt.getSha256(rawPasswd);
		
		StringBuffer sql = new StringBuffer();
		String tempPw = "";
		String[] param = {email};
		int result = 9;
		
		sql.append("SELECT passwd FROM ac_user WHERE email = ?");
		
		DataEntity[] entity = dao.getResult(property, sql.toString(), param);
		
		if(entity != null && entity.length == 1){
			tempPw = (String)entity[0].get("passwd");
			if (tempPw.equals(passwd)) {
				result = 2;
			} else {
				result = 1;
			}
		} else { 
			result = 0;
		}
		return result;
	}
	
	/**
	 * 단체 정보 입력
	 * @param grpId
	 * @param grpName
	 * @param grpText
	 * @param grpDeptId
	 * @return
	 */
	public int insertGrp(String grpId, String grpName, String grpText, String grpDeptId){
		int result = 0;
		DataEntity data = new DataEntity();
		data.put("grp_id", grpId);
		data.put("grp_name", grpName);
		data.put("grp_text", grpText);
		data.put("grp_dept_id", grpDeptId);
		result = dao.inertData(property, "ac_grp", data);
		return result;
	}
	
	/**
	 * 단체 정보 불러오기
	 * @return
	 */
	public DataEntity[] getGrp(){
		String sql = "SELECT * FROM ac_grp WHERE status <> 'DELETE'";
		return dao.getResult(property, sql, null);
		
	}
	
	/**
	 * 단체 소속 정보 불러오기
	 * @return
	 */
	public DataEntity[] getGrpDept(){
		String sql = "SELECT * FROM ac_grp_dept WHERE status <> 'DELETE'";
		return dao.getResult(property, sql, null);
	}
	
	/**
	 * 이메일 중복여부 확인
	 * @param email
	 * @return
	 */
	public boolean isExistMail(String email) {
		StringBuffer sql = new StringBuffer();
		String[] param = {email};
		sql.append("SELECT count(*) as cnt FROM ac_user WHERE email = ?");
		int cnt = dao.getCount(property, sql.toString(), param);
		
		if (cnt == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 닉네임 중복여부 확인
	 * @param email
	 * @return
	 */
	public boolean isExistNicname(String nicname) {
		StringBuffer sql = new StringBuffer();
		String[] param = {nicname};
		sql.append("SELECT count(*) as cnt FROM ac_user WHERE nicname = ?");
		int cnt = dao.getCount(property, sql.toString(), param);
		
		if (cnt == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 그룹 ID 중복여부 확인
	 * @param grpId
	 * @return
	 */
	public boolean isExistGrpId(String grpId){
		StringBuffer sql = new StringBuffer();
		String[] param = {grpId};
		sql.append("SELECT count(*) as cnt FROM ac_grp WHERE grp_id = ?");
		int cnt = dao.getCount(property, sql.toString(), param);
		
		if (cnt == 0) {
			return false;
		} else {
			return true;
		}
	}
	
}
