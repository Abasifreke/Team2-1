package KevinsWork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import com.itextpdf.text.DocumentException;

public class DataToArray 
{
	
	private static char[][] c;
	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<char[][]> chars = new ArrayList<char[][]>();
	private static ArrayList<Integer> partitionLength = new ArrayList<Integer>();
	public static String textFile = "Test.txt";
	public static String Title = "Default";
	public static String SubTitle = "Default";
	public static double Spacing = 8.0;
	
	private static ArrayList<char[][]> newchars = new ArrayList<char[][]>();
	
	private static int col;
	public static ArrayList<char[][]> textToArray(String source) throws DocumentException, IOException 
	{
		chars.clear();
		lines.clear();
		BufferedReader input = null;
		input = new BufferedReader (new FileReader(source));
		String line = "";	
		while(null != (line = input.readLine()))
		{	
			if (line.contains("subtitle") || line.contains("SUBTITLE")){
				SubTitle = line.substring(line.indexOf('=')+1,line.length());
			}else if (line.contains("title") || line.contains("TITLE")){
				Title = line.substring(line.indexOf('=')+1,line.length());
			}
			if (line.contains("spacing") ||line.contains("SPACING")){
				Spacing = Double.parseDouble(line.substring(line.indexOf('=')+1,line.length()));
			}
			if (line.trim().length() == 0)
			{
				continue;
			}
//			System.out.println("Title is: " + Title);
//			System.out.println("SubTitle is: " + SubTitle);
//			System.out.println("Spacing is: " + Spacing);
			if(line.charAt(0) == '|'){
				lines.add(line);		
			}
		}	
		
		/*	System.out.println(lines.size());	
			System.out.println(lines.get(0).length());
			Read the lines from the array list put them into 2d array list

			the c = new char[6][col] works.
		 */
		for(int z = 0; z < lines.size(); z = z + 6)
		{
			col = lines.get(z).length();
			c = new char[6][col];
			int temp = z;
			for (int i = 0; i<6; i++, temp++)
			{
					
				//System.out.println(temp);
				for(int j = 0; j< lines.get(temp).length(); j++)
				{
					c[i][j] = lines.get(temp).charAt(j);
					//System.out.println("i = " + i + " temp = " + temp + " j = " + j);
					//System.out.println(j);
				}					
			}
			chars.add(c);
		}	
			
		
		//Test to see printed lines
		for (int i = 0; i<lines.size(); i++)
		{
			System.out.println(lines.get(i));
		}
			
		//Test to see if characters properly placed in 2-d array.
		for (int i = 0; i<chars.size(); i++)
		{
			System.out.println(Arrays.deepToString(chars.get(i)));
		}
		
			
		System.out.println("Done. TxtToPdf successfully got the txt to an array. \n");
		input.close();
		
		//Added by Daniel McVicar, reprocesses Chars to place one bar per element.
		/*
		char[][] indchar = new char[6][chars.get(0)[0].length/2]; //Needs to be made variable length
		for (int q = 0; q < 6; q++)
		{
			indchar[q][0] = '|';
		}
		for (int a = 0; a < chars.size(); a++) //move between bars
		{
			int increment = 0;
			for (int b = 1; b < 27 chars.get(a)[0].length - 1; b ++) //Move between columns, ignore first column and fill manually to avoid double first element
			{
				for (int r = 0; r < 6; r ++) //Move between rows
				{
					char temp = chars.get(a)[r][b];
					if (temp == '|')
					{
						if (r == 0)
						{
							newchars.add(indchar);
							indchar = new char[6][chars.get(a)[0].length/2]; //make a new character array to hold everything from here
							indchar[r][b - (chars.get(a)[0].length/2)*increment] = chars.get(a)[r][b];
							increment++;
						}
						indchar[r][b] = chars.get(a)[r][b];
					}
					else
					{
					indchar[r][b] = chars.get(a)[r][b];
					}
				}
			}
		}
		for (int i = 0; i<newchars.size(); i++)
		{
			System.out.println(Arrays.deepToString(newchars.get(i)));
		}
		*/
		return chars; //Change to use newchars soon
	}
	
	public static void LengthOfPartition(){
		int length;
		String temp;
		for (int i=0; i<lines.size(); i++){
			String line = lines.get(i);
			StringTokenizer StrTkn = new StringTokenizer(line, "|");
			System.out.println("NUMBER OF TOKENS = " + StrTkn.countTokens());
			while(StrTkn.hasMoreTokens())
			{
				temp = StrTkn.nextToken();
				length = temp.length();
				partitionLength.add(length);
			}
		}	
		for (int i = 0; i<partitionLength.size(); i++)
		{
			System.out.println(partitionLength.get(i));
		}
	}
	public static int getMaxColumnAmount()
	{
		return DataToArray.col;
	}
	
	public static int getTotalRowAmount()
	{
		return DataToArray.lines.size();
	}
	
	public static int getBarAmount()
	{
		return DataToArray.lines.size()/6;
	}
	public static String getTitle(){
		return Title;
	}
	public static String getsubTitle(){
		return SubTitle;
	}
	public static double getSpacing(){
		return Spacing;
	}
	

	
	
	public static void main (String[]args) throws DocumentException, IOException{
		//textToArray();
		LengthOfPartition();
	}
}

    
   
