package com.coding.member.controller;

import com.coding.member.Dto.BoardDTO;
import com.coding.member.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class BoardController {
    private final BoardService boardService;

    @GetMapping("/member/write")
    public String saveForm(){ return "write";}

    @PostMapping("/member/write")
    public String save(@ModelAttribute BoardDTO boardDTO) throws IOException {
    boardService.save(boardDTO);
        return "redirect:/main";
    }
    @GetMapping("/main")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList=boardService.findAll();
        model.addAttribute("boardDTOList",boardDTOList);
        return "main";
    }
    @GetMapping("/board/{id}")
    public String findByid(@PathVariable Long id,Model model){
        // 조회수 올리고 게시글 데이터를 죄회 두번의 호출
        boardService.updateHits(id);
        BoardDTO boardDTO=boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "boardDetail";
    }
    @GetMapping("/boardUpdate/{id}")
    public String updateForm(@PathVariable Long id , Model model){
        BoardDTO boardDTO=boardService.findById(id);
        model.addAttribute("boardUpdate",boardDTO);
        return "boardUpdate";
    }
    @PostMapping("/board/boardUpdate")
    public String boardupdate(@ModelAttribute BoardDTO boardDTO,Model model){
        BoardDTO board=boardService.boardupdate(boardDTO);
        model.addAttribute("board",board);
        return "redirect:/main";
        //수정햇을때 조회수 올라가는거
//        return "redirect:/board/"+boardDTO.getId();
        }
        @GetMapping("/boardDelete/{id}")
         public String delete(@PathVariable Long id){
            boardService.delete(id);
            return "redirect:/main";
        }
}
