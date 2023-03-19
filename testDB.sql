--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-03-19 12:49:18

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3355 (class 1262 OID 16400)
-- Name: test; Type: DATABASE; Schema: -; Owner: admin
--

CREATE DATABASE test WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';


ALTER DATABASE test OWNER TO admin;

\connect test

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 16418)
-- Name: cart; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.cart (
    id bigint NOT NULL,
    amountwithdis double precision,
    amountwithoutdis double precision,
    promodisc double precision,
    carddisc double precision,
    discountcard_id integer
);


ALTER TABLE public.cart OWNER TO admin;

--
-- TOC entry 218 (class 1259 OID 16417)
-- Name: cart_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.cart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.cart_id_seq OWNER TO admin;

--
-- TOC entry 3356 (class 0 OID 0)
-- Dependencies: 218
-- Name: cart_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.cart_id_seq OWNED BY public.cart.id;


--
-- TOC entry 220 (class 1259 OID 16429)
-- Name: cart_product; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.cart_product (
    cart_id integer NOT NULL,
    product_id integer NOT NULL
);


ALTER TABLE public.cart_product OWNER TO admin;

--
-- TOC entry 215 (class 1259 OID 16402)
-- Name: discountcard; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.discountcard (
    id bigint NOT NULL,
    percentdiscount integer NOT NULL,
    percentdiscountindouble double precision NOT NULL,
    CONSTRAINT discountcard_percentdiscount_check CHECK ((percentdiscount > 0)),
    CONSTRAINT discountcard_percentdiscountindouble_check CHECK ((percentdiscountindouble > (0)::double precision))
);


ALTER TABLE public.discountcard OWNER TO admin;

--
-- TOC entry 214 (class 1259 OID 16401)
-- Name: discountcard_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.discountcard_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.discountcard_id_seq OWNER TO admin;

--
-- TOC entry 3357 (class 0 OID 0)
-- Dependencies: 214
-- Name: discountcard_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.discountcard_id_seq OWNED BY public.discountcard.id;


--
-- TOC entry 217 (class 1259 OID 16411)
-- Name: product; Type: TABLE; Schema: public; Owner: admin
--

CREATE TABLE public.product (
    id bigint NOT NULL,
    name character varying(30) NOT NULL,
    maker character varying(30) NOT NULL,
    height double precision,
    width double precision,
    length double precision,
    weight double precision,
    price double precision,
    isdiscount boolean
);


ALTER TABLE public.product OWNER TO admin;

--
-- TOC entry 216 (class 1259 OID 16410)
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: admin
--

CREATE SEQUENCE public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.product_id_seq OWNER TO admin;

--
-- TOC entry 3358 (class 0 OID 0)
-- Dependencies: 216
-- Name: product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: admin
--

ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;


--
-- TOC entry 3189 (class 2604 OID 16421)
-- Name: cart id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.cart ALTER COLUMN id SET DEFAULT nextval('public.cart_id_seq'::regclass);


--
-- TOC entry 3187 (class 2604 OID 16405)
-- Name: discountcard id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.discountcard ALTER COLUMN id SET DEFAULT nextval('public.discountcard_id_seq'::regclass);


--
-- TOC entry 3188 (class 2604 OID 16414)
-- Name: product id; Type: DEFAULT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);


--
-- TOC entry 3348 (class 0 OID 16418)
-- Dependencies: 219
-- Data for Name: cart; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.cart VALUES (1, 43682.99, 46134.15, 288.9, 2162.26, 2);
INSERT INTO public.cart VALUES (2, 43682.99, 46134.15, 288.9, 2162.26, 2);


--
-- TOC entry 3349 (class 0 OID 16429)
-- Dependencies: 220
-- Data for Name: cart_product; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 9);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 6);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 9);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 6);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 2);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 4);
INSERT INTO public.cart_product VALUES (1, 5);
INSERT INTO public.cart_product VALUES (1, 5);
INSERT INTO public.cart_product VALUES (1, 5);
INSERT INTO public.cart_product VALUES (1, 5);
INSERT INTO public.cart_product VALUES (1, 5);
INSERT INTO public.cart_product VALUES (1, 5);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (1, 3);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 2);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 4);
INSERT INTO public.cart_product VALUES (2, 5);
INSERT INTO public.cart_product VALUES (2, 5);
INSERT INTO public.cart_product VALUES (2, 5);
INSERT INTO public.cart_product VALUES (2, 5);
INSERT INTO public.cart_product VALUES (2, 5);
INSERT INTO public.cart_product VALUES (2, 5);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);
INSERT INTO public.cart_product VALUES (2, 3);


--
-- TOC entry 3344 (class 0 OID 16402)
-- Dependencies: 215
-- Data for Name: discountcard; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.discountcard VALUES (1, 20, 0.8);
INSERT INTO public.discountcard VALUES (2, 5, 0.95);
INSERT INTO public.discountcard VALUES (3, 13, 0.87);
INSERT INTO public.discountcard VALUES (4, 10, 0.9);
INSERT INTO public.discountcard VALUES (5, 6, 0.94);


--
-- TOC entry 3346 (class 0 OID 16411)
-- Dependencies: 217
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: admin
--

INSERT INTO public.product VALUES (1, 'Purrfect Treats', 'unknown', 0, 0, 0, 0, 100, true);
INSERT INTO public.product VALUES (2, 'Furry Friends', 'unknown', 0, 0, 0, 0, 175, false);
INSERT INTO public.product VALUES (3, 'Cuddlr', 'unknown', 0, 0, 0, 0, 1000, false);
INSERT INTO public.product VALUES (4, 'Petropolitan', 'unknown', 0, 0, 0, 0, 321, true);
INSERT INTO public.product VALUES (5, 'Colorart', 'unknown', 0, 0, 0, 0, 444, false);
INSERT INTO public.product VALUES (6, 'Roche', 'unknown', 0, 0, 0, 0, 700, false);
INSERT INTO public.product VALUES (7, 'God''s apple ', 'unknown', 0, 0, 0, 0, 100000, false);
INSERT INTO public.product VALUES (8, 'Phone', 'unknown', 0, 0, 0, 0, 55, false);
INSERT INTO public.product VALUES (9, 'Car', 'unknown', 0, 0, 0, 0, 123.55, false);
INSERT INTO public.product VALUES (10, 'Fruit', 'unknown', 0, 0, 0, 0, 666, true);


--
-- TOC entry 3359 (class 0 OID 0)
-- Dependencies: 218
-- Name: cart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.cart_id_seq', 2, true);


--
-- TOC entry 3360 (class 0 OID 0)
-- Dependencies: 214
-- Name: discountcard_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.discountcard_id_seq', 5, true);


--
-- TOC entry 3361 (class 0 OID 0)
-- Dependencies: 216
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: admin
--

SELECT pg_catalog.setval('public.product_id_seq', 10, true);


--
-- TOC entry 3197 (class 2606 OID 16423)
-- Name: cart cart_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);


--
-- TOC entry 3193 (class 2606 OID 16409)
-- Name: discountcard discountcard_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.discountcard
    ADD CONSTRAINT discountcard_pkey PRIMARY KEY (id);


--
-- TOC entry 3195 (class 2606 OID 16416)
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- TOC entry 3198 (class 2606 OID 16424)
-- Name: cart cart_discountcard_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_discountcard_id_fkey FOREIGN KEY (discountcard_id) REFERENCES public.discountcard(id);


--
-- TOC entry 3199 (class 2606 OID 16432)
-- Name: cart_product cart_product_cart_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.cart_product
    ADD CONSTRAINT cart_product_cart_id_fkey FOREIGN KEY (cart_id) REFERENCES public.cart(id);


--
-- TOC entry 3200 (class 2606 OID 16437)
-- Name: cart_product cart_product_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: admin
--

ALTER TABLE ONLY public.cart_product
    ADD CONSTRAINT cart_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.product(id);


-- Completed on 2023-03-19 12:49:18

--
-- PostgreSQL database dump complete
--

