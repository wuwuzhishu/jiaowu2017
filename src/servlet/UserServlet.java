package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import dao.TeacherDao;
import dao.impl.StudentDaoImpl;
import dao.impl.TeacherDaoImpl;
import model.Student;
import model.Teacher;
import util.DateConvert;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//声明dao层中的StudentDao对象
	private StudentDao sd;
	private TeacherDao td;   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
      //利用多态的用法，创建接口StudentDao对象的实现类StudentDaoImpl对象
        sd = new StudentDaoImpl();
        td = new TeacherDaoImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		//解决表单中中文乱码的问题，为了解决写入数据库中乱码的问题，此条语句必须写在第一条哈
		request.setCharacterEncoding("utf-8");
		//通过页面中action参数来决定属于什么操作，然后相应的调用不同的方法
		String action = request.getParameter("action");
		String style = request.getParameter("style");
		if(action.equals("login")) {
			login(request, response);//调用登录方法
		}else if(action.equals("register")&&style.equals("student")){
			register_stu(request, response);//调用学生注册方法
		}else if(action.equals("register")&&style.equals("teacher")){
			register_tec(request, response);//调用教师注册方法
		}
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//教师注册方法
	protected void register_tec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//得到前端表单中的信息
		String id = request.getParameter("ID");
		String name = request.getParameter("user");
		String password = request.getParameter("password");
		String dept = request.getParameter("dept");//部门，与学生不同的地方
		String birth = request.getParameter("birth");
		String type = request.getParameter("type");
		//声明创建Teacher对象，并把表单中的内容封装到对象中
		Teacher teacher = new Teacher();
		teacher.setTecId(id);
		teacher.setTecName(name);
		teacher.setTecPwd(password);
		teacher.setTecDept(dept);
		teacher.setTecBirth(DateConvert.stringToDate(birth));
		teacher.setTypeId(Integer.parseInt(type));
		if(td.register(teacher)){
			response.sendRedirect("success.html");
		}else{
			response.sendRedirect("success.html");
		}
	}
	//学生注册方法
	private void register_stu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到前端表单中的信息
		String id = request.getParameter("ID");
		String name = request.getParameter("user");
		String password = request.getParameter("password");
		String major = request.getParameter("major");
		String birth = request.getParameter("birth");
		String type = request.getParameter("type");
		
		Student student = new Student();
		student.setStuId(id);
		student.setStuName(name);
		student.setStuPwd(password);
		student.setStuMajor(major);
		student.setStuBirth(DateConvert.stringToDate(birth));
		student.setTypeId(Integer.parseInt(type));
		//调用DAO层中的各个类及方法，再来决定转向哪个页面
		if(sd.register(student)) {
			response.sendRedirect("success.html");//登录成功
		}else {
			response.sendRedirect("failure.html");//登录失败
		}		
	}
	//登录方法
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到前端表单中的信息
		String name = request.getParameter("user");
		String password = request.getParameter("password");
		//调用DAO层中的各个类及方法，再来决定转向哪个页面
		if(td.signin(name, password)) {
			response.sendRedirect("success.html");//教师登录成功，显示成功
		}else  if(sd.signin(name, password)){
			response.sendRedirect("index.html");//学生登录成功,转到首页
		}else {
			response.sendRedirect("failure.html");//登录失败
		}
	}

}
