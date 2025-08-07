package com.example.todoapp.controller;

import com.example.todoapp.entity.Todo;
import com.example.todoapp.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    // Home page - show all todos
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("todos", todoService.getAllTodos());
        model.addAttribute("newTodo", new Todo());
        model.addAttribute("totalCount", todoService.getTotalCount());
        model.addAttribute("completedCount", todoService.getCompletedCount());
        model.addAttribute("pendingCount", todoService.getPendingCount());
        return "index";
    }
    
    // Show completed todos
    @GetMapping("/completed")
    public String completedTodos(Model model) {
        model.addAttribute("todos", todoService.getCompletedTodos());
        model.addAttribute("pageTitle", "Completed Tasks");
        model.addAttribute("totalCount", todoService.getTotalCount());
        model.addAttribute("completedCount", todoService.getCompletedCount());
        model.addAttribute("pendingCount", todoService.getPendingCount());
        return "todos";
    }
    
    // Show pending todos
    @GetMapping("/pending")
    public String pendingTodos(Model model) {
        model.addAttribute("todos", todoService.getPendingTodos());
        model.addAttribute("pageTitle", "Pending Tasks");
        model.addAttribute("totalCount", todoService.getTotalCount());
        model.addAttribute("completedCount", todoService.getCompletedCount());
        model.addAttribute("pendingCount", todoService.getPendingCount());
        return "todos";
    }
    
    // Create new todo
    @PostMapping("/todos")
    public String createTodo(@Valid @ModelAttribute("newTodo") Todo todo, 
                           BindingResult bindingResult, 
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("todos", todoService.getAllTodos());
            model.addAttribute("totalCount", todoService.getTotalCount());
            model.addAttribute("completedCount", todoService.getCompletedCount());
            model.addAttribute("pendingCount", todoService.getPendingCount());
            return "index";
        }
        
        todoService.saveTodo(todo);
        redirectAttributes.addFlashAttribute("successMessage", "Todo created successfully!");
        return "redirect:/";
    }
    
    // Show edit form
    @GetMapping("/todos/{id}/edit")
    public String editTodoForm(@PathVariable Long id, Model model) {
        Optional<Todo> todo = todoService.getTodoById(id);
        if (todo.isPresent()) {
            model.addAttribute("todo", todo.get());
            return "edit";
        }
        return "redirect:/";
    }
    
    // Update todo
    @PostMapping("/todos/{id}")
    public String updateTodo(@PathVariable Long id, 
                           @Valid @ModelAttribute Todo todo,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes,
                           Model model) {
        if (bindingResult.hasErrors()) {
            todo.setId(id);
            model.addAttribute("todo", todo);
            return "edit";
        }
        
        Todo updatedTodo = todoService.updateTodo(id, todo.getTitle(), todo.getDescription());
        if (updatedTodo != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Todo updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Todo not found!");
        }
        return "redirect:/";
    }
    
    // Toggle todo completion
    @PostMapping("/todos/{id}/toggle")
    public String toggleTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Todo todo = todoService.toggleTodoCompletion(id);
        if (todo != null) {
            String message = todo.isCompleted() ? "Todo marked as completed!" : "Todo marked as pending!";
            redirectAttributes.addFlashAttribute("successMessage", message);
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Todo not found!");
        }
        return "redirect:/";
    }
    
    // Delete todo
    @PostMapping("/todos/{id}/delete")
    public String deleteTodo(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = todoService.deleteTodo(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Todo deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Todo not found!");
        }
        return "redirect:/";
    }
}
