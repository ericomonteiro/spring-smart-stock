package com.github.ericomonteiro.smartstock.service.photo;

import com.github.ericomonteiro.smartstock.config.error.ErrorKeys;
import com.github.ericomonteiro.smartstock.config.error.exceptions.BusinessException;
import com.github.ericomonteiro.smartstock.model.Photo;
import com.github.ericomonteiro.smartstock.model.Product;
import com.github.ericomonteiro.smartstock.model.dto.photo.PhotoDto;
import com.github.ericomonteiro.smartstock.model.dto.product.FileResponseDto;
import com.github.ericomonteiro.smartstock.model.mapper.PhotoMapper;
import com.github.ericomonteiro.smartstock.repository.ProductRepository;
import com.github.ericomonteiro.smartstock.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final ProductService productService;
    private final ProductRepository productRepository;

    public FileResponseDto addPhotoToProduct(Long productId, MultipartFile file, Boolean main) throws IOException {
        Product product = productService.getAndValidProduct(productId);
        Photo photo = PhotoMapper.of(product, file, main);
        product.addPhoto(photo);
        productRepository.save(product);
        return PhotoMapper.toFileResponseDto(photo);
    }

    public PhotoDto getMainPhoto(Long productId) {
        Product product = productService.getAndValidProduct(productId);
        var photo = product
                .getAlbum()
                .stream()
                .filter(Photo::getMain)
                .findFirst()
                .orElseThrow(() -> new BusinessException(ErrorKeys.Product.Album.MAIN_PHOTO_UNDEFINED));

        return PhotoMapper.toPhotoDto(photo);
    }

}
