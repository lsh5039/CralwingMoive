package com.lsh.movie.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lsh.movie.crawling.MovieVO;
import com.lsh.movie.model.UserVO;

public interface UserDao {
	public int doJoin(UserVO param);
	public List<UserVO> getList();
	public int doUserDel(UserVO param);
	public List<MovieVO> getMovieList(@Param("start") int start,@Param("end") int end);
	public int doInsertMovie(MovieVO param);
	public List<MovieVO> getMovieBest();
	public int getPageNum();
	public int getMaxPk();// ON DUPLICATE KEY UPDATE로 자동증가해버린 pk를 바로 잡기위해 현재 db의 최대pk값 리턴
	public int updPK(@Param("pk") int pk);//getMaxPk()로 리턴된 값으로 alter table로 pk바로잡기
}
