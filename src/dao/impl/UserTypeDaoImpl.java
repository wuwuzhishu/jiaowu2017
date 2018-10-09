package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserTypeDao;
import util.DBConnection;

public class UserTypeDaoImpl implements UserTypeDao{

	@Override
	public int getTypeIdByName(String name) {
		// TODO Auto-generated method stub
		int typeId = -1;
		//1.建立数据库的连接
		Connection con = DBConnection.getConnection();
		//2.要执行的SQL语句
		String sql = "select typeId from usertype where typeName=?";
		//3.声明一个Statement
		PreparedStatement psm = null;
		//3.声明一个ResultSet对象
		ResultSet rs = null;
		try {
			//3.创建Statement对象
			psm = con.prepareStatement(sql);
			//4.给参数传值
			psm.setString(1, name);			
			//5.执行数据查询操作
			rs = psm.executeQuery();			
			if(rs.next()) {
				typeId = rs.getInt("typeId");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//6.关闭结果集
				rs.close();	
				//6.关闭Statement
				psm.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//7.关闭连接
			DBConnection.closeConnection(con);
		}
		return typeId;

	}

}
