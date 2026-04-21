package github.com.MaxBr221.imageliteapi.application.images;

import github.com.MaxBr221.imageliteapi.InfraRepository.ImageRepository;
import github.com.MaxBr221.imageliteapi.domain.entity.Image;
import github.com.MaxBr221.imageliteapi.domain.enums.ImageExtension;
import github.com.MaxBr221.imageliteapi.domain.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    public ImageServiceImpl(ImageRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public Optional<Image> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public List<Image> search(ImageExtension extension, String query) {
        return repository.findByExtensionAndNameOrTagsLike(extension, query);
    }
}
