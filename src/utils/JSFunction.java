package utils;
import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class JSFunction {
	public static void alertLocation(String msg,String url,JspWriter out) {
		String script=""
				+"<script>"
				+"    alert('" +msg+ "');"
				+"    location.href='"+url+"';"
				+"</script>";
		try {
			out.println(script);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 메시지 알림창을 띄운 후 이전 페이지로 돌아간다. 
	public static void alertBack(String msg,JspWriter out) {
		String script=""
				+"<script>"
				+"    alert('" +msg+ "');"
				+"    history.back();"
				+"</script>";
		try {
			out.println(script);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
