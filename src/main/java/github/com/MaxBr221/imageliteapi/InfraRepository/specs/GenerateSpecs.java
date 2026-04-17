package github.com.MaxBr221.imageliteapi.InfraRepository.specs;

import org.springframework.data.jpa.domain.Specification;

public class GenerateSpecs {

    private GenerateSpecs(){}


    public static<T> Specification<T> conjunction(){
        return (root, q, criteriaBuilder) -> criteriaBuilder.conjunction();

    }
}
