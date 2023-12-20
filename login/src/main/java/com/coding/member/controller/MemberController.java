package com.coding.member.controller;

import com.coding.member.Dto.MemberDTO;
import com.coding.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;
    //회원가입 페이지 출력 요청
    @GetMapping("/member/save")
    public String saveForm(){return "save";}

    @PostMapping("/member/save")
    public String save(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "index";
    }

    @PostMapping("/member/Id-check")
    public @ResponseBody String emailCheck(@RequestParam("memberId") String memberId) {
        String checkResult = memberService.Idcheck(memberId);
        return checkResult;
    }

    @GetMapping("/member/index")
    public String loginForm(){return "index";}


    @PostMapping("/member/index")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginresult = memberService.login(memberDTO);
        if (loginresult != null) {
            session.setAttribute("loginId", loginresult.getMemberId());
            return "redirect:/main";
        } else {
            return "index";
        }
    }
        @GetMapping("/member/logout")
        public String logout(HttpSession session){
//        invalidate  세션 무효화
            session.invalidate();
            return "index";
        }
        @GetMapping("/member/update")
        public String updateForm(HttpSession session, Model model){
            String myId= (String)session.getAttribute("loginId");
            MemberDTO memberDTO=memberService.updateForm(myId);
            model.addAttribute("updateMember",memberDTO);
            return "update";
        }
        @PostMapping("/member/update")
        public String update(@ModelAttribute MemberDTO memberDTO){
            memberService.update(memberDTO);
//            return "redirect:/member/"+memberDTO.getId();
            return "redirect:/main";
        }

//    @GetMapping("/member/")
//    public String findAll(Model model) {
//        List<MemberDTO> memberDTOList= memberService.findAll();
//        model.addAttribute("memberList",memberDTOList);
//        return "list";
//    }
    @GetMapping("/member/detail")
    public String detail(HttpSession session, Model model){
        String myId = (String) session.getAttribute("loginId");
        if (myId == null) {
            // 로그인이 안 된 경우 처리 (예: 로그인 페이지로 redirect)
            return "redirect:/index";
        }
        MemberDTO myInfo = memberService.findById(myId);
        model.addAttribute("myInfo", myInfo);
        return "detail";
    }

    //@PathVariable 이 경로로상에 있는 이 {id}값을 밑에 long id 에 넘겨줌
//    public String findById(@PathVariable Long id , Model model){
//        MemberDTO memberDTO = memberService.findById(id);
//        model.addAttribute("member",memberDTO);
//        return "detail";
//    }
    @GetMapping("/member/delete")
    public String deleteById(HttpSession session, Model model){
        String myId = (String) session.getAttribute("loginId");
        if (myId != null) {
            MemberDTO deleteId = memberService.deleteById(myId);
            model.addAttribute("deleteId", deleteId);
            // 세션에서 로그인 정보를 삭제
            session.removeAttribute("loginId");
            return "index";
        } else {
            // 로그인이 안 된 경우 처리 (예: 로그인 페이지로 redirect)
            return "redirect:/index";
        }
    }
//    @GetMapping("/member/")
//    public String getId(@PathVariable Long id , Model model2) {
//        MemberDTO memberDTO = memberService.findById(id);
//        model2.addAttribute("member",memberDTO);
//        return "main";
//    }
}
