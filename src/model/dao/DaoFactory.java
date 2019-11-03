package model.dao;

import java.sql.Connection;

import db.DB;
import model.dao.impl.DepartmentDaoImpl;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static SellerDao createSellerDao(Connection connection) {
		return new SellerDaoJDBC(connection);
	}
	
	public static SellerDao createSellerDao( ) {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao(Connection connection) {
		return new DepartmentDaoImpl(connection);
		
	}

	public static DepartmentDao createDepartmentDao() {
		// TODO Auto-generated method stub
		return new DepartmentDaoImpl(DB.getConnection());
	}

}
