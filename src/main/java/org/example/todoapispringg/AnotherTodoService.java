package org.example.todoapispringg;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("AnotherTodoService")
public class AnotherTodoService implements TodoService {

    @Override
    public String doSomething() {
        return "Something from another todo service";
    }
}
