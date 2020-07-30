package com.lsh.movie.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lsh.movie.crawling.MovieVO;

@Repository
public class MovieDAOImple implements MovieDao{
	@Autowired
	private SqlSession sqlSession;
	private static final String NAME_SPACE="com.lsh.movie.mybatis.MovieDao";
	
	@Override
	public MovieVO getOneMovie(MovieVO param) {
		return sqlSession.selectOne(NAME_SPACE+".getOneMovie");
	}
	
	
	
	

}
