package com.tisma.bookmarks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class Bookmarks {

	private File inputBookmarksFile = null;
	private File outputBookmarksFile = null;
	private BufferedReader br = null;
	private PrintWriter pw = null;
	private Set<String> set = null;
	
	public Bookmarks() throws FileNotFoundException {
		inputBookmarksFile = new File("bookmarks_10_18_14.html");
		outputBookmarksFile = new File("bookmarks_out.html");
		br = new BufferedReader(new FileReader(inputBookmarksFile));
		pw = new PrintWriter(outputBookmarksFile);
		set = new LinkedHashSet<String>();
	}
	
	public Bookmarks(String inputFileName, String outputFileName) throws FileNotFoundException {
		assert(inputFileName != null);
		assert(outputFileName != null);
		
		inputBookmarksFile = new File(inputFileName);
		outputBookmarksFile = new File(outputFileName);
		br = new BufferedReader(new FileReader(inputBookmarksFile));
		pw = new PrintWriter(outputBookmarksFile);
		set = new LinkedHashSet<String>();
	}
	
	public void parseBookmarksFile() {
		String currentLine = null;
		try {
			currentLine = br.readLine();
			while (currentLine != null) {
				if (currentLine.contains("javacodegeeks")) {
					String parsedLine = parseCurrentLine(currentLine);
					set.add(parsedLine);
				} else {
					set.add(currentLine);
				}
				currentLine = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					br = null;
				}
			}
		}
	}
	
	public String parseCurrentLine(String currentLine) {
		Document d = Jsoup.parse(currentLine, "UTF-8", Parser.xmlParser());
		Elements link = d.select("a[href]");
		URL url = null;
		StringBuilder sb = new StringBuilder(128);

		for (Element l : link) {
			try {
				url = new URL(l.attr("abs:href"));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			sb.append(url.getProtocol()).append("://").append(url.getHost())
					.append(url.getPath());
		}

		d.attr("href", sb.toString());
		return d.toString();
	}

	public void writeToOutputBookmarksFile() {
		for (String s : set) {
			pw.write(s + "\n");
		}
		pw.flush();
		pw.close();
		pw = null;
	}

	public static void main(String[] args) {
		try {
			Bookmarks bookmarks = new Bookmarks();
			bookmarks.parseBookmarksFile();
			bookmarks.writeToOutputBookmarksFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
