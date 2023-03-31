package com.chemistry.articles.controller;

import com.chemistry.articles.model.Article;
import com.chemistry.articles.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/articles")
    public ResponseEntity<?> getArticles() {
        List<Article> articleList = articleService.readArticles();

        return new ResponseEntity<>(articleList, HttpStatus.OK);
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticle(@PathVariable long id) {
        Article articleResponse = articleService.readArticle(id);

        return new ResponseEntity<>(articleResponse, HttpStatus.OK);
    }

    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody Article article) {
        Article articleResponse = articleService.createArticle(article);

        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PutMapping("/article/{id}")
    public ResponseEntity<?> postArticle(@RequestBody Article article, @PathVariable long id) {
        article.setId(id);
        Article articleResponse = articleService.updateArticle(article);

        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> deletetArticle(@PathVariable long id) {
        articleService.deleteArticle(id);

        return new ResponseEntity<>("Article with id " + id + " deleted successfully.", HttpStatus.OK);
    }
}
