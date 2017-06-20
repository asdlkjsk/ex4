package com.choa.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.xml.stream.events.Namespace;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.DBConnector;
import com.choa.util.RowMaker;

@Repository	//("id")
//NoticeDAO noticeDAO = new NoticeDAO();
public class NoticeDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private static final String NAMESPACE = "NoticeMapper.";
	
	/*@Autowired
	private DataSource dataSource;*/
	
	/*public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/	
	
	@Override
	public List<BoardDTO> boardList(RowMaker rowMaker) throws Exception {
		 return sqlSession.selectList(NAMESPACE+"list", rowMaker);
	}


	@Override
	public BoardDTO boardView(int num) throws Exception {
		BoardDTO boardDTO = sqlSession.selectOne(NAMESPACE+"view", num);	//noticeMapper.id(view)를 가져온다, 파라미터는 num
				
		return boardDTO;
	}


	@Override
	public int boardWrite(BoardDTO boardDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"write", boardDTO);
		
		/*int result = sqlSession.insert(NAMESPACE+"write", boardDTO);
		return result;*/
	}
	
	@Override
	public int boardUpdate(BoardDTO boardDTO) throws Exception {
		return sqlSession.update(NAMESPACE+"update", boardDTO);
	}
	

	@Override
	public int boardDelete(int num) throws Exception {
		return sqlSession.delete(NAMESPACE+"delete", num);
	}


	@Override
	public int boardCount() throws Exception {
		return sqlSession.selectOne(NAMESPACE+"count");
	}


	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
