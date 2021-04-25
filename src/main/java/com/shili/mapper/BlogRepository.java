/*
package com.shili.mapper;

import com.shili.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog>{

    List<Blog> findTop(Pageable pageable);

    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying

    int updateViews(Long id);
}
*/
