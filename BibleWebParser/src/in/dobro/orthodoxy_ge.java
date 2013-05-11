package in.dobro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class orthodoxy_ge {

	public static String books[] = {
		"",
		"dabadeba",
		"gamosvla",
		"levianni",
		"ritskhvni",
		"2rjuli",
		"iesonave",
		"msajulni",
		"ruti",
		"1mepeta",
		"2mepeta",
		"3mepeta",
		"4mepeta",
		"1neshtta",
		"2neshtta",
		"1ezra",
		"neemia",
		"esteri",
		"iobi",
		"fsalmunni",
		"igavni",
		"eklesiaste",
		"qeba",
		"esaia",
		"ieremia",
		"godeba",
		"ezekieli",
		"danieli",
		"osia",
		"ioveli",
		"amos",
		"abdia",
		"iona",
		"miqa",
		"naumi",
		"abakumi",
		"sofonia",
		"angia",
		"zaqaria",
		"malaqia",
		"mate",
		"markozi",
		"luka",
		"ioane",
		"saqme",
		"iakobi",
		"1petre",
		"2petre",
		"1ioane",
		"2ioane",
		"3ioane",
		"iuda",
		"romaelta",
		"1korintelta",
		"2korintelta",
		"galatelta",
		"efeselta",
		"filipelta",
		"kolaselta",
		"1tesalonikelta",
		"2tesalonikelta",
		"1timote",
		"2timote",
		"tite",
		"filemoni",
		"ebraelta",
		"apokalips"
	};
	
	static int[] chapters = {0,50,40,27,36,34,24,21,4,31,24,22,25,29,36,10,13,10,42,151,31,12,8,66,52,5,48,14,14,3,9,1,4,7,3,3,3,2,14,3,28,16,24,21,28,5,5,3,5,1,1,1,16,16,13,6,6,4,4,5,3,6,4,3,1,13,22};

	public static void main(String[] args) throws IOException {
		File f = new File("orthodoxy_ge67.txt");
		if (!f.exists()) f.delete();
		parser();
	}

	private static void parser() throws IOException {
		FileWriter fstreamxml = new FileWriter("orthodoxy_ge67.txt");
		BufferedWriter outfstreamxml = new BufferedWriter(fstreamxml);

		for(int b = 1; b < 67; b++ ) {

		//outfstreamxml.write("Bible_" + b + "\r\n");
			
		int biblechapters = chapters[b];	

		for(int i = 1; i <=biblechapters ; i++ ) {
		//for(int i = 1; i <=2 ; i++ ) {	
			
		String url = "";	
			
		if(b < 40) {
			url = "http://www.orthodoxy.ge/tserili/biblia/" + books[b] + "/" + books[b] + "-" + i + ".htm";
		} else {
			if(biblechapters != 1) {
				url = "http://www.orthodoxy.ge/tserili/akhali_agtqma/" + books[b] + "-" + i + ".htm";
			} else {
				url = "http://www.orthodoxy.ge/tserili/akhali_agtqma/" + books[b] + ".htm";
			}
		}
			
		Document doc = Jsoup.connect(url).get();	
				
		Elements verses = doc.select("p");
		
		
		for (Element element : verses) {
			
			String newverses = element.outerHtml();
			
			if(!element.ownText().isEmpty()) {
			
			String new1 = b + "_" + i + "_" + newverses;
			String new2 = new1.replaceFirst("\\. ", "_");
			String new3 = new2.replaceAll("\\<.*?>","");
			String new5 = new3.replace("&laquo;", "");
			String new7 = new5.replace("&raquo;", "");
			String new8 = new7.replaceFirst(" _","_");
			String new9= new8.replaceFirst("_ ","_");
			
			System.out.println(new9);
			
			
			outfstreamxml.write(new9 + "\n");
			
			}
		}
		

		}
		
		
		}
		
		outfstreamxml.close();
	}
	}

