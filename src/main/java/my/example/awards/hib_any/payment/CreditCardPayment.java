package my.example.awards.hib_any.payment;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pay_card")
public class CreditCardPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String cardNumber;
    private Integer expirationMonth;
    private Integer expirationYear;
    private String cardHolderName;

    private Double amount;

//    @OneToOne(mappedBy = "payment")
//    private Order order;
// org.hibernate.AnnotationException:
// Association 'my.example.awards.hib_any.payment.CreditCardPayment.order'
// is 'mappedBy' a property named 'payment'
// of the target entity type 'my.example.awards.hib_any.payment.Order'
// which is not a '@OneToOne' or '@ManyToOne' association

}