package com.hcl.bhoomi.DesignPatterns;

import com.hcl.bhoomi.DAO.UsersDAO;
import com.hcl.bhoomi.DAOImpl.UsersDAOImpl;
import com.hcl.bhoomi.ServiceImpl.UsersServiceImpl;
import com.hcl.bhoomi.ServiceInterface.UsersService;


public class UsersFactory {
	
	public static UsersDAO getUserDAO()
	{
		return new UsersDAOImpl();
	}
	public static UsersService getUserService()
	{
		return new UsersServiceImpl();
	}

}
