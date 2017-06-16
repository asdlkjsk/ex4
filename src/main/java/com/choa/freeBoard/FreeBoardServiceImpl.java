package com.choa.freeBoard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.choa.board.BoardDTO;
import com.choa.board.BoardService;
import com.choa.util.PageMaker;


@Service
//FreeBoardService freeBoardService = new FreeBoardService();
public class FreeBoardServiceImpl implements BoardService{
   
   @Inject
   private FreeBoardDAOImpl freeBoardDAOImpl;
   
   @Override
   public List<BoardDTO> boardList(int curPage) throws Exception {
      int result = freeBoardDAOImpl.boardCount();
      PageMaker pageMaker = new PageMaker(curPage, result);
      List<BoardDTO> ar = freeBoardDAOImpl.boardList(pageMaker.getRowMaker());
      return ar;
   }

   @Override
   public BoardDTO boardView(int num) throws Exception {
      // TODO Auto-generated method stub
      return freeBoardDAOImpl.boardView(num);
   }

   @Override
   public int boardWrite(BoardDTO boardDTO) throws Exception {
      return freeBoardDAOImpl.boardWrite(boardDTO);
   }

   @Override
   public int boardUpdate(BoardDTO boardDTO) throws Exception {
      return freeBoardDAOImpl.boardUpdate(boardDTO);
   }

   @Override
   public int boardDelete(int num) throws Exception {
      return freeBoardDAOImpl.boardDelete(num);
   }
   


}