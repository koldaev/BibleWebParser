package in.dobro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class bibele_lv {

	/**
	 * @param args
	 * @throws IOException 
	 */
	static int[] chapters = {0,50,40,27,36,34,24,21,4,31,24,22,25,29,36,10,13,10,42,150,31,12,8,66,52,5,48,14,14,3,9,1,4,7,3,3,3,2,14,4,28,16,24,21,28,16,16,13,6,6,4,4,5,3,6,4,3,1,13,5,5,3,5,1,1,1,22};
	static String[] strline = null;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//getfrompost();
		parsefromget();
	}

	private static void parsefromget() throws IOException {
		// TODO Auto-generated method stub
		File f = new File("bible_lv1.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line;
		Integer numbible = 0;
		Integer numchapter = 0;
		FileWriter fstreamxml = new FileWriter("bible_lv2.txt");
		BufferedWriter outfstreamxml = new BufferedWriter(fstreamxml);
		while ((line = reader.readLine()) != null) {
			strline = line.split("_");
			if(strline[0].contains("Bible")) {
				numbible++;
				numchapter = 0;
			} else if(strline[0].contains("Chapter")) {
				numchapter++;
			} else {
				System.out.println(numbible + "_" + numchapter + "_" + line.replaceFirst(" ", "_"));
				outfstreamxml.write(numbible + "_" + numchapter + "_" + line.replaceFirst(" ", "_") + "\r\n");
			}
		}
	}

	private static void getfrompost() throws IOException {
		// TODO Auto-generated method stub
		FileWriter fstreamxml = new FileWriter("bible_lv1.txt");
		BufferedWriter outfstreamxml = new BufferedWriter(fstreamxml);
		
		for(int b = 1; b < 67; b++ ) {
		outfstreamxml.write("Bible_" + b+"\r\n");
		System.out.println("Bible_" + b);
		
		int biblechapters = chapters[b];
		for(int i = 1; i <=biblechapters ; i++ ) {
		outfstreamxml.write("Chapter_"+i+"\r\n");
		System.out.println("Chapter_" + i+"\r\n");
		Document doc = Jsoup.connect("http://bibele.lv/bibele/bibele.php")
				.data("book", b+"")
				.data("chapter", i+"")
				.post();
		Element verses = doc.select("table.normal span.normal").first();
				String newverses = verses.outerHtml().replace("<br />", "\r\n");
				String new1 = newverses.replaceAll("\\<.*?>","");
				String new2 = new1.trim();
				String new3 = new2.replaceAll("&quot;","");
				System.out.println(new3);
				outfstreamxml.write(new3+"\r\n");
		}
		}
		outfstreamxml.close();
	}

}
