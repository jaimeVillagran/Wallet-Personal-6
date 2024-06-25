package cl.project.walletprofesional.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "transaction_type", nullable = false, length = 20)
    private String transactionType;

    @CreationTimestamp
    @Column(name = "transaction_date", nullable = false, updatable = false)
    private Timestamp transactionDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
