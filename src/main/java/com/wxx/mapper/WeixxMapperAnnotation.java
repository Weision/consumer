package com.wxx.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wxx.entity.Weixx;

public interface WeixxMapperAnnotation {

	/**
	 * 查询单条数据
	 * 当有单个参数的时候 可以不用@Param注解或者param1
	 * 用了也不会错
	 */
	@Select("select * from weixx where username = #{name}")
	Weixx query(String name);
	
	/**
	 * 查询单条数据
	 * 当有多个参数的时候 而且不用@Param注解 则需要用 0,1,2... 或者param1,param2,param3...
	 */
	@Select("select * from weixx where username = #{0} and id=#{1}")
	Weixx queryParams(String username,String id);

	/**
	 * 查询列表
	 */
	@Select("select * from weixx")
	List<Weixx> queryList();

	/**
	 * 以单个字段插入
	 */
	@Insert("insert into weixx(id,userid,username) values(#{id},#{userid},#{username})")
	int insert(@Param("id") String id, @Param("userid") String userid,
			@Param("username") String username);

	/**
	 * 以对象插入 同理 增删改查都可以这样操作
	 * 当入参为JavaBean的时候也不需要@param注解
	 */
	@Insert("insert into weixx(id,userid,username) values(#{id},#{userid},#{username})")
	int insertWeixx(Weixx weixx);

	/**
	 * 删除数据
	 */
	@Delete("delete from weixx where id=#{username}")
	int delete(Weixx weixx);

	/**
	 * 更新数据
	 */
	@Update("update weixx set id=#{id} where username=#{username}")
	int update(Weixx weixx);
}
