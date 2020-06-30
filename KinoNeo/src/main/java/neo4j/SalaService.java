package neo4j;

import org.neo4j.ogm.session.Session;

class SalaService extends GenericService<Sala> {
    public SalaService(Session session) {
        super(session);
    }

    @Override
    Class<Sala> getEntityType() {
        return Sala.class;
    }

    ;
}
