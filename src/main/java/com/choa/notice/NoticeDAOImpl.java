package com.choa.notice;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.choa.board.BoardDAO;
import com.choa.board.BoardDTO;
import com.choa.util.ListInfo;
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
	public List<BoardDTO> boardList(ListInfo listInfo) throws Exception {
	/*	HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rowMaker", rowMaker);
		map.put("search", search);
		map.put("find", find);*/
		return sqlSession.selectList(NAMESPACE+"list", listInfo);
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
	public int boardCount(ListInfo listInfo) throws Exception {
		return sqlSession.selectOne(NAMESPACE+"count", listInfo);
	}


	@Override
	public void boardHit(int num) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
