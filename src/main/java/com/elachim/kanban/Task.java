package com.elachim.kanban;

public record Task(
    long id,
    String title,
    String description,
    String developer,
    int duration,
    Status status
) {
    public enum Status { TODO, DOING, DONE }
}
