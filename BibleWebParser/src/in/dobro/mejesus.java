package in.dobro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class mejesus {
	
static int[] chapters = {0,50,40,27,36,34,24,21,4,31,24,22,25,29,36,10,13,10,42,150,31,12,8,66,52,5,48,14,14,3,9,1,4,7,3,3,3,2,14,4,28,16,24,21,28,16,16,13,6,6,4,4,5,3,6,4,3,1,13,5,5,3,5,1,1,1,22};	
static String[] strline = null;	
static String lang = "id";

	public static void main(String[] args) throws IOException {
		
		File f = new File("myjesus_" + lang + ".txt");
		if (!f.exists()) f.delete();
		
		//первый этап - парсинг стихов с сайта
		parsing();
		//второй этап - обработка сгенерированного текста
		processing();
	}


	private static void processing() throws IOException {
		File f = new File("myjesus_" + lang + ".txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line;
		Integer numbible = 0;
		Integer numchapter = 0;
		FileWriter fstreamxml = new FileWriter("myjesus_" + lang + "_final.txt");
		BufferedWriter outfstreamxml = new BufferedWriter(fstreamxml);
		while ((line = reader.readLine()) != null) {
			strline = line.split("_");
			if(strline[0].contains("Bible")) {
				numbible++;
				numchapter = 0;
			} else if(strline[0].contains("Chapter")) {
				numchapter++;
			} else {
				System.out.println(numbible + "_" + numchapter + "_" + line);
				outfstreamxml.write(numbible + "_" + numchapter + "_" + line + "\r\n");
			}
		}
	}

	private static void parsing() throws IOException {
		FileWriter fstreamxml = new FileWriter("myjesus_" + lang + ".txt");
		BufferedWriter outfstreamxml = new BufferedWriter(fstreamxml);
		
		String numbible;
		
		String csverse;
		
		for(int b = 1; b < 67; b++ ) {
		/*
			if (b < 10) {
				numbible = "0" + b;
			} else {
				numbible = b+"";
			}	
		*/	
		System.out.println("Bible_" + b + "\r\n");	
		outfstreamxml.write("Bible_" + b + "\r\n");
			
		int biblechapters = chapters[b];	
			
		for(int i = 1; i <=biblechapters ; i++ ) {
		//for(int i = 1; i <=2 ; i++ ) {
			
		/*	
		Document doc = Jsoup.connect("http://www.wordproject.org/bibles/ur/"+numbible+"/" + i + ".htm").get();
		doc.outputSettings().prettyPrint(false);
		Elements verses = doc.select("#textBody p");
		String newverses = verses.toString();
		String new2 = newverses.replace("<br />", "");
		String new3 = new2.replaceAll("\\<.*?>","");
		String new4 = new3.replace("  "," ").replace("  "," ");
		String new5 = new4.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
		*/
			
		Document doc = Jsoup.connect("http://www.mejesus.com/en/bible/normal/chapter/"+b+"/" + i + "/" + lang).get();	
		Elements verses = doc.select("div #row");
		String newverses = verses.toString();
		String new2 = newverses.replaceAll("\\<.*?>","");
		String new3 = new2.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
		
		//String csverse = new5.replaceFirst("[0-9]+\\ ", b + "_");
		String new4 = new3.replace("comment (0)", "");
		String new5 = new4.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
		
		csverse = new5;
		
		//if(!csverse.contains("comment")) {
			System.out.println(csverse);
		//}
		
		outfstreamxml.write("Chapter_" + i + "\r\n");
		outfstreamxml.write(csverse);
		
		}
		
		
		}
		
		outfstreamxml.close();
	}

}
