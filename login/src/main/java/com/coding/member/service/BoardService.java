package com.coding.member.service;

import com.coding.member.Dto.BoardDTO;
import com.coding.member.Entity.BoardEntity;
import com.coding.member.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public void save(BoardDTO boardDTO){
//    save 는 entity를 써야해서 dto->entity 변환
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);

    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        log.info("list : {}", boardEntityList);
        List<BoardDTO> boardDTOList=new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
                boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
}
