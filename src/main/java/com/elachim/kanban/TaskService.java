package com.elachim.kanban;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public TaskService() {
        tasks.add(new Task(nextId.getAndIncrement(), "Set up project",
            "Initialize Spring Boot + React", "Elachim", 5, Task.Status.DONE));
        tasks.add(new Task(nextId.getAndIncrement(), "Design Kanban board",
            "3-column layout with drag and drop", "Elachim", 8, Task.Status.DOING));
        tasks.add(new Task(nextId.getAndIncrement(), "Add task CRUD",
            "Create, edit, delete tasks", "Elachim", 4, Task.Status.TODO));
    }

    public List<Task> findAll() { return tasks; }

    public Task create(TaskRequest req) {
        Task created = new Task(nextId.getAndIncrement(), req.title(),
            req.description(), req.developer(), req.duration(), req.status());
        tasks.add(created);
        return created;
    }

    public Task updateStatus(long id, Task.Status status) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id() == id) {
                Task t = tasks.get(i);
                Task updated = new Task(t.id(), t.title(), t.description(),
                    t.developer(), t.duration(), status);
                tasks.set(i, updated);
                return updated;
            }
        }
        return null;
    }

    public boolean delete(long id) {
        return tasks.removeIf(t -> t.id() == id);
    }

    public record TaskRequest(String title, String description,
                              String developer, int duration, Task.Status status) {}
}
