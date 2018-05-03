package com.my.spring.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.my.spring.exception.AdvertException;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.Advert;
import com.my.spring.pojo.Category;
import com.my.spring.pojo.User;

public class AdvertDAO extends DAO {

    public Advert create(Advert advert)
            throws AdvertException {
        try {
            begin();            
            getSession().save(advert);     
            commit();
            return advert;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advert", e);
            throw new AdvertException("Exception while creating advert: " + e.getMessage());
        }
    }

    public void delete(Advert advert)
            throws AdvertException {
        try {
            begin();
            getSession().delete(advert);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
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
            throw new AdvertException("Could not delete advert", e);
        }
    	
    }
    
  public List<Advert> search(Category category, int minprice, int maxprice, String product) throws AdvertException{
    	
    	try {
            begin();
            List<Advert> searchedAdverts = new ArrayList<Advert>();
            Query q = getSession().createQuery("from Advert where itemName= :product and price > :minprice and price < :maxprice and itemStatus = :itemStatus");
            q.setString("product", product);
            q.setInteger("minprice", minprice);
            q.setInteger("maxprice", maxprice);
            q.setString("itemStatus", "pending");
            List<Advert> adverts = q.list();
            commit();
            Set<Advert> advertInCategory = category.getAdverts();
            for(Advert advert : advertInCategory){
            	for(Advert a : adverts){
            		if(a.getId() == (advert.getId())){
            			searchedAdverts.add(a);
            		}
            	}
            }
            return searchedAdverts;
        } catch (HibernateException e) {
            rollback();
            throw new AdvertException("Could not delete advert", e);
        }
    	
    }
  
  
  public boolean update(int advId) throws AdvertException {
		try {
			begin();
			Query q = getSession().createQuery("from Advert where id= :id");
			q.setInteger("id", advId);		
			Advert advert = (Advert) q.uniqueResult();
			advert.setItemStatus("Completed");
			getSession().update(advert);
            commit();
			return true;
		} catch (HibernateException e) {
			rollback();
			throw new AdvertException("Could not get and update advert ", e);
		}
	}
    
}