package com.lsh.movie.crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NaverMovieCraw {
	public static void main(String[] args) {
		String url = "https://movie.naver.com/movie/running/current.nhn";
		Document doc = null;
		try {
			doc =  Jsoup.connect(url).get();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
