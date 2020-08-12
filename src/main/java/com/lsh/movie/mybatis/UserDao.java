package com.lsh.movie.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.lsh.movie.crawling.MovieVO;
import com.lsh.movie.model.ProfileVO;
import com.lsh.movie.model.UserVO;

public interface UserDao {
	public int doJoin(UserVO param);
	public UserVO doLogin(UserVO param);
	public UserVO chkId(String id);
	public UserVO chkNick(String nick);
	public List<UserVO> getList();
	public int doUserDel(UserVO param);
	public List<MovieVO> getMovieList(@Param("start") int start,@Param("end") int end);
	public int doInsertMovie(MovieVO param);
	public List<MovieVO> getMovieBest();
	public String getBestLargeImg(int pk);//영화의 pk값으로 code를 리턴받아 그 값으로 크롤링 후 큰 이미지출력에 사용
	public int getPageNum();
	public int getMaxPk();// ON DUPLICATE KEY UPDATE로 자동증가해버린 pk를 바로 잡기위해 현재 db의 최대pk값 리턴
	public int updPK(@Param("pk") int pk);//getMaxPk()로 리턴된 값으로 alter table로 pk바로잡기
	public int getPageMaxNum();
	public int upProfile(ProfileVO param);
	public String getProfileImg(int user_pk);
}
