package org.commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tshirt_size")
public class Size {

    @Id
    @Column(name = "id_size", nullable = false, columnDefinition = "VARCHAR(100)", length = 100)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product productId;
    @Column(name = "size", nullable = false, columnDefinition = "VARCHAR(3)", length = 3)
    private String size;
    @Column(name = "stock", nullable = false, columnDefinition = "INTEGER")
    private int stock;


}