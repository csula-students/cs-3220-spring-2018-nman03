package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class game_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<head>\n");
      out.write("\t<meta charset=\"UTF-8\">\n");
      out.write("\t<title>Dungeon Crawler</title>\n");
      out.write("\t<link href=\"app.css\" rel=\"stylesheet\" />\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<header>\n");
      out.write("\t\t<h1>Dungeon Runner</h1>\n");
      out.write("\t</header>\n");
      out.write("\n");
      out.write("\t\n");
      out.write("\t<main>\n");
      out.write("\t\t<div class=\"story\">\n");
      out.write("\t\t\t<p>Need Gold? Click the buttton to look for loot and earn Gold.</p>\n");
      out.write("\t\t\t<p>You've made a name for yourself as a veteran dungeoneer.</p>\n");
      out.write("\t\t\t<p>You've hired an Adventurer to do the looting for you.</p>\n");
      out.write("\t\t</div>\n");
      out.write("\n");
      out.write("\t\t<ul></ul>\n");
      out.write("\n");
      out.write("\t\t<game-counter></game-counter>\n");
      out.write("\t\t<game-button></game-button>\t\n");
      out.write("\n");
      out.write("\t\t<div class=\"container\">\n");
      out.write("\t\t\t<game-generator data-id=\"0\"></game-generator>\n");
      out.write("\t\t\t<game-generator data-id=\"1\"></game-generator>\n");
      out.write("\t\t\t<game-generator data-id=\"2\"></game-generator>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t</main>\n");
      out.write("\n");
      out.write("\t<footer><span>&copy; 2018 Neil Manimtim</span></footer>\n");
      out.write("\n");
      out.write("\t<script src='app.js'>\n");
      out.write("    \twindow.incrementalGame.state.generators.forEach(g =>\n");
      out.write("    \t\tvar node = document.createElement(li);\n");
      out.write("    \t\tnode.textContent(g.name);\n");
      out.write("    \t\tdocument.querySelector(ul).appendChild(node);\n");
      out.write("    \t);\n");
      out.write("\t</script>\n");
      out.write("\n");
      out.write("</body>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
