package com.lsh.movie.controller;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lsh.movie.crawling.MovieVO;
import com.lsh.movie.mybatis.MovieService;
@Controller
public class MovieContoller {
	@Autowired
	private MovieService movieService;
	

	  @RequestMapping(value = "/movie/goDetail.do", method = RequestMethod.GET)
	  public String doComment(Model model, @RequestParam(required = false, defaultValue = "0")int pk ) { 
		if(pk == 0) {//비정상 경로 return
		return "redirect:/movie/board.do"; 
	  }
		MovieVO vo = new MovieVO();
		vo.setPk(pk);
		vo = movieService.getOneMoive(vo);
		model.addAttribute("one",vo);
	  	String URL = "https://movie.naver.com/movie/bi/mi/basic.nhn?code=";
		URL+=vo.getCode();
		
		Document doc = null;
		Elements title = null;
		Elements content = null;
		String strContent=null;
		String strTitle = null;
		try {
			doc = Jsoup.connect(URL).get();
			title = doc.select(".h_tx_story");
			content = doc.select(".con_tx");
			strContent = content.html();
			strTitle = title.html();
			System.out.println("title파싱 : "+strTitle);
			System.out.println("content파싱 : "+strContent);
			model.addAttribute("title",strTitle);
			model.addAttribute("content",strContent);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		URL = "https://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode="+vo.getCode();
		Elements largeImg = null;
		String strLargeImg = null;
		try {
			doc = Jsoup.connect(URL).get();
			largeImg = doc.select("img");
			strLargeImg = largeImg.attr("src");
			
			model.addAttribute("largeImg",strLargeImg);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		  return  "/movie/detail";
	  }
	
}
