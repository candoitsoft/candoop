package cdp.comm;

public class CdpMsg {

	/**
	 * 오류 메시지. 이전 페이지로 이동.
	 * @param errMsg
	 * @return
	 */
	public static String error(String errMsg){
		StringBuffer msg = new StringBuffer();
		msg.append("<script> \n");
		msg.append("	alert(\""+errMsg+"\"); \n");
		msg.append("	history.go(-1); \n");
		msg.append("</script> \n");
		return msg.toString();
	}
	public static String loginGoFirst(){
		StringBuffer msg = new StringBuffer();
		msg.append("<script> \n");
		msg.append("	alert(\"로그인 후 이용해 주시기 바랍니다.\"); \n");
		msg.append("	location.replace('/Login'); \n");
		msg.append("</script> \n");
		return msg.toString();
	}
	
	public static String loginError(){
		return error("로그인 후 이용해 주시기 바랍니다.");
	}
	
	public static String approachError(){
		return error("잘못된 접근입니다.");
	}
	
	public static String saveError(){
		return error("저장하는 중 오류가 발생되었습니다.");
	}
	
}
