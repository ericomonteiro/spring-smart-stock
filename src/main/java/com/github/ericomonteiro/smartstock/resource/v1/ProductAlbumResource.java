package com.github.ericomonteiro.smartstock.resource.v1;

import com.github.ericomonteiro.smartstock.service.photo.PhotoService;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/v1/product/{productId}/album")
@RequiredArgsConstructor
@Transactional
public class ProductAlbumResource {
    private final PhotoService photoService;

    @PostMapping
    public ResponseEntity<String> insertPhoto(
            @PathVariable("productId") Long productId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("main") Boolean main
    ) throws IOException {
        photoService.addPhotoToProduct(productId, file, main);
        return ResponseEntity.ok("Sucesso");
    }

    @GetMapping("/main")
    public void showMainPhoto(
            @PathVariable("productId") Long productId,
            HttpServletResponse response
    ) throws IOException {
        setPhotoResponse(response, photoService.getMainPhoto(productId));
    }

    @GetMapping("/download/main")
    public ResponseEntity<Resource> downloadMainPhoto(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=blabla")
                .body(new ByteArrayResource(photoService.getMainPhoto(productId)));
    }

    private void setPhotoResponse(HttpServletResponse response, byte[] photo) throws IOException {
        ByteArrayResource resource = new ByteArrayResource(photo);
        InputStream isPhoto = resource.getInputStream();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(isPhoto, response.getOutputStream());
    }

}
