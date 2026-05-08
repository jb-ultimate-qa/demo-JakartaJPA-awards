package my.example.awards.hib_any.payment;

import jakarta.persistence.*;
import org.hibernate.annotations.Any;
import org.hibernate.annotations.AnyDiscriminatorValue;
import org.hibernate.annotations.AnyKeyJavaClass;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "pay_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Any
    @AnyKeyJavaClass(UUID.class)   //the foreign key type
    @JoinColumn(name="payment_id") //the foreign key column
    @Column(name="payment_type")   //the discriminator column
    @AnyDiscriminatorValue(discriminator="CASH", entity=CashPayment.class)
    @AnyDiscriminatorValue(discriminator="CREDIT", entity=CreditCardPayment.class)
    private Payment payment;
//    private Object payment;
//    private Serializable payment;
}