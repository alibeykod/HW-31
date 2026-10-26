package ir.maktabsharif.repository;

import java.util.List;
import java.util.Optional;

public abstract interface BaseRepository <T>{
T add(T t);
T update(T t);
Optional<T> findById(Long id);
List<T> findAll();
boolean delete(Long id);

}
