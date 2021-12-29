package model1.board;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class BoardDAO extends JDBConnect {
	// 데이터베이스 연결
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	// 검색 조건에 맞는 게시물의 개수를 반환한다 
	public int selectCount(Map<String,Object> map) {
		int totalCount=0;
		
		String query="select count(*) from board";
		if(map.get("searchWord")!=null) {
			query+=" where "+map.get("searchField")+" "
					+"like '%"+map.get("searchWord")+"%'";
		}
		
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			rs.next();
			totalCount=rs.getInt(1);
		}catch(Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	} 
	
	// 검색 조건에 맞는 게시물 목록을 반환한다.
	public List<BoardDTO> selectList(Map<String,Object> map){
		List<BoardDTO> bbs=new Vector<BoardDTO>();
		
		String query="select * from board";
		if(map.get("searchWord")!=null) {
			query+=" where "+map.get("searchField")+" "
					+" like '%"+map.get("searchWord")+"%'";
		}
		query+=" order by num desc ";
		
		try{
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			
			while(rs.next()) {
				BoardDTO dto=new BoardDTO();

                dto.setNum(rs.getString("num"));          // 일련번호
                dto.setTitle(rs.getString("title"));      // 제목
                dto.setContent(rs.getString("content"));  // 내용
                dto.setPostdate(rs.getDate("postdate"));  // 작성일
                dto.setId(rs.getString("id"));            // 작성자 아이디
                dto.setVisitcount(rs.getString("visitcount"));  // 조회수
				
                bbs.add(dto);
			}
		}catch(Exception e) {
			System.out.println("게시물 조회 중 오류 발생");
			e.printStackTrace();
		}
		
		return bbs;
	}
	
	// 검색 조건에 맞는 게시물 목록을 반환한다(페이징 기능 지원)
	public List<BoardDTO> selectListPage(Map<String,Object> map){
		List<BoardDTO> bbs=new Vector<BoardDTO>();
		
		String query="select * from "
						+" (select rownum rn, tb.* "
						+"    from (select * from board ";
		if(map.get("searchWord")!=null) {
			query+="				where "+map.get("searchField")
				+ " 				like '%"+map.get("searchWord")+"%' ";
		}
		query+=" 					order by num desc) tb "
				+ "			)"
				+" 	  where rn between ? and ?";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				BoardDTO dto=new BoardDTO();
				dto.setNum(rs.getString("num"));          // 일련번호
                dto.setTitle(rs.getString("title"));      // 제목
                dto.setContent(rs.getString("content"));  // 내용
                dto.setPostdate(rs.getDate("postdate"));  // 작성일
                dto.setId(rs.getString("id"));            // 작성자 아이디
                dto.setVisitcount(rs.getString("visitcount"));  // 조회수

                bbs.add(dto);  // 결과 목록에 저장
			}
			
		}catch(Exception e) {
			System.out.println("페이징 처리 목록 보여주는 중 오류 발생");
			e.printStackTrace();
		}
		return bbs;
	}
	
	
	// 게시글 데이터를 받아 DB에 추가합니다. 
	public int insertWrite(BoardDTO dto) {
		int result=0;
		try {
			String query="insert into board (num,title,content,id,visitcount) "
					+ " values( seq_board_num.nextval,?,?,?,0) ";
			psmt=con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result=psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 지정한 게시물을 찾아 내용을 반환합니다. 
	public BoardDTO selectView(String num) {
		BoardDTO dto=new BoardDTO();
		
		String query="select b.*, m.name "
				+" from board b join member m "
				+" on b.id=m.id " 
				+" where b.num=? ";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, num);
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				dto.setName(rs.getString("name"));
			}
		}catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 지정한 게시물의 조회수를 1씩 증가시킨다 
	public void updateVisitCount(String num) {
		String query="update board "
				+" set visitcount=visitcount+1 "
				+" where num=?";
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
		}catch(Exception e) {
			System.out.println("게시물 조회수 증가 중 예외발생");
			e.printStackTrace();
		}
	}
	
	// 지정한 게시물을 수정합니다.
	public int updateEdit(BoardDTO dto) {
		int result=0;
		
		String query=" update board "
				+" set title=?, content=? "
				+" where num=? ";
		
		try {
			psmt=con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			
			result=psmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("게시물 수정 중 오류 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 지정한 게시물을 삭제합니다. 
	public int deletePost(BoardDTO dto) {
		int result=0;
		try {
			String query="delete from board where num=?";
			psmt=con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			
			result=psmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("게시글 삭제 중 오류발생");
			e.printStackTrace();
		}
		return result;
	}
}
