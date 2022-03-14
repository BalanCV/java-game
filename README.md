# OpenGL Game Project: From Scratch in Java

DISCLAIMER: JAVA RUNTIME ENVIRONMENT(JRE) Must be installed on your device in order to run the program through runGame.jar
The exe file is not optimized, as such it is preferred to run through runGame.jar

A three-dimensional platformer game developed in the OpenGL 
application program interface. This project and report will go through the 
process of building the necessary interfaces which are needed to run the 
visuals and mechanics of a video game. These major interfaces consist of 
an engine on which the game will be built on and run and a renderer that is 
the part of the engine that manages graphics and how they are displayed 
on the user screen.


The game itself consists of a sandbox environment where the player has to 
travel across the map and collect three main items to end the game. In 
order to do this, they must solve a puzzle and fight or manage to avoid 
simple enemies.

# Extended Project Introduction:
<details>
  <summary>Click to expand!</summary>
  
  * This project represents the creation of a video game from scratch using the OpenGL 
Application Programming Interface.
  * OpenGL API contains functions that directly target the capacities of the Graphics 
Processing Unit to output display on an external monitor. OpenGL is also basically a 
specification that is directly integrated into the Graphics Card by the manufacturer. The 
drivers supplied by the manufacturer contain the actual implementation of OpenGL.
  
  * There is a form of programming language specific to coding the shaders for OpenGL 
called GL Shader Language, which this project does use for dealing with code for 
shaders, but this is limited to the vertex and fragment shaders necessary for rendering, 
so the majority of the project will be coded in Java using the library called “Lightweight 
Java Game Library” which allows calls to OpenGL using the Java Runtime 
Environment. In addition to this, “slick” and “PngDecoder” are libraries that this project 
uses to easily import the .png image format which will be used as textures, menus, ingame text, and more. Although these two libraries are needed for a similar task, the 
necessity for both of them exists because they come with different sets of functions and 
levels of accessibility. For example, slick can load images much more efficiently in the 
project and is good for general image use on textures, but PngDecoder comes with 
functions that can also return detailed information about images if those details are 
needed for computation.
  
  *The java game library provides the programmer with a unified set of methods that 
perform calls to OpenGL functions; Since they are coded by the Graphics Card 
manufacturer they may differ depending on the manufacturer. The library recognizes the 
format used by each manufacturer (e.g. Intel, AMD, Nvidia) and performs the call while 
the programmer only needs to know the function names defined in the game library. It 
also provides access to an alternative branch of OpenGL that deals with audio 
manipulation and decoding. Therefore, it is possible to set up background music for the 
game and implement functions such as audio location, movement, intensity, and infinite 
replay loops.
  
  *Because the project starts from scratch, the majority of the workflow is first directed 
towards creating a renderer and a simplified game engine. This is the prerequisite to 
generate a game world environment and gameplay functions are added in the final 
stage.

  *Rendering must be performed efficiently from the initial coding stage, as large models 
later on can add exponential complexity due to bad calculations and limit the potential 
for creating objects, environments, and features.
  
  *In the initial phases the game engine has the job of declaring the structure of the display 
and what type of objects and entities are there to be displayed, how many, where, and 
in which way. It also stands that in the game engine we will have the conditions and 
functions to initialize the program and calls to destroy functions when the program ends 
by user input. After the renderer is finalized, the game engine will become the focus as 
the game world is populated and gameplay features are added.
  
  *I chose this project because of my passion for video games and my desire to learn their 
inner workings in order to produce games myself. The learning process will familiarize 
me with game developing techniques, what works and what does not, and prepare me 
for future projects in the industry I want to work in.
  *I am very interested in learning the limits of a solo developer-made engine and how 
much optimization can be done so that the program runs as efficient as possible within 
the capabilities of the Graphics Card.
  *I also intend to learn and implement features present in video games today and 
discover the inner workings. Among these are some that affect the whole rendering 
process as shaders, lightning, texturing, reflections but also common features such as 
particles, tiling, and camera effects.
  *In the end, I want the project to evolve into a functioning three-dimensional game with a 
generated world that has a controllable player. I also want to add as many mechanics in 
the end to allow a variety of actions aside from the features rendered in the world that 
grant the user some capacity to interact with this environment.
  
</details>
