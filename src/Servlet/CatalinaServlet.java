package Servlet;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/numbers")
public class CatalinaServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		String log = "";

/*
 * Give the location of log file  
 */
		String fileName = "/Users/balgovindsingh/Desktop/GeekTrust/TestLog"; // this path is on my local
		try (BufferedReader fileBufferReader = new BufferedReader(new FileReader(fileName))) {
			String fileLineContent;
			while ((fileLineContent = fileBufferReader.readLine()) != null) {
				// process the line.
				log += fileLineContent + "\"" + System.lineSeparator();
			}
		}
		findSuccessIpCount(log.replaceAll("\\\\", ""), response);

	}

	public static void findSuccessIpCount(String record, HttpServletResponse response) throws IOException {
		// Creating a regular expression for the records
		final String regex = "^(\\S+) (\\S+) (\\S+) " + "\\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+)"
				+ " (\\S+)\\s*(\\S+)?\\s*\" (\\d{3}) (\\S+)";

		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(record);

		// Creating a Hashmap containing string as
		// the key and integer as the value.
		HashMap<String, Integer> countSuccessIP = new HashMap<String, Integer>();
		HashMap<String, Integer> countFailedIP = new HashMap<String, Integer>();
		while (matcher.find()) {

			String IP = matcher.group(1);
			String stringResponse = matcher.group(8);
			int responseStatus = Integer.parseInt(stringResponse);

			// Inserting the IP addresses in the
			// HashMap and maintaining the frequency
			// for each HTTP 200 code.
			if (responseStatus == 200) {
				if (countSuccessIP.containsKey(IP)) {
					countSuccessIP.put(IP, countSuccessIP.get(IP) + 1);
				} else {
					countSuccessIP.put(IP, 1);
				}
			}
			// Inserting the IP addresses in the
			// HashMap and maintaining the frequency
			// for each HTTP 500 code.
			if (responseStatus == 500) {
				if (countFailedIP.containsKey(IP)) {
					countFailedIP.put(IP, countFailedIP.get(IP) + 1);
				} else {
					countFailedIP.put(IP, 1);
				}
			}
		}

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>Reports</title></head>");
		out.println("<body>");
		out.println("<ul>");
		out.println("Success Ips Having Status code as 200 ");
		for (Map.Entry entrySuccess : countSuccessIP.entrySet()) {
			{
				out.println("<li>" + entrySuccess.getKey() + "===Hits===>" + entrySuccess.getValue() + "</li>");
			}
			out.println("</ul>");

		}
		out.println("UnSuccess Ips Having Status Code as 500");
		for (Map.Entry entryFail : countFailedIP.entrySet()) {
			{
				out.println("<li>" + entryFail.getKey() + "===Hits===>" + entryFail.getValue() + "</li>");
			}
			out.println("</body></html>");
		}
	}
}
