package com.my.spring.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.CategoryDAO;
import com.my.spring.dao.UserDAO;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.User;
import com.my.spring.validator.UserValidator;
import com.captcha.botdetect.web.servlet.Captcha;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@Controller
@RequestMapping("/user/*")
public class UserController {


	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;

	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;


	// Method called when user clicks login on index page
	@RequestMapping(value = "/user/login", method = RequestMethod.GET)
	protected String goToIndex(HttpServletRequest request) throws Exception {
		// go to index page inside views
		return "login";
	}
	
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	protected String goToUserHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}

	// @RequestMapping(value = "**/user/login", method = RequestMethod.POST)
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	protected ModelAndView loginUser(HttpServletRequest request, ModelMap map, UserDAO userDao) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		try {

			User u = userDao.get(request.getParameter("username"), request.getParameter("password"));

			if (u == null) {

				session.setAttribute("errorMessage", "UserName/Password does not exist");
				return new ModelAndView("error");
			} else if (u != null && u.getUserstatus() == 1) {
				session.setAttribute("user", u);
				// login according to roles 
				if (u.getUserRole().equals("ActivatedCustomer")) {
					return new ModelAndView("home-page");
				} else if (u.getUserRole().equals("admin")) {
					return new ModelAndView("redirect:/admin/view");
				}

				return new ModelAndView("home-page");
			} else if (u != null && u.getUserstatus() == 0) {
				session.setAttribute("errorMessage", "Please activate your account to login!");
				return new ModelAndView("error");
			} else {
				session.setAttribute("errorMessage", "Invalid username/password!");
				return new ModelAndView("error");
			}

		} catch (UserException e) {
			System.out.println("Exception: " + e.getMessage());
			session.setAttribute("errorMessage", "error while login");

			return new ModelAndView("error");
		}

	}

	@RequestMapping(value = "/user/post", method = RequestMethod.GET)
	protected String postAdv(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		User u = (User) session.getAttribute("user");
		session.setAttribute("user", u);

		return "user-home";

	}

	@RequestMapping(value = "/user/search", method = RequestMethod.GET)
	protected ModelAndView buyProducts(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

		User u = (User) session.getAttribute("user");
		session.setAttribute("user", u);
		// code to get category
		List<Category> categories = categoryDAO.list();

		return new ModelAndView("search-products", "categories", categories);

	}

	// @RequestMapping(value = "/user/register", method = RequestMethod.POST)
	// protected ModelAndView registerNewUser(HttpServletRequest request,
	// @ModelAttribute("user") User user, BindingResult result) throws Exception
	// {
	//
	// validator.validate(user, result);
	//
	// if (result.hasErrors()) {
	// return new ModelAndView("register-user", "user", user);
	// }
	//
	// try {
	//
	// System.out.print("registerNewUser");
	// user.setUserRole("Customer");
	// User u = userDao.register(user);
	//
	// request.getSession().setAttribute("user", u);
	//
	// return new ModelAndView("home-page", "user", u);
	//
	// } catch (UserException e) {
	// System.out.println("Exception: " + e.getMessage());
	// return new ModelAndView("error", "errorMessage", "error while login");
	// }
	// }
	@RequestMapping(value = "/user/validateemail.htm", method = RequestMethod.GET)
	public String validateEmail(HttpServletRequest request, UserDAO userDao, ModelMap map) {

		// The user will be sent the following link when the use registers
		// This is the format of the email
		// http://hostname:8080/lab10/user/validateemail.htm?email=useremail&key1=<random_number>&key2=<body
		// of the email that when user registers>
		HttpSession session = request.getSession();

		String username = (String) session.getAttribute("userName");
		int key1 = Integer.parseInt(request.getParameter("key1"));
		int key2 = Integer.parseInt(request.getParameter("key2"));
		System.out.println(session.getAttribute("key1"));
		System.out.println(session.getAttribute("key2"));

		if ((Integer) (session.getAttribute("key1")) == key1 && ((Integer) session.getAttribute("key2")) == key2) {
			try {

				boolean updateStatus = userDao.updateUser(username);
				if (updateStatus) {
					// return "user-home";
					return "login";
				} else {

					return "error";
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			map.addAttribute("errorMessage", "Link expired , generate new link");
			map.addAttribute("resendLink", true);
			return "error";
		}

		return "user-home";

	}

	public void sendEmail(String useremail, String message) {
		try {
			SimpleEmail email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("contactapplication2018@gmail.com", "springmvc"));
			email.setSSLOnConnect(true);
			email.setFrom("no-reply@msis.neu.edu"); // This user email does not
													// // exist
			email.setSubject("Password Reminder");
			email.setMsg(message); // Retrieve email from the DAO and send this
			email.addTo(useremail);
			email.send();
		} catch (EmailException e) {
			System.out.println("Email cannot be sent");
		}
	}

	// control comes here when user clicks href on index jsp register user bcz
	// it has default method get
	// @RequestMapping(value = "**/user/register", method = RequestMethod.GET)
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	protected ModelAndView registerUser() throws Exception {
		System.out.print("registerUser");

		return new ModelAndView("register-user", "user", new User());

	}

	// when user clicks register user button on regiser-user page having captcha
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	protected ModelAndView registerNewUser(HttpServletRequest request, @ModelAttribute("user") User user,
			BindingResult result, ModelMap map, UserDAO userDao) throws Exception {

		validator.validate(user, result);
		Captcha captcha = Captcha.load(request, "CaptchaObject");
		String captchaCode = request.getParameter("captchaCode");
		HttpSession session = request.getSession();
		if (result.hasErrors()) {
			return new ModelAndView("register-user", "user", user);
		}

		User u = null;
		if (captcha.validate(captchaCode)) {
			String useremail = request.getParameter("email.emailAddress");
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			
			user.setUserstatus(0);
			session.setAttribute("userName", username);

			try {
				if (userDao.checkUser(username)) {
					user.setUserRole("InactivatedCustomer");
					u = userDao.register(user);
					request.getSession().setAttribute("user", u);
					Random rand = new Random();
					int randomNum1 = rand.nextInt(5000000);
					int randomNum2 = rand.nextInt(5000000);
					try {
						String str = "http://localhost:8080/lab8/user/validateemail.htm?email=" + useremail + "&key1="
								+ randomNum1 + "&key2=" + randomNum2;
						session.setAttribute("key1", randomNum1);
						session.setAttribute("key2", randomNum2);
						sendEmail(useremail, "Click on this link to activate your account : " + str);
					} catch (Exception e) {
						System.out.println("Email cannot be sent");
					}
				} else {
					// System.out.println("Exception: " + e.getMessage());
					return new ModelAndView("error", "errorMessage",
							"user name already exist! Please give another user name.");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Exception: " + e.getMessage());
				return new ModelAndView("error", "errorMessage", "error while login");
				// e.printStackTrace();

			}
		} else {
			map.addAttribute("errorMessage", "Invalid Captcha!");
			return new ModelAndView("error", "errorMessage", "error while login");
		}

		// return new ModelAndView("home-page", "user", u);

		return new ModelAndView("user-created");

	}

	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	protected String goToHome(HttpServletRequest request) throws Exception {
		return "user-home";
	}
}
