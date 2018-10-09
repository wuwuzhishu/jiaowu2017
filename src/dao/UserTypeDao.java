package dao;

public interface UserTypeDao {
	//通过用户类型名字找到用户的类型id
	int getTypeIdByName(String name);
}
