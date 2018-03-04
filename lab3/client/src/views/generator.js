export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view



			// TODO: subscribe to store on change event
			this.onStateChange = this.handleStateChange.bind(this);
			// TODO: add click event

			
		}

		handleStateChange(newState) {
			this.innerHTML = `<p class="generator-name">${newState.generators[this.dataset.id].name}
							  <span class="generator-count">${newState.generators[this.dataset.id].quantity}</span></p>
							  <p>${newState.generators[this.dataset.id].description}</p>
							  <span class="rate">${newState.generators[this.dataset.id].rate}/60</span>
							  <button class="buy-button">${newState.generators[this.dataset.id].unlockValue} Gold</button>`;
		}

		connectedCallback() {
			this.innerHTML = `<p class="generator-name">${store.state.generators[this.dataset.id].name}
							  <span class="generator-count">${store.state.generators[this.dataset.id].quantity}</span></p>
							  <p>${store.state.generators[this.dataset.id].description}</p>
							  <span class="rate">${store.state.generators[this.dataset.id].rate}/60</span>
							  <button class="buy-button">${store.state.generators[this.dataset.id].unlockValue} Gold</button>`;

			this.addEventListener('click', () => {
				this.store.dispatch({
					type: 'BUY_GENERATOR',
					payload: {
						name: this.store.state.generators[this.dataset.id].name
					}
				});
			});
							
            this.store.subscribe(this.onStateChange);
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}




	};

}
