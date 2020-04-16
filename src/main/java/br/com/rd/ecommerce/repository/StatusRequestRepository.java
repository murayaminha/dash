package br.com.rd.ecommerce.repository;
import br.com.rd.ecommerce.model.Request;

import br.com.rd.ecommerce.model.StatusRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StatusRequestRepository extends JpaRepository<StatusRequest, Long> {
    StatusRequest findByRequest(Optional<Request> request);

//    @Query(value="SELECT COUNT(statusRequest) AS qtd, statusRequest AS status FROM StatusRequest GROUP BY status")
//    List<?> listarStatus();

}