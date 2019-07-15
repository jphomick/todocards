package com.card.todo;

import org.springframework.data.repository.CrudRepository;

public interface TodoSet extends CrudRepository<Todo, Long>{
}
