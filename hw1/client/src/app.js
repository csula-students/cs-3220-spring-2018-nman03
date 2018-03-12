import '@webcomponents/webcomponentsjs';

import {loop} from './game';
import Store from './store';
import reducer from './reducer';

import ButtonComponent from './views/button';
import CounterComponent from './views/counter';
import ExampleComponent from './views/example';
import GeneratorComponent from './views/generator';
import StoryBookComponent from './views/story-book';

/**
 * Data flow diagram
 +----------------------------------------------------+
 | +------------------+          +------------------+ |
 | |                  |          |                  | |
++-|       Loop       |<---------|    Generator     | |
|| |                  |          |                  | |
|| +------------------+          +------------------+ |
||G          ^                                        |
||a          +-----------------------------+          |
||m                                        |          |
||e                                        |          |
||                               +------------------+ |
||                               |                  | |
||                               |     Stories      | |
||                               |                  | |
||                               +------------------+ |
|+----------------------------------------------------+
+------------------------------------------------------------+
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|                                                            |
|       +----------------------------------------------------+----------+
|       | +------------------+                     +------------------+ |
|       | |                  |        Mutates      |                  | |
|       | |     Reducer      |-------------------->|      State       | |
|       | |                  |                     |                  | |
|       | +------------------+                     +------------------+ |
|       |S          ^                                        |          |
|       |t          |                                        |          |
|       |o          |                                        |          |
|       |r          | Triggers                     Notifies  |          |
|       |e          |                                        |          |
|       |           |                                        v          |
|       | +------------------+                     +------------------+ |
|       | |                  |                     |                  | |
+-------+>|      Action      |                     |    Listeners     | |
        | |                  |                     |                  | |
        | +------------------+                     +------------------+ |
        +-----------^----------------------------------------+----------+
                    |                                        |
                    |                                        |
                    |                                        |
                    |                                        |
                    | Dispatches                             |
                    |                                        |
                    |                                        |
          +------------------+                               |
          |                  |                               |
          |      Views       |              Notifies changes |
          |    Components    |<------------------------------+
          |                  |
          +------------------+
 */
main();

// main function wraps everything at top level
function main () {
	// TODO: fill the blank based on the theme you have choosen
	const initialState = {
		example: 'Hello custom element',
		counter: 0,
		generators: [
			{
				type: 'autonomous',
				name: 'Adventurer',
				description: 'This Solo Adventurer will be able to earn enough 1 gold every second',
				rate: 1,
				baseCost: 10,
				quantity: 0,
				unlockValue: 10

			},
			{
				type: 'autonomous',
				name: 'Raid Team',
				description: 'This team of experienced fighters will be able to amass 10 gold every second',
				rate: 10,
				baseCost: 100,
				quantity: 0,
				unlockValue: 100

			},
			{
				type: 'autonomous',
				name: 'Dungeon Champion',
				description: 'This OP character can defeat several bosses per run without using a single HP potion, allowing him to earn 100 gold every second',
				rate: 100,
				baseCost: 1000,
				quantity: 0,
				unlockValue: 1000

			}],
		story: [
		{
			name: 'Respected',
			description: 'You are now a Respected Dungeoneer, you can now hire an adventurer to do the looting for you.',
			triggeredAt: 10,
			state: 'hidden'
		},
		{
			name: 'Famous',
			description: 'Your name echoes throughout the lands, tales of your adventure are sung in many taverns',
			triggeredAt: 200,
			state: 'hidden'
		},
		{
			name: 'Kingpin',
			description: 'You have achieved the title of Dungeon Kingpin, adventurers seek your advice, monster fear your name, and gold is nothing but number',
			triggeredAt: 2000,
			state: 'hidden' 
		}
		]
	};

	// initialize store
	const store = new Store(reducer, initialState);
	console.log(ExampleComponent(store));

	// define web components
	window.customElements.define('component-example', ExampleComponent(store));
	// no longer used
	window.customElements.define('game-button', ButtonComponent(store));
	window.customElements.define('game-counter', CounterComponent(store));
	// lab 3
	window.customElements.define('game-generator', GeneratorComponent(store));
	// homework 1
	window.customElements.define('game-story-book', StoryBookComponent(store));

	// For ease of debugging purpose, we will expose the critical store under window
	// ps: window is global
	window.store = store;

	// start game loop
	loop(store);
}
