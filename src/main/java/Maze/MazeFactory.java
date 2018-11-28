package Maze;

public class MazeFactory {
    public Maze makeMaze(MAZETYPE mazeType)
    {

        Maze maze=null;
        switch(mazeType)
        {
            case SIMPLE: maze=new SimpleMaze();break;
            case MEDIUM: maze=new MediumMaze();break;
            case HARD: maze=new HardMaze();break;
        }
        return maze;
    }
}
