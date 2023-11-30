create Table IF NOT EXISTS contact_list (
      id bigint not null auto_increment,
      user_name varchar(255),
      phone varchar(255),
      primary key (id)
);


create TABLE IF NOT EXISTS black_list (
        id bigint not null auto_increment,
        phone varchar(255),
        primary key (id)
);

create TABLE IF NOT EXISTS call_data (
        id bigint not null auto_increment,
        time TIMESTAMP,
        call_type varchar(255),
        duration bigint,
        phone_number varchar(255),
        save_contact BIT,
        primary key (id)
);