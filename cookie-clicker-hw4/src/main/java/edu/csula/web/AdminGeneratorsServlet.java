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

		String idStr = request.getParameter("id");

		if (idStr != null) {
			int index = retrieveIndex(safeParseInt(idStr));
			request.setAttribute("index", index);
		}

		String deleteId = request.getParameter("deleteId");

		if (deleteId != null) {
			int id = safeParseInt(deleteId);
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
			int id = safeParseInt(idStr);

			Generator generator = generators.get(retrieveIndex(id));
			generator.setName(name);
			generator.setDescription(description);
			generator.setRate(safeParseInt(rate));
			generator.setBaseCost(safeParseInt(baseCost));
			generator.setUnlockAt(safeParseInt(unlockAt));
			dao.set(id, generator);
		}

		else {
			Generator generator = new Generator(dao.getAll().size(), name, description, safeParseInt(rate), 
				safeParseInt(baseCost), safeParseInt(unlockAt));
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

	public static Integer safeParseInt(String text) {
		if (!text.isEmpty()) {
			return Integer.parseInt(text);
		}
		return 0;
	}
}
