package data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Transports")
public class Transport implements Serializable {

    @Id
    @Column(name = "transportId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_orderId")
    private Order order;

    @OneToOne()
    @JoinColumn(name = "fk_statusId")
    private Status status;

    @Column(nullable = false)
    private Date deliveryDate;

    @Column(nullable = false)
    private Date issueDate;

    public long getId() {
        return id;
    }

    public void setId(long transportId) {
        this.id = transportId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
