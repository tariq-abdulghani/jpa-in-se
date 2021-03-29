package tariq.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQuery(name = "GET_BY_ID", query = "SELECT t from Todo  t  WHERE t.id=:pId ")
@NamedQuery(name = "LAST_ONE", query = "SELECT t from Todo t WHERE t.id=(SELECT MAX(t.id) from Todo  t)")
public class Todo {
    public static final String GET_BY_ID_QUERY = "GET_BY_ID";
    public static final String LAST_ONE_QUERY = "LAST_ONE";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private String description;

    public Long getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Todo [summary=" + summary + ", description=" + description
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id.equals(todo.id) && Objects.equals(summary, todo.summary) && Objects.equals(description, todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summary, description);
    }
}
