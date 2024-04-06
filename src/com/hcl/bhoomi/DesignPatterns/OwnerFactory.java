package com.hcl.bhoomi.DesignPatterns;

import com.hcl.bhoomi.DAO.OwnerDAO;
import com.hcl.bhoomi.DAO.UsersDAO;
import com.hcl.bhoomi.DAOImpl.OwnerDAOImpl;
import com.hcl.bhoomi.DAOImpl.UsersDAOImpl;
import com.hcl.bhoomi.ServiceImpl.OwnerServiceImpl;
import com.hcl.bhoomi.ServiceInterface.OwnerService;

public class OwnerFactory {
	
	public static OwnerDAO getOwnerDao()
	{
		return new OwnerDAOImpl();
	}
	public static OwnerService getOwnerService()
	{
		return new OwnerServiceImpl();
	}

}
