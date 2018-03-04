export default function reducer (state, action) {
	switch (action.type) {
		case 'BUY_GENERATOR':
			var index = -1;
			for (var i = 0 ; i < state.generators.length ; i++) {
				if (state.generators[i].name == action.payload.name) {
					index = i;
				}
			}
			var generator = state.generators[index];
			if (state.counter >= generator.unlockValue) {
				state.counter = state.counter - generator.unlockValue;
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
		default:
			return state;
	}
}

