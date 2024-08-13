package com.luckyvicky.woosan.domain.board.controller;

import com.luckyvicky.woosan.domain.board.dto.DailyBestBoardDTO;
import com.luckyvicky.woosan.domain.board.dto.RankingDTO;
import com.luckyvicky.woosan.domain.board.dto.SearchPageResponseDTO;
import com.luckyvicky.woosan.domain.board.service.ElasticsearchBoardService;
import com.luckyvicky.woosan.global.util.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@Log4j2
public class ElasticsearchBoardController {

    private final ElasticsearchBoardService elasticsearchBoardService;


    /**
     *  일반/유의어 검색 
     */
    @GetMapping("/search")
    public ResponseEntity<SearchPageResponseDTO> search(
            @RequestParam(value = "category", required = false) String categoryName,
            @RequestParam(value = "filter", required = false) String filterType,
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") int standardPage,
            @RequestParam(value = "rpage", required = false, defaultValue = "1") int synonymPage,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

        PageRequestDTO standardPageRequest = new PageRequestDTO(standardPage, size);
        PageRequestDTO synonymPageRequest = new PageRequestDTO(synonymPage, size);

        // 검색 키워드 저장
        elasticsearchBoardService.saveSearchKeyword(keyword);

        SearchPageResponseDTO result = elasticsearchBoardService.searchWithStandardAndSynonyms(standardPageRequest, synonymPageRequest, categoryName, filterType, keyword);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 검색 키워드 자동완성
     */
    @GetMapping("/autocomplete")
    public ResponseEntity<List<String>> autocomplete(
            @RequestParam(value = "category", required = false) String categoryName,
            @RequestParam(value = "searchType", required = false) String filterType,
            @RequestParam(value = "keyword", required = false) String keyword) {
        List<String> result = elasticsearchBoardService.autocomplete(categoryName, filterType, keyword);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 검색 순위
     */
    @GetMapping("/ranking")
    public ResponseEntity<List<RankingDTO>> getRanking() {
        List<RankingDTO> result = elasticsearchBoardService.getRankingChanges();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 주간 조회수 기준 상위 7개 인기글
     */
    @GetMapping("/weekly/best")
    public List<DailyBestBoardDTO> getTop5BoardsByViews() {
        return elasticsearchBoardService.getTop5BoardsByViews();
    }
}

