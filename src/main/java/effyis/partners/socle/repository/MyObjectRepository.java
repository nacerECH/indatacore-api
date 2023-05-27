package effyis.partners.socle.repository;

import effyis.partners.socle.entity.MyObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyObjectRepository extends JpaRepository<MyObject, Long> {


}
