package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;

public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM generators;";
	protected static final String getByIdQuery = "SELECT * FROM generators WHERE id = ?";
	protected static final String setQuery = "UPDATE generators SET name = ?, description = ?, rate =  ?, base_cost = ?, unlock_at = ? WHERE id = ?;";
	protected static final String addQuery = "INSERT INTO generators (name, description, rate, base_cost, unlock_at) VALUES (?, ?, ?, ?, ?);";
	protected static final String removeQuery = "DELETE FROM generators WHERE id = ?;";

	public GeneratorsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		List<Generator> result = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				Generator generator = new Generator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
			
				result.add(generator);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return result;
	}

	@Override
	public Optional<Generator> getById(int id) {
		Generator result;
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(getByIdQuery)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			result = new Generator(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
		return Optional.of(result);
	}

	@Override
	public void set(int id, Generator generator) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(setQuery)) {
			stmt.setString(1, generator.getName());
			stmt.setString(2, generator.getDescription());
			stmt.setInt(3, generator.getRate());
			stmt.setInt(4, generator.getBaseCost());
			stmt.setInt(5, generator.getUnlockAt());
			stmt.setInt(6, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void add(Generator generator) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(addQuery)) {
			stmt.setString(1, generator.getName());
			stmt.setString(2, generator.getDescription());
			stmt.setInt(3, generator.getRate());
			stmt.setInt(4, generator.getBaseCost());
			stmt.setInt(5, generator.getUnlockAt());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void remove(int id) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(removeQuery)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
}
