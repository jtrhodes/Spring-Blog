package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Ad findAdByTitle(String title);

    Ad findAdByDescription(String Description);
}
