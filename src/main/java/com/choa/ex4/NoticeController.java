package com.choa.ex4;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.board.BoardDTO;
import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeServiceImpl;
import com.choa.util.RowMaker;

@Controller
@RequestMapping(value = "/notice/**")
public class NoticeController {
	
	@Autowired	//data type
	private NoticeServiceImpl noticeService;
	
	//list
	@RequestMapping(value = "noticeList", method = RequestMethod.GET)
	public String noticeList(Model model, @RequestParam(defaultValue = "1") Integer curPage, String search, String kind) throws Exception{
		List<BoardDTO> ar = noticeService.boardList(curPage);
		model.addAttribute("list" , ar);
		model.addAttribute("board", "notice");
		return "board/boardList";
	}
	
	//view
	@RequestMapping(value = "noticeView", method = RequestMethod.GET)
	public void noticeView(Integer num, Model model) throws Exception{
		BoardDTO boardDTO = noticeService.boardView(num);
		model.addAttribute("dto", boardDTO);
	}	
	
	//Form
	@RequestMapping(value = "noticeWrite", method = RequestMethod.GET)
	public void noticeWrite(Model model){
		model.addAttribute("path", "Write");
	}
	
	//write
		@RequestMapping(value = "noticeWrite", method = RequestMethod.POST)
		public String noticeWrite(BoardDTO boardDTO, RedirectAttributes rd) throws Exception{
			int result=noticeService.boardWrite(boardDTO);
			String message = "FAIL";
			if(result>0){
				message = "SUCCESS";
			}
			rd.addFlashAttribute("message", message);	//주소창에 파라미터는 안써짐
			//rd.addAttribute(arg0);	//주소창에 파라미터가 남음
			/*return "notice/result"; forward*/
			return "redirect:noticeList";	//redirect
			//return "redirect:/notice/noticeList?curPage=2";	//redirect 파라미터 넘기는법 / redirect 는 직접 주소쳐서 들어가는거임 ㅎ
		}
			
	
	//Form
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public String noticeUpdate(Integer num, Model model) throws Exception{
		BoardDTO noticeDTO = noticeService.boardView(num);
		model.addAttribute("dto", noticeDTO);
		model.addAttribute("path", "Update");
		return "notice/noticeWrite";
	}
	
	//update
	@RequestMapping(value = "noticeUpdate", method = RequestMethod.POST)
	public String noticeUpdate(BoardDTO boardDTO, RedirectAttributes rd) throws Exception{
		int result = noticeService.boardUpdate(boardDTO);
		String message = "FAIL";
		if(result>0){
			message="SUCCESS";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList?curPage=1";
	}
	
	@RequestMapping(value = "noticeDelete", method = RequestMethod.GET)
	public String noticeDelete(Integer num, RedirectAttributes rd) throws Exception{
		int result = noticeService.boardDelete(num);
		String message = "FFFF";
		if(result>0){
			message = "SSS";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:/notice/noticeList";
	}
}
