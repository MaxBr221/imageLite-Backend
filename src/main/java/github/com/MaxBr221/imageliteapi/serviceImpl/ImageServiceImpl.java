package github.com.MaxBr221.imageliteapi.serviceImpl;

import github.com.MaxBr221.imageliteapi.domain.entity.Image;
import github.com.MaxBr221.imageliteapi.domain.service.ImageService;
import github.com.MaxBr221.imageliteapi.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;


    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }
}
