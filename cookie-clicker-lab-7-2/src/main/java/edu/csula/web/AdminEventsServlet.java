package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.EventsDAO;
import edu.csula.storage.mysql.*;
import edu.csula.models.Event;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventsDAO dao = new EventsDAOImpl(new Database());
		List<Event> events = (ArrayList<Event>) dao.getAll();
		
		request.setAttribute("events", events);

		if (request.getParameter("id") != null) {
			int index = retrieveIndex(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("index", index);
		}

		if (request.getParameter("deleteId") != null) {
			int id = Integer.parseInt(request.getParameter("deleteId"));
			dao.remove(id);
			response.sendRedirect("events");

			return;
		}

		request.getRequestDispatcher("/WEB-INF/admin-events.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EventsDAO dao = new EventsDAOImpl(new Database());
		List<Event> events = (ArrayList<Event>) dao.getAll();

		String name = request.getParameter("eventName");
		String description = request.getParameter("eventDescription");
		String triggerAt = request.getParameter("eventTriggerAt");	

		String idStr = request.getParameter("id");

		if (idStr != null) {
			int id = Integer.parseInt(idStr);

			Event event = events.get(retrieveIndex(id));
			event.setName(name);
			event.setDescription(description);
			event.setTriggerAt(Integer.parseInt(triggerAt));
			dao.set(id, event);
		}

		else {
			Event event = new Event(dao.getAll().size(), name, description, Integer.parseInt(triggerAt));
			dao.add(event);
		}

		response.sendRedirect("events");
	}

	public int retrieveIndex(int id) {
		EventsDAO dao = new EventsDAOImpl(new Database());
		ArrayList<Event> events = (ArrayList<Event>) dao.getAll();

		for (int i = 0 ; i < events.size() ; i++) {
			if (id == events.get(i).getId()) {
				return i;
			}
		}

		return -1;

	}
}
