# CAB302Project

Version 1 
Notes:
13/04/22
- Created the public class for Controller.Maze and Controller.Logo with all its variables and constructors
- Each Class has a toString variable for debugging purposes.
- Each Controller.Maze will be put into mazeList upon creationNotes:
- Created the public class for Controller.Maze and Controller.Logo with all its variables and constructors
- Each Class has a toString variable for debugging purposes.
- Each Controller.Maze will be put into mazeList upon creation
- Several Classes were added to the src file, some contain skeleton methods.

Version 1.1 
Notes:
14/04/22
- Created Model, View, Controller packages to seperate the classes 
- Added an implmentation to KidsMaze class to inherit from Maze class 

Version 1.2
Notes:
26/04/2022
- Added IMaze interface
- Fixed Errors in KidzMaze and Maze class, added interface implmentation 
- Added return statement to all methods

Version 1.3
Notes:
27/04/2022
- Added Cell, DisjSets classes
- Added implmentation of draw, getMazeSize, getMazeCells, makeWalls, clearWalls, windowSize, getMaze, toString, drawMazeTemplate and getPath to Maze and KidsMaze classes
- Added implmentation of createPath and depthSearch in MazeSolver class
- Linked up the classes to enable complication 
- Ran tests on creating a maze
- Created Model Package to store database related code
- Stored Database and MazeCollections in Model package
- Added some encapuslation to the mainGUI class, moving the Main method to a seperate class and called mainGUI object

Version 1.4
Notes:
28/04/2022
- Added JavaDocs comments for every class
- Added JavaDocs comments for every method 
- Generated JavaDocs
 
Version 1.5
Notes:
05/05/2022
- Added Logo implementation
- Added Database Connection
- Changed to GUI

Version 2.0
Notes:
26/05/2022
- Added new implmentation for maze generation 
- Added new implementation for maze solving
- Added new implementation for the GUI

Version 2.1
Notes:
3/06/2022
- Database connection in new implementation
- Added Jdocs for new implementation 
- Removed redundant methods from Controller 
- Removed redundant methods from View

Version 2.2
Notes:
6/06/2022
- Add Kids Maze
- Add logo
- Enable Logo importing
- Enable Start,Goal and Logo points to be set when generating a maze

Version 2.3
Notes:
07/06/2022
- Add Java Document (Maze API)
- Added Comments to every method
- Added Database Coonection and other classes related to database 

Version 2.4
Notes:
10/06/2022
- Add Search Panel Class - linked upto GUI
- Added two panes, Maze and Search to GUI
- Added Kids Logo to Kids Maze (also on auto generated kids mazes)
- movable logo icon, both when imported and with temp logo icon

Version 2.5
Notes:
12/06/2022
- Added Unit testing
- TestCell 
- TestMaze
- TestMazeGenerator
- TestMazeSolver
