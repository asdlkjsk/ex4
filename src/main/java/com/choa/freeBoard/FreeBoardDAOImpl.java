package com.choa.freeBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDTO;
import com.choa.util.DBConnector;
import com.choa.util.RowMaker;

@Repository
public class FreeBoardDAOImpl implements BoardDAO {
   
   @Inject
   private DataSource dataSource;

   @Override
   public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
      Connection con =dataSource.getConnection();
      PreparedStatement st=null;
      ResultSet rs = null;
      String sql="select * from "
            + "(select rownum R, F.* from "
            + "(select * from qna order by ref desc, step asc) F) "
            + "where R between ? and ?";
      
      List<BoardDTO> ar = new ArrayList<BoardDTO>();
      
      st = con.prepareStatement(sql);
      st.setInt(1, rowMaker.getStartRow());
      st.setInt(2, rowMaker.getLastRow());
      rs = st.executeQuery();
      
      while(rs.next()){
         FreeBoardDTO freeBoardDTO = new FreeBoardDTO();
         freeBoardDTO.setNum(rs.getInt("num"));
         freeBoardDTO.setWriter(rs.getString("writer"));
         freeBoardDTO.setTitle(rs.getString("title"));
         freeBoardDTO.setContents(rs.getString("contents"));
         freeBoardDTO.setReg_date(rs.getDate("reg_date"));
         freeBoardDTO.setHit(rs.getInt("hit"));
         freeBoardDTO.setRef(rs.getInt("ref"));
         freeBoardDTO.setStep(rs.getInt("step"));
         freeBoardDTO.setDepth(rs.getInt("depth"));
         ar.add(freeBoardDTO);   
      }
      // close
      DBConnector.disConnector(rs, st, con);
      System.out.println(ar.size());
      return ar;
   }

   @Override
   public BoardDTO boardView(int num) throws Exception {
      Connection con =dataSource.getConnection();
      PreparedStatement st=null;
      ResultSet rs = null;
      String sql="select * from freeboard where num=?";
      NoticeDTO noticeDTO =null;
      
      st = con.prepareStatement(sql);
      st.setInt(1, num);
      rs = st.executeQuery();
      if(rs.next()){
         noticeDTO = new NoticeDTO();
         noticeDTO.setNum(rs.getInt("num"));
         noticeDTO.setWriter(rs.getString("writer"));
         noticeDTO.setTitle(rs.getString("title"));
         noticeDTO.setContents(rs.getString("contents"));
         noticeDTO.setReg_date(rs.getDate("reg_date"));
         noticeDTO.setHit(rs.getInt("hit"));      
      }
      
      //close
      DBConnector.disConnector(rs, st, con);
   
      return noticeDTO;
   }

   @Override
   public int boardWrite(BoardDTO boardDTO) throws Exception {
      Connection con =dataSource.getConnection();
      PreparedStatement st=null;
      int result=0;
      
      String sql="insert into freeboard values(notice_seq.nextval, ?,?,?, sysdate, 0)";
      
      st = con.prepareStatement(sql);
      st.setString(1, boardDTO.getWriter());
      st.setString(2, boardDTO.getTitle());
      st.setString(3, boardDTO.getContents());
      result =st.executeUpdate();
      
      //close
      DBConnector.disConnector(st, con);
      
      
      return result;
   }

   @Override
   public int boardUpdate(BoardDTO boardDTO) throws Exception {
      Connection con = dataSource.getConnection();
      PreparedStatement st =null;
      String sql =" update freeboard set writer=?, title=? , contents=? where num=?";
      int result =0;
      
      st = con.prepareStatement(sql);
      st.setString(1, boardDTO.getWriter());
      st.setString(2, boardDTO.getTitle());
      st.setString(3, boardDTO.getContents());
      st.setInt(4, boardDTO.getNum());
      result =st.executeUpdate();

      //close
      DBConnector.disConnector(st, con);
      
      return result;
   }

   @Override
   public int boardDelete(int num) throws Exception {
      Connection con = dataSource.getConnection();
      PreparedStatement st =null;
      String sql =" delete freeboard where num=?";
      int result=0;
      
      st = con.prepareStatement(sql);
      st.setInt(1, num);
      result = st.executeUpdate();
      
      //close
      DBConnector.disConnector(st, con);
      
      return result;
   }

   @Override
   public int boardCount() throws Exception {
      Connection con = dataSource.getConnection();
      PreparedStatement st = null;
      ResultSet rs = null;
      int result=0;
      String sql ="select nvl(count(num),0) from freeboard ";
      
         st= con.prepareStatement(sql);
         rs = st.executeQuery();
         rs.next();
         result = rs.getInt(1);
         
         //close
         DBConnector.disConnector(rs, st, con);
   
      return result;
   }

   @Override
   public void boardHit(int num) throws Exception {
      // TODO Auto-generated method stub
      
   }
   
   
}