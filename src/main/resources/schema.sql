CREATE TABLE IF NOT EXISTS Ingredient (
                                          id VARCHAR(4) NOT NULL PRIMARY KEY,
                                          name VARCHAR(25) NOT NULL,
                                          type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(50) NOT NULL,
                                    created_at TIMESTAMP NOT NULL,
                                    taco_order BIGINT NOT NULL,
                                    taco_order_key BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco_Ingredients (
                                                taco BIGINT NOT NULL,
                                                ingredient VARCHAR(4) NOT NULL,
                                                PRIMARY KEY (taco, ingredient),
                                                FOREIGN KEY (taco) REFERENCES Taco(id),
                                                FOREIGN KEY (ingredient) REFERENCES Ingredient(id)
);

CREATE TABLE IF NOT EXISTS Taco_Order (
                                          id SERIAL PRIMARY KEY,
                                          delivery_name VARCHAR(50) NOT NULL,
                                          delivery_street VARCHAR(50) NOT NULL,
                                          delivery_city VARCHAR(50) NOT NULL,
                                          delivery_state VARCHAR(2) NOT NULL,
                                          delivery_zip VARCHAR(10) NOT NULL,
                                          cc_number VARCHAR(16) NOT NULL,
                                          cc_expiration VARCHAR(5) NOT NULL,
                                          cc_CVV VARCHAR(3) NOT NULL,
                                          placed_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Taco_Order_Tacos (
                                                taco_order BIGINT NOT NULL,
                                                taco BIGINT NOT NULL,
                                                PRIMARY KEY (taco_order, taco),
                                                FOREIGN KEY (taco_order) REFERENCES Taco_Order(id),
                                                FOREIGN KEY (taco) REFERENCES Taco(id)
);
