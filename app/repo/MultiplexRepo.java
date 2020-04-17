package repo;

import entities.MultiplexDetails;
import entities.MultiplexScreenDetails;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;

public class MultiplexRepo {

        @Inject
        private JPAApi jpaApi;

        // wrap this.jpaApi.withTransaction : to make it reusable
        private <T> T wrap(Function<EntityManager, T> function){
            return this.jpaApi.withTransaction(function);
        }

        public List<MultiplexDetails> getAllMultiplexlist(){
            return this.wrap(entityManager -> {
                List<MultiplexDetails> multiplexList =  entityManager.createQuery("select p from MultiplexDetails p", MultiplexDetails.class).getResultList();
                return multiplexList;
            });
        }

        public List<MultiplexDetails> getMultiplexlistByName(String searchParam){
            return this.wrap(entityManager -> {
                List<MultiplexDetails> multiplexList =  entityManager.createQuery("select p from MultiplexDetails p where p.multiplexName like lower(:searchParam)", MultiplexDetails.class)
                .setParameter("searchParam", "%" + searchParam + "%").getResultList();
                return multiplexList;
            });
        }

        public MultiplexDetails findMultiplexDetailsById(Integer multiplexId) {
            return this.wrap(entityManager -> entityManager.createQuery("select m from MultiplexDetails m where m.multiplexId=" + multiplexId , MultiplexDetails.class).getSingleResult());
        }

        public void deleteMuliplexById(Integer multiplexId) {
            this.wrap(entityManager ->
                    entityManager.createQuery("delete from MultiplexDetails m where m.multiplexId=" + multiplexId).executeUpdate()
            );
        }

        public MultiplexDetails insertOrUpdateMultiplexDetails(MultiplexDetails multiplexDetails) {
          return this.wrap(eM -> {
                if (multiplexDetails.getMultiplexId()== null) {
                    eM.persist(multiplexDetails);
                } else {
                    eM.merge(multiplexDetails);
                }
                return multiplexDetails;
            });
        }

        public MultiplexScreenDetails insertOrUpdateScreenDetails(MultiplexScreenDetails screen) {
            return this.wrap(eM -> {
                if (screen.getScreenId() == null) {
                    eM.persist(screen);
                } else {
                    eM.merge(screen);
                }
                return screen;
            });
        }

        public void deleteScreenByMuliplexById(Integer multiplexId) {
            this.wrap(entityManager ->
                    entityManager.createQuery("delete from MultiplexScreenDetails m where m.multiplexId=" + multiplexId).executeUpdate()
            );
        }
}
