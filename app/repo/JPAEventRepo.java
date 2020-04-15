package repo;

import entities.Events;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

@Singleton
public class JPAEventRepo {
    @Inject
    private JPAApi jpaApi;

    // wrap this.jpaApi.withTransaction : to make it reusable
    private <T> T wrap(Function<EntityManager, T> function){
        return this.jpaApi.withTransaction(function);
    }

    @Transactional
    public Events insert(Events event){
        this.jpaApi.withTransaction(entityManager -> {
            entityManager.persist(event);
        });
        return event;
    }

    public List<Events> list(){
        return this.wrap(entityManager -> {
            List<Events> eventList =  entityManager.createQuery("select p from Events p", Events.class).getResultList();
            return eventList;
        });
    }
}
