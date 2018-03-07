export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: add click event to increment counter
			
			// hint: use "store.dispatch" method (see example component)
		}

		connectedCallback() {
			this.innerHTML = '<button>Find Gold</button>';
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: 'BUTTON-CLICK'
				});
			});
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}

	};
}
