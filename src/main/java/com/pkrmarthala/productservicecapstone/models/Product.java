package com.pkrmarthala.productservicecapstone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import java.io.Serializable;

@Getter
@Setter
@Entity
public class Product extends BaseModel  implements Serializable
{

    private String imageUrl;
    private double price;
    private long quantity;

    @ManyToOne
    private Category category;
}
