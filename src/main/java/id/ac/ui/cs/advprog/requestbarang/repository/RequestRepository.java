package id.ac.ui.cs.advpro.requestbarang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advpro.beliproduk.models.Requestl;

@Repository
public interface TransactionRepository extends JpaRepository<Request, Long>{

}