// 代码生成时间: 2025-09-06 18:14:45
 * It includes error handling, documentation, and follows Java best practices for maintainability and scalability.
 */
package com.example.reactivelayout;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/layout")
public class ReactiveLayoutService {

    @Autowired
    private LayoutRepository layoutRepository;

    // GET endpoint to retrieve all layouts
    @GetMapping
    public Mono<List<Layout>> getAllLayouts() {
        return layoutRepository.findAll().onErrorMap(this::toResponseEntity);
    }

    // Helper method to convert errors to a ResponseStatusException
    private ResponseStatusException toResponseEntity(Throwable t) {
        if (t instanceof NoSuchElementException) {
            return new ResponseStatusException(HttpStatus.NOT_FOUND, "Layout not found", t);
        } else {
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", t);
        }
    }
}

/*
 * Layout.java
 *
 * Entity class representing a layout
 */
@Entity
class Layout {
    @Id
    private Long id;
    private String name;
    private String description;
    // Constructors, getters, and setters
    public Layout() {}
    public Layout(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.name = description; }
}

/*
 * LayoutRepository.java
 *
 * Repository interface for Layout entity
 */
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LayoutRepository extends ReactiveCrudRepository<Layout, Long> {
    Mono<List<Layout>> findAll();
}