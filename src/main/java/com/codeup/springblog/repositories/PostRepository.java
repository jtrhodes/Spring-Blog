package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String ad_to_be_deleted);
//    @Query("from a where a.id like ?1")
//    Post getAdById(long id);
//
//    // The following method shows you how to use named parameters in a HQL custom query:
//    @Query("from Ad a where a.title like %:term%")
//    List<Post> searchByTitleLike(@Param("term") String term);
//
//    Post save();
}
