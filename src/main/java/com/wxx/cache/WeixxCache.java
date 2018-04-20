package com.wxx.cache;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxx.constant.StaticEnum;

/**
 * 该类是对 配置文件的处理类
 * 该缓存类只是练习读取配置文件和缓存数据
 * 缓存数据可以用Map也可以用Properties 两者是一样的
 * Properties Hashtable<Object,Object>
 * @author weixx
 * @createTime 2017-7-12 15:07:35
 */
public class WeixxCache extends Properties {

	private static final long serialVersionUID = -2087594832764758377L;
	private static final Logger LOGGER = LoggerFactory.getLogger(WeixxCache.class);

	WeixxCache() throws ClassNotFoundException, SQLException, NamingException {
		// 加载驱动
		Class.forName("com.mysql.jdbc.Driver");
		Context ctx = null;
		System.out.println("有问题找>>url>>"+StaticEnum.url.getValue());
		System.out.println("systemProperties>>url>>"+ResourceBundle.getBundle("system").getString("url"));

		// 创建连接
		Connection conn = DriverManager.getConnection(
				// 读取properties配置文件方法
				ResourceBundle.getBundle("system").getString("url"),
				ResourceBundle.getBundle("system").getString("username"),
				ResourceBundle.getBundle("system").getString("password"));
		// 创建声明
		Statement statement = conn.createStatement();
		ResultSet rs = null;
		try {
			// 拿到结果集并遍历取出
			rs = statement.executeQuery("select sp.userid, sp.username from weixx sp");
			while (rs.next()) {
				this.put(rs.getString("userid"), rs.getString("username"));
			}
		} catch (SQLException e) {
			this.doErrorLogger(e);
		} finally {
			// 关闭所有
			this.closeResource(ctx, conn, statement, rs);
		}
	}

	private void closeResource(Context ctx, Connection conn,
			Statement statement, ResultSet rs) {
		try {
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			this.doErrorLogger(e);
		}
		try {
			if (null != statement) {
				statement.close();
			}
		} catch (SQLException e) {
			this.doErrorLogger(e);
		}
		try {
			if (null != conn) {
				conn.close();
			}
		} catch (SQLException e) {
			this.doErrorLogger(e);
		}
		try {
			if (null != ctx) {
				ctx.close();
			}
		} catch (NamingException e) {
			this.doErrorLogger(e);
		}
	}

	private void doErrorLogger(Exception e) {
		if (WeixxCache.LOGGER.isErrorEnabled()) {
			WeixxCache.LOGGER.error(e.getMessage(), e);
		}
	}
}
