CREATE TABLE "transaction" (
transaction_id VARCHAR PRIMARY KEY,
user_id VARCHAR REFERENCES "user"(user_id),
store_id VARCHAR REFERENCES "store"(store_id),
amount DOUBLE PRECISION
);