package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		String sampleDesc = "Lorem ipsum dolor sit amet, consectetur adipisicing elit.";
		dao.add(new Event(dao.getAll().size(), "Grandma shows up", sampleDesc, 10));
		dao.add(new Event(dao.getAll().size(), "You can create a factory now!", sampleDesc, 50));
		dao.add(new Event(dao.getAll().size(), "We've found cookies in deep mounain ... in the mine?", sampleDesc, 200));

	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		EventsDAO dao = new EventsDAOImpl(getServletContext());
		ArrayList<Event> events = (ArrayList<Event>) dao.getAll();

		request.setAttribute("events", events);

		request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);



		/*
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

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			html += "<input name='eventName' id='name' value=\"" + events.get(retrieveIndex(id)).getName() + "\" type='text' /><br>";
		} 
		else {
			html += "<input name='eventName' id='name' type='text' /><br>";
		}

		html += "<label for='description'>Event Description</label><br>";

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			html += "<textarea name='eventDescription' type='text'>" + events.get(retrieveIndex(id)).getDescription() + "</textarea><br>";
		} 
		else {
			html += "<textarea name='eventDescription' type='text'></textarea><br>";
		}

		html += "<label for='triggerAt'>Trigger At</label><br>";

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			html += "<input name='eventTriggerAt' id='triggerAt' value='" + events.get(retrieveIndex(id)).getTriggerAt() + "' type='text' /><br>";
		} 
		else  {
			html += "<input name='eventTriggerAt' id='triggerAt' type='text' /><br>";
		}

		html += "<button>Add | Edit</button>";
		html += "</form></div>";

		html += "<table>";
		html += "<tr><td>Name</td><td>Description</td><td>Trigger At</td><td>Action</td></tr>";

		for (Event e : events) {
			html += "<tr><td><div class='name'>" + e.getName() + "</div></td><td><div class='description'>" + e.getDescription() + "</div></td><td>" + e.getTriggerAt();
			html += "</td><td><a href='events?id=" + e.getId() + "'>edit</a> | <a href='events?deleteId=" + e.getId() + "'>delete</a></td></tr>";
		}
		
		html += "<tr><td><div class='name'></div></td><td><div class='description'></div></td><td></td><td></td></tr>";
		html += "<tr><td><div class='name'></div></td><td><div class='description'></div></td><td></td><td></td></tr>";
		html += "<tr><td><div class='name'></div></td><td><div class='description'></div></td><td></td><td></td></tr>";		
		html += "</table></div>";


		if (request.getParameter("deleteId") != null) {
			int id = Integer.parseInt(request.getParameter("deleteId"));
			Event event = null;

			event = events.get(retrieveIndex(id));
			

			dao.remove(event.getId());

			response.sendRedirect("events");
		}

		out.println(html);
		*/
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		ArrayList<Event> events = (ArrayList<Event>) dao.getAll();

		String name = request.getParameter("eventName");
		String description = request.getParameter("eventDescription");
		String triggerAt = request.getParameter("eventTriggerAt");	

		String idStr = request.getParameter("id");
		Event event = null;

		if (idStr != null) {
			int id = Integer.parseInt(idStr);

			event = events.get(retrieveIndex(id));
			event.setName(name);
			event.setDescription(description);
			event.setTriggerAt(Integer.parseInt(triggerAt));
			dao.set(id, event);
		}

		else {
			event = new Event(dao.getAll().size(), name, description, Integer.parseInt(triggerAt));
			dao.add(event);
		}

		response.sendRedirect("events");
	}

	public int retrieveIndex(int id) {
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		ArrayList<Event> events = (ArrayList<Event>) dao.getAll();

		for (int i = 0 ; i < events.size() ; i++) {
			if (id == events.get(i).getId()) {
				return i;
			}
		}

		return -1;

	}
}
