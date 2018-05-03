package com.my.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.spring.dao.AdvertDAO;
import com.my.spring.exception.AdvertException;
import com.my.spring.exception.UserException;



@Controller
@RequestMapping("/confirm/*")
public class ReportController {
	
	@Autowired
	@Qualifier("advertDao")
	AdvertDAO advertDao;
	
	@RequestMapping(value= "/confirm/conf", method = RequestMethod.POST)
	public ModelAndView createReport(HttpServletRequest request)
	{
		HttpSession session = (HttpSession) request.getSession();
		Map<String,Object> map = new HashMap<String,Object>();
		String itemName = request.getParameter("itemName");
		String customerName = request.getParameter("cutomername");
		String address = request.getParameter("address");
		String itemPrice = request.getParameter("itemPrice");
		String advId =  request.getParameter("advId");
		// make status to sold
		boolean updateSuccess= false;
		try {
			updateSuccess = advertDao.update(Integer.parseInt(advId));
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (AdvertException e) {
			
			e.printStackTrace();
		}
		if(updateSuccess){
			map.put("customerName", customerName);
			map.put("address", address);
			map.put("itemName", itemName);
			map.put("itemPrice", itemPrice);
			return new ModelAndView(new PdfReportView(),map);
		}
		return null;
		
		
	}

}
