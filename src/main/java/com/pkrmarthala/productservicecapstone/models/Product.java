package com.pkrmarthala.productservicecapstone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id=?")
// @Where(clause = "is_deleted = false")
@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedProductFilter", condition = "is_deleted = :isDeleted")
public class Product extends BaseModel {

    private String imageUrl;
    private double price;
    private long quantity;

    @ManyToOne
    private Category category;
}
