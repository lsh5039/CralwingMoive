package com.lsh.movie.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsh.movie.crawling.MovieVO;


@Service
public class MovieService {
	@Autowired
	private MovieDao movieDao;
	
	
	public MovieVO getOneMoive(MovieVO param) {
		//MovieVO vo = new MovieVO();
		
		return movieDao.getOneMovie(param);
	}
	
	
}
