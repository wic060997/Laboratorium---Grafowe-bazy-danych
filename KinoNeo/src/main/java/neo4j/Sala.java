package neo4j;
import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import javax.annotation.Generated;

@NodeEntity(label = "Sala")
public class Sala {
    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "nazwa")
    private String nazwaSali;

    public Sala() {
    }

    public Sala(String nazwaSali) {
        this.nazwaSali = nazwaSali;
    }

    public Long getId() {
        return id;
    }

    public String getNazwaSali() {
        return nazwaSali;
    }

    public void setNazwaSali(String nazwaSali) {
        this.nazwaSali = nazwaSali;
    }

    @Relationship(type = "SALA_SEANS")
    private Set<Seans> seansy =  new HashSet<>();

    public void addSeans(Seans s){
        seansy.add(s);
    }
    @Override
    public String toString() {
        return "Sala{" +
                "id=" + id +
                ", nazwaSali='" + nazwaSali + '\'' +
                '}';
    }
}
