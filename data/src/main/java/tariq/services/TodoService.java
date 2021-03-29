package tariq.services;


import tariq.models.Todo;
import tariq.utils.EntityManagerProducer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class TodoService implements CRUDService<Todo> {

    private EntityManagerProducer entityManagerProducer ;

    public TodoService(EntityManagerProducer entityManagerProducer){
        this.entityManagerProducer = entityManagerProducer;
    }

    @Override
    public Todo create(Todo e) {
        EntityManager em = entityManagerProducer.produce();
        em.getTransaction().begin();
        Todo todo = new Todo();
        todo.setSummary(e.getSummary());
        todo.setDescription(e.getDescription());
        em.persist(todo);
        em.getTransaction().commit();
        em.close();
        return todo;
    }

    public Todo create(String summary, String description) {
        EntityManager em = entityManagerProducer.produce();
        em.getTransaction().begin();
        Todo todo = new Todo();
        todo.setSummary(summary);
        todo.setDescription(description);
        em.persist(todo);
        em.getTransaction().commit();
        em.close();
        return todo;
    }

    @Override
    public List<Todo> getAll() {
        EntityManager em = entityManagerProducer.produce();
//        Query q = em.createQuery("select t from Todo t");
//        List<Todo> todoList = q.getResultList();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Todo> criteriaQuery = cb.createQuery(Todo.class);
        Root<Todo> todoRoot = criteriaQuery.from(Todo.class);
        criteriaQuery.select(todoRoot);
        TypedQuery<Todo> todoTypedQuery = em.createQuery(criteriaQuery);
        List<Todo> todoList =  todoTypedQuery.getResultList();
//        List<Todo> todoList = em.createQuery(criteriaQuery).getResultList();
        em.close();
        for (Todo todo : todoList) {
            System.out.println(todo);
        }
        System.out.println("Size: " + todoList.size());
        return todoList;
    }

    @Override
    public Todo getById(String id) {
        EntityManager em = entityManagerProducer.produce();
//        Query q = em.createQuery("select t from Todo t where t.id=:pId");
        Query q = em.createNamedQuery(Todo.GET_BY_ID_QUERY, Todo.class);
        q.setParameter("pId", id);
        Todo todo = (Todo) q.getSingleResult();
        System.out.println(todo.getId() + " " + todo.toString());
        em.close();
        return todo;
    }

    @Override
    public Todo getById(Long id) {
        EntityManager em = entityManagerProducer.produce();
//        Query q = em.createQuery("select t from Todo t where t.id=:pId");
        Query q = em.createNamedQuery(Todo.GET_BY_ID_QUERY, Todo.class);
        q.setParameter("pId", id);
        Todo todo = (Todo) q.getSingleResult();
        System.out.println(todo.getId() + " " + todo.toString());
        em.close();
        return todo;
    }

    public Todo getLastTodo(){
        EntityManager em = entityManagerProducer.produce();
        Query q = em.createNamedQuery(Todo.LAST_ONE_QUERY, Todo.class);
        Todo todo = (Todo) q.getSingleResult();
        System.out.println(todo.getId() + " " + todo.toString());
        em.close();
        return todo;
    }
    @Override
    public Todo update(Todo newE) {
        EntityManager em = entityManagerProducer.produce();
//        Query q = em.createQuery("select t from Todo t where t.id=:pId");
//        q.setParameter("pId", newE.getId());
//        Todo todo = (Todo) q.getSingleResult();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Todo> todoCriteriaQuery = cb.createQuery(Todo.class);
        Root<Todo> todoRoot = todoCriteriaQuery.from(Todo.class);
        Predicate wherePredicate = cb.equal(todoRoot.get("id"), newE.getId());
        todoCriteriaQuery.select(todoRoot).where(wherePredicate);
        Todo todo = em.createQuery(todoCriteriaQuery).getSingleResult();
        em.getTransaction().begin();
        todo.setSummary(newE.getSummary());
        todo.setDescription(newE.getDescription());
        em.persist(todo);
        em.getTransaction().commit();
        em.close();
        return todo;
    }

    @Override
    public Todo delete(Todo e) {
        EntityManager em = entityManagerProducer.produce();
        em.getTransaction().begin();
        em.remove(e);
        em.getTransaction().commit();
        em.close();
        return e;
    }

}
