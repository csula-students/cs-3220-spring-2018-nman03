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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		ArrayList<Generator> generators = (ArrayList<Generator>) dao.getAll();

		System.out.println(generators);
		String html = "<link rel='stylesheet' type='text/css\' href='" + request.getContextPath() + "/app.css' />";
		html += "<h1>Incremental Game Framework</h1>";
		html += "<ul>";
		html +=  "<li><a href='" + request.getContextPath() + "/admin/'>Game Information</a></li>";
		html += "<li ><a href='" + request.getContextPath() + "/admin/generators'>Generators</a></li>";
		html += "<li ><a href='" + request.getContextPath() + "/admin/events'>Events</a></li>";
		html += "</ul>";
		html += "<div class='container'><div class='left'><form method='POST'>";
		html += "<label for='name'>Generator name:</label><br>";

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			html += "<input name='generatorName' id='name' value=\"" + generators.get(retrieveIndex(id)).getName() + "\" type='text' /><br>";
		} 
		else {
			html += "<input name='generatorName' id='name' type='text' /><br>";
		}

		html += "<label for='rate'>Generator Rate</label><br>";

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			html += "<input name='generatorRate' id='rate' value='" + generators.get(retrieveIndex(id)).getRate() + "' type='text' /><br>";
		} 
		else  {
			html += "<input name='generatorRate' id='rate' type='text' /><br>";
		}

		html += "<label for='cost'>Unlock At</label><br>";

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			html += "<input name='generatorCost' id='cost' value='" + generators.get(retrieveIndex(id)).getBaseCost() + "' type='text' /><br>";
		} 
		else  {
			html += "<input name='generatorCost' id='cost' type='text' /><br>";
		}

		

		html += "<label for='unlockAt'>Unlock At</label><br>";

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			html += "<input name='generatorUnlockAt' id='unlockAt' value='" + generators.get(retrieveIndex(id)).getUnlockAt() + "' type='text' /><br>";
		} 
		else  {
			html += "<input name='generatorUnlockAt' id='unlockAt' type='text' /><br>";
		}

		html += "<label for='description'>Generator Description</label><br>";

		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			html += "<textarea name='generatorDescription' type='text'>" + generators.get(retrieveIndex(id)).getDescription() + "</textarea><br>";
		} 
		else {
			html += "<textarea name='generatorDescription' type='text'></textarea><br>";
		}

		html += "<button>Add | Edit</button>";
		html += "</form></div>";

		html += "<table>";
		html += "<tr><td>Name</td><td>Rate</td><td>Cost</td><td>Unlock At</td><td>Action</td></tr>";

		for (Generator e : generators) {
			html += "<tr><td><div class='name'>" + e.getName() + "</div></td><td><div class='rate'>" + e.getRate() + "</div></td><td><div class='cost'>" + e.getBaseCost() + "</div></td><td><div class='unlockAt'>" + e.getUnlockAt() + "</div></td>";
			html += "<td><a href='generators?id=" + e.getId() + "'>edit</a> | <a href='generators?deleteId=" + e.getId() + "'>delete</a></td></tr>";
		}
		
		html += "<tr><td><div class='name'></div></td><td><div class='rate'></div></td><td><div class='cost'></div></td><td><div class='unlockAt'></div></td><td></td></tr>";
		html += "<tr><td><div class='name'></div></td><td><div class='rate'></div></td><td><div class='cost'></div></td><td><div class='unlockAt'></div></td><td></td></tr>";
		html += "<tr><td><div class='name'></div></td><td><div class='rate'></div></td><td><div class='cost'></div></td><td><div class='unlockAt'></div></td><td></td></tr>";
		html += "</table></div>";


		if (request.getParameter("deleteId") != null) {
			int id = Integer.parseInt(request.getParameter("deleteId"));
			Generator generator = null;

			generator = generators.get(retrieveIndex(id));
			

			dao.remove(generator.getId());

			response.sendRedirect("generators");
		}

		out.println(html);
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
