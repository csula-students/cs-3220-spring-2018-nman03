package edu.csula.web;

import java.io.IOException;
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

		String idStr = request.getParameter("id");

		if (idStr != null) {
			int index = retrieveIndex(Integer.parseInt(idStr));
			request.setAttribute("index", index);
		}

		String deleteId = request.getParameter("deleteId");

		if (deleteId != null) {
			int id = Integer.parseInt(deleteId);
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
			event.setTriggerAt(safeParseInt(triggerAt));
			dao.set(id, event);
		}

		else {
			Event event = new Event(dao.getAll().size(), name, description, safeParseInt(triggerAt));
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

	public static Integer safeParseInt(String text) {
		if (!text.isEmpty()) {
			return Integer.parseInt(text);
		}
		return 0;
	}
}