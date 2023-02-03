package com.springlab.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springlab.member.MemberDTO;
import com.springlab.common.JDBCUtil;

@Repository("memberDAO")
public class MemberDAO {
	
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private final String MEMBER_LOGIN = "select * from MB where id=? and pass=?"; 
	private final String MEMBER_INSERT = "insert into MB(idx, id, pass, name , email , age , weight ) values((select nvl(max(idx),0)+1 from MB), ?,?,?,?,?,?)";
	private final String MEMBER_UPDATE = "update MB set id=?, pass=? , name=? , email=? , age=? , weight=? where idx=? ";
	private final String MEMBER_DELETE = "delete MB where idx=?"; 
	private final String MEMBER_GET = "select * from MB where idx=?"; 
	private final String MEMBER_LIST = "select * from MB order by idx desc"; 	
	
	
	
	// 글 로그인 처리 메소드 : loginMB()
	
	public boolean loginMB (MemberDTO dto) {
		System.out.println("==> JDBC로 loginMB() 기능처리 - 시작");
		//Connection 객체를 사용해서 PreparedStatement 객체 활성화 
	
		
		boolean passs = false;
		
		try {
			//오류가 발생시 프로그램이 종료되지 않도록 try catch 블락으로 처리
			
			conn = JDBCUtil.getConnection();
			//select * from MB where id=? and pass=?"; 
			pstmt = conn.prepareStatement(MEMBER_LOGIN);
			
			// pstmt 에 ? 에 변수값을 할당.
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPass());

			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				passs = true;	
			}else {
				passs = false;
			}
			
			System.out.println("==> JDBC로 LoginMB() 기능처리 - 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 LoginMB() 기능처리 - 실패");
		}finally {
			JDBCUtil.close(rs , pstmt, conn);
			System.out.println("모든 객체가 잘 close() 되었습니다.");
		}
	
		return  passs;
	}
	
	// 글 등록 처리 메소드 : insertMB() 

		public void insertMB(MemberDTO dto) {
			System.out.println("==> JDBC로 insertBoard() 기능처리 - 시작");
			//Connection 객체를 사용해서 PreparedStatement 객체 활성화 
			
			
			try {
				//오류가 발생시 프로그램이 종료되지 않도록 try catch 블락으로 처리
				
				conn = JDBCUtil.getConnection();
				//insert into MB((idx, id, pass, name , email , age , weight ) 
				//values((select nvl(max(idx),0)+1 from MB), ?,?,?,?,?,?)"; 
				pstmt = conn.prepareStatement(MEMBER_INSERT);
				
				// pstmt 에 ? 에 변수값을 할당.
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPass());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.getEmail());
				pstmt.setInt(5, dto.getAge());
				pstmt.setDouble(6, dto.getWeight());
				
				pstmt.executeUpdate();
				
				System.out.println("==> JDBC로 insertMB() 기능처리 - 완료");
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("==> JDBC로 insertMB() 기능처리 - 실패");
			}finally {
				JDBCUtil.close(pstmt, conn);
				System.out.println("모든 객체가 잘 close() 되었습니다.");
			}
			
		}
		
		// 3-2. 글 수정 처리 메소드 : updateMB() 
		public void updateMB(MemberDTO dto) {
			System.out.println("==> JDBC로 updateMB() 기능처리 - 시작 ");
			
			try {
				// 객체 생성
				conn = JDBCUtil.getConnection();
				// MEMBER_UPDATE = update MB set id=?, pass=? , name=? , email=? , age=? , weight=? where idx=?
				pstmt = conn.prepareStatement(MEMBER_UPDATE);
				
				//pstmt 의 ? 에 dto에서 넘어오는 변수값 할당.
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPass());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.getEmail());
				pstmt.setInt(5, dto.getAge());
				pstmt.setDouble(6, dto.getWeight());
				pstmt.setInt(7, dto.getIdx());
				
				pstmt.executeUpdate();
				
				System.out.println("==> JDBC로 updateMB() 기능처리 - 완료");
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("==> JDBC로 updateMB() 기능처리 - 실패");
			}finally {
				JDBCUtil.close(pstmt, conn);
			}
			
		}
		
		// 3-3. 글 삭제 처리 메소드 : deleteMB() 

		public void deleteMB(MemberDTO dto) {
			System.out.println("==> JDBC로 deleteMB() 기능처리 - 시작");
			
			try {
				conn = JDBCUtil.getConnection();
				//MEMBER_DELETE = "delete MB where idx=?"; 
				pstmt = conn.prepareStatement(MEMBER_DELETE);
				pstmt.setInt(1, dto.getIdx());
				
				pstmt.executeUpdate();
				
				System.out.println("==> JDBC로 deleteMB() 기능처리 - 완료");
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("==> JDBC로 deleteMB() 기능처리 - 실패");
			}finally {
				JDBCUtil.close(pstmt, conn);
			}
		}
		
		// 3-4. 글 조회 처리 메소드 : getMB() : 레코드 1개를 DB에서 select 해서 DTO 객체에 담아서 리턴
		
		public MemberDTO getMB(MemberDTO dto) {
			System.out.println("==> JDBC로 getMB() 기능처리 - 시작 ");
			
			//리턴으로 돌려줄 변수 선언 : try 블락 밖에서 선언 
			MemberDTO member = new MemberDTO(); 
			
			try {
				//객체 생성 : Connection, PreparedStatement
				conn = JDBCUtil.getConnection();
				//MEMBER_GET = "select * from MB where idx=?"; 
				pstmt = conn.prepareStatement(MEMBER_GET);
				pstmt.setInt(1, dto.getIdx());
				
				//DB를 Select 한 결과를 rs에 저장함.
				rs = pstmt.executeQuery(); 
				
				//rs에 담긴 값을 DTO(board) 에 저장해서 리턴으로 돌려줌
				
				if (rs.next()) {	//rs의 값이 존재한다면  , rs의 값을 DTO에 담아서 리턴
					member.setIdx(rs.getInt("IDX"));
					member.setId(rs.getString("ID"));
					member.setPass(rs.getString("PASS"));
					member.setName(rs.getString("NAME"));
					member.setEmail(rs.getString("EMAIL"));
					member.setAge(rs.getInt("AGE"));
					member.setWeight(rs.getDouble("WEIGHT"));
					member.setRegdate(rs.getDate("REGDATE"));
					member.setCnt(rs.getInt("CNT"));
					
					
				}else {
					System.out.println("레코드의 결과가 없습니다.");
				}
				
				
				System.out.println("==> JDBC로 getMB() 기능처리 - 완료 ");
				
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("==> JDBC로 getMB() 기능처리 - 실패 ");
			}finally {
				JDBCUtil.close(rs, pstmt, conn);
			}
			
			return member; 
			
		}
		
		
		// 3-5. 글 목록 처리 메소드 : getBoardList() : 많은 레코드 
				// 

			public List<MemberDTO> getMBList(MemberDTO dto) {
				System.out.println("==> JDBC로 getMBList() 기능처리 - 시작");
				
				//리턴 돌려줄 변수 선언 : List <= 인터페이스 , 
					// ArrayList, Vector, LinkedList <== List 인터페이스 구현한 클래스
						//ArrayList : 싱글 쓰레드 환경, <== 80%
						//Vector : 멀티쓰레드 환경 
						//LinkedList : 자주 수정, 삭제시 성능이 빠르게 처리도미
				
				List<MemberDTO> MemberList = new ArrayList<MemberDTO>();
					MemberDTO member ;
				
				try {
					conn = JDBCUtil.getConnection();
					//MEMBER_LIST = "select * from MB order by idx desc"; 	
					pstmt = conn.prepareStatement(MEMBER_LIST);
					
					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						do {
							//DTO 객체는 여기서 만들어야함. ( 별도의 객체에 담기게됨 )
							member = new MemberDTO();
							
							//rs에서 가져온 1개의 레코드를 board (DTO)
							member.setIdx(rs.getInt("IDX"));
							member.setId(rs.getString("ID"));
							member.setPass(rs.getString("PASS"));
							member.setName(rs.getString("NAME"));
							member.setEmail(rs.getString("EMAIL"));
							member.setAge(rs.getInt("AGE"));
							member.setWeight(rs.getDouble("WEIGHT"));
							member.setRegdate(rs.getDate("REGDATE"));
							member.setCnt(rs.getInt("CNT"));
							
							//boardList : ArrayList에 add () 메소드를 사용해서 board(DTO) 를 저장
							MemberList.add(member);
							
						}while (rs.next());
						
					}else {
						System.out.println("테이블에 레코드가 비어있습니다.");
					}
					
					System.out.println("==> JDBC로 getMemberList() 기능처리 - 완료");
					
				}catch (Exception e) {
					e.printStackTrace();
					System.out.println("==> JDBC로 getMemberList() 기능처리 - 실패");
				}finally {
					JDBCUtil.close(rs, pstmt, conn);
				}
				
				return MemberList;
			}
			
		
}
