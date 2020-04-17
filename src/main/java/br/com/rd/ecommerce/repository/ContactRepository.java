package br.com.rd.ecommerce.repository;

import br.com.rd.ecommerce.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository <Contact, Long> {
    List<Contact>findBySubject(String subject);
    List<Contact>findByMail(String mail);

    @Query(value = "SELECT COUNT(subject) AS quantidade, subject AS assunto FROM Contact GROUP BY assunto")
    List<?> buscarAssunto();
<<<<<<< HEAD
=======

>>>>>>> 7f341e8d5f9156e66fd5c548b375d30592d071c3
}
