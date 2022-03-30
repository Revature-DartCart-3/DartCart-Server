INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'HughTheMann',
    '$2a$04$vwsthDC7JUu9kfqA264DJenCuUC2ifUcRAGdEUFNrxa/gz9p9rgC2',
    'Hugh',
    'Mann',
    'hughmanntheman@gmail.com',
    '757-319-0192',
    1645736771148,
    'address'
);

INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'Sammykins',
    '$2a$04$vwsthDC7JUu9kfqA264DJenCuUC2ifUcRAGdEUFNrxa/gz9p9rgC2',
    'Samantha',
    'Mann',
    'sammannnotthefish@gmail.com',
    '757-978-6422',
    1645737413547,
    'address'
);

INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'Caesar92',
    '$2a$04$vwsthDC7JUu9kfqA264DJenCuUC2ifUcRAGdEUFNrxa/gz9p9rgC2',
    'Roe',
    'Mann',
    'thedieiscastxd@gmail.com',
    '757-433-4787',
    1645737476942,
    'address'
);

INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'JerManny',
    '$2a$04$vwsthDC7JUu9kfqA264DJenCuUC2ifUcRAGdEUFNrxa/gz9p9rgC2',
    'Jer',
    'Mann',
    'jermanny@gmail.com',
    '757-670-8879',
    1645737745123,
    'address'
);

INSERT INTO Users (
    user_id,
    username,
    password,
    first_name,
    last_name,
    email,
    phone,
    registration_date,
    location
)
VALUES (
    DEFAULT,
    'test',
    '$2a$04$vwsthDC7JUu9kfqA264DJenCuUC2ifUcRAGdEUFNrxa/gz9p9rgC2',
    'Jer',
    'Mann',
    'jermanny@gmail.com',
    '757-670-8879',
    1645737745123,
    'address'
);

INSERT INTO Sellers (
    seller_id,
    homepage,
    description,
    user_id
)
VALUES (
    DEFAULT,
    '/sellers/bestseller',
    'THE BEST SELLER!',
    2
);

INSERT INTO Sellers (
    seller_id,
    homepage,
    description,
    user_id
)
VALUES (
    DEFAULT,
    '/sellers/alsobestseller',
    'ALSO THE BEST SELLER!!!',
    4
);
INSERT INTO Products (product_id, name, description) VALUES
(1, 'Kelloggs Froot Loops', 'Delicious frooty flava');


INSERT INTO Shop_Products (shop_product_id, quantity, price, discount, product_id) VALUES
(1, 10, 15, 2, 1),
(2, 10, 15, 2, 1);

INSERT INTO Categories (category_id, name) VALUES (1, 'Food');

INSERT INTO Product_Category (product_id, category_id) VALUES
(1, 1);

INSERT INTO Shops VALUES
(default, 'Heaven', 1),
(default, 'Hell', 2);

INSERT INTO Invoices VALUES
(default, 1646419824936, 'Heaven', 'Some Address', 1, 1),
(default, 1646419824936, 'Hell', 'Some Address', 1, 2),
(default, 1646419824936, 'Heaven', 'Some Address', 2, 1),
(default, 1646419824936, 'Hell', 'Some Address', 2, 2),
(default, 1646419824936, 'Heaven', 'Some Address', 3, 1),
(default, 1646419824936, 'Hell', 'Some Address', 3, 2),
(default, 1646419824936, 'Heaven', 'Some Address', 4, 1),
(default, 1646419824936, 'Hell', 'Some Address', 4, 2),
(default, 1646419824936, 'Heaven', 'Some Address', 5, 1),
(default, 1646419824936, 'Hell', 'Some Address', 5, 2);

INSERT INTO Order_Details VALUES
(default, 15, 'Delicious frooty flava', 'Kelloggs Froot Loops', 1, 1),
(default, 2, 'Delicious frooty flava', 'Kelloggs Froot Loops', 3, 2),
(default, 51, 'Delicious frooty flava', 'Kelloggs Froot Loops', 10, 3),
(default, 107, 'Delicious frooty flava', 'Kelloggs Froot Loops', 105, 4),
(default, 31, 'Delicious frooty flava', 'Kelloggs Froot Loops', 8, 5),
(default, 29, 'Delicious frooty flava', 'Kelloggs Froot Loops', 6, 6),
(default, 37, 'Delicious frooty flava', 'Kelloggs Froot Loops', 5, 7),
(default, 41, 'Delicious frooty flava', 'Kelloggs Froot Loops', 15, 8),
(default, 1, 'Delicious frooty flava', 'Kelloggs Froot Loops', 7, 9),
(default, 139, 'Delicious frooty flava', 'Kelloggs Froot Loops', 107, 10),
(default, 1300, 'Delicious frooty flava', 'Kelloggs Froot Loops', 107, 10),
(default, 1500, 'Delicious frooty flava', 'Kelloggs Froot Loops', 107, 10),
(default, 1250, 'Delicious frooty flava', 'Kelloggs Froot Loops', 5, 10);
