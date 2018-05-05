package edu.csula.web;

import java.util.ArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.models.User;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		ArrayList<Event> events = (ArrayList<Event>) dao.getAll();

		GeneratorsDAO gdao = new GeneratorsDAOImpl(getServletContext());
		ArrayList<Generator> generators = (ArrayList<Generator>) gdao.getAll();

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String jsonString = gson.toJson(generators);

		request.setAttribute("events", events);

		request.setAttribute("generators", generators);

		request.setAttribute("jsonString", jsonString);

		request.getRequestDispatcher("/WEB-INF/game.jsp").forward(request, response);
	}
}