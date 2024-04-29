package in.nucleusteq.plasma.mapper;

/**
 * Mapper interface provides a method to map objects from one type to another.
 */
public interface Mapper {

    /**
     * Maps an object of type T to an object of type U.
     *
     * @param source          The source object to map.
     * @param destinationType The destination type to map to.
     * @param <T>             The type of the source object.
     * @param <U>             The type of the destination object.
     * @return The mapped object of type U.
     */
    <T, U> U map(T source, Class<U> destinationType);
}
