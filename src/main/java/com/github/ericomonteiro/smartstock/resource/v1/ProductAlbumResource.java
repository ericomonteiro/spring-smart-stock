package com.github.ericomonteiro.smartstock.resource.v1;

import com.github.ericomonteiro.smartstock.model.dto.photo.PhotoDto;
import com.github.ericomonteiro.smartstock.model.dto.product.FileResponseDto;
import com.github.ericomonteiro.smartstock.service.photo.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/product/{productId}/album")
public class ProductAlbumResource {

    private final PhotoService photoService;

    @PostMapping
    public ResponseEntity<FileResponseDto> insertPhoto(
            @PathVariable("productId") Long productId,
            @RequestParam("file")MultipartFile file,
            @RequestParam("main")Boolean main
    ) throws IOException {
        var fileResponse = photoService.addPhotoToProduct(productId, file, main);
        return ResponseEntity
                .created(
                    ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/" + file.getName())
                        .build()
                        .toUri())
                .body(fileResponse);
    }

    @GetMapping
    public ResponseEntity<Resource> downloadMainPhoto(@PathVariable("productId") Long productId) {
        PhotoDto photo = photoService.getMainPhoto(productId);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + photo.getFileName() + "\"")
                .body(new ByteArrayResource(photo.getData()));
    }

}
