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

import edu.csula.storage.mysql.*;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EventsDAO dao = new EventsDAOImpl(new Database());
		ArrayList<Event> story = (ArrayList<Event>) dao.getAll();

		GeneratorsDAO gdao = new GeneratorsDAOImpl(new Database());
		ArrayList<Generator> generators = (ArrayList<Generator>) gdao.getAll();

		DTO dto = new DTO(generators, story);

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		String state = gson.toJson(dto);

		request.setAttribute("state", state);

		request.getRequestDispatcher("/WEB-INF/game.jsp").forward(request, response);
	}

	class DTO {
		private ArrayList<Generator> generators;
		private ArrayList<Event> story;
		private int counter;

		public DTO(ArrayList<Generator> generators, ArrayList<Event> story) {
			this.generators = generators;
			this.story = story;
			this.counter = 0;
		}

		public ArrayList<Generator> getGenerators() {
			return this.generators;
		}

		public ArrayList<Event> getEvents() {
			return this.story;
		}
	}
}