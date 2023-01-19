package com.iu.main.employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.iu.main.util.DBConnection;

public class EmployeeDAO {
	
	//월급의 평균
	public ResultSet getAvg() throws Exception {
		Connection con = DBConnection.getConnection();
		
		String sql = "SELECT AVG(SALARY), SUM(SALARY) FROM EMPLOYEES";
		
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs = st.executeQuery();
		
		rs.next();
		
		System.out.println(rs.getDouble(1));
		System.out.println(rs.getDouble(2));
		
		DBConnection.disConnect(rs, st, con);
		
		return rs;
	}
	
	//method 1 : query 1
	//list
	public ArrayList<EmployeeDTO> getList() throws Exception{
		ArrayList<EmployeeDTO> ar = new ArrayList<EmployeeDTO>();
		
		//1. DB연결
		Connection connection = DBConnection.getConnection();
		
		//2. Query문 생성
		String sql = "SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, JOB_ID, DEPARTMENT_ID FROM EMPLOYEES ORDER BY HIRE_DATE DESC";
		
		//3. 미리 전송
		PreparedStatement st = connection.prepareStatement(sql);
		
		//4. ?
		
		//5. 최종 전송 및 결과 처리
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployee_id(rs.getInt("EMPLOHYEE_ID"));
			employeeDTO.setFirst_name(rs.getString("FIRST_NAME"));
			employeeDTO.setLast_name(rs.getString("LAST_NAME"));
			employeeDTO.setJob_id(rs.getString("JOB_ID"));
			employeeDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			
			ar.add(employeeDTO);
		}
		
		//6. 연결 해제
		DBConnection.disConnect(rs, st, connection);
		
		return ar;
	}
	
	public ArrayList<EmployeeDTO> getFind(String search) throws Exception{
		ArrayList<EmployeeDTO> ar = new ArrayList<EmployeeDTO>();
		
		Connection connection = DBConnection.getConnection();
		
		String sql = "SELECT * FROM EMPLOYEES WHERE LAST_NAME LIKE '%'||?||'%' ";
		
		PreparedStatement st = connection.prepareStatement(sql);
		
		st.setString(1, search);
		
		ResultSet rs = st.executeQuery();
		
		while(rs.next()) {
			EmployeeDTO employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployee_id(rs.getInt("EMPLOHYEE_ID"));
			employeeDTO.setFirst_name(rs.getString("FIRST_NAME"));
			employeeDTO.setLast_name(rs.getString("LAST_NAME"));
			employeeDTO.setHire_date(rs.getDate("HIRE_DATE"));
			employeeDTO.setJob_id(rs.getString("JOB_ID"));
			employeeDTO.setSalary(rs.getInt("SALARY"));
			employeeDTO.setDepartment_id(rs.getInt("DEPARTMENT_ID"));
			
			ar.add(employeeDTO);
		}
		DBConnection.disConnect(rs, st, connection);
		
		return ar;
	}
	
}
