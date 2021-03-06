package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.dto.BookDto;
import bookmall.vo.BookVo;

public class BookDao {
	
	public static List<BookDto> findAll() {
		List<BookDto> result = new ArrayList<BookDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConn.getConn();
			
			String sql = "SELECT a.no    ,"
					+ "          a.title ,"
					+ "          a.price , "
					+ "	         b.name  "
					+ "   FROM   book a"
					+ "   INNER  JOIN category b ON a.category_no = b.no";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				int price = rs.getInt(3);
				String category_name = rs.getString(4);
				
				BookDto dto = new BookDto();
				dto.setNo(no);
				dto.setTitle(title);
				dto.setPrice(price);
				dto.setCategory_name(category_name);
				
				result.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		System.out.println("***** Book List *****");
		
		for(BookDto dto : result) {
			System.out.println(dto);
		}
		
		System.out.println("*** Book List END ***");
		
		return result;
	}
	
	public static boolean insert(BookVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConn.getConn();
			
			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setInt(3, vo.getCategory_no());
			
			int count = pstmt.executeUpdate();
			
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		if(result) System.out.println("*** Insert book[o] ***");
		else System.out.println("*** Insert book[x] ***");
		
		return result;
	}
	
	public static boolean deleteAll() {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConn.getConn();
			
			String sql = "delete from book where no > '0'";

			pstmt = conn.prepareStatement(sql);
			
			int count = pstmt.executeUpdate();

			result = count >= 1;
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		if(result) System.out.println("** Delete book table[o] **");
		else System.out.println("** Delete book table[x] **");
		
		return result;
	}
}
