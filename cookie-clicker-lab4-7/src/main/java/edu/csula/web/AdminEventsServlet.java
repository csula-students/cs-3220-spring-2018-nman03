package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet(loadOnStartup=1, urlPatterns={"/admin/events"})
public class AdminEventsServlet extends HttpServlet {
	public void init() {
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		dao.add(new Event(events.size(), "Grandma shows up", "Lorem ...", 10));
		dao.add(new Event(events.size(), "You can create a factory now!", "Lorem ...", 50));
		dao.add(new Event(events.size(), "We've found cookies in deep mounain ... in the mine?", "Lorem ...", 200));

	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		String boo = "test";
		
		System.out.println(events);
		String html = "<link rel='stylesheet' type='text/css\' href='" + request.getContextPath() + "/app.css' />";
		html += "<h1>Incremental Game Framework</h1>";
		html += "<ul>";
		html +=  "<li><a href='" + request.getContextPath() + "/admin/'>Game Information</a></li>";
		html += "<li ><a href='" + request.getContextPath() + "/admin/generators'>Generators</a></li>";
		html += "<li ><a href='" + request.getContextPath() + "/admin/events'>Events</a></li>";
		html += "</ul>";

		html += "<div class='container'><div class='left'><form method='POST'>";
		html += "<label for='name'>Event name</label><br>";
		html += "<input name='eventName' id='name' type='text' /><br>";
		html += "<label for='description'>Event Description</label><br>";
		html += "<textarea name='eventDescription' type='text'></textarea><br>";
		html += "<label for='triggerAt'>Trigger At</label><br>";
		html += "<input name='eventTriggerAt' id='triggerAt' type='text' /><br>";
		html += "<button>Add | Edit</button>";
		html += "</form></div>";

		html += "<table>";
		html += "<tr><td>Name</td><td>Description</td><td>Trigger At</td><td>Action</td></tr>";

		for (Event e : events) {
			html += "<tr><td>" + e.getName() + "</td><td>" + e.getDescription() + "</td><td>" + e.getTriggerAt() + "</td><td>edit | delete</td></tr>";
		}

		html += "</table></div>";


		out.println(html);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		
		String name = request.getParameter("eventName");
		String description = request.getParameter("eventDescription");
		String triggerAt = request.getParameter("eventTriggerAt");	
		
		Event event = new Event(events.size(), name, description, Integer.parseInt(triggerAt));
		dao.add(event);



		response.sendRedirect("events");
	}
}
