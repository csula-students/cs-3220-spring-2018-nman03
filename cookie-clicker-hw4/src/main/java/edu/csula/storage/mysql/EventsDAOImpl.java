package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

import edu.csula.storage.EventsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Event;

public class EventsDAOImpl implements EventsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM events;";
	protected static final String getByIdQuery = "SELECT * FROM events WHERE id = ?";
	protected static final String setQuery = "UPDATE events SET name = ?, description = ?, trigger_at = ? WHERE id = ?;";
	protected static final String addQuery = "INSERT INTO events (name, description, trigger_at) VALUES (?, ?, ?);";
	protected static final String removeQuery = "DELETE FROM events WHERE id = ?;";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		List<Event> result = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				Event event = new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			
				result.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return result;
	}

	@Override
	public Optional<Event> getById(int id) {
		Event result;
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(getByIdQuery)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			result = new Event(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Optional.empty();
		}
		return Optional.of(result);
	}

	@Override
	public void set(int id, Event event) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(setQuery)) {
			stmt.setString(1, event.getName());
			stmt.setString(2, event.getDescription());
			stmt.setInt(3, event.getTriggerAt());
			stmt.setInt(4, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void add(Event event) {
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(addQuery)) {
			stmt.setString(1, event.getName());
			stmt.setString(2, event.getDescription());
			stmt.setInt(3, event.getTriggerAt());
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
