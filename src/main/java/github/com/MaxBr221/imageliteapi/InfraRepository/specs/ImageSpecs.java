package github.com.MaxBr221.imageliteapi.InfraRepository.specs;

import github.com.MaxBr221.imageliteapi.domain.entity.Image;
import github.com.MaxBr221.imageliteapi.domain.enums.ImageExtension;
import org.springframework.data.jpa.domain.Specification;

public class ImageSpecs {
    private ImageSpecs(){}

    public static Specification<Image> extensionEqual(ImageExtension extesion){
        return (root, q, cb) -> cb.equal(root.get("extesion"), extesion);


    }
    public static Specification<Image> nameLike(String name){
        return  (root, q, cb) -> cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%" );

    }
    public static Specification<Image> tagsLike(String tags){
        return (root, q, cb) -> cb.like(cb.upper(root.get("tags")), "%" + tags.toUpperCase() + "%" );

    }
}
