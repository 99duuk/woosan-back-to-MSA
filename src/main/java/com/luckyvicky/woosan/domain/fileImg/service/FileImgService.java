package com.luckyvicky.woosan.domain.fileImg.service;

import com.luckyvicky.woosan.domain.fileImg.dto.FileUpdateDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileImgService {

    void fileUploadMultiple(String type, Long targetId, List<MultipartFile> files);
    List<String> findFiles(String type, Long targetId);
    void targetFilesDelete(String type, Long targetId);
    void deleteS3FileByUrl(Long id,String type,String beforeFile);
    void updateMainBanner(FileUpdateDTO fileUpdateDTO);
}
