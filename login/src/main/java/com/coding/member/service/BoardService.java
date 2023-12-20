package com.coding.member.service;

import com.coding.member.Dto.BoardDTO;
import com.coding.member.Entity.BoardEntity;
import com.coding.member.Entity.BoardFileEntity;
import com.coding.member.repository.BoardFileRepository;
import com.coding.member.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    public void save(BoardDTO boardDTO) throws IOException {
//    save 는 entity를 써야해서 dto->entity 변환

        if (boardDTO.getBoardFile().isEmpty()) {
            // 첨부 파일 없음.
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        }else {
            MultipartFile boardFile = boardDTO.getBoardFile();
            String originalFilename = boardFile.getOriginalFilename();
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;
            String savePath = "C:/springboot_img/" + storedFileName;
            boardFile.transferTo(new File(savePath));
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long saveId = boardRepository.save(boardEntity).getId();
            BoardEntity board= boardRepository.findById(saveId).get();

            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board, originalFilename, storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
    }


    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        log.info("list : {}", boardEntityList);
        List<BoardDTO> boardDTOList=new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
                boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);

    }

    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> boardRepositoryById = boardRepository.findById(id);
        if(boardRepositoryById.isPresent()){
                BoardEntity boardEntity=boardRepositoryById.get();
                BoardDTO boardDTO=BoardDTO.toBoardDTO(boardEntity);
                return boardDTO;
        }else {
            return null;
        }
    }


    public BoardDTO boardupdate(BoardDTO boardDTO) {
    //id값이 존재한채로 save가 넘어오면 업데이트
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
