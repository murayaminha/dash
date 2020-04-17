package br.com.rd.ecommerce.Controller;

import br.com.rd.ecommerce.model.Client;
import br.com.rd.ecommerce.model.Contact;
import br.com.rd.ecommerce.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ContactController {

    @Autowired
    private ContactRepository repository;
    @ResponseStatus(HttpStatus.CREATED)

//    @PostMapping("/create-contact")
//    public Contact save(@RequestBody Contact contact){
//        return repository.save(contact);   }

    @PostMapping("/create-contact")
    public Contact save(@RequestBody Contact contact){
        return repository.save(contact);
    };
    @GetMapping("/contato")
    public ResponseEntity<?> elogio(){
        return ResponseEntity.ok().body(repository.buscarAssunto());
    }
}

