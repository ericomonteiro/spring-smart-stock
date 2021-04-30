package com.github.ericomonteiro.smartstock.resource.v1;

import com.github.ericomonteiro.smartstock.model.dto.product.FileResponseDto;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/product/album")
public class ProductAlbumResource {

    @PostMapping
    public FileResponseDto insertPhoto(@RequestParam("file")MultipartFile file) {
        return new FileResponseDto(
                FilenameUtils.getName(file.getOriginalFilename()),
                FilenameUtils.getExtension(file.getOriginalFilename()),
                "url"
        );
    }

}
