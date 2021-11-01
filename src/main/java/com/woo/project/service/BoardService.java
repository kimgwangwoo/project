package com.woo.project.service;

import com.woo.project.model.Board;
import com.woo.project.model.User;
import com.woo.project.repository.BoardRepository;
import com.woo.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();



        String fileName = uuid+"_"+file.getOriginalFilename();
        File saveFile = new File(projectPath,fileName);
        file.transferTo(saveFile);


      User user=  userRepository.findByUsername(username);
        board.setUser(user);
        board.setFilename(fileName);
        board.setFilepath("/files/"+fileName);

      return boardRepository.save(board);
    }


    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }
    public void deleteByBoard(Board board) {

        // article 게시물 삭제
        boardRepository.deleteById(board.getId());
    }
}
