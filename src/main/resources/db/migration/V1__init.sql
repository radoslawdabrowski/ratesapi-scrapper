create table if not exists rates (
    ID uuid not null constraint rates_pkey primary key,
    SYMBOL varchar(3) not null,
    RATE number not null,
    START_DATE date not null,
    END_DATE date not null,
    FETCHED_DATE date,
    constraint rate unique (symbol, start_date, end_date)
);
