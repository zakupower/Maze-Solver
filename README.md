# Rules for the maze
+ 1 px wide corridors
+ entrance should be at top of image 1 px 
+ exit should be at bottom of image 1 px
+ only 1 exit only 1 entrance
+ RGB Image - this could be easily changed to greyscale but I prefer to stay with it for now

Result image is generated by the program with blue path.


# DFS and BFS<br>
The solver uses undirected non-weighted graph for the DFS and BFS solving of the maze<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/maze15Solved%20DFS.png) - DFS<br>
<b>93x93</b><br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/maze15Solved%20BFS.png) - BFS<br>
<b>93x93</b><br>
# Shortest Route
For the shortest route it converts the graph to a vertices weighted undriected graph - very fast
And finds the shortest route on an algorithm that looks for the highest weighted neighbour vertex.<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/maze19Solved%20ShortestRoute.png)<br>
<b>179x179</b><br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/mazeBossSolved%20ShortestRoute.png)<br>
<b>400x400</b><br>
DFS and BFS start to get really memory heavy after maze 200x200 but the Shortest root remains really fast
The slowest part of the program is when the image is converted into a undirected graph.

# Algorithms illustrated
<h3>Maze Image to Nodes     ->  Nodes to Graph      ->   Graph to Weighted Graph      ->   Shortest root algorithm</h3>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/Algorithms%20illustrated.png)

# Web mazes
Mazes i have found on the web and edited just so they can fit my rules of a maze<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/webMaze2Solved%20ShortestRoute.png) <br>
<b>601x151</b> - 103831 ms - 1.73 min<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/webMaze3Solved%20ShortestRoute.png)<br>
<b>512x384</b> - 123047 ms - 2.05 min<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/webMazeSolved%20ShortestRoute.png)<br>
<b>515x488</b> - 14.09 min<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/webMaze5Solved%20ShortestRoute.png)<br> <b>700x700</b> - 66.3 min<br>
![alt tag](https://raw.githubusercontent.com/zakupower/Maze-Solver/master/mazes/finalMazeSolved%20ShortestRoute%20RED%20ROUTE.png)<br> <a href="https://en.wikipedia.org/wiki/Maze_solving_algorithm"> Wikipedia Maze Solving</a> - <b>1802x1802</b> - ~24 hours(first build) / 1.02 minutes (loaded from serialization -> only the shortest path algorithm)
