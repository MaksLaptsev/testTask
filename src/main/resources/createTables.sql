-- create schema if not exists check_example;
-- comment on schema check_example is 'Create check_example schema';
create table if not exists DiscountCard(
                                                         id bigserial primary key ,
                                                         percentDiscount integer not null check ( percentDiscount > 0 ),
                                                         percentDiscountInDouble double precision not null check ( percentDiscountInDouble > 0 )
);

create table if not exists Product(
                                                    id bigserial primary key ,
                                                    name varchar(30) not null ,
                                                    maker varchar(30) not null ,
                                                    height double precision ,
                                                    width double precision ,
                                                    length double precision ,
                                                    weight double precision ,
                                                    price double precision ,
                                                    isDiscount boolean
);

create table if not exists Cart(
                                                 id bigserial primary key ,
                                                 amountWithDis double precision,
                                                 amountWithoutDis double precision,
                                                 promoDisc double precision,
                                                 cardDisc double precision,
                                                 discountcard_id int references discountcard(id)
);
create table if not exists cart_product(
                                                         cart_id int not null references cart(id),
                                                         product_id int not null references product(id)
)
