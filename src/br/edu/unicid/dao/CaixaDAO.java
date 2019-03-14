package br.edu.unicid.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.unicid.bean.Caixa;
import br.edu.unicid.util.ConnectionFactory;

public class CaixaDAO extends Caixa {
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static String SQL2;

	public CaixaDAO(){
		try {
			CaixaDAO.conn = ConnectionFactory.getConnection();
		} catch (Exception erro) {
			erro.printStackTrace();
		}
	}	
	
	public static void pegaCedulas100 (){
		try {
			SQL2 = "SELECT * FROM CAIXA where cedula ='100'";
			ps = conn.prepareStatement(SQL2);
			rs = ps.executeQuery();			
			while(rs.next()){
				int codigo = rs.getInt("codigo");
				int cedula = rs.getInt("cedula");
				int quantidade = rs.getInt("quantidade");				
				new Caixa(codigo, cedula, quantidade);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}	
	
	public static void pegaCedulas50 (){
		try {
			SQL2 = "SELECT * FROM CAIXA where cedula ='50'";
			ps = conn.prepareStatement(SQL2);
			rs = ps.executeQuery();
			while(rs.next()){
				int codigo = rs.getInt("codigo");
				int cedula = rs.getInt("cedula");
				int quantidade = rs.getInt("quantidade");				
				new Caixa(codigo, cedula, quantidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}	
	
	public static void pegaCedulas20 (){
		try {
			new Caixa();
			SQL2 = "SELECT * FROM CAIXA where cedula ='20'";
			ps = conn.prepareStatement(SQL2);
			rs = ps.executeQuery();
			while(rs.next()){
				int codigo = rs.getInt("codigo");
				int cedula = rs.getInt("cedula");
				int quantidade = rs.getInt("quantidade");				
				new Caixa(codigo, cedula, quantidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}	
	
	public static void pegaCedulas10 (){
		try {
			new Caixa();
			SQL2 = "SELECT * FROM CAIXA where cedula ='10'";
			ps = conn.prepareStatement(SQL2);
			rs = ps.executeQuery();
			while(rs.next()){
				int codigo = rs.getInt("codigo");
				int cedula = rs.getInt("cedula");
				int quantidade = rs.getInt("quantidade");				
				new Caixa(codigo, cedula, quantidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}	
	
	public static void pegaCedulas5 (){
		try {
			new Caixa();
			SQL2 = "SELECT * FROM CAIXA where cedula ='5'";
			ps = conn.prepareStatement(SQL2);
			rs = ps.executeQuery();
			while(rs.next()){
				int codigo = rs.getInt("codigo");
				int cedula = rs.getInt("cedula");
				int quantidade = rs.getInt("quantidade");				
				new Caixa(codigo, cedula, quantidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}	
	
	public static void pegaCedulas2 (){
		try {
			new Caixa();
			SQL2 = "SELECT * FROM CAIXA where cedula ='2'";
			ps = conn.prepareStatement(SQL2);
			rs = ps.executeQuery();
			while(rs.next()){
				int codigo = rs.getInt("codigo");
				int cedula = rs.getInt("cedula");
				int quantidade = rs.getInt("quantidade");				
				new Caixa(codigo, cedula, quantidade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}	

	public static void atualizaSaldoNoBanco(int cedula, int quantidade) throws Exception{
		try {
			CaixaDAO dao = new CaixaDAO();
			String SQL = "UPDATE caixa set quantidade=? WHERE cedula =? ";
			ps = conn.prepareStatement(SQL);
			ps.setString(1, String.valueOf(quantidade));
			ps.setString(2, String.valueOf(cedula));
			ps.executeUpdate();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			ConnectionFactory.close(conn, ps, rs);
		}
	}
	
}
