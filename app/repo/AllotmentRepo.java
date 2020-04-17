package repo;

import entities.AllotmentDetails;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;

@Singleton
public class AllotmentRepo {
    @Inject
    private JPAApi jpaApi;

    // wrap this.jpaApi.withTransaction : to make it reusable
    private <T> T wrap(Function<EntityManager, T> function){
        return this.jpaApi.withTransaction(function);
    }

    public List<AllotmentDetails> getAllotmentlist(){
        return this.wrap(entityManager -> {
            List<AllotmentDetails> allotmentList =  entityManager.createQuery("select p from AllotmentDetails p", AllotmentDetails.class).getResultList();
            return allotmentList;
        });
    }

    public AllotmentDetails findAllotmentDetailsById(Integer allotmentId) {
        return this.wrap(entityManager -> entityManager.createQuery("select m from AllotmentDetails m where m.allotmentId=" + allotmentId , AllotmentDetails.class).getSingleResult());
    }

    public void deleteAllotmentById(Integer allotmentId) {
        this.wrap(entityManager ->
                entityManager.createQuery("delete from AllotmentDetails m where m.allotmentId=" + allotmentId).executeUpdate()
        );
    }

    public AllotmentDetails insertOrUpdateAllotmentDetails(AllotmentDetails allotment) {
        return this.wrap(eM -> {
            if (allotment.getAllotmentId() == null) {
                eM.persist(allotment);
            } else {
                eM.merge(allotment);
            }
            return allotment;
        });
    }

}
