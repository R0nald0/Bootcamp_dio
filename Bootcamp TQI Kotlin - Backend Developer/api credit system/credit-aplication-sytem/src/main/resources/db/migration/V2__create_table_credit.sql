CREATE TABLE credit (
  id BIGINT AUTO_INCREMENT NOT NULL,
   credit_code char(36) NOT NULL,
   credit_value FLOAT NOT NULL,
   day_first_installment BIGINT NOT NULL,
   number_of_installments INT NOT NULL,
   status INT NULL,
   customer_id BIGINT NULL,
   CONSTRAINT pk_credit PRIMARY KEY (id)
);

ALTER TABLE credit ADD CONSTRAINT uc_credit_creditcode UNIQUE (credit_code);

ALTER TABLE credit ADD CONSTRAINT FK_CREDIT_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES costumer (id);