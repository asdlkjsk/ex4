package com.choa.notice;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.choa.board.BoardDTO;
import com.choa.util.PageMaker;



public class NoticeDAOImplTest extends MyAbstractTest {	//빈클래스 하나만들어서 상속받으면됨
	
	@Autowired
	private NoticeDAOImpl noticeDAOImpl;
	
	@Test
	public void connetonTest() throws Exception {
PageMaker pageMaker = new PageMaker(1, 20);
		
		List<BoardDTO> ar = noticeDAOImpl.boardList(pageMaker.getRowMaker(), "writer", "choa");
		System.out.println(ar.get(0).getWriter());
		System.out.println(ar.get(1).getWriter());
		assertNotEquals(0, ar.size());
		
		
		
		/*int result = noticeDAOImpl.boardDelete(102);
		
		assertNotNull(result);*/
	}
	
	public void countTest() throws Exception {
		int count = noticeDAOImpl.boardCount();
		
		assertNotEquals(0, count);
	}
		
	
	/*@Test
	public void noticeUpdate() throws Exception{
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setTitle("updatetest!!");
		noticeDTO.setContents("싸인회 하는겁니까?");
		noticeDTO.setNum(401);
		int result = noticeDAOImpl.boardUpdate(noticeDTO);
		System.out.println(result);
		assertEquals(1, result);
	}*/
	   
	  
}
