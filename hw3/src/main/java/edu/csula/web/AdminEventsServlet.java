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

		EventsDAO dao = new EventsDAOImpl(getServletContext());
		ArrayList<Event> events = (ArrayList<Event>) dao.getAll();

		request.setAttribute("events", events);

		String idStr = request.getParameter("id");
		int index = -1;

		if (idStr != null) {
			index = retrieveIndex(Integer.parseInt(idStr));
		}

		request.setAttribute("index", index);


		if (request.getParameter("deleteId") != null) {
			int id = Integer.parseInt(request.getParameter("deleteId"));
			Event event = null;

			event = events.get(retrieveIndex(id));
			

			dao.remove(event.getId());

			response.sendRedirect("events");

			return;
		}

		request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
	
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
