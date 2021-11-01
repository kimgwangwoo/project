package com.woo.project.controller;

import com.woo.project.model.Board;
import com.woo.project.model.User;
import com.woo.project.repository.BoardRepository;


import com.woo.project.repository.UserRepository;
import com.woo.project.service.BoardService;
import com.woo.project.service.UserService;
import com.woo.project.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/login")
public class maincontroller {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @GetMapping("main")
    public String main() {
        return "main";
    }


    @GetMapping("/board")
    public String board(Model model, @PageableDefault(size = 4) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String searchText) {
        //Page<Board> boards= boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentsContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);
        return "board";
    }

    @GetMapping("/boardwrite")
    public String boardwrite(Model model, @RequestParam(required = false) Long id, MultipartFile file) {
        if (id == null) {
            Board board = new Board();
            board.setRead_num(0);
            model.addAttribute("board", board);

        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "boardwrite";
    }

    @PostMapping("/boardwrite")
    public String boardwriteSubmit(@Valid Board board, BindingResult bindingResult, Authentication authentication, MultipartFile file) throws Exception {
        boardValidator.validate(board, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/boardwrite";
        }
        String username = authentication.getName();
        boardService.save(username, board, file);

        return "redirect:/login/board";
    }

    @GetMapping("/boarddetail")
    public String boarddetail(Model model, @RequestParam(required = false) Long id) {
        Board board = boardRepository.findById(id).orElse(null);
        board.setRead_num(board.getRead_num() + 1);
        boardRepository.save(board);
        board.setFilepath(board.getFilepath());
        board.setFilename(board.getFilename());
        model.addAttribute("board", board);

        return "/boarddetail";
    }

   /* @PostMapping("currentUser")
    public String inactiveUser(@Valid User user, Authentication authentication, HttpServletRequest request) {
        String referer = request.getHeader("Referer");
        String currentUsername = authentication.getName();

        if (currentUsername.equals(user.getUsername())) {
            // getEnable toggle
            if (user.getEnabled()) {
                userService.updateEnabled(user.getId(), false);
            } else {
                userService.updateEnabled(user.getId(), true);
            }
        }

        return "redirect:" + referer;

    }*/
   @GetMapping("/delete")
   public String delete(@RequestParam(required = true) Long id, Authentication authentication) {

       Board board             = boardService.findById(id);
       String  boardUsername = board.getUser().getUsername();
       String  currentUsername = authentication.getName();

       // 사용자 인증. 원글 사용자면 삭제
       if (currentUsername.equals(boardUsername))
           boardService.deleteByBoard(board);

       return "redirect:/login/board";
   }

}