create TABLE category
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    is_deleted    BIT(1)                NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

create TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    created_at    datetime              NULL,
    updated_at    datetime              NULL,
    is_deleted    BIT(1)                NOT NULL,
    image_url     VARCHAR(255)          NULL,
    price         DOUBLE                NOT NULL,
    quantity      BIGINT                NOT NULL,
    category_id   BIGINT                NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

alter table product
    add CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);