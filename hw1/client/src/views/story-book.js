export default function (store) {
	return class StoryBookComponent extends window.HTMLElement {
		constructor () {
			super();
			this.store = store;

			this.onStateChange = this.handleStateChange.bind(this);
		}

		handleStateChange (newState) {
			var storyOut = `<p>Story:</p>`;

			for (var i = 0 ; i < newState.story.length ; i++) {
				if (newState.story[i].state == 'visible') {
					storyOut += `<p>${newState.story[i].description}</p>`;
				}	
			}

			this.innerHTML = storyOut;
			
		}

		connectedCallback () {
			this.store.subscribe(this.onStateChange);
			this.innerHTML = `<p>Story:</p>`;
		}

		disconnectedCallback () {
			this.store.unsubscribe(this.onStateChange);
		}
	};
}

