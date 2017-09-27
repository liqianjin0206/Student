package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class CountListener implements HttpSessionListener,ServletRequestListener{

	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		ServletContext application=se.getSession().getServletContext();
//		if(application.getAttribute("set")==null){ 
//	          Set<String> set=new HashSet<String>();
//	          application.setAttribute("set", set);
//	          }
	           int num=0;
	          if(application.getAttribute("num")!=null){
	          num=(Integer)application.getAttribute("num");
	          }
	          
	        //  String ip= request.getRemoteAddr();
	         // Set<String> set=( Set<String>)application.getAttribute("set");
	         //  boolean flag=set.add(ip);
	          
	     //     if(session.isNew()){
	          num++;
	      //    }
	          application.setAttribute("num", num);
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		
	}

	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		
	}

	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		System.out.println("«Î«Û");
	}

}
