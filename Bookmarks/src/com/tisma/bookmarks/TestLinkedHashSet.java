package com.tisma.bookmarks;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestLinkedHashSet {

	public static void main(String[] args) throws MalformedURLException {

		String test = "    <DT><A HREF=\"http://www.javacodegeeks.com/2011/08/android-interview-questions-answers.html\" ADD_DATE=\"1386539663\" ICON=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABzklEQVQ4jZWTv09TURTHP+e0FTcxLkTa+FhcrTHGkeckhKVsbXXAwY3E9C8g/QsarYsJDk2wISFSHIgytW6gEl93E54hJC5Gx0dee49DaUP6Q+FO95z7vd9z7vl+L1xyFauZ34VqutGP9bIEhgWYhP1Y/gVOFXezCdWcIXec65bj+lIwjEmOu5h/ObsCsPNZPdA1ARKq7RguRqCiFcRC19WyJno5Z3LrSmEvpwmXNZNrp28XSiMEV/MfPJJ4B98Pg5vXv9bUXDB747B18uteWbuEJOUIFOk9vDQyg6knHxuC5M5qPlx+8GxNRPz66rEATD3e+yZCFoCOm4s2F8Nkvpr2VaSJWXn7wNoMCPDNrCbwKV+Z8d59eTMt5l4YMi1iQRRFfwYdFKrpJmLvG/vrgSHPBWt3nduJ60vB+arRxqMR1SbKWHiVOcIs3N5fR0R8gK7r3B2WcqwKAJiFIhLaWRUza42DTezgvIlONxaWoecPVZ2vrx4/7eMmWlklUemZSHKp4m4WQFXngZV8Zcb7L4FB2N8n0CyAi+Oyi+O5zdLPwdnEGahYDewH0Lp/+7WXrqabnY6VtkonFxtitLHYAloA6WomJyJ+MmU+Q//hLxEss+1XOPFFAAAAAElFTkSuQmCC\">Android Interview Questions and Answers | Java Code Geeks</A>";
		String test2 = "        <DT><A HREF=\"http://www.javacodegeeks.com/2014/04/java-how-to-schedule-a-task-to-run-in-an-interval.html?utm_content=buffer97c5b&utm_medium=social&utm_source=facebook.com&utm_campaign=buffer\" ADD_DATE=\"1398594044\" ICON=\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABzklEQVQ4jZWTv09TURTHP+e0FTcxLkTa+FhcrTHGkeckhKVsbXXAwY3E9C8g/QsarYsJDk2wISFSHIgytW6gEl93E54hJC5Gx0dee49DaUP6Q+FO95z7vd9z7vl+L1xyFauZ34VqutGP9bIEhgWYhP1Y/gVOFXezCdWcIXec65bj+lIwjEmOu5h/ObsCsPNZPdA1ARKq7RguRqCiFcRC19WyJno5Z3LrSmEvpwmXNZNrp28XSiMEV/MfPJJ4B98Pg5vXv9bUXDB747B18uteWbuEJOUIFOk9vDQyg6knHxuC5M5qPlx+8GxNRPz66rEATD3e+yZCFoCOm4s2F8Nkvpr2VaSJWXn7wNoMCPDNrCbwKV+Z8d59eTMt5l4YMi1iQRRFfwYdFKrpJmLvG/vrgSHPBWt3nduJ60vB+arRxqMR1SbKWHiVOcIs3N5fR0R8gK7r3B2WcqwKAJiFIhLaWRUza42DTezgvIlONxaWoecPVZ2vrx4/7eMmWlklUemZSHKp4m4WQFXngZV8Zcb7L4FB2N8n0CyAi+Oyi+O5zdLPwdnEGahYDewH0Lp/+7WXrqabnY6VtkonFxtitLHYAloA6WomJyJ+MmU+Q//hLxEss+1XOPFFAAAAAElFTkSuQmCC\">[Java] How to Schedule a task to run in an interval | Java Code Geeks</A>";
		Document d = Jsoup.parse(test2, "UTF-8");
		Elements link = d.select("a[href]");
		
		for (Element l : link) {
			URL url = new URL(l.attr("abs:href"));
			StringBuilder sb = new StringBuilder(128);
			sb.append(url.getProtocol()).append("://").append(url.getHost()).append(url.getPath());
			System.out.println(sb);
		}
	}

}
