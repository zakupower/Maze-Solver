# Rules for the maze
+ 1 px wide corridors
+ entrance should be at top of image 1 px 
+ exit should be at bottom of image 1 px
+ only 1 exit only 1 entrance
+ RGB Image - this could be easily changed to greyscale but I prefer to stay with it for now

Result image is generated by the program with blue path.


# DFS and BFS<br>
The solver uses undirected non-weighted graph for the DFS and BFS solving of the maze<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/maze19Solved%20DFS.png) - DFS<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/maze19Solved%20BFS.png) - BFS<br>
# Shortest Root
For the shortest route it converts the graph to a vertices weighted undriected graph - very fast
And finds the shortest route on an algorithm that looks for the highest weighted neighbour vertex.<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/maze19Solved%20ShortestRoute.png)

DFS and BFS start to get really memory heavy after maze 200x200 but the Shortest root remains really fast
The slowest part of the program is when the image is converted into a undirected graph.
# Web mazes
Mazes i have found on the web and edited just so they can fit my rules of a maze<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/webMaze2Solved%20ShortestRoute.png)<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/webMaze3Solved%20ShortestRoute.png) - 123047ms - 2.05 min
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/webMazeSolved%20ShortestRoute.png)
