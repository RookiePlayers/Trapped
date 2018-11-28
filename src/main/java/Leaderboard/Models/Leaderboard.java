package Leaderboard.Models;

import java.io.*;
import java.util.*;

public class Leaderboard
{
	public	ArrayList<ArrayList<Object>> leaderboard;
	
	public Leaderboard()
	{
		File file1 = null;
		InputStream file=null;
		
		String elements[];
		
		leaderboard = new ArrayList<ArrayList<Object>>(); //setting up Leaderboard arraylist
		leaderboard.add(new ArrayList<Object>());
		leaderboard.add(new ArrayList<Object>());

		try
		{
			file1 = new File(this.getClass().getResource("/text/leaderboard.txt").getFile());
			//file= getClass().getClassLoader().getResourceAsStream("/text/leaderboard.txt");
			Scanner in = new Scanner(file1);
			int c;

			while(in.hasNext())
			{
				elements = (in.nextLine()).split(",");				
				leaderboard.get(0).add(elements[0]); 
				leaderboard.get(1).add(elements[1]);
			}
			in.close();
		}
		catch(FileNotFoundException anError)
		{
			System.out.print("file not found!!!ksldjfasdhfksajh");
		}
		Collections.reverse(leaderboard.get(0));
		Collections.reverse(leaderboard.get(1));
	}

	public String toString()
	{
		String leaderboardS = "";	

		for(int i=0; i<leaderboard.get(0).size();i++)
		{
			leaderboardS += leaderboard.get(0).get(i)+" "+leaderboard.get(1).get(i)+"\n";
		}
		return leaderboardS;
	}
}