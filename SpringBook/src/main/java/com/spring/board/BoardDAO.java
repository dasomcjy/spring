package com.spring.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.common.JDBCUtil;

import jakarta.annotation.Resource;

@Repository("boardDAO")		// Spring Framework ���� �ڵ����� ��ü�� �����Ǿ RAM(�޸�)�� �ε�  
public class BoardDAO implements BoardService {
	
	// DAO : Data Access Object : 
	// DataBase�� CRUD �ϴ� ��ü : Select, Insert, Update, Delete
	
	// 1. JDBC ���� ������ ���� : Connection, Statement/PreparedStatement, ResultSet 
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;			//�ַλ�� 
	private ResultSet rs = null; 
	
	// 2. SQL ������ ��� ����� ��Ƽ� ó�� ���� ������ �Ҵ� : ����� : ��ü �빮�ڷ� ���
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values(select nvl(max(seq),0)+1 from board, ?,?,?)"; 
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=? "; 
	private final String BOARD_DELETE = "delete board where seq=?"; 
	private final String BOARD_GET = "select * from board where seq=?"; 			//DataBase�� ���̺����� 1���� ���ڵ常 ���(�󼼺���)
	private final String BOARD_LIST = "select * from board order by seq desc"; 			//DataBase�� ���̺��� �������� ���ڵ带 LIST (ArrayList() ) 
	
	// 3. �޼ҵ� : 
			// insertBoard(), updateBoard(), deleteBoard(), <== ���� ���� ����. void
			// getBoard() : BoardDTO �� ��Ƽ� ���� , ������ ���ڵ尡 1��
			// getBoardList() : �� ���� ���ڵ带 DTO (1��) , ArrayList�� DTO ��ü�� ��Ƽ� ���� 
	
	// 3-1. �� ��� ó�� �޼ҵ� : insertBoard() 
	@Override
	public void insertBoard(BoardDTO dto) {
		System.out.println("==> JDBC�� insertBoard() ���ó�� - ����");
		//Connection ��ü�� ����ؼ� PreparedStatement ��ü Ȱ��ȭ 
		
		
		try {
			//������ �߻��� ���α׷��� ������� �ʵ��� try catch �������� ó��
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			
			// pstmt �� ? �� �������� �Ҵ�.
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			
			pstmt.executeUpdate();
			
			System.out.println("==> JDBC�� insertBoard() ���ó�� - �Ϸ�");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC�� insertBoard() ���ó�� - ����");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	// 3-2. �� ���� ó�� �޼ҵ� : updateBoard() 
	@Override
	public void updateBoard(BoardDTO dto) {
		System.out.println("==> JDBC�� insertBoard() ���ó�� - ���� ");
		
		try {
			// ��ü ����
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			
			//pstmt �� ? �� dto���� �Ѿ���� ������ �Ҵ�.
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getSeq());
			
			pstmt.executeUpdate();
			
			System.out.println("==> JDBC�� insertBoard() ���ó�� - �Ϸ�");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC�� insertBoard() ���ó�� - ����");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	// 3-3. �� ���� ó�� �޼ҵ� : deleteBoard() 
	@Override
	public void deleteBoard(BoardDTO dto) {
		System.out.println("==> JDBC�� deleteBoard() ���ó�� - ����");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			pstmt.setInt(1, dto.getSeq());
			
			pstmt.executeUpdate();
			
			System.out.println("==> JDBC�� deleteBoard() ���ó�� - �Ϸ�");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC�� deleteBoard() ���ó�� - ����");
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// 3-4. �� ��ȸ ó�� �޼ҵ� : getBoard() : ���ڵ� 1���� DB���� select �ؼ� DTO ��ü�� ��Ƽ� ����
	@Override
	public BoardDTO getBoard(BoardDTO dto) {
		System.out.println("==> JDBC�� getBoard() ���ó�� - ���� ");
		
		//�������� ������ ���� ���� : try ���� �ۿ��� ���� 
		BoardDTO board = null; 
		
		try {
			//��ü ���� : Connection, PreparedStatement
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, dto.getSeq());
			
			//DB�� Select �� ����� rs�� ������.
			rs = pstmt.executeQuery(); 
			
			//rs�� ��� ���� DTO(board) �� �����ؼ� �������� ������
			
			if (rs.next()) {	//rs�� ���� �����Ѵٸ�  , rs�� ���� DTO�� ��Ƽ� ����
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegdate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				
				
			}else {
				System.out.println("���ڵ��� ����� �����ϴ�.");
			}
			
			
			System.out.println("==> JDBC�� getBoard() ���ó�� - �Ϸ� ");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC�� getBoard() ���ó�� - ���� ");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return board; 
		
	}
	
	// 3-5. �� ��� ó�� �޼ҵ� : getBoardList() : ���� ���ڵ� 
		// 
	@Override
	public List<BoardDTO> getBoardList(BoardDTO dto) {
		System.out.println("==> JDBC�� getBoardList() ���ó�� - ����");
		
		//���� ������ ���� ���� : List <= �������̽� , 
			// ArrayList, Vector, LinkedList <== List �������̽� ������ Ŭ����
				//ArrayList : �̱� ������ ȯ��, <== 80%
				//Vector : ��Ƽ������ ȯ�� 
				//LinkedList : ���� ����, ������ ������ ������ ó������
		
		List<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO board = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_LIST);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				do {
					//rs���� ������ 1���� ���ڵ带 board (DTO)
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegdate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
					
					//boardList : ArrayList�� add () �޼ҵ带 ����ؼ� board(DTO) �� ����
					boardList.add(board);
					
				}while (rs.next());
				
			}else {
				System.out.println("���̺��� ���ڵ尡 ����ֽ��ϴ�.");
			}
			
			System.out.println("==> JDBC�� getBoardList() ���ó�� - �Ϸ�");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC�� getBoardList() ���ó�� - ����");
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return boardList;
	}
	
	
	
	
}