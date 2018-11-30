package Leaderboard.Models;

import Interface.LeaderboardInterface;

import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Leaderboard implements LeaderboardInterface
{
	private	ArrayList<ArrayList<String>> leaderboard= new ArrayList<ArrayList<String>>(); //setting up Leaderboard arraylist
	;

	public ArrayList<ArrayList<String>> getLeaderboard() {
		return leaderboard;
	}

	public void setLeaderboard(ArrayList<ArrayList<String>> leaderboard) {
		this.leaderboard = leaderboard;
	}

	public void saveToLeaderBoard(String file, String name, String value)
	{
		FileWriter fileWriter= null;
		try {

			fileWriter = new FileWriter(new File(this.getClass().getResource("/text/"+file).getFile()),true);
			fileWriter.write(name+","+value+","+ LocalDateTime.now());
			fileWriter.close();
			System.out.println("Written");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public Leaderboard(String url)
	{
		loadLeaderboard(url);
	}
	public ArrayList<ArrayList<String>> loadLeaderboard(String url)
	{
		leaderboard.clear();
		File file1 = null;
		InputStream file=null;

		String elements[];
		leaderboard = new ArrayList<ArrayList<String>>(); //setting up Leaderboard arraylist
		leaderboard.add(new ArrayList<String>());
		leaderboard.add(new ArrayList<String>());
		leaderboard.add(new ArrayList<String>());

		try
		{
			file1 = new File(this.getClass().getResource("/text/"+url).getFile());
			//file= getClass().getClassLoader().getResourceAsStream("/text/leaderboard.txt");
			Scanner in = new Scanner(file1);
			int c;

			while(in.hasNext())
			{
				elements = (in.nextLine()).split(",");
				leaderboard.get(0).add(elements[0]);
				leaderboard.get(1).add(elements[1]);
				leaderboard.get(2).add(elements[2]);
			}
			in.close();
		}
		catch(FileNotFoundException anError)
		{
			System.out.print("file not found!!!ksldjfasdhfksajh");
		}
		leaderboard=sort(leaderboard);
		return leaderboard;
	}

	public String toString()
	{
		String leaderboardS = "";

		for(int i=0; i<leaderboard.get(0).size();i++)
		{
			leaderboardS +="Name: "+leaderboard.get(0).get(i)+" Value: "+leaderboard.get(1).get(i)+"Date: "+leaderboard.get(2).get(i)+"\n";
		}
		return leaderboardS;
	}

	@Override
	public ArrayList<ArrayList<String>> sort( ArrayList<ArrayList<String>> list) {



		ArrayList<ArrayList<String>> temp=new ArrayList<ArrayList<String>>();
		temp.add(new ArrayList<String>());
		temp.add(new ArrayList<String>());
		temp.add(new ArrayList<String>());


		temp.get(0).add("");
		temp.get(1).add("");
		temp.get(2).add("");

		for(int i=0; i<list.get(0).size();i++)
		{

			for(int j=i+1; j<list.get(0).size(); j++)
			{
				if(list.get(1).get(j).compareToIgnoreCase(list.get(1).get(i)) < 0)
				{

					//System.out.println(temp);
					temp.get(0).set(0, list.get(0).get(j));
					temp.get(1).set(0,list.get(1).get(j));
					temp.get(2).set(0,list.get(2).get(j));



					list.get(0).set(j, list.get(0).get(i));
					list.get(1).set(j,list.get(1).get(i));
					list.get(2).set(j,list.get(2).get(i));

					//System.out.println(list);
					list.get(0).set(i, temp.get(0).get(0));//System.out.println("i'm Putting in temp "+ temp.get(0).get(i)+ " @ " +j);
					list.get(1).set(i,temp.get(1).get(0));//System.out.println("i'm Putting in temp "+ temp.get(1).get(i)+ " @ " +j);
					list.get(2).set(j,list.get(2).get(i));

				}
			}
		}return list;
	}
}