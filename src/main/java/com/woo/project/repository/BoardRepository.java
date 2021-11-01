package com.woo.project.repository;

import com.woo.project.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContents(String title,String contents);

    Page<Board> findByTitleContainingOrContentsContaining(String title,String contents,Pageable pageable);




}
