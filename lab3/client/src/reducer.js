export default function reducer (state, action) {
	switch (action.type) {
		case 'BUY_GENERATOR':
			state.quantity += 1;
			state.unlockValue = state.getCost();
			return state;
		default:
			return state;
	}
}

