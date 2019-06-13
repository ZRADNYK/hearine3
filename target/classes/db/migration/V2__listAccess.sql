alter table playlist
    add lst_access varchar(255) not null
        default 'public'
    after lst_type;
