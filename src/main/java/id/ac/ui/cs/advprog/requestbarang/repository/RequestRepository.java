package id.ac.ui.cs.advprog.requestbarang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.requestbarang.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

}