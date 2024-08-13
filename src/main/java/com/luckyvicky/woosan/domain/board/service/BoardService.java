package com.luckyvicky.woosan.domain.board.service;

import com.luckyvicky.woosan.domain.board.dto.*;
import com.luckyvicky.woosan.global.util.PageRequestDTO;
import com.luckyvicky.woosan.global.util.PageResponseDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
public interface BoardService {

    void createBoard(BoardDTO boardDTO, List<MultipartFile> images);

    @Transactional(readOnly = true)
    BoardPageResponseDTO getBoardList(PageRequestDTO pageRequestDTO, String categoryName);

    @Transactional
    BoardDetailDTO getBoard(Long id);

    UpdateBoardDTO getBoardForUpdate(Long id);

    void updateBoard(BoardDTO boardDTO, List<MultipartFile> images);

    void deleteBoard(RemoveDTO removeDTO);

}


