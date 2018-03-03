export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view


			// TODO: subscribe to store on change event
			this.onStateChange = this.handleStateChange.bind(this);

			// TODO: add click event
			this.addEventListener('click',  () => {
				this.store.dispatch({
					type: 'BUY_GENERATOR',
				});
			});
			
		}

		handleStateChange(newState) {
			this.store.state.quantity = newState.quantity;
			this.store.state.counter = newState.counter;
		}

		connectedCallBack() {
            this.store.subscribe(this.onStateChange);
		}


	};

}
