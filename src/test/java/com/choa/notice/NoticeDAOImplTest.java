package com.choa.notice;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.Spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.choa.util.PageMaker;

public class NoticeDAOImplTest extends MyAbstractTest {	//빈클래스 하나만들어서 상속받으면됨
	
	@Autowired		//이렇게 해야댐
	private NoticeDAOImpl noticeDAO;
	
	@Test
	public void test() throws Exception {
		//NoticeDAO noticeDAO = new NoticeDAO();	//이건 무조건 Null임 / Service 보면 View 할때 datasource를 xml에서 받아 써서 안됨
		/*NoticeDTO noticeDTO = noticeDAO.noticeView(800);
		System.out.println(noticeDTO.getTitle());
		assertNotNull(noticeDTO);*/
		PageMaker pageMaker = new PageMaker(1, 10);
		List<NoticeDTO> ar = noticeDAO.noticeList(pageMaker.getRowMaker());
		assertEquals(0, ar.size());
	}
	
	//@Test
	public void test2() throws Exception {
		int result = noticeDAO.noticeDelete(33);
		assertEquals(1, result);
	}
}
