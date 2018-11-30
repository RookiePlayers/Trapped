package Maze;

import Interface.MazeInterface;

public class MazeFactory {
    public MazeInterface makeMaze(MAZETYPE mazeType,GAMEMODES gameMode)
    {

        MazeInterface maze=null;
        switch(mazeType)
        {
            case SIMPLE: switch(gameMode){
                case CLASSIC:{
                    SimpleMaze style=new SimpleMaze();
                    ClassicMazeDecorator game=new ClassicMazeDecorator(style);
                    maze=game;
                    Thread mazeThread=new Thread(style);
                    mazeThread.start();
                    Thread mazeThread2=new Thread(game);
                    mazeThread2.start();

                }break;
                default:break;
            }break;
            case MEDIUM: switch(gameMode){
                case CLASSIC: {
                    SimpleMaze style=new SimpleMaze();
                    ClassicMazeDecorator game=new ClassicMazeDecorator(style);
                    maze=game;
                    Thread mazeThread=new Thread(style);
                    mazeThread.start();
                    Thread mazeThread2=new Thread(game);
                    mazeThread2.start();

                }break;
                default:break;
            }break;
            case HARD: switch(gameMode){
                case CLASSIC: {
                        SimpleMaze style=new SimpleMaze();
                    ClassicMazeDecorator game=new ClassicMazeDecorator(style);
                    maze=game;
                    Thread mazeThread=new Thread(style);
                    mazeThread.start();
                    Thread mazeThread2=new Thread(game);
                    mazeThread2.start();

                }break;
                default:break;
            }break;
        }

        return maze;
    }
}
