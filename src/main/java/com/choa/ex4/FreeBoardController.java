package com.choa.ex4;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.choa.freeBoard.FreeBoardServiceImpl;

@Controller
@RequestMapping(value = "/freeBoard/**")
public class FreeBoardController {

	@Autowired
	private FreeBoardServiceImpl freeBoardServiceImpl;
	
	@RequestMapping(value = "freeBoardList", method = RequestMethod.GET)
	public String boardList(@RequestParam(defaultValue="1") Integer curPage, Model model) throws Exception {		
		model.addAttribute("board", "freeBoard");
		model.addAttribute("list", freeBoardServiceImpl.boardList(curPage));
		return "board/boardList";		
	}
	
}
