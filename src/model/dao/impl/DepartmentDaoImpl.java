package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoImpl implements DepartmentDao {
	
	private Connection connection;
	
	public DepartmentDaoImpl(Connection connection) {
			this.connection = connection;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO department (Name) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, obj.getName());
			
			int rows = preparedStatement.executeUpdate();
			
			if (rows > 0) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				
				if (resultSet.next()) {
					obj.setId(resultSet.getInt(1));
				}
				
				DB.closeResultSet(resultSet);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closePreparedStatement(preparedStatement);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("UPDATE  department SET name = ? where id = ?");
			
			preparedStatement.setString(1, obj.getName());
			preparedStatement.setInt(2, obj.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closePreparedStatement(preparedStatement);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement preparedStatement = null;
		
		try {
			preparedStatement = connection.prepareStatement("DELETE FROM department where id = ?");
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closePreparedStatement(preparedStatement);
		}

		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement = connection.prepareStatement("SELECT * FROM department WHERE "
					+ "department.Id = ?");
			
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return instantiateDepartment(resultSet);
			}	
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closePreparedStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
	}

	private Department instantiateDepartment(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return new Department(resultSet.getInt("Id"),
				resultSet.getString("Name"));
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			preparedStatement  = connection.prepareStatement("SELECT * FROM department WHERE 1=1");
			
			resultSet = preparedStatement.executeQuery();
			
			List<Department> list = new ArrayList<Department>();
			
			while (resultSet.next()) {
				list.add(instantiateDepartment(resultSet));
			}
			
			return list;

		}catch (SQLException e ) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closePreparedStatement(preparedStatement);
			DB.closeResultSet(resultSet);
		}
		
	}

}
