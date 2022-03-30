package com.xeniabuscarons.restapirockets;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PropellantRepository extends CrudRepository<Propellant,String> {
    @Transactional
    List<Propellant> deleteAllByRocket(Rocket rocket);
}
