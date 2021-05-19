package com.github.ericomonteiro.smartstock.model.mapper;

import com.github.ericomonteiro.smartstock.model.Photo;
import com.github.ericomonteiro.smartstock.model.Product;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PhotoMapper {

    public static Photo of(Product product, MultipartFile file, Boolean main) throws IOException {
        return new Photo(
                product,
                main,
                FilenameUtils.getExtension(file.getOriginalFilename()),
                file.getBytes()
        );
    }

}
