package neo4j;

import org.neo4j.ogm.session.Session;

import java.util.HashMap;
import java.util.Map;

class SeansService extends GenericService<Seans>{
    public SeansService(Session session) {
        super(session);
    }

    @Override
    Class<Seans> getEntityType() {
        return Seans.class;
    }

    Iterable<Map<String, Object>> getSeansRelationships() {
        String query =
                "MATCH (b:Seans)-[r]-() " +
                        "WITH type(r) AS t, COUNT(r) AS c " +
                        "WHERE c >= 1 " +
                        "RETURN t, c";
        System.out.println("Executing " + query);
        HashMap<String, Object> params = new HashMap<String, Object>();
        return session.query(query, params).queryResults();
    }
}
