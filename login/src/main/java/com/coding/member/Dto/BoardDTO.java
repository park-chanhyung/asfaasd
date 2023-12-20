package com.coding.member.Dto;

import com.coding.member.Entity.BoardEntity;
import com.coding.member.Entity.BoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;

    private MultipartFile boardFile;  // write.html-> Controller 파일 담는 용도
    private String originalFileName;   // 원본 파일 이름
    private String storedFileName; // 서버 저장용 파일 이름
    private int fileAttached;  //파일첨부

    public BoardDTO(Long id, String boardTitle, String boardContents, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardCreatedTime = boardCreatedTime;
        this.boardHits = boardHits;
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getBoardCreatedTime());
        if (boardEntity.getFileAttached() == 0) {
            boardDTO.setFileAttached(boardEntity.getFileAttached()); //1
        } else {
            boardDTO.setFileAttached(boardEntity.getFileAttached());
            boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
            boardDTO.setStoredFileName(boardEntity.getBoardFileEntityList().get(0).getStoredFileName());

        }

        return boardDTO;
    }

}