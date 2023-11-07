package com.example.demo.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class BoardImage {

    @Id @GeneratedValue
    @Column(name="BOARD_IMAGE_ID")
    private Long Id;

    // 외래키 표시, 연관관계의 주인이라는 의미, board랑 조인돼서 가져옴.
    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Column(columnDefinition = "TEXT")
    private String BoardImagePath;
}
