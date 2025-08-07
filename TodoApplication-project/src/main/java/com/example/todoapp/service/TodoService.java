package com.example.todoapp.service;

import com.example.todoapp.entity.Todo;
import com.example.todoapp.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;
    
    // Get all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAllByOrderByCreatedAtDesc();
    }
    
    // Get completed todos
    public List<Todo> getCompletedTodos() {
        return todoRepository.findByCompletedTrueOrderByUpdatedAtDesc();
    }
    
    // Get pending todos
    public List<Todo> getPendingTodos() {
        return todoRepository.findByCompletedFalseOrderByCreatedAtDesc();
    }
    
    // Get todo by ID
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }
    
    // Save todo
    public Todo saveTodo(Todo todo) {
        return todoRepository.save(todo);
    }
    
    // Create new todo
    public Todo createTodo(String title, String description) {
        Todo todo = new Todo(title, description);
        return todoRepository.save(todo);
    }
    
    // Update todo
    public Todo updateTodo(Long id, String title, String description) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setTitle(title);
            todo.setDescription(description);
            return todoRepository.save(todo);
        }
        return null;
    }
    
    // Toggle todo completion status
    public Todo toggleTodoCompletion(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            Todo todo = todoOptional.get();
            todo.setCompleted(!todo.isCompleted());
            return todoRepository.save(todo);
        }
        return null;
    }
    
    // Delete todo
    public boolean deleteTodo(Long id) {
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Get statistics
    public long getCompletedCount() {
        return todoRepository.countCompletedTodos();
    }
    
    public long getPendingCount() {
        return todoRepository.countPendingTodos();
    }
    
    public long getTotalCount() {
        return todoRepository.count();
    }
}
