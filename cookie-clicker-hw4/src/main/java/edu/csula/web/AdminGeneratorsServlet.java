package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.ArrayList;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.mysql.*;
import edu.csula.storage.mysql.*;
import edu.csula.models.Generator;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		List<Generator> generators = (ArrayList<Generator>) dao.getAll();
		
		request.setAttribute("generators", generators);

		if (request.getParameter("id") != null) {
			int index = retrieveIndex(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("index", index);
		}

		if (request.getParameter("deleteId") != null) {
			int id = Integer.parseInt(request.getParameter("deleteId"));
			dao.remove(id);
			response.sendRedirect("generators");

			return;
		}

		request.getRequestDispatcher("/WEB-INF/admin-generators.jsp").forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		List<Generator> generators = (ArrayList<Generator>) dao.getAll();

		String name = request.getParameter("generatorName");
		String description = request.getParameter("generatorDescription");
		String rate = request.getParameter("generatorRate");
		String baseCost = request.getParameter("generatorBaseCost");
		String unlockAt = request.getParameter("generatorUnlockAt");	

		String idStr = request.getParameter("id");

		if (idStr != null) {
			int id = Integer.parseInt(idStr);

			Generator generator = dao.getById(id).get();
			generator.setName(name);
			generator.setDescription(description);
			generator.setRate(Integer.parseInt(rate));
			generator.setBaseCost(Integer.parseInt(baseCost));
			generator.setUnlockAt(Integer.parseInt(unlockAt));
			dao.set(id, generator);
		}

		else {
			Generator generator = new Generator(dao.getAll().size(), name, description, Integer.parseInt(rate), 
				Integer.parseInt(baseCost), Integer.parseInt(unlockAt));
			dao.add(generator);
		}

		response.sendRedirect("generators");
	}

	public int retrieveIndex(int id) {
		GeneratorsDAO dao = new GeneratorsDAOImpl(new Database());
		ArrayList<Generator> generators = (ArrayList<Generator>) dao.getAll();

		for (int i = 0 ; i < generators.size() ; i++) {
			if (id == generators.get(i).getId()) {
				return i;
			}
		}

		return -1;
	}
}
