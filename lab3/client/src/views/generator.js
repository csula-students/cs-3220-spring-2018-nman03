export default function (store) {
	return class GeneratorComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			// TODO: render generator initial view

			// TODO: subscribe to store on change event
			this.onStateChange

			// TODO: add click event
			this.addEventListener('click', () => {
                this.store.dispatch({
                    type: 'BUY_GENERATOR';
                });
            });
		}
	};
}
