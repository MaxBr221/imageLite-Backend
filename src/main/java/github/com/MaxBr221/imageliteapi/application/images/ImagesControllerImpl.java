package github.com.MaxBr221.imageliteapi.application.images;


import github.com.MaxBr221.imageliteapi.domain.entity.Image;
import github.com.MaxBr221.imageliteapi.domain.enums.ImageExtension;
import github.com.MaxBr221.imageliteapi.domain.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@CrossOrigin("*" )
public class ImagesControllerImpl {
    private final ImageService service;
    private final ImageMapper imageMapper;

    public ImagesControllerImpl(ImageService service, ImageMapper imageMapper) {
        this.service = service;
        this.imageMapper = imageMapper;
    }

    @PostMapping
    public ResponseEntity saveImage(@RequestParam("file") MultipartFile file,
                                    @RequestParam("name") String name,
                                    @RequestParam("tags") List<String> tags
    ) throws IOException {
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());
        Image image = imageMapper.mapToImage(file, name, tags);
        Image imageSalva = service.save(image);
        URI imageUri = buildImageUrl(imageSalva);

        return ResponseEntity.created(imageUri).build();
    }
    private URI buildImageUrl(Image image){
        String imagePath = "/" + image.getId();
        return ServletUriComponentsBuilder.fromCurrentRequestUri().
                path(imagePath).
                build().
                toUri();
    }
    @GetMapping("{id}")
    public ResponseEntity<byte []> getImage(@PathVariable("id") String id){
        var possivelImage = service.findById(id);
        if(possivelImage.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var image = possivelImage.get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtesion().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() +  "\"", image.getFileName());
        log.info("retornando image");
        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(@RequestParam(value = "extension", required = false, defaultValue = "")
                                                 String extesion,
                                                 @RequestParam(value = "query", required = false) String query){
        var result = service.search(ImageExtension.ofName(extesion), query);
        var images = result.stream().map(image -> {
            var url = buildImageUrl(image);
            return imageMapper.imageToDTO(image, url.toString());
        }).collect(Collectors.toList());

        return ResponseEntity.ok(images);
    }

}
