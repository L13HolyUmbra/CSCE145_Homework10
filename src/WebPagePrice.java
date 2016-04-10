import java.io.*;
import java.net.*;

/**
 * 
 * @author Dion de Jong
 * @version December 3, 2013
 * This program will go to a certain webpage and search the 
 * HTML code in that webpage for the price of a CD.
 */

//main class
public class WebPagePrice {

	//main method
	public static void main(String[] args) {
		System.out.println("Beginning Web Page Parsing");
		//Initialize reader, stream, and urls. 
		URL page1;
		URL page2;
		InputStream uStream;
		BufferedReader br;
		
		//first web page will be parsed here
		try
		{
			//this is the link we will use. 
			page1 = new URL("http://www.barnesandnoble.com/w/ramones-ramones/5700009?cds2pid=17348&ean=81227430627&isbn=81227430627");
			//must prepare to parse it and take in the code
			uStream = page1.openStream();
			//System.out.println("URL stream = " +uStream.available());
			InputStreamReader isr = new InputStreamReader(uStream);
			br = new BufferedReader(isr);
			//create a variable that holds the value of each line.
			String urlLine = br.readLine();
			//System.out.println("Buffered reader = " +br);
			//System.out.println("Buffered reader ready = " +br.ready());
			//initialize a variable outside the do while loop
			String PriceString1; 
			//do this action, make the variable PriceString1 = a particular line of code from the web page 
			do 
			{
				PriceString1 = urlLine;
			}
			//as long as there is another line to read and the line does not contain the words that guarantee the price will be in this line
			while ((urlLine = br.readLine()) != null && !PriceString1.contains("CD Sale")); 
			//close your reader
			br.close();
			
			//take the string you saved from the do while and convert it to a character array
			char[] PriceArray = PriceString1.toCharArray();
			//variable that exists outside of for loop
			int dollar = 0; 
			//search the array for the value of the array where the character starts with a $. 
			for (int i = 0; i < PriceString1.length(); i++)
			{
				if (PriceArray[i] == '$') 
				{
					//make that value of the array equal our outside variable to use later
					dollar = i;
				}
			}
			//from the point of the dollar sign, create a substring just long enough to hold the price of the item
			String price = PriceString1.substring(dollar, dollar +5);
			//print the items price. Yay. 
			System.out.println("Barnes and Noble: " + price);
		}
		
		//catch some exceptions. 
		catch (MalformedURLException e1) 
		{
			e1.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		//repeat all comments from above as this is an exact copy. 
		try
		{
			page2 = new URL("http://www.walmart.com/ip/1805378");
			uStream = page2.openStream();
			//System.out.println("URL stream = " +uStream.available());
			InputStreamReader isr = new InputStreamReader(uStream);
			br = new BufferedReader(isr);
			String urlLine = br.readLine();
			//System.out.println("Buffered reader = " +br);
			//System.out.println("Buffered reader ready = " +br.ready());
			
			String PriceString2; 
			do 
			{
				PriceString2 = urlLine;
			}
			while ((urlLine = br.readLine()) != null && !PriceString2.contains("item_price")); 
			br.close();
			
			char[] PriceArray = PriceString2.toCharArray();
			int dollar = 0; 
			for (int i = 0; i < PriceString2.length(); i++)
			{
				if (PriceArray[i] == '$') 
				{
					dollar = i;
				}
			}
			String price = PriceString2.substring(dollar, dollar +5);
			
			
			System.out.println("Walmart: " + price);
		
		}
		catch (MalformedURLException e1) 
		{
			e1.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
}
