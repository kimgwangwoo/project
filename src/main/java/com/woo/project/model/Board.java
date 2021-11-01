package com.woo.project.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    @Column(insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String board_date;
    private Integer read_num;
    private String filename;
    private String filepath;


    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;



}
