package neo4j;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = "Seans")
public class Seans {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "nazwa")
    private String nazwaSeans;

    public Seans() {
    }

    public Seans(String nazwaSeans) {
        this.nazwaSeans = nazwaSeans;
    }

    public Long getId() {
        return id;
    }

    public String getNazwaSeans() {
        return nazwaSeans;
    }

    public void setNazwaSeans(String nazwaSeans) {
        this.nazwaSeans = nazwaSeans;
    }

    @Override
    public String toString() {
        return "Seans{" +
                "id=" + id +
                ", nazwa='" + nazwaSeans + '\'' +
                '}';
    }
}
