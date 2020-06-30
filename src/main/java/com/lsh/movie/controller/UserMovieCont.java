package com.lsh.movie.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.lsh.movie.crawling.MovieVO;
import com.lsh.movie.model.UserVO;
import com.lsh.movie.mybatis.UserDao;
import com.lsh.movie.mybatis.UserService;

import net.sf.json.JSONObject;

@Controller
public class UserMovieCont {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="join.do", method = RequestMethod.GET)
	public String goJoin(Model model) {
		model.addAttribute("vo",new UserVO());
		return"/user/join";
	}
	@RequestMapping(value="join.do", method = RequestMethod.POST)
	public String doJoin(Model model, @ModelAttribute UserVO vo) {
		userService.doJoin(vo);
		
		return"redirect:/list.do";		
	}
	@RequestMapping(value="list.do", method = RequestMethod.GET)
	public String goList(Model model, @RequestParam (value="error", required = false)String error) {
		if(error != null) {
			model.addAttribute("msg","잘못된 경로의 접근입니다.");
		}
		
		model.addAttribute("list",userService.getList());
		
		return"/user/list";
	}
	
	@RequestMapping(value="/user/del.do", method = RequestMethod.GET)
	public String goDel(Model model, @RequestParam (value="pk", required = true, defaultValue = "0")int pk) {
		if(pk==0) {
			
			
			return "redirect:/list.do?error=1";
		}
		UserVO vo = new UserVO();
		vo.setPk(pk);
		userService.doUserDel(vo);
		
		return "redirect:/list.do";
	}
	
	@RequestMapping(value="/movie/board.do", method = RequestMethod.GET)
	public String goIndex(Model model,
			@RequestParam (value="start", required = false, defaultValue = "0")int start,
			@RequestParam (value="end", required = false, defaultValue = "10")int end) {
		
		if(start!=0 && end!=10) {
			start = start*10;
			end = start+10;
		}
		
		
		
		
		String url = "https://movie.naver.com/movie/running/current.nhn";
		Document doc = null;
		Elements ele = null;
		Elements eleStar=null;
		Elements title = null;//영화제목
		Elements info_txt = null;
		Elements img = null;//이미지
		Elements num = null;//평점
		Elements num2 = null;//투표자 수
		
		
	
		List<MovieVO> list = new ArrayList<MovieVO>();//크롤링 한 정보를 담을 리스트
		
		
		
		try {
			doc = Jsoup.connect(url).get();
			//ele = doc.select(".lst_detail_t1");
			info_txt = doc.select(".info_txt1");
			
			ele = doc.select(".thumb");
			eleStar = doc.select(".star_t1");
			num = eleStar.select("a").select(".num");
			num2 = eleStar.select(".num2");
			

			img = ele.select("img");//
			System.out.println("img:"+img);
			

			
			
			for(int i=0;i<ele.size();i++) { 
			  MovieVO vo = new MovieVO();
			  vo.setImg(img.get(i).attr("src"));
			  vo.setTitle(img.get(i).attr("alt"));
			  vo.setNum(num.get(i).html());
			  vo.setNum2(num2.get(i).select("em").html());
			  
			  userService.doInsertMoive(vo);
			  
			  
				/*
				 * System.out.println("src : "+vo.getImg());
				 * System.out.println("alt : "+vo.getTitle());
				 * System.out.println("num : "+vo.getNum());
				 * System.out.println("num2 : "+vo.getNum2());
				 */
			
			  list.add(vo);
			}
			 
			
			model.addAttribute("movieList",userService.getMovieList(start,end));
			model.addAttribute("movieBest5",userService.getMovieBest());
			model.addAttribute("pageMax",userService.getPageNum());
			int maxPk = userService.getMaxPk();
			userService.updPk(maxPk);
			//userService.getP
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return"/user/index";
	}
	
	@ResponseBody
	@RequestMapping(value="/movie/board.do", method = RequestMethod.POST,  produces = "application/text; charset=utf8")
	public	String goIndex2(Model model,
			@RequestParam (value="start", required = false, defaultValue = "0")int start,
			@RequestParam (value="end", required = false, defaultValue = "10")int end) {
		
		if(start!=0 ) {
			start = start*10;
		}
		List<MovieVO> list = new ArrayList<MovieVO>();
		JSONObject json = new JSONObject();
		try {
			System.out.println("post방식입니다.");
			System.out.println("start : "+start);
			System.out.println("end : "+end);
			list = userService.getMovieList(start, end);
			
			System.out.println("크기 : "+list.size());
			
			json.put("movieList", list);
			
		} catch (Exception e) {
			System.out.println("실패했네요");
			e.printStackTrace();
		}
		String returnJson = json.toString();
		System.out.println("returnJson : "+returnJson);
		return returnJson;
	}
	
		
	
}
