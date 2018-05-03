package com.my.spring.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.my.spring.exception.AdvertException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Advert;
import com.my.spring.pojo.User;

public class AdminDAO  extends DAO {

	public AdminDAO() {
	}

	   public List<Advert> list() throws AdvertException{
	    	
	    	try {
	            begin();
	            Query q = getSession().createQuery("from Advert");
	            List<Advert> adverts = q.list();
	            commit();
	            return adverts;
	        } catch (HibernateException e) {
	            rollback();
	            throw new AdvertException("Could not list advert", e);
	        }
	    	
	    }
	    
	
	public List getMostActiveUser() throws UserException {
		try {
			begin();
			SQLQuery q = getSession().createSQLQuery("select user_personID,user_table.userName, count(*) from advert_table, user_table where user_table.personID = advert_table.user_personID group by user_personID order by count(*) desc");
			List list =  q.list();		
			//User user = (User) q.uniqueResult();
			commit();
			return list;
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Could not get user " , e);
		}
	}
	
}
