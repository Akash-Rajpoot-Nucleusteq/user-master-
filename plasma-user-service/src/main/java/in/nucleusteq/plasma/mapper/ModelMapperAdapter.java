package in.nucleusteq.plasma.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * ModelMapperAdapter implements the Mapper interface using ModelMapper library.
 */
@Component
public class ModelMapperAdapter implements Mapper {
    private final ModelMapper modelMapper;

    /**
     * Constructs a new ModelMapperAdapter with a new instance of ModelMapper.
     */
    public ModelMapperAdapter() {
        this.modelMapper = new ModelMapper();
    }

    /**
     * Maps an object of type T to an object of type U using ModelMapper.
     *
     * @param source          The source object to map.
     * @param destinationType The destination type to map to.
     * @param <T>             The type of the source object.
     * @param <U>             The type of the destination object.
     * @return The mapped object of type U.
     */
    @Override
    public <T, U> U map(T source, Class<U> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
