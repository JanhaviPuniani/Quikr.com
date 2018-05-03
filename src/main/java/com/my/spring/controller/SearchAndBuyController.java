package com.my.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.AdvertDAO;
import com.my.spring.dao.CategoryDAO;
import com.my.spring.pojo.Advert;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.User;

@Controller
@RequestMapping("/searchProducts/*")
public class SearchAndBuyController {
	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;
	
	@Autowired
	@Qualifier("categoryDao")
	CategoryDAO categoryDAO;
	
	@RequestMapping(value = "/searchProducts/search", method = RequestMethod.POST)
	protected ModelAndView searchAdv(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();

			User u = (User)session.getAttribute("user");
			session.setAttribute("user", u);
			//code to get category
			String productName = request.getParameter("name");
			String categoryName = request.getParameter("categories");
			int minprice =Integer.parseInt(request.getParameter("minprice"));
			int maxprice =Integer.parseInt(request.getParameter("maxprice"));
			//get category
			Category category = categoryDAO.get(categoryName);
			List<Advert> adverts = advertDao.search(category, minprice,maxprice , productName);

			return new ModelAndView("display-products", "adverts", adverts);
            
		

	}
	
	@RequestMapping(value = "/searchProducts/buyProduct", method = RequestMethod.GET)
	protected ModelAndView buyProduct(HttpServletRequest request) throws Exception {

		HttpSession session = (HttpSession) request.getSession();
		Map<String,Object> map = new HashMap<String,Object>();
		String itemName = request.getParameter("code");
		String itemPrice = request.getParameter("price");	
		String advId = request.getParameter("advId");	
		map.put("itemName", itemName);
		map.put("itemPrice", itemPrice);
		map.put("advId", advId);
		return new ModelAndView("buy-product","map",map);

	}
	
	
}
