package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.Client;
import br.com.rd.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository

public interface ClientRepository extends JpaRepository<Client,Long> {

    List<Client> findByName(String Name);
    List<Client> findByIdClient(Long idClient);
    Client findByCpf(String cpf);
    List<Client> findByBirthDate(Integer birth);
    Client findByMail(String mail);
    List<Client> findByPhone(Number phone);

    @Query(value = "SELECT max(idClient) FROM Client")
    public Long max();
}