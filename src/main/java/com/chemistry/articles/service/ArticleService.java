package com.chemistry.articles.service;

import com.chemistry.articles.model.Article;
import com.chemistry.articles.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> readArticles() {
        List<Article> articles = articleRepository.findAll();
        if (articles == null) {
            return Collections.emptyList();
        }

        return articles;
    }

    public Article readArticle(long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Article with id " + id + " is not found."));

        return article;
    }

    public Article createArticle(Article article) throws NullPointerException {
        if (article == null) {
            throw new NullPointerException("Article object is null.");
        }

        Article articleResponse = articleRepository.save(article);

        return articleResponse;
    }

    public Article updateArticle(Article article) {
        Article updatedArticle = readArticle(article.getId());

        updatedArticle.setName(article.getName());
        updatedArticle.setContent(article.getContent());
        updatedArticle.setAuthor(article.getAuthor());

        updatedArticle = articleRepository.saveAndFlush(updatedArticle);

        return updatedArticle;
    }

    public void deleteArticle(long id) {
        readArticle(id);

        articleRepository.deleteById(id);
    }
}
