package com.example.todoapp.repository;

import com.example.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    
    // Find all todos ordered by creation date (newest first)
    List<Todo> findAllByOrderByCreatedAtDesc();
    
    // Find completed todos
    List<Todo> findByCompletedTrueOrderByUpdatedAtDesc();
    
    // Find pending todos
    List<Todo> findByCompletedFalseOrderByCreatedAtDesc();
    
    // Count completed todos
    @Query("SELECT COUNT(t) FROM Todo t WHERE t.completed = true")
    long countCompletedTodos();
    
    // Count pending todos
    @Query("SELECT COUNT(t) FROM Todo t WHERE t.completed = false")
    long countPendingTodos();
}
