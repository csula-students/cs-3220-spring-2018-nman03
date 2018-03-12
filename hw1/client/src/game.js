import Generator from './models/generator';

// default interval as 1 second
const interval = 1000;

/**
 * loop is main loop of the game, which will be executed once every second (
 * based on the interval variable configuration)
 */
export function loop (store) {
	var increment = 0;

	for (var i = 0 ; i < store.state.generators.length ; i++) {
		var gen = new Generator(Object.assign({}, store.state.generators[i]));
		increment += gen.generate();
	}

	store.dispatch({
		type: 'INCREMENT',
		payload: increment
	});

	store.dispatch({
		type: 'CHECK_STORY'
	});

	// recursively calls loop method every second
	setTimeout(loop.bind(this, store), interval);
}
