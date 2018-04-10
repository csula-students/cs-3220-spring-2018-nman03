package edu.csula.storage.servlet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

/**
 * To abstract the storage access from the business layer using ServletContext
 * (application scope). This implementation will store the underlying data under
 * the application scope and read from it accordingly.
 *
 * As ServletContext is like a global HashMap, it's important for you to add a
 * context name to separate out the different section of data (e.g. events vs
 * generators) so that you can have the application scope looks like below:
 *
 * ```json
 * {
 *   "events": [
 *     { "id": 0, "name": "event-1", "description": "..." }
 *   ],
 *   "generators": [
 *     { "id": 0, "name": "generator-1", "description": "..." }
 *   ]
 * }
 * ```
 */
public class EventsDAOImpl implements EventsDAO {
	private final ServletContext context;
	protected static final String CONTEXT_NAME = "events";

	public EventsDAOImpl(ServletContext context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		Object data = context.getAttribute(CONTEXT_NAME);
		if (data == null) {
			return new ArrayList<>();
		}

		return (List<Event>) data;
	}

	@Override
	public Optional<Event> getById(int id) {
		// TODO: get a certain event given its id from context (see getAll() on
		// getting a list first and get a certain one from the list)
		List<Event> list = getAll();

		for (int i = 0 ; i < list.size() ; i++) {
			if (list.get(i).getId() == id) {
				return Optional.of(list.get(i));
			}
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		List<Event> list = getAll();
		
		
		for (int i = 0 ; i < list.size() ; i++) {
			if (list.get(i).getId() == id) {
				list.set(i, event);
			}
		}
		
		context.setAttribute(CONTEXT_NAME, list);
		
	}

	@Override
	public void add(Event event) {
		List<Event> list = getAll();
		list.add(new Event(event.getId(), event.getName(), event.getDescription(), event.getTriggerAt()));
		
		context.setAttribute(CONTEXT_NAME, list);
	}

	@Override
	public void remove(int id) {
		List<Event> list = getAll();

		for (int i = 0 ; i < list.size() ; i++) {
			if (list.get(i).getId() == id) {
				list.remove(i);
			}
		}
	}
}
