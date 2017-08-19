/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finalproject.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Sukhjinder Kaur
 */
public class DbMgr {
    private String userName;
	private String password;
	private String url;
	private String className;
	
	private Connection connection ;
	public DbMgr(String userName, String password, String url, String className) throws ClassNotFoundException, SQLException {
		super();
		this.userName = userName;
		this.password = password;
		this.url = url;
		this.className = className;
		Class.forName(className);
		this.connection =
				DriverManager.getConnection(url, userName, password);
		
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	
}
