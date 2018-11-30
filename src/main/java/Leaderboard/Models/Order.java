package Leaderboard.Models;

import java.util.*;
import java.io.*;
public class Order
{
    private ArrayList<ArrayList<String>>list;
    private ArrayList<ArrayList<Integer>>intList;
    private ArrayList<ArrayList<Character>>CharacterList;
    private ArrayList<ArrayList<String>>returnStringList;
    private ArrayList<ArrayList<Integer>>returnIntList;
    private ArrayList<ArrayList<Character>>returnCharacterList;
    public Order(ArrayList<ArrayList<String>> list)
    {
        this.list=list;

    }

    public Order(ArrayList<ArrayList<Integer>> intList,int intOrder)
    {
        this.intList=intList;

    }
    public Order(ArrayList<ArrayList<Character>> CharacterList,char charOrder)
    {
        this.CharacterList=CharacterList;

    }

    public ArrayList<ArrayList<String>> ascendingOrder(String Compareby)
    {
        Compareby=Compareby.toLowerCase();
        int sortindex=0;
        if(Compareby.equalsIgnoreCase("fn")||Compareby.equalsIgnoreCase("firstName")||Compareby.equalsIgnoreCase("first_name")||Compareby.equalsIgnoreCase("f")||Compareby.equalsIgnoreCase("name")||Compareby.equalsIgnoreCase("n"))
            sortindex=0;
        else if(Compareby.equalsIgnoreCase("sn")||Compareby.equalsIgnoreCase("secondName")||Compareby.equalsIgnoreCase("second_name")||Compareby.equalsIgnoreCase("s"))
            sortindex=1;
        else if(Compareby.equalsIgnoreCase("n")||Compareby.equalsIgnoreCase("no")||Compareby.equalsIgnoreCase("num")||Compareby.equalsIgnoreCase("number"))
            sortindex=2;
        else sortindex=sortindex;
        System.out.println(Compareby+","+sortindex+"\n\n");
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
                System.out.println(j+")"+list.get(sortindex).get(i).compareToIgnoreCase(list.get(sortindex).get(j))+"?< 0 ("+list.get(sortindex).get(i)+" is being compared to "+ list.get(sortindex).get(j)+")");
                if(list.get(sortindex).get(j).compareToIgnoreCase(list.get(sortindex).get(i)) < 0)
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
                    list.get(2).set(i,temp.get(2).get(0));//System.out.println("i'm Putting in temp "+ temp.get(2).get(i)+ " @ " +j);

                }
            }
        }return list;

    }
    public  ArrayList<ArrayList<String>> descOrder(String Compareby)
    {
        Compareby=Compareby.toLowerCase();
        int sortindex=0;
        if(Compareby.equalsIgnoreCase("fn")||Compareby.equalsIgnoreCase("firstName")||Compareby.equalsIgnoreCase("first_name")||Compareby.equalsIgnoreCase("f"))
            sortindex=0;
        else if(Compareby.equalsIgnoreCase("sn")||Compareby.equalsIgnoreCase("secondName")||Compareby.equalsIgnoreCase("second_name")||Compareby.equalsIgnoreCase("s"))
            sortindex=1;
        else if(Compareby.equalsIgnoreCase("n")||Compareby.equalsIgnoreCase("no")||Compareby.equalsIgnoreCase("num")||Compareby.equalsIgnoreCase("number"))
            sortindex=2;
        else sortindex=sortindex;
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
                System.out.println(j+")"+list.get(sortindex).get(i).compareToIgnoreCase(list.get(sortindex).get(j))+"?> 0 ("+list.get(sortindex).get(i)+" is being compared to "+ list.get(sortindex).get(j)+")");
                if(list.get(sortindex).get(j).compareToIgnoreCase(list.get(sortindex).get(i)) > 0)
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
                    list.get(2).set(i,temp.get(2).get(0));//System.out.println("i'm Putting in temp "+ temp.get(2).get(i)+ " @ " +j);

                }
            }
        }return list;
    }

    public ArrayList<ArrayList<Integer>> i_ascendingOrder()
    {


        ArrayList<ArrayList<Integer>> temp=new ArrayList<ArrayList<Integer>>();
        temp.add(new ArrayList<Integer>());
        temp.add(new ArrayList<Integer>());
        temp.add(new ArrayList<Integer>());

        temp.get(0).add(0);
        temp.get(1).add(0);
        temp.get(2).add(0);
        for(int i=0; i<intList.get(0).size();i++)
        {

            for(int j=i+1; j<intList.get(0).size(); j++)
            {
                //System.out.println(j+")"+intList.get(0).get(i).compareToIgnoreCase(intList.get(0).get(j))+"?< 0 ("+intList.get(0).get(i)+" is being compared to "+ intList.get(0).get(j)+")");
                if(intList.get(0).get(j)<intList.get(0).get(i))
                {

                    //System.out.println(temp);
                    temp.get(0).set(0, intList.get(0).get(j));
                    temp.get(1).set(0,intList.get(1).get(j));
                    temp.get(2).set(0,intList.get(2).get(j));


                    intList.get(0).set(j, intList.get(0).get(i));
                    intList.get(1).set(j,intList.get(1).get(i));
                    intList.get(2).set(j,intList.get(2).get(i));
                    //System.out.println(intList);
                    intList.get(0).set(i, temp.get(0).get(0));//System.out.println("i'm Putting in temp "+ temp.get(0).get(i)+ " @ " +j);
                    intList.get(1).set(i,temp.get(1).get(0));//System.out.println("i'm Putting in temp "+ temp.get(1).get(i)+ " @ " +j);
                    intList.get(2).set(i,temp.get(2).get(0));//System.out.println("i'm Putting in temp "+ temp.get(2).get(i)+ " @ " +j);

                }
            }
        }return intList;

    }

    public  ArrayList<ArrayList<Integer>> i_descOrder()
    {
        ArrayList<ArrayList<Integer>> temp=new ArrayList<ArrayList<Integer>>();
        temp.add(new ArrayList<Integer>());
        temp.add(new ArrayList<Integer>());
        temp.add(new ArrayList<Integer>());

        temp.get(0).add(0);
        temp.get(1).add(0);
        temp.get(2).add(0);
        for(int i=0; i<intList.get(0).size();i++)
        {

            for(int j=i+1; j<intList.get(0).size(); j++)
            {
                //System.out.println(j+")"+intList.get(0).get(i).compareToIgnoreCase(intList.get(0).get(j))+"?> 0 ("+intList.get(0).get(i)+" is being compared to "+ intList.get(0).get(j)+")");
                if(intList.get(0).get(j)>intList.get(0).get(i))
                {

                    //System.out.println(temp);
                    temp.get(0).set(0, intList.get(0).get(j));
                    temp.get(1).set(0,intList.get(1).get(j));
                    temp.get(2).set(0,intList.get(2).get(j));


                    intList.get(0).set(j, intList.get(0).get(i));
                    intList.get(1).set(j,intList.get(1).get(i));
                    intList.get(2).set(j,intList.get(2).get(i));
                    //System.out.println(intList);
                    intList.get(0).set(i, temp.get(0).get(0));//System.out.println("i'm Putting in temp "+ temp.get(0).get(i)+ " @ " +j);
                    intList.get(1).set(i,temp.get(1).get(0));//System.out.println("i'm Putting in temp "+ temp.get(1).get(i)+ " @ " +j);
                    intList.get(2).set(i,temp.get(2).get(0));//System.out.println("i'm Putting in temp "+ temp.get(2).get(i)+ " @ " +j);

                }
            }
        }return intList;
    }

    public ArrayList<ArrayList<Character>> c_ascendingOrder()
    {


        ArrayList<ArrayList<Character>> temp=new ArrayList<ArrayList<Character>>();
        temp.add(new ArrayList<Character>());
        temp.add(new ArrayList<Character>());
        temp.add(new ArrayList<Character>());

        temp.get(0).add(null);
        temp.get(1).add(null);
        temp.get(2).add(null);
        for(int i=0; i<CharacterList.get(0).size();i++)
        {

            for(int j=i+1; j<CharacterList.get(0).size(); j++)
            {
                //System.out.println(j+")"+CharacterList.get(0).get(i)+"?<" =CharacterList.get(0).get(j)+" ("+CharacterList.get(0).get(i)+" is being compared to "+ CharacterList.get(0).get(j)+")");
                if(CharacterList.get(0).get(j)<CharacterList.get(0).get(i))
                {

                    //System.out.println(temp);
                    temp.get(0).set(0, CharacterList.get(0).get(j));
                    temp.get(1).set(0,CharacterList.get(1).get(j));
                    temp.get(2).set(0,CharacterList.get(2).get(j));


                    CharacterList.get(0).set(j, CharacterList.get(0).get(i));
                    CharacterList.get(1).set(j,CharacterList.get(1).get(i));
                    CharacterList.get(2).set(j,CharacterList.get(2).get(i));
                    //System.out.println(CharacterList);
                    CharacterList.get(0).set(i, temp.get(0).get(0));//System.out.println("i'm Putting in temp "+ temp.get(0).get(i)+ " @ " +j);
                    CharacterList.get(1).set(i,temp.get(1).get(0));//System.out.println("i'm Putting in temp "+ temp.get(1).get(i)+ " @ " +j);
                    CharacterList.get(2).set(i,temp.get(2).get(0));//System.out.println("i'm Putting in temp "+ temp.get(2).get(i)+ " @ " +j);

                }
            }
        }return CharacterList;

    }

    public  ArrayList<ArrayList<Character>> c_descOrder()
    {
        ArrayList<ArrayList<Character>> temp=new ArrayList<ArrayList<Character>>();
        temp.add(new ArrayList<Character>());
        temp.add(new ArrayList<Character>());
        temp.add(new ArrayList<Character>());

        temp.get(0).add(null);
        temp.get(1).add(null);
        temp.get(2).add(null);
        for(int i=0; i<CharacterList.get(0).size();i++)
        {

            for(int j=i+1; j<CharacterList.get(0).size(); j++)
            {
                //System.out.println(j+")"+CharacterList.get(0).get(i)+"?>" =CharacterList.get(0).get(j)+" ("+CharacterList.get(0).get(i)+" is being compared to "+ CharacterList.get(0).get(j)+")");
                if(CharacterList.get(0).get(j)>CharacterList.get(0).get(i))
                {

                    //System.out.println(temp);
                    temp.get(0).set(0, CharacterList.get(0).get(j));
                    temp.get(1).set(0,CharacterList.get(1).get(j));
                    temp.get(2).set(0,CharacterList.get(2).get(j));


                    CharacterList.get(0).set(j, CharacterList.get(0).get(i));
                    CharacterList.get(1).set(j,CharacterList.get(1).get(i));
                    CharacterList.get(2).set(j,CharacterList.get(2).get(i));
                    //System.out.println(CharacterList);
                    CharacterList.get(0).set(i, temp.get(0).get(0));//System.out.println("i'm Putting in temp "+ temp.get(0).get(i)+ " @ " +j);
                    CharacterList.get(1).set(i,temp.get(1).get(0));//System.out.println("i'm Putting in temp "+ temp.get(1).get(i)+ " @ " +j);
                    CharacterList.get(2).set(i,temp.get(2).get(0));//System.out.println("i'm Putting in temp "+ temp.get(2).get(i)+ " @ " +j);

                }
            }
        }return CharacterList;
    }
	/*public static void main(String[] args)throws IOException{
		ArrayList<ArrayList<Character>>tests=new ArrayList<ArrayList<Character>>();
		/*
		tests.add(new ArrayList<Character>());
		tests.add(new ArrayList<Character>());
		tests.add(new ArrayList<Character>());
		for (int i=0;i<tests.size();i++ ) 
		{
			char randomChar=(char)(Math.random()*('Z')+'A');
			tests.get(0).add(randomChar);
			tests.get(1).add(randomChar);
			tests.get(2).add(randomChar);
		}
		Order ord=new Order(tests,'A');
		System.out.println("Before sorting..\n"+tests+"\n After sort \n"+ord.c_ascendingOrder());
		Phonebook pb=new Phonebook();
	pb.getPhonebook();
		Order o=new Order(pb.getPhonebook());
		//System.out.println(o.ascendingOrder("n"));
	}
	/*public ArrayList<ArrayList<String>> decending()pb.getPhonebook()
	{
		Comparator comparator = Collections.reverseOrder();
		 Collections.sort(list, comparator
		 	@Override
        public int compare(ArrayList<String> o1, ArrayList<String> o2) {
            return o1.get(0).compareTo(o2.get(0));
        }               
		});
		return list;);
    

	}*/

}