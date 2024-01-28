package com.blog.blog.dto;

import com.blog.blog.entity.BoardEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

//DTO (Data Transfer Object)
@Getter
@Setter
@ToString
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id; //id
    private String boardWriter; //작성자
    private String boardPass; //작성글 비밀번호
    private String boardTitle; //작성글 제목
    private String boardContents; //작성글 내용
    private int boardHits; //게시글 조회수
    private LocalDateTime boardCreatedTime; //게시글 작성시간
    private LocalDateTime boardUpdatedTime; //게시글 수정시간

    private MultipartFile boardFile; // save.html -> Controller 파일 받는 용도
    private String originalFileName; // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름 (파일명 중복제거용)
    private int fileAttached; // 파일 첨부 여부 (첨부1, 미첨부0)

    // 생성자
    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }

    //DTO -> Entity 변환
    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());
        return boardDTO;
    }
}
