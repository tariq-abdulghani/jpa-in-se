package tariq;


import tariq.models.Todo;
import tariq.services.TodoService;
import tariq.utils.EntityManagerProducer;

import java.io.IOException;

public class Main {


    private static final String PERSISTENCE_UNIT_NAME = "PU";

    public static void main(String[] args) throws IOException {


        // write your code here
        System.out.println("Hello JPA");
        EntityManagerProducer entityManagerProducer = new EntityManagerProducer(PERSISTENCE_UNIT_NAME);
        TodoService todoService = new TodoService(entityManagerProducer);

//        todoService.create("summary 2", "summary 2");
        todoService.getAll();
        Todo todo = todoService.getById(1L);

        todoService.getLastTodo();
        entityManagerProducer.close();
    }
}
