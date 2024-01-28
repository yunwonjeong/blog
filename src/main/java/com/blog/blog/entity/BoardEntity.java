package com.blog.blog.entity;

import com.blog.blog.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.boot.jaxb.mapping.marshall.FetchTypeMarshalling;

import java.util.ArrayList;
import java.util.List;

// db테이블 역할을 하는 클래스 (테이블 객체를 구성)
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id //pk컬럼 지정, 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) //크기 20, not null
    private String boardWriter;

    @Column // 크기 255, null 가능
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    @Column
    private int boardHits;

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFileEntity> boardFileEntityList = new ArrayList<>();

    // DTO -> Entity 변환 메소드
    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardHits(0);
        boardEntity.setFileAttached(0); // 파일 미첨부
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        return boardEntity;
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {

        return null;
    }
}
