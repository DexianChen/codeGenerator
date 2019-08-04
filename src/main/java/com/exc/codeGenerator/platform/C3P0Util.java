package com.exc.codeGenerator.platform;

import com.exc.codeGenerator.model.InitRequestParam;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import lombok.Data;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

@Data
public class C3P0Util {
	private String jdbcUrl;
	private String driverClass;
	private String user;
	private String password;

	public C3P0Util(InitRequestParam param) {
		// 正常使用
//		this.jdbcUrl = "jdbc:mysql://" + param.getAddress() + ":" + param.getPort() + "/" + param.getDatabaseName()
//				+ "?useUnicode=true&characterEncoding=utf8&autoReconnect=true&useSSL=false";
//		this.driverClass = "com.mysql.jdbc.Driver";
//		this.user = param.getUser();
//		this.password = param.getPassword();

		// 测试使用
		this.jdbcUrl = "jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=utf8"
				+ "&autoReconnect=true&useSSL=false";
		this.driverClass = "com.mysql.jdbc.Driver";
		this.user = "root";
		this.password = "123456";
	}

	/**
	 * 创建私有静态数据源成员变量
	 */
	private static final ComboPooledDataSource ds = new ComboPooledDataSource();

	/**
	 * 在工具类中创建全局的静态ThreadLocal
	 */
	private static ThreadLocal<Connection> local = new ThreadLocal<>();
	
	/**
	 * 2.创建公有的得到数据源方法
	 * @return ComboPooledDataSource 获得的连接对象
	 */
	public ComboPooledDataSource getDataSource() {

		try {
			ds.setJdbcUrl(jdbcUrl);
			ds.setDriverClass(driverClass);
			ds.setUser(user);
			ds.setPassword(password);
			ds.setAcquireIncrement(3);
			ds.setInitialPoolSize(5);
			ds.setMinPoolSize(2);
			ds.setMaxPoolSize(10);
			ds.setMaxIdleTime(30);
			ds.setCheckoutTimeout(3000);

		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

        return ds;
	}
	
	/**
	 * 3.创建得到连接对象的方法
	 */
	public static Connection getConnection() throws SQLException{
		//首先从ThreadLocal中获取连接
		Connection conn = local.get();
		//如果没有得到连接对象，则创建一个新的连接
		if(conn==null){
			conn = ds.getConnection();
			//将新创建的连接对象放入到ThreadLocal中
			local.set(conn);
		}
		return conn;
	}

}
