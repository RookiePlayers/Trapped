package Leaderboard.UI;

//import Leaderboard.Models.Leaderboard;
import Leaderboard.Models.Leaderboard;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text;

public class LeaderboardGUI extends Stage
{    

    public LeaderboardGUI()
    {
        setScene(leaderboardScene());
        initModality(Modality.APPLICATION_MODAL);
        setTitle("High Score");

    }

    public Scene leaderboardScene()
    {
        Group root = new Group(getLeaderboard()); 
        Scene scene = new Scene(root, 400, 400); 
        return scene;
    }

    public Text getLeaderboard()
    {
		Leaderboard a = new Leaderboard();
		
		Text text = new Text(); 
       
    	text.setFont(Font.font("comic sans", FontWeight.BOLD, FontPosture.REGULAR, 35)); 
       
    	text.setX(50); 
    	text.setY(50);     
       
    	text.setFill(Color.BLUE); 
       
    	text.setStrokeWidth(2); 
      

    	text.setStroke(Color.PINK);        
      
    	text.setText(a.toString()); 
           
    	//Group root = new Group(text);   
               
    	//Scene scene = new Scene(root, 400, 400);  
      
    	 return text;
	}
}