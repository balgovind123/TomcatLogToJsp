

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

//public class ValveMain extends ValveBase {
//
//	@Override
//	public void invoke(Request request, Response response) throws IOException, ServletException {
//
//		String reqlogin = request.getRemoteUser();
//		String method = request.getMethod();
//		String requestURI = request.getRequestURI();
//		HttpSession session = request.getRequest().getSession();
//		String sid = session.getId();
//		//String user_id = (String) session.getAttribute("UserID");
//		//String sesslogin = (String) session.getAttribute("login"); // put in session by app code
//		LocalDateTime now = LocalDateTime.now();
//		
//		
//		System.out.println("LoginValve: " + now + " sid: " + sid + " UserID: " + "" + " method: " + method
//				+ " requestURI: " + requestURI + " reqlogin: " + reqlogin + " sesslogin: " + "");
//
//		
//		request.setAttribute("NAME", "balu");
//		request.setAttribute("SECN", "singh");
//		request.setAttribute("TITLE", "kuch bi");
//		RequestDispatcher requestDispatcher;
//		requestDispatcher = request.getRequestDispatcher("/GetRequestHeader.jsp");
//		requestDispatcher.forward(request, response);
//		getNext().invoke(request, response);
//	}
//}
