package my.example.awards.hib_any.payment;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "pay_cash")
public class CashPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Double amount;

    @Column(name = "is_checked")
    private Boolean isCheckedForCounterfeiting;

    @Column(name = "currency_code", length = 4, nullable = false)
    private String currency;

    @OneToMany(mappedBy = "payment")
    private Set<Order> orders = new HashSet<>();

}