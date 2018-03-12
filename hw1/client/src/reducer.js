import Generator from './models/generator';
import Story from './models/story';

export default function reducer (state, action) {
	switch (action.type) {
		case 'BUY_GENERATOR':
			var index = -1;

			for (var i = 0 ; i < state.generators.length ; i++) {
				if (state.generators[i].name == action.payload.name) {
					index = i;
				}
			}

			const generator = new Generator(Object.assign({}, state.generators[index]));
			var cost = Math.round(generator.getCost());

			if (state.counter >= cost) {
				state.counter = state.counter - cost;
				state.generators[index].quantity ++;
			} 
			else {
				alert('Not Enough Gold');
			}

			return state;
		case 'BUTTON-CLICK':
			state.counter ++;
			return state;
		case 'EXAMPLE_MUTATION':
			state.example = action.payload;
			return state;
		case 'CHECK_STORY':

			for (var i = 0 ; i < state.story.length ; i++) {
				var story = new Story(Object.assign({}, state.story[i]));
				if (story.isUnlockYet(state.counter)) {
					state.story[i].state = 'visible';
				}
			}
			return state;

		case 'INCREMENT':
			state.counter += action.payload;

			return state;	
		default:
			return state;
	}
}

