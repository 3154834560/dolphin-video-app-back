package com.example.dolphin.acomm.domain;

import com.example.dolphin.acomm.infrastructure.AppException;
import com.example.dolphin.acomm.model.PageOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.function.Function;

/**
 * @author ankelen
 * @date 2022-10-14 13:27
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, String>, JpaSpecificationExecutor<T> {
    //region ById

    static <T> PageOutput<T> of(Page<T> page) {
        PageOutput<T> output = new PageOutput<>();
        output.setContent(page.getContent());
        output.setPageSize(page.getSize());
        output.setPageNumber(page.getNumber());
        output.setTotalPages(page.getTotalPages());
        output.setTotalElements(page.getTotalElements());
        return output;
    }

    default T getById(@NonNull String id) {
        return this.findById(id).orElseThrow(() -> new AppException("EntityNotFound!"));
    }

    //endregion

    //region Page

    default boolean delById(@NonNull String id) {
        this.deleteById(id);
        return true;
    }

    default PageOutput<T> pageAll(@Nullable Specification<T> spec, Pageable pageable) {
        Page<T> page = this.findAll(spec, pageable);
        return BaseRepository.of(page);
    }

    //endregion

    //region PageOutput

    default <U> PageOutput<U> pageAll(@Nullable Specification<T> spec, Pageable pageable, Function<? super T, ? extends U> converter) {
        Page<U> page = this.findAll(spec, pageable).map(converter);
        return BaseRepository.of(page);
    }

    //endregion
}
