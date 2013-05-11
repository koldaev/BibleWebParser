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
import org.jsoup.select.Elements;

public class armenianchurchlibrary_com {
	
	static String[] strline = null;		

	public static String books[] = {
		"",
		"Genesis.html",
		"Exod.html",
		"Levit.html",
		"Numbers.html",
		"2Law.html",
		"Joshua.html",
		"Judes.html",
		"Ruth.html",
		"Kings1.html",
		"Kings2.html",
		"Kings3.html",
		"Kings4.html",
		"Mnac1.html",
		"Mnac2.html",
		"Ezras1.html",
		"Neemi.html",
		"Ester.html",
		"Job.html",
		"Psalms.html",
		"Proverbs.html",
		"Eccel.html",
		"Songs.html",
		"Esai.html",
		"Eremia.html",
		"voxb.html",
		"Ezekel.html",
		"Daniel.html",
		"Osse.html",
		"Hovel.html",
		"Amos.html",
		"Abdiu.html",
		"Hovnan.html",
		"Micia.html",
		"Naum.html",
		"Ambakum.html",
		"Soponia.html",
		"Ange.html",
		"Zaqaria.html",
		"Maxaqia.html",
		"Mattew.html",
		"Mark.html",
		"Luke.html",
		"John.html",
		"Acts.html",
		"Rome.html",
		"Kor1.html",
		"Kor2.html",
		"Galat.html",
		"Epes.html",
		"Pilip.html",
		"Koxos.html",
		"Tes1.html",
		"Tes2.html",
		"Tim1.html",
		"Tim2.html",
		"Titos.html",
		"Pilimon.html",
		"Ebra.html",
		"Jakob.html",
		"Petros1.html",
		"Petros2.html",
		"John1.html",
		"John2.html",
		"John3.html",
		"Juda.html",
		"Rev.html"
	};

	public static void main(String[] args) throws IOException {
		File f = new File("armenianchurchlibrary_com_2.txt");
		if (!f.exists()) f.delete();
		//parser1();
		parser2();
	}

	private static void parser2() throws IOException {
		File f = new File("armenianchurchlibrary_com_1.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line;
		Integer numbible = 0;
		Integer numchapter = 0;
		FileWriter fstreamxml = new FileWriter("armenianchurchlibrary_com_2.txt");
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

	private static void parser1() throws IOException {
		FileWriter fstreamxml = new FileWriter("armenianchurchlibrary_com_1.txt");
		BufferedWriter outfstreamxml = new BufferedWriter(fstreamxml);

		for(int b = 1; b < 67; b++ ) {
			
		outfstreamxml.write("Bible_" + b + "\r\n");
		
		System.out.println("Bible_" + b + "\r\n");

		String url = "http://www.armenianchurchlibrary.com/files/easternarmenianbible/" + books[b];

		Document doc = Jsoup.connect(url).get();	
				
		Elements verses = doc.select("div");
		
		verses.select("h1").remove();
		verses.select("br").remove();

			String newverses = verses.toString();

			String s1 = newverses.replace("<div id=\"main\"> ", "");
			String s2 = s1.replace(" <hr />", "");
			String s3 = s2.replace("</div>", "");
			String new1 = s3.replace("<strong>", "Chapter_");
			String new2 = new1.replace("</strong>", "\r\n1_");
			String new3 = new2.replaceAll("<sup>","\r\n");
			String new4 = new3.replace("</sup>", "_");
			String new5 = new4.replace("&laquo;", "");
			String new6 = new5.replace("&raquo;", "");
			String new7 = new6.replace("_ ", "_");
			String new8 = new7.replace(" Chapter","Chapter");
			String new9 = new8.replaceAll("(?m)^\\s*$[\n\r]{1,}", "");
			
			System.out.println(new9);
			
			
			outfstreamxml.write(new9);

		}
		
		outfstreamxml.close();
	}
	}

