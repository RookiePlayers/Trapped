package Maze;

import Interface.MazeInterface;
import Maze.MazeDifficulty.HardMaze;
import Maze.MazeDifficulty.MediumMaze;
import Maze.MazeDifficulty.SimpleMaze;
import MazeDecoration.ClassicMazeDecorator;
import MazeDecoration.ItemHunt;
import MazeDecoration.TimeChallengeMaze;

public class MazeFactory {
    private Thread mazeThread;
    private Thread styleThread;
    private  MazeInterface maze=null;

    public Thread getMazeThread() {
        return mazeThread;
    }

    public void setMazeThread(Thread mazeThread) {
        this.mazeThread = mazeThread;
    }

    public MazeInterface getMaze() {
        return maze;
    }

    public Thread getStyleThread() {
        return styleThread;
    }

    public void setStyleThread(Thread styleThread) {
        this.styleThread = styleThread;
    }

    public MazeInterface makeMaze(MAZETYPE mazeType, GAMEMODES gameMode)
    {


        switch(mazeType)
        {
            case SIMPLE: switch(gameMode){
                case CLASSIC:{
                    SimpleMaze style=new SimpleMaze();
                    ClassicMazeDecorator game=new ClassicMazeDecorator(style);
                    maze=game;
                     mazeThread=new Thread(style);
                    mazeThread.start();
                     styleThread=new Thread(game);
                    styleThread.start();


                }break;
                case TIMECHALLENGE:{
                    SimpleMaze style=new SimpleMaze();
                    TimeChallengeMaze game=new TimeChallengeMaze(style);
                    maze=game;
                    mazeThread=new Thread(style);
                    mazeThread.start();
                    styleThread=new Thread(game);
                    styleThread.start();


                }break;
                case ITEMHUNTER:{
                    SimpleMaze style=new SimpleMaze();
                    ItemHunt game=new ItemHunt(style);
                    maze=game;
                    mazeThread=new Thread(style);
                    mazeThread.start();
                    styleThread=new Thread(game);
                    styleThread.start();


                }break;
                default:break;
            }break;
            case MEDIUM: switch(gameMode){
                case CLASSIC:{
                    MediumMaze style=new MediumMaze();
                    ClassicMazeDecorator game=new ClassicMazeDecorator(style);
                    maze=game;
                    mazeThread=new Thread(style);
                    mazeThread.start();
                    styleThread=new Thread(game);
                    styleThread.start();


                }break;
                case TIMECHALLENGE:{
                    MediumMaze style=new MediumMaze();
                    TimeChallengeMaze game=new TimeChallengeMaze(style);
                    maze=game;
                    mazeThread=new Thread(style);
                    mazeThread.start();
                    styleThread=new Thread(game);
                    styleThread.start();


                }break;
                case ITEMHUNTER:{
                    MediumMaze style=new MediumMaze();
                    ItemHunt game=new ItemHunt(style);
                    maze=game;
                    mazeThread=new Thread(style);
                    mazeThread.start();
                    styleThread=new Thread(game);
                    styleThread.start();


                }break;

                default:break;

            }break;
            case HARD: switch(gameMode){
                case CLASSIC:{
                    HardMaze style=new HardMaze();
                    ClassicMazeDecorator game=new ClassicMazeDecorator(style);
                    maze=game;
                    mazeThread=new Thread(style);
                    mazeThread.start();
                    styleThread=new Thread(game);
                    styleThread.start();


                }break;
                case TIMECHALLENGE:{
                    HardMaze style=new HardMaze();
                    TimeChallengeMaze game=new TimeChallengeMaze(style);
                    maze=game;
                    mazeThread=new Thread(style);
                    mazeThread.start();
                    styleThread=new Thread(game);
                    styleThread.start();


                }break;
                case ITEMHUNTER:{
                    HardMaze style=new HardMaze();
                    ItemHunt game=new ItemHunt(style);
                    maze=game;
                    mazeThread=new Thread(style);
                    mazeThread.start();
                    styleThread=new Thread(game);
                    styleThread.start();


                }break;

                default:break;
            }break;
            default:break;
        }

        return maze;
    }
}
