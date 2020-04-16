package br.com.rd.ecommerce.Controller;


import br.com.rd.ecommerce.model.*;
import br.com.rd.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import javax.mail.internet.MimeMessage;

import javax.mail.internet.MimeMessage;

import java.util.List;
import java.util.Optional;

@RestController
public class RequestController {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RequestRepository repository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private StatusRequestRepository statusRequestRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ItemCartRepository itemCartRepository;

    @PostMapping("/request")
    public Request save(@RequestBody StatusRequest statusRequest){
        Request request = statusRequest.getRequest();
        request.setPayment(paymentRepository.save(request.getPayment()));
        statusRequest.setRequest(repository.save(request));
        statusRequestRepository.save(statusRequest);
        return request;
//    for(int i=0; i<request.getStatusRequest().size();i++){
//          StatusRequest status = statusRequestRepository.save(request.getStatusRequest().get(i));
//        System.out.println(status);
//          request.getStatusRequest().remove(i);
//           request.getStatusRequest().add(status);
//        System.out.println(request.getStatusRequest());
//    }
//        request.setPayment(paymentRepository.save(request.getPayment()));
//        return repository.save(request);
    }
    @PostMapping("/adicionar-statusRequest")
    public ResponseEntity<?> alterar(@RequestBody StatusRequest statusRequest){
        statusRequestRepository.save(statusRequest);
        Request requestPaymentAlterado = statusRequest.getRequest();
        Optional<Request> requestOptional = repository.findById(statusRequest.getRequest().getId());
        Request request = requestOptional.orElse(null);
        Payment payment = request.getPayment();
        payment.setStatus(requestPaymentAlterado.getPayment().getStatus());
        paymentRepository.save(payment);
        repository.save(request);
        Client client = request.getClient();
        String email =client.getMail();
        String texto = null;
        if(statusRequest.getStatusRequest().equals("pagamento foi aprovado")){
        texto = ("<p>Nós rebecemos o pagamento do pedido "+request.getId()+"</p>"+
                "<p>Embreve você receberá informações sobre a entrega</p>");
        }
        else if (!statusRequest.getStatusRequest().equals("cancelado")){
            texto = ("<p>Seu pedido nº "+request.getId()+ "está com um como status:</p>"+
                    "<p>"+statusRequest.getStatusRequest()+"</p>");
        }
        else if(statusRequest.getStatusRequest().equals("cancelado")){
            texto = ("<p> Poxa, que pena, seu pedido nº "+request.getId()+ " foi cancelado, acesse nosso site para mais informações</p>");
        }

        try {
            MimeMessage mail = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( email );
            helper.setSubject( "Compra "+request.getId() );
            helper.setText(texto);
            mailSender.send(mail);
            return  ResponseEntity.ok().body("email enviado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body("Deu Ruim");
        }
    }


    @GetMapping("/request/{id}")
    public Request findId(@PathVariable("id") Long id) { return repository.findById(id).get();
    }
    @DeleteMapping("/request")
    public void deletById(@PathVariable("id") Long id){
        repository.deleteById(id);
    }
    @PostMapping("/acompanhar")
    public ResponseEntity<List<Request>> acompanhar(@RequestBody() Client user){
        List request = repository.findByClient(user);
        return ResponseEntity.ok().body(request);
    }
    @GetMapping("buscarRequest")
    public List<ItemCart>  buscar(){
        return itemCartRepository.findAll();
    }
    @GetMapping("buscarPedidoID/{id}")
    public Optional<Request> buscarporId(@PathVariable("id") Long id){
        Optional<Request> request =  repository.findById(id);
        return request;
    }

//    @GetMapping("/listar_status")
//    public ResponseEntity<?> status(){
//        return ResponseEntity.ok().body(statusRequestRepository.listarStatus());
//    }

    @GetMapping("/status_valor")
    public ResponseEntity<?> statusValor(){
        return ResponseEntity.ok().body(repository.valorStatus());
    }

}


