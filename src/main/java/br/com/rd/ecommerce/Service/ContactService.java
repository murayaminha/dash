//package br.com.rd.ecommerce.Service;
//
//import br.com.rd.ecommerce.model.Contact;
//import br.com.rd.ecommerce.repository.ContactRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//
//@Service("ContactService")
//public class ContactService {
//@PersistenceContext
//private EntityManager em;
//
//@Autowired
//private ContactRepository contactRepository;
//
//public Integer numElogio(){
//        Integer elogio = null;
//        String sql =
//        new StringBuffer()
//        .append("SELECT COUNT(DS_SUBJECT)")
//        .append("FROM TB_CONTACT")
//        .append("WHERE DS_SUBJECT = 'elogio'").toString();
//
//        Query query = em.createNativeQuery(sql,Integer.class);
//        elogio = query.getFirstResult();
//        return  elogio;
//        }