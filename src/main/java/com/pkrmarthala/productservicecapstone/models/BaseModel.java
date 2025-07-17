package com.pkrmarthala.productservicecapstone.models;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseModel {

    private long id;
    private String name;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;

}
