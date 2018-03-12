export default function (store) {
	return class ButtonComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

	
		}

		connectedCallback() {
			this.innerHTML = '<button>Find Gold</button>';
			this.addEventListener('click', () => {
				this.store.dispatch({
					type: 'BUTTON-CLICK'
				});

				this.store.dispatch({
					type: 'CHECK_STORY'
				});
			});
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}

	};
}
