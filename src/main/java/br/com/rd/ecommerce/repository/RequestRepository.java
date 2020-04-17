package br.com.rd.ecommerce.repository;


import br.com.rd.ecommerce.model.Client;
import br.com.rd.ecommerce.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request>findByClient(Client client);

    @Query(value = "SELECT st.statusRequest,MAX(st.date),COUNT(st.statusRequest), SUM(r.price + r.priceFreight) AS total FROM StatusRequest st INNER JOIN Request r ON st.id = r.id GROUP BY st.statusRequest")
    List<?> valorStatus();
}
