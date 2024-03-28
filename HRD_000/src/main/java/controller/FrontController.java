package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FrontController
 */
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	String charset=null;
	HashMap<String, Controller> list = null;
	
	private final String prefix = "/WEB-INF/view/";
	private final String postfix = ".jsp";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    @Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		System.out.println("1");
		list = new HashMap<String, Controller>();
		list.put("/", new MainController());
		list.put("/memberInsert", new MemberInsertController());
		list.put("/memberList", new MemberListController());
		list.put("/memberMoneyList", new MemberMoneyListController());
		list.put("/memberUpdate", new MemberUpdateController());
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
    	req.setCharacterEncoding(charset);
    	
    	String url = req.getRequestURI();
    	String contextPath = req.getContextPath();
    	String path = url.substring(contextPath.length());
    	System.out.println("1");
    	System.out.println(contextPath);
    	System.out.println(path);
    	Controller subController = list.get(path);
    	
    	if(subController == null) {
    		resp.sendRedirect("/");
    		return;
    	}
    	String result = subController.execute(req, resp);
    	System.out.println(result);
    	
    	if(result.indexOf("redirect::") != 0) {
    		RequestDispatcher dispatcher = req.getRequestDispatcher(prefix + result + postfix);
    		dispatcher.forward(req, resp);
    	}else {
			String reUrl = result.substring("redirect::".length());
			resp.sendRedirect(reUrl);
		}
	}

}
