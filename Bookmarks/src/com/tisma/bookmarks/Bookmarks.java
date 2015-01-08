package com.tisma.bookmarks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Bookmarks {

private static void parse(File f, Set<String> set, List<String> list) throws IOException {
	int i = 0;
	Document d = Jsoup.parse(f, "UTF-8");

	Elements link = d.select("a[href]");
	for (Element l : link) {
		URL url = new URL(l.attr("abs:href"));
		StringBuilder sb = new StringBuilder(64);
		if (url.toString().contains("javacodegeeks")) {
			sb.append(url.getProtocol()).append("://").append(url.getHost()).append(url.getPath());
			i++;
			System.out.println(i + ": " + sb.toString());
			set.add(sb.toString());
			list.add(sb.toString());
//			sb.setLength(0);
//			System.out.println(set);
		}
	}
}

private static String parse(String line) {
	
	Document d = Jsoup.parse(line, "UTF-8");
	Elements link = d.select("a[href]");
	URL url = null;
	StringBuilder sb = new StringBuilder(128);
	
	for (Element l : link) {
		try {
			url = new URL(l.attr("abs:href"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		sb.append(url.getProtocol()).append("://").append(url.getHost()).append(url.getPath());
	}
	
	return sb.toString();
}

private static void parseCurrentLine(String currentLine, Set<String> set) {
	if (currentLine.contains("javacodegeeks")) {
		String parsedLine = parse(currentLine);
		set.add(parsedLine);
	} else {
		set.add(currentLine);
	}
}

private static void writeSetFile(Set<String> set, FileWriter fw) {
	
}

	public static void main(String[] args) {

		File f = new File("bookmarks_10_18_14.html");
		File out = new File("bookmars_out.html");
		Set<String> set = new LinkedHashSet<String>();
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		FileWriter fw = null;
		
		int i = 0;
		try {
			br = new BufferedReader(new FileReader(f));
			fw = new FileWriter(out);
			String currentLine = br.readLine();
			while (currentLine != null) {
//				System.out.println(currentLine);
				++i;
				parseCurrentLine(currentLine, set);
				currentLine = br.readLine();
			}
			writeSetFile(set, fw);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println("set: " + set.size() + ", list: " + list.size() + " i: " + i);

	}

}
