package cdp.comm;

import java.util.Date;

import jm.net.DataEntity;

public class CdpUserObj {

	public String getGrpId(){
		return grpId;
	}
	public String getUsrType(){
		return usrType;
	}
	public String getEmail() {
		return email;
	}
	public String getPasswd() {
		return passwd;
	}
	public String getNicname() {
		return nicname;
	}
	public String getName() {
		return name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getPhone() {
		return phone;
	}
	public String getState() {
		return state;
	}
	public String getGrpDeptId() {
		return grpDeptId;
	}
	public String getGrpName() {
		return grpName;
	}
	public String getGrpText() {
		return grpText;
	}
	public Date getRegTime() {
		return regTime;
	}
	
	// setter 시작
	public void setGrpId(String grpId){
		this.grpId = grpId;
	}
	public void setUsrType(String usrType){
		this.usrType = usrType;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setNicname(String nicname) {
		this.nicname = nicname;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setGrpDeptId(String grpDeptId) {
		this.grpDeptId = grpDeptId;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public void setGrpText(String grpText) {
		this.state = grpText;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	
	private String grpId = "";
	private String usrType = "";
	private String email = "";
	private String passwd = "";
	private String nicname = "";
	private String name = "";
	private Date birthday = null;
	private String phone = "";
	private String state = "";
	
	private String grpDeptId = "";
	private String grpName = "";
	private String grpText = "";
	private Date regTime = null;
	
	public CdpUserObj(){};
	
	public CdpUserObj(DataEntity data){
		this.setEmail((String)data.get("email"));
		this.setGrpId((String)data.get("grp_id"));
		this.setUsrType((String)data.get("user_type"));
		this.setPasswd((String)data.get("passwd"));
		this.setName((String)data.get("name"));
		this.setNicname((String)data.get("nicname"));
		this.setBirthday((Date)data.get("birthday"));
		this.setPhone(data.get("phone")+"");
		this.setState((String)data.get("status"));
		this.setGrpName((String)data.get("grp_name"));
		this.setGrpText((String)data.get("grp_text"));
		this.setGrpDeptId((String)data.get("grp_dept_id"));
		this.setRegTime((Date)data.get("regtime"));
	}
}
