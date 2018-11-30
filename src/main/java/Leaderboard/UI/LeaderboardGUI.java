package Leaderboard.UI;

//import Leaderboard.Models.Leaderboard;
import Leaderboard.Models.Leaderboard;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Font; 
import javafx.scene.text.FontPosture; 
import javafx.scene.text.FontWeight; 
import javafx.scene.text.Text;

import java.util.ArrayList;

public class LeaderboardGUI extends Stage
{

    private String filename="leaderboard.txt";
    private String title="High Scores";
    private TableView<Leaders> table = new TableView<Leaders>();
    private final ObservableList<Leaders> data =
            FXCollections.observableArrayList(  );
    public LeaderboardGUI()
    {
        setScene(leaderboardScene());
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);


    }
    public LeaderboardGUI(String title,String filename)
    {
        this.filename=filename;
        this.title=title;
        setScene(leaderboardScene());
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);


    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public String gettitle() {
        return title;
    }


    public void settitle(String title) {
        this.title = title;
    }

    public Scene leaderboardScene()
    {
        Group root = new Group(getLeaderboard()); 
        Scene scene = new Scene(root, 400, 400,Color.DARKGRAY);
        Leaderboard a = new Leaderboard(filename);



        ArrayList<ArrayList<String>>list=a.loadLeaderboard(filename);
        for(int i=0;i<list.get(0).size();i++)
        {
            data.add(new Leaders(list.get(0).get(i),list.get(1).get(i),list.get(2).get(i)));

        }

        final Label label = new Label(title);
        label.setFont(new Font("Arial", 20));

        table.setEditable(false);


        TableColumn firstNameCol = new TableColumn("Username");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Leaders, String>("name"));

        TableColumn lastNameCol = new TableColumn("Time/points");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Leaders, String>("val"));

        TableColumn emailCol = new TableColumn("Date");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Leaders, String>("date"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);


        return scene;
    }

    public Text getLeaderboard()
    {


        Text text = new Text();
       
    	text.setFont(Font.font("comic sans", FontWeight.BOLD, FontPosture.REGULAR, 35)); 
       
    	text.setX(50); 
    	text.setY(50);     
       
    	text.setFill(Color.BLUE); 
       
    	text.setStrokeWidth(2); 
      

    	text.setStroke(Color.GREEN);
      

    	//Group root = new Group(text);   
               
    	//Scene scene = new Scene(root, 400, 400);  
      
    	 return text;
	}
	public static  class Leaders{
        private final SimpleStringProperty name;
        private  final SimpleStringProperty val;
        private final SimpleStringProperty date;

        public Leaders(String fname, String value, String adate) {
            this.name = new SimpleStringProperty(fname);
            this.val = new SimpleStringProperty(value);
            this.date = new SimpleStringProperty(adate);
        }
        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        public String getVal() {
            return val.get();
        }

        public void setValue(String fName) {
            val.set(fName);
        }

        public String getDate() {
            return date.get();
        }

        public void setDate(String fName) {
            date.set(fName);
        }
    }

}
