package id.ac.ui.cs.advprog.requestbarang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.requestbarang.model.RequestModel;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<RequestModel, Long>{
    @Query("SELECT r FROM RequestModel r WHERE r.username = :username")
    List<RequestModel> findByUsername(String username);
}