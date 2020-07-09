package com.lsh.movie.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lsh.movie.crawling.MovieVO;
import com.lsh.movie.model.UserVO;

@Service

public class UserService {
	@Autowired
	private UserDao userDao;
	
	public int doJoin(UserVO param) {
		
		return userDao.doJoin(param);
		
	}
	
	public List<UserVO> getList(){
		List<UserVO> list =  new ArrayList<UserVO>();
		
		
		return userDao.getList();
	}
	public int doUserDel(UserVO param) {
		return userDao.doUserDel(param);
	}
	
	public int doInsertMoive(MovieVO param) {
		return userDao.doInsertMovie(param);
	}
	
	
	public List<MovieVO> getMovieList(int start, int end){
		List<MovieVO> list = new ArrayList<MovieVO>();
		return userDao.getMovieList(start, end);
	}
	public List<MovieVO> getMovieBest(){
		List<MovieVO> list = new ArrayList<MovieVO>();
		return userDao.getMovieBest();
	}
	
	public int getPageNum() {
		return userDao.getPageNum();
	}
	public int getMaxPk() {
		return userDao.getMaxPk();
	}
	public int updPk(int pk) {
		return userDao.updPK(pk);
	}
	public int getPageMaxNum() {
		return userDao.getPageMaxNum();
	}
}
