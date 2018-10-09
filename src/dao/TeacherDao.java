package dao;

import model.Teacher;

public interface TeacherDao {
	//注册方法
	boolean register(Teacher teacher);
	//登录方法
	boolean signin(String id ,String password);
	//通过教师id得到整个教师对象
	Teacher getTeacherById(String id);
	//根据教师id得到教师所对应的权限名称
	String getTypeNameByTecId(String name);
}
