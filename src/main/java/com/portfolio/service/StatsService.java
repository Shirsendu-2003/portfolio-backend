package com.portfolio.service;

import com.portfolio.model.PageView;
import com.portfolio.repository.PageViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final PageViewRepository repo;

    // ✅ Increment view count
    @Transactional
    public void increment(String page) {

        PageView pv = repo.findByPage(page)
                .orElseGet(() -> {
                    PageView newPage = new PageView();
                    newPage.setPage(page);
                    newPage.setViews(0L);
                    return newPage;
                });

        pv.setViews(pv.getViews() + 1);
        repo.save(pv);
    }

    // ✅ Get view count
    public Long getViews(String page) {
        return repo.findByPage(page)
                .map(PageView::getViews)
                .orElse(0L);
    }
}