package com.lsh.movie.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lsh.movie.crawling.MovieVO;
import com.lsh.movie.model.ProfileVO;
import com.lsh.movie.model.UserVO;
import com.lsh.movie.mybatis.UserService;

import net.sf.json.JSONObject;

@Controller
public class UserMovieCont {
	@Autowired
	UserService userService;
	//MovieService movieService;
	
	@RequestMapping(value="join.do", method = RequestMethod.GET)
	public String goJoin(Model model) {
		model.addAttribute("vo",new UserVO());
		return"/user/join";
	}
	@RequestMapping(value="join.do", method = RequestMethod.POST)
	public String doJoin(Model model, @ModelAttribute UserVO vo) {
		int result = userService.doJoin(vo);
		System.out.println("join메서드 : "+result);
		
		return"redirect:/list.do";		
	}
	
	@RequestMapping(value="/user/profile.do", method = RequestMethod.POST)
	public String updProfile(Model model, @RequestParam("profile") MultipartFile multi, HttpServletRequest request){
		HttpSession loginUser = request.getSession();
		UserVO user = (UserVO) loginUser.getAttribute("loginUser");
		
		
		int pk = -1;//로그인유저의 pk값 으로 나누어서 프로필사진 저장
		if(user != null) {
			pk = user.getPk();
		} else {
			return "redirect:/user/login.do";
		}
		
		
		byte[] bytes;
		String uploadPath="C:\\Users\\이승훈\\eclipse-workspace2\\movieBoard\\src\\main\\webapp\\resources\\upload\\profile";
		uploadPath+="\\"+pk;
		File file = new File(uploadPath);
		if(!file.exists()) {//없으면 생성
			System.out.println("경로 : "+uploadPath);
			file.mkdir();
		}
		
		file = new File(uploadPath,multi.getOriginalFilename());
		System.out.println("fileName : "+multi.getOriginalFilename());
		
		
		try {
			bytes = multi.getBytes();
			FileCopyUtils.copy(bytes, file);
			
			ProfileVO param = new ProfileVO();
			
			param.setUser_pk(pk);
			param.setImg(multi.getOriginalFilename());
			
			userService.upProfile(param);
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
		
		System.out.println("여기여기");
		
		return "redirect:/movie/board.do";
	}
	
	
	
	@RequestMapping(value="/user/login.do", method=RequestMethod.GET)
	public String doLogin(Model model) {
				
		return "/user/login";
	}
	
	@RequestMapping(value="/user/login.do", method=RequestMethod.POST)
	public String doLogin(Model model,  
			@RequestParam(value="id",required = true)String id, 
			@RequestParam (value="pw",required = true)String pw, HttpServletRequest request){
		
		int result = 0;//0 db오류, 1 로그인성공, -1 아이디 없음 -2 아이디 비밀번호 불일치 
		UserVO vo = new UserVO();
		vo.setId(id);
		UserVO vo2 = userService.doLogin(vo);
		if(vo2 != null) {//아이디는 맞음
			
			if(pw.equals(vo2.getPw())) {//로그인성공
				result = 1;
			}else {//아이디 비밀번호 불일치
				result = -2;
			}
		} else {//아이디없음
			result = -1;
		}
		
		switch(result) {
			case 0:
				model.addAttribute("msg","알 수 없는 이유에서 로그인 실패");
			break;
			
			case 1:
				HttpSession loginUser = request.getSession();
				loginUser.setAttribute("loginUser", vo2);
				return "redirect:/movie/board.do";//로그인 성공
				
			case -1:
				model.addAttribute("msg","아이디와 비밀번호가 일치하지 않습니다.");
			break;
			
			case -2:
				model.addAttribute("msg","비밀번호가 일치하지 않습니다.");
			break;
		}
		
		
		
		return "/user/login";//로그인 실패
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/chkid", method = RequestMethod.POST,produces = "application/text; charset=utf8")
	public String chkId(Model model, @RequestParam (value = "id", required = false, defaultValue = "back")String id) {
		if(("back").equals(id)) {
			return "redirect:/join.do";
		}
		String result = "사용가능한 아이디 입니다.";
		JSONObject json = new JSONObject();
		
		UserVO vo = userService.chkId(id);
		if(vo != null) {
			result="중복된 아이디 입니다.";
		}
		
		json.put("msg", result);
		return json.toString();
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/user/nick", method=RequestMethod.POST,produces = "application/text; charset=utf8")
	public String chkNick(Model model,@RequestParam (value="nick", required = false, defaultValue = "back") String nick ) {
		if("back".equals(nick)) {
			return "redirect:/join.do";
		}
		UserVO vo = userService.chkNick(nick);
		JSONObject json = new JSONObject();
		String msg = "사용가능한 닉네임 입니다.";
		if(vo != null) {
			msg = "중복된 닉네임 입니다.";
		}
		json.put("msg", msg);
		return json.toString();
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
	public String goIndex(Model model,HttpServletRequest request,
			@RequestParam (value="start", required = false, defaultValue = "0")int start,
			@RequestParam (value="end", required = false, defaultValue = "10")int end) {
		
		
		HttpSession lgUser = request.getSession();
		UserVO user = (UserVO) lgUser.getAttribute("loginUser");
		int profilePk=-1;
		if(user != null) {
			profilePk = user.getPk();
		}
		
		
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
		Elements code = null;//영화마다의 개별 코드
		Elements tit = null;
		Elements day = null;//개봉일
		
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
			tit = doc.select(".tit");
			code = tit.select("a");//영화의 고유코유
			
			
			
			
			System.out.println("code의 크기 : "+code.size());
			System.out.println("ele의크기 : "+ele.size());
		
			 String strCode = null;
			
			for(int i=0;i<ele.size();i++) { 
			  MovieVO vo = new MovieVO();
			  vo.setImg(img.get(i).attr("src"));
			  vo.setTitle(img.get(i).attr("alt"));
			  vo.setNum(num.get(i).html());
			  vo.setNum2(num2.get(i).select("em").html());
			  strCode = code.get(i).attr("href").split("code=")[1];  
			  vo.setCode(strCode);
			  
			  
			  userService.doInsertMoive(vo);
			  
			  
				
				  System.out.println("src : "+vo.getImg());
				  System.out.println("alt : "+vo.getTitle());
				  System.out.println("num : "+vo.getNum());
				  System.out.println("num2 : "+vo.getNum2());
				  System.out.println("code : "+vo.getCode());
			  list.add(vo);
			}
			 
			model.addAttribute("pageMaxNum",userService.getPageMaxNum());//최대 몇페이지인가
			model.addAttribute("movieList",userService.getMovieList(start,end));
			model.addAttribute("movieBest5",userService.getMovieBest());
			model.addAttribute("pageMax",userService.getPageNum());
			if(profilePk != -1) {//로그인 했다면 프로필사진 가져오기
				model.addAttribute("profileImg",userService.getProfileImg(profilePk));
				model.addAttribute("lgUser",profilePk);
			}
			
			
			int maxPk = userService.getMaxPk();
			userService.updPk(maxPk);
			//userService.getP
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		List<MovieVO> bestMovie = userService.getMovieBest();// 베스트5 리스트 초기화
		//
		for(int i=0;i<bestMovie.size();i++) {
			System.out.println("제목은 : "+bestMovie.get(i).getCode());
		}
			//
		String[] bestImgCodes;
		bestImgCodes = new String[bestMovie.size()];
		
		Elements largeImg = null;
		String strLargeImg = null;
		
		List<String> bestImg = new ArrayList<String>();
		for(int i=0;i<bestMovie.size();i++) {
			bestImgCodes[i] = userService.getBestLargeImg(bestMovie.get(i).getPk());
			System.out.println("코드는 : "+bestImgCodes[i]);
			url = "https://movie.naver.com/movie/bi/mi/photoViewPopup.nhn?movieCode="+bestImgCodes[i];
			System.out.println("주소값은 : "+url);
			try {
				doc = Jsoup.connect(url).get();
				largeImg = doc.select("img");
				strLargeImg = largeImg.attr("src");
				System.out.println("strLargeImg : "+strLargeImg);
				bestImg.add(strLargeImg);
				
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			for(int j=0;j<bestImg.size();j++) {
				System.out.println("리스트배너 : "+bestImg.get(j));
			}
			model.addAttribute("mainImg",bestImg);
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
	
	
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public String doLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession hs = request.getSession();
		UserVO vo = (UserVO) hs.getAttribute("loginUser");
		
		//Authentication auth = SecurityContext 
		
		return "redirect:/moive/board.do";
	}
	
	
	 
		
	
}
