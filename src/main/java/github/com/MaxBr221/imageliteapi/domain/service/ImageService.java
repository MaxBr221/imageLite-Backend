package github.com.MaxBr221.imageliteapi.domain.service;

import github.com.MaxBr221.imageliteapi.domain.entity.Image;
import github.com.MaxBr221.imageliteapi.domain.enums.ImageExtension;

import java.util.List;
import java.util.Optional;

public interface ImageService {
    Image save (Image image);
    Optional<Image> findById(String id);
    List<Image> search(ImageExtension extesion, String query);
}
