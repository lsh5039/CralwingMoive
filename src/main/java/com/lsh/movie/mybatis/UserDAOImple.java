package com.lsh.movie.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lsh.movie.crawling.MovieVO;
import com.lsh.movie.model.ProfileVO;
import com.lsh.movie.model.UserVO;

@Repository
public class UserDAOImple implements UserDao{
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private static final String NAME_SPACE="com.lsh.movie.mybatis.UserDao";
	
	
	
	@Override
	public int doJoin(UserVO param) {
		return sqlSession.insert(NAME_SPACE+".doJoin");
	}



	@Override
	public List<UserVO> getList() {
		
		return sqlSession.selectList(NAME_SPACE+".getList");
	}



	@Override
	public int doUserDel(UserVO param) {
		
		return sqlSession.delete(NAME_SPACE+".doUserDel");
	}



	@Override
	public List<MovieVO> getMovieList(int start, int end) {
		return sqlSession.selectList(NAME_SPACE+".getMoiveList");
	}



	@Override
	public int doInsertMovie(MovieVO param) {
		return sqlSession.insert(NAME_SPACE+".doInsertMovie");
		
	}



	@Override
	public List<MovieVO> getMovieBest() {
		
		return sqlSession.selectList(NAME_SPACE+".getMovieBest");
	}



	@Override
	public int getPageNum() {
		return sqlSession.selectOne(NAME_SPACE+".getPageNum");
	}



	@Override
	public int getMaxPk() {
		return sqlSession.selectOne(NAME_SPACE+".getMaxPk");
		
	}



	@Override
	public int updPK(int pk) {
		return sqlSession.update(NAME_SPACE+".updPK");
	}



	@Override
	public int getPageMaxNum() {
	
		return sqlSession.selectOne(NAME_SPACE+".getPageMaxNum");
	}



	@Override
	public String getBestLargeImg(int pk) {
		return sqlSession.selectOne(NAME_SPACE+".getBestLargeImg");
	}



	@Override
	public UserVO chkId(String id) {
		
		return sqlSession.selectOne(NAME_SPACE+".chkId");
	}



	@Override
	public UserVO chkNick(String nick) {
		return sqlSession.selectOne(NAME_SPACE+".chkNick");
	}



	@Override
	public UserVO doLogin(UserVO param) {
		return sqlSession.selectOne(NAME_SPACE+".doLogin");
	}



	@Override
	public int upProfile(ProfileVO param) {
		
		return sqlSession.insert(NAME_SPACE+".upProfile");
	}



	@Override
	public String getProfileImg(int user_pk) {
		return sqlSession.selectOne(NAME_SPACE+".getProfileImg");
	}



	




}
