package github.com.MaxBr221.imageliteapi.repository;

import github.com.MaxBr221.imageliteapi.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
