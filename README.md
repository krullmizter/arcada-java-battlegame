### University Course | Unity3D Covid simulation assigment

A Unity3D covid particle system simulation by me Samuel 👋 for my second year [Arcada](https://www.arcada.fi/en) oscillation & particle system course assignment.

#### About
This simulation uses Unitys game engine to produce a simulations of a particle system, in this case aa infectious virus spreading in a closed off area. The main task of this assignment was to created a closed of area, have a particle system attached to some game objects that on their own moved around in the gameplay scene. 

#### Gameplay 🕹️
The game uses the Unitys particle system to emit particles from an infectious gameobject character at set intervals. If that infectious characters emission comes into contact with a healthy character that character becomes infected, and its material turns yellow. Patient zero has a red material. The game continues until all the characters have been infected.

The simulation lets the player choose the max amount of spawnable characters. At runtime the game calculates a random amount of characters between 2 - the inputted max number. One of them will be patient zero.

The movement of the characters are based on a waypoint `NavMesh` concept, made up by scattered waypoints that the characters in the simulation move between at a set speed.
