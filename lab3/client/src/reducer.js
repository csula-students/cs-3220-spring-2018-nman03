export default function reducer (state, action) {
	switch (action.type) {
		case 'BUY_GENERATOR':
			state.quantity += 1;
			state.counter -= state.unlockValue;
			return state;
		case 'EXAMPLE_MUTATION':
			state.example = action.payload;
			return state;
		default:
			return state;
	}
}

