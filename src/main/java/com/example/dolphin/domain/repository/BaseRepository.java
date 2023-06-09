package com.example.dolphin.domain.repository;

import com.example.dolphin.infrastructure.model.PageOutput;
import com.example.dolphin.infrastructure.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.function.Function;

/**
 * @author 王景阳
 * @date 2022/11/10 15:58
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {

    static <T> PageOutput<T> of(Page<T> page) {
        PageOutput<T> output = new PageOutput<>();
        output.setContent(page.getContent());
        output.setPageSize(page.getSize());
        output.setPageNumber(page.getNumber());
        output.setTotalPages(page.getTotalPages());
        output.setTotalElements(page.getTotalElements());
        return output;
    }

    @Override
    default T getById(@NonNull String id) {
        return this.findById(id).orElseThrow(() -> new AppException("EntityNotFound!"));
    }

    default T getBy(@NonNull Specification<T> spec) {
        List<T> all = this.findAll(spec);
        if (all.isEmpty()) {
            return null;
        }
        return all.get(0);
    }

    default void del(T entity) {
        if (entity != null) {
            this.delete(entity);
        }
    }

    default boolean delById(@NonNull String id) {
        this.deleteById(id);
        return true;
    }

    default PageOutput<T> pageAll(@Nullable Specification<T> spec, Pageable pageable) {
        Page<T> page = this.findAll(spec, pageable);
        return BaseRepository.of(page);
    }


    default <U> PageOutput<U> pageAll(@Nullable Specification<T> spec, Pageable pageable, Function<? super T, ? extends U> converter) {
        Page<U> page = this.findAll(spec, pageable).map(converter);
        return BaseRepository.of(page);
    }
}
