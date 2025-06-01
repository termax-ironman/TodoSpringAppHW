package org.example.todoapispringg;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("FakeTodoService")
public class FakeTodoService implements TodoService {
    public String doSomething() {
        return "Fake Something";
    }
}
