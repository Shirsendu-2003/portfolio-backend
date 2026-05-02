package com.portfolio.repository;

import com.portfolio.model.PageView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PageViewRepository extends JpaRepository<PageView, Long> {
    Optional<PageView> findByPage(String page);
}