package in.dobro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class obohu_cz {
	
	public static String lang = "EST";

	public static String books[] = {
		"",
		"Gn",
		"Ex",
		"Lv",
		"Nu",
		"Dt",
		"Joz",
		"Sd",
		"Rt",
		"1S",
		"2S",
		"1Kr",
		"2Kr",
		"1Pa",
		"2Pa",
		"Ezd",
		"Neh",
		"Est",
		"Jb",
		"Z",
		"Pr",
		"Kaz",
		"Pis",
		"Iz",
		"Jr",
		"Pl",
		"Ez",
		"Da",
		"Oz",
		"Jl",
		"Am",
		"Abd",
		"Jon",
		"Mi",
		"Na",
		"Abk",
		"Sf",
		"Ag",
		"Za",
		"Mal",
		"Mt",
		"Mk",
		"L",
		"J",
		"Sk",
		"R",
		"1K",
		"2K",
		"Ga",
		"Ef",
		"Fp",
		"Ko",
		"1Te",
		"2Te",
		"1Tm",
		"2Tm",
		"Tit",
		"Fm",
		"Zd",
		"Jk",
		"1P",
		"2P",
		"1J",
		"2J",
		"3J",
		"Ju",
		"Zj"
	};
	
	static int[] chapters = {0,50,40,27,36,34,24,21,4,31,24,22,25,29,36,10,13,10,42,150,31,12,8,66,52,5,48,14,14,3,9,1,4,7,3,3,3,2,14,4,28,16,24,21,28,16,16,13,6,6,4,4,5,3,6,4,3,1,13,5,5,3,5,1,1,1,22};

	public static void main(String[] args) throws IOException {
		File f = new File("obohu_ca_" + lang + ".txt");
		if (!f.exists()) f.delete();
		parser();
	}

	private static void parser() throws IOException {
		FileWriter fstreamxml = new FileWriter("obohu_cz" + lang + ".txt");
		BufferedWriter outfstreamxml = new BufferedWriter(fstreamxml);

		for(int b = 1; b < 67; b++ ) {

		//outfstreamxml.write("Bible_" + b + "\r\n");
			
		int biblechapters = chapters[b];	

		for(int i = 1; i <=biblechapters ; i++ ) {
		//for(int i = 1; i <=2 ; i++ ) {	
			
		//http://www.obohu.cz/bible/index.php?k=Gn&kap=1&styl=KAZ	
			
		Document doc = Jsoup.connect("http://www.obohu.cz/bible/index.php?k="+books[b]+"&kap=" + i + "&styl=" + lang).get();	
				
		Elements verses = doc.select("div #blok_versu");
		String newverses = verses.toString();

		String new2 = newverses.replace("<span class=\"cisloverse\">", b + "_" + i + "_");
		String new3 = new2.replace("</span>","_");

		String new4 = new3.replace("&nbsp;", "");
		String new5 = new4.replace("&laquo;", "");
		String new7 = new5.replace("&raquo;", "");
		String new8 = new7.replaceAll("\\<.*?>","");
		String new9 = new8.trim();
		
		
		System.out.println(new9);
		
		
		outfstreamxml.write("\n"+new9);

		}
		
		
		}
		
		outfstreamxml.close();
	}
	}

