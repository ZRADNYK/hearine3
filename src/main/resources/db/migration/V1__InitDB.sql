create table album (
    id bigint not null auto_increment,
    avatar varchar(2000),
    name varchar(255) not null,
    release_date datetime,
    total_length bigint,
    primary key (id) );

    create table album_band (
        band_id bigint not null,
        album_id bigint not null, 
        primary key (album_id, band_id) );

    create table album_genre (
        genre_id bigint not null, 
        album_id bigint not null, 
        primary key (album_id, genre_id) );

    create table album_tag (
        album_id bigint not null, 
        tag_id bigint not null, 
        primary key (album_id, tag_id) );

    create table band (
        id bigint not null auto_increment,
        create_date date,
        name varchar(255) not null,
        primary key (id) );

    create table genre (
        id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id) );

    create table hibernate_sequence (next_val bigint);

    create table playlist (
        id bigint not null auto_increment,
        avatar varchar(2000),
        create_date datetime,
        dsc varchar(2000),
        lstType varchar(255),
        name varchar(255) not null,
        user_id bigint not null,
        primary key (id) );

    create table song (
        id bigint not null auto_increment,
        length varchar(255),
        private_name varchar(255) not null,
        public_name varchar(255) not null,
        song_path varchar(2000) not null,
        primary key (id) );

    create table song_album (
        song_id bigint not null,
        album_id bigint not null,
        primary key (album_id, song_id) );

    create table song_genre (
        song_id bigint not null,
        genre_id bigint not null,
        primary key (genre_id, song_id) );

    create table song_playlist (
        song_id bigint not null,
        playlist_id bigint not null,
        primary key (playlist_id, song_id) );

    create table song_tag (
        song_id bigint not null,
        tag_id bigint not null,
        primary key (tag_id, song_id) );

    create table tag (
        id bigint not null auto_increment,
        name varchar(255) not null,
        primary key (id) );

    create table user_band (
        user_id bigint not null,
        band_id bigint not null,
        primary key (user_id, band_id) );

    create table user_role (
        user_id bigint not null,
        roles varchar(255) );

    create table user_subscription (
        subscriber_id bigint not null,
        channel_id bigint not null,
        primary key (channel_id, subscriber_id) );

    create table usr (
        id bigint not null auto_increment,
        activation_code varchar(500),
        active bit not null,
        avatar varchar(2000),
        email varchar(255) not null,
        password varchar(255) not null,
        username varchar(255) not null,
        primary key (id) );

    alter table album_band
        add constraint album_band_album_fk
            foreign key (album_id)
                references album (id);

    alter table album_band
        add constraint album_band_band_fk
            foreign key (band_id)
                references band (id);

    alter table album_genre
        add constraint album_genre_album_fk
            foreign key (album_id)
                references album (id);

    alter table album_genre
        add constraint album_genre_genre_fk
            foreign key (genre_id)
                references genre (id);

    alter table album_tag
        add constraint album_tag_tag_fk
            foreign key (tag_id)
                references tag (id);

    alter table album_tag
        add constraint album_tag_album_fk
            foreign key (album_id)
                references album (id);

    alter table playlist
        add constraint playlist_user_fk
            foreign key (user_id)
                references usr (id);

    alter table song_album
        add constraint song_album_album_fk
            foreign key (album_id)
                references album (id);

    alter table song_album
        add constraint song_album_song_fk
            foreign key (song_id)
                references song (id);

    alter table song_genre
        add constraint song_genre_genre_fk
            foreign key (genre_id)
                references genre (id);

    alter table song_genre
        add constraint song_genre_song_fk
            foreign key (song_id)
                references song (id);

    alter table song_playlist
        add constraint song_playlist_playlist_fk
            foreign key (playlist_id)
                references playlist (id);

    alter table song_playlist
        add constraint
            song_playlist_song_fk
            foreign key (song_id)
                references song (id);

    alter table song_tag
        add constraint song_tag_tag_fk
            foreign key (tag_id)
                references tag (id);

    alter table song_tag
        add constraint song_tag_song_fk
            foreign key (song_id)
                references song (id);

    alter table user_band
        add constraint user_band_band_fk
            foreign key (band_id)
                references band (id);

    alter table user_band
        add constraint user_band_user_fk
            foreign key (user_id)
                references usr (id);

    alter table user_role
        add constraint user_role_user_fk
            foreign key (user_id)
                references usr (id);

    alter table user_subscription
        add constraint user_subscription_user_fk
            foreign key (channel_id)
                references usr (id);

    alter table user_subscription
        add constraint user_subscription_subscriber_fk
            foreign key (subscriber_id)
                references usr (id);