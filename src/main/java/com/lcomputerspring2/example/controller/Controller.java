package com.lcomputerspring2.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lcomputerspring2.example.domain.Board;
import com.lcomputerspring2.example.service.BoardService;

@org.springframework.stereotype.Controller
public class Controller {
	
	@Autowired BoardService boardService;
	
	@RequestMapping("/")
	public String home(Model model) {
		
		List<Board> list = boardService.selectBoardList();
		model.addAttribute("list", list);
		return "/index";
	}
}
