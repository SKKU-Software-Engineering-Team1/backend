package com.example.demo.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor // constructor 생성
@NoArgsConstructor // getter, setter 생성
public class Board {

    @Id @GeneratedValue
    @Column(name = "BOARD_ID")
    private Long Id;

    private String boardWriter;

    private String boardTitle;

    @Column(columnDefinition = "TEXT")
    private String boardContent;

    // Java에서 Date도 사용하지만 시간 + 날짜 모두 사용하고
    // 기존 Date 클래스 자체의 문제도 어느정도 해결을 위해 사용
    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime boardCreatedAt;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime boardUpdatedAt;

    // user
    @OneToMany(mappedBy = "board")
    @Builder.Default
    private List<BoardImage> boardImages = new ArrayList<>();

}
