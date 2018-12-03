package Leaderboard.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class LeaderBoardOptions extends Stage {
    public LeaderBoardOptions()
    {
        setScene(leaderboardScene());
        initModality(Modality.APPLICATION_MODAL);
        setTitle("LeaderBoards");


    }
    public Scene leaderboardScene()
    {
        BorderPane borderPane=new BorderPane();
        VBox box=new VBox();
        box.setPadding(new Insets(20));
        box.setSpacing(15);
        Label title=new Label("LEADERBARDS");
        Button ihl=new Button("Item Hunters (LB)");
        Button clb=new Button("\"Classic\" Leaderboard");
        Button tclb=new Button("Time Challengers (LB)");
        box.getChildren().addAll(title,ihl,clb,tclb);
        box.setAlignment(Pos.CENTER);
        borderPane.setCenter(box);

        ihl.setOnAction(e->{
            LeaderboardGUI gui=new LeaderboardGUI("ITEM HUNTERS LEADERBOARD","item_hunt_leaderboard.txt",0);

            gui.showAndWait();
        });
        clb.setOnAction(e->{
            LeaderboardGUI gui=new LeaderboardGUI("CLASSIC LEADERBOARD","classic.txt");

            gui.showAndWait();
        });
        tclb.setOnAction(e->{
            LeaderboardGUI gui=new LeaderboardGUI("TIME CHALLENGE LEADERBOARD","tc_leaderboard.txt");

            gui.showAndWait();
        });

        Scene sc=new Scene(borderPane,800,800);
        return sc;

    }


}
