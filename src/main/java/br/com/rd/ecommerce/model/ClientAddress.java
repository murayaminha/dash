package br.com.rd.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_client_address")
public class ClientAddress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_client_address")
    private Long clienteAddress;

@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cliente")
    private Client client;

@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="id_address")
    private Address address;
}
