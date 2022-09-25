import java.io.*;
import java.util.Scanner;
public class Censor {
	public static void censor(File src, File dest, File badList )
	{
		Scanner sin=null,bin=null;
		PrintWriter pw=null;
		int badWords=0;
		try
		{
			sin=new Scanner(src);
			bin=new Scanner(badList);
			pw=new PrintWriter(dest);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Could not open files Exiting");
			return;
		}
		String bLine,sLine,bWords[],r;
		while(sin.hasNextLine()&&bin.hasNextLine())
		{
			sLine=sin.nextLine();
			bLine=bin.nextLine();
			badWords=0;
			bWords=null;
			for(int i=0;i<bLine.length();i++)
				if(bLine.charAt(i)==',')
					badWords++;
			bWords=new String[badWords];
			for(int i=0;i<badWords;i++)
			{
				bWords[i]=bLine.substring(0,bLine.indexOf(','));
				bWords[i]=bWords[i].strip();
				if(i<badWords-1)
				bLine=bLine.substring(bLine.indexOf(',')+1);
				else
					bLine="";
			}
			for(int i=0;i<badWords;i++)
			{
				r="";
				if(sLine.contains(bWords[i]))
				{
					for(int k=0;k<bWords[i].length();k++)
						r+="*";
					sLine=sLine.replaceAll(bWords[i], r);
				}
			}
			
			pw.println(sLine);
		}
		while(sin.hasNextLine())
		{
			sLine=sin.nextLine();
			pw.println(sLine);
		}
		sin.close();
		bin.close();
		pw.close();
	}
	
}
