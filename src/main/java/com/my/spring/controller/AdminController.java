package com.my.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.AdminDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Advert;
import com.my.spring.pojo.User;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

//	@Autowired
//	@Qualifier("userDao")//name shud be same as one given in bean id in root context.xml
//	//above annotations get an instance of user dao class and binds it to this local variable defined below
//	UserDAO userDao;
	
	@RequestMapping(value = "/admin/view")
	protected ModelAndView loginAdmin(HttpServletRequest request) throws Exception {
		AdminDAO adminDAO = new AdminDAO();
		List<Advert> adverts = adminDAO.list();
			
		return new ModelAndView("admin","adverts",adverts);

	}
	@RequestMapping(value = "/admin/mostActive")
	protected ModelAndView viewUsersAdmin(HttpServletRequest request) throws Exception {
		AdminDAO adminDAO = new AdminDAO();
		List list = adminDAO.getMostActiveUser();
		return new ModelAndView("most-activeUser","list",list);

	}
	
	
}
