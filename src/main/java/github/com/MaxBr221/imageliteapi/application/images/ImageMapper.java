package github.com.MaxBr221.imageliteapi.application.images;


import github.com.MaxBr221.imageliteapi.domain.entity.Image;
import github.com.MaxBr221.imageliteapi.domain.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
public class ImageMapper {

    public Image mapToImage(MultipartFile file, String name, List<String> tags ) throws IOException {
        return Image.builder()
                .name(name)
                .tags(String.join(",",tags))
                .size(file.getSize())
                .extesion(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();
    }
    public ImageDTO imageToDTO(Image image, String url){
        return ImageDTO.builder()
                .url(url)
                .extension(image.getExtesion().name())
                .name(image.getName())
                .size(image.getSize())
                .uploadDate(image.getUploadTime().toLocalDate())
                .build();
    }

}
