package edu.csula.web;

import java.util.ArrayList;
import java.util.Collection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	public void init() {
		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		String sampleDesc = "Lorem ipsum dolor sit amet, consectetur adipisicing elit.";
		dao.add(new Generator(dao.getAll().size(), "Grandma", sampleDesc, 5, 10, 5));
		dao.add(new Generator(dao.getAll().size(), "Factory", sampleDesc, 10, 50, 50));
		dao.add(new Generator(dao.getAll().size(), "Mine", sampleDesc, 20, 200, 200));
	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		ArrayList<Generator> generators = (ArrayList<Generator>) dao.getAll();

		request.setAttribute("generators", generators);

		String idStr = request.getParameter("id");
		int index = -1;

		if (idStr != null) {
			index = retrieveIndex(Integer.parseInt(idStr));
		}

		request.setAttribute("index", index);

		if (request.getParameter("deleteId") != null) {
			int id = Integer.parseInt(request.getParameter("deleteId"));
			Generator generator = null;

			generator = generators.get(retrieveIndex(id));
			

			dao.remove(generator.getId());

			response.sendRedirect("generators");
		}

		request.getRequestDispatcher("/WEB-INF/admin-generators.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		ArrayList<Generator> generators = (ArrayList<Generator>) dao.getAll();

		String name = request.getParameter("generatorName");
		String rate = request.getParameter("generatorRate");
		String cost = request.getParameter("generatorCost");
		String unlockAt = request.getParameter("generatorUnlockAt");	
		String description = request.getParameter("generatorDescription");

		String idStr = request.getParameter("id");
		Generator generator = null;

		if (idStr != null) {
			int id = Integer.parseInt(idStr);

			generator = generators.get(retrieveIndex(id));
			generator.setName(name);
			generator.setRate(Integer.parseInt(rate));
			generator.setBaseCost(Integer.parseInt(cost));
			generator.setUnlockAt(Integer.parseInt(unlockAt));
			generator.setDescription(description);
			dao.set(id, generator);
		}

		else {
			generator = new Generator(dao.getAll().size(), name, description, Integer.parseInt(rate), Integer.parseInt(cost), Integer.parseInt(unlockAt));
			dao.add(generator);
		}

		response.sendRedirect("generators");
	}

	public int retrieveIndex(int id) {
		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		ArrayList<Generator> generators = (ArrayList<Generator>) dao.getAll();

		for (int i = 0 ; i < generators.size() ; i++) {
			if (id == generators.get(i).getId()) {
				return i;
			}
		}

		return -1;
	}
}
