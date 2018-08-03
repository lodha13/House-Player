import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WordInSentance 
{

	List<String> wordList = new ArrayList<>();
	static Map<Integer, String> sentanceMap = new HashMap<>();

	static List<String> ignoreWord = new ArrayList<>();
	public static void populateIgnoreWord()
	{
		ignoreWord.add(".i.e");
	}
	public static void main(String args[]) throws IOException
	{	
		String fileStr = Files.readAllLines(Paths.get("D:\\test.txt")).stream().collect(Collectors.joining(" "));

		int i=1;
		boolean endOfSentance = false;
		String sentance="";
		for(String word:fileStr.split(" "))
		{
			
			if(!ignoreWord.contains(word) && (word.endsWith(".") || word.endsWith("?")))
			{
				word = word.substring(0, word.length()-1);
				sentance+=word+" ";
				endOfSentance=false;
				sentanceMap.put(i, sentance);
				i++;
				sentance = new String();
			}
			else
			{
				sentance+=word+" ";
			}
		}
		
		findOccurence(fileStr);
	}
	
	private static void findOccurence(String fileStr) 
	{

		fileStr = sentanceMap.values().stream().collect(Collectors.joining(" "));
		for(String word:fileStr.split(" "))
		{
			if(word.equals(""))
				continue;
			System.out.print(word+" present in: ");
			for(Integer key:sentanceMap.keySet())
			{
				if(sentanceMap.get(key).contains(word+" ") || sentanceMap.get(key).contains(word+".") || sentanceMap.get(key).contains(word+"?"))
				{
					System.out.print(key+" ");
				}
			}
			System.out.println("\n");
		}
	}
}
