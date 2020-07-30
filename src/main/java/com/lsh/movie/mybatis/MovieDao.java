package com.lsh.movie.mybatis;

import com.lsh.movie.crawling.MovieVO;

public interface MovieDao {
	public MovieVO getOneMovie(MovieVO param);//detail페이지로 가기전 특정 movie객체를 select
	
}
