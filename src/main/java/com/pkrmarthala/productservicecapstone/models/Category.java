package com.pkrmarthala.productservicecapstone.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel implements Serializable
{
    @JsonIgnore
    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
