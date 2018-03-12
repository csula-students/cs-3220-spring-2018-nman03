import Generator from '../models/generator';

export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);			
		}

		handleStateChange(newState) {
			const generator = new Generator(Object.assign({}, newState.generators[this.dataset.id]));

			this.innerHTML = `<p class="generator-name">${generator.name}
							  <span class="generator-count">${generator.quantity}</span></p>
							  <p>${generator.description}</p>
							  <span class="rate">${generator.rate}/s</span>
							  <button class="buy-button">${Math.round(generator.getCost())} Gold</button>`;
		}

		connectedCallback() {
			const generator = new Generator(Object.assign({}, store.state.generators[this.dataset.id]));

			this.innerHTML = `<p class="generator-name">${generator.name}
							  <span class="generator-count">${generator.quantity}</span></p>
							  <p>${generator.description}</p>
							  <span class="rate">${generator.rate}/s</span>
							  <button class="buy-button">${Math.round(generator.getCost())} Gold</button>`;


			this.addEventListener('click', () => {
				this.store.dispatch({
					type: 'BUY_GENERATOR',
					payload: {
						name: generator.name
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
