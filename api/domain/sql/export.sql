
    alter table PlayerResult
        drop
        foreign key FK_auybgmaeybs8l2v75u30bc077

    alter table PlayerResult
        drop
        foreign key FK_21wjyol5hiq71b0xknm8nlap5

    drop table if exists Game

    drop table if exists Player

    drop table if exists PlayerResult

    create table Game (
        id bigint not null auto_increment,
        createDate datetime,
        deleted char(1) not null,
        updateDate datetime,
        date datetime,
        matchingNumber varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table Player (
        id bigint not null auto_increment,
        createDate datetime,
        deleted char(1) not null,
        updateDate datetime,
        name varchar(255),
        primary key (id)
    ) ENGINE=InnoDB;

    create table PlayerResult (
        id bigint not null auto_increment,
        createDate datetime,
        deleted char(1) not null,
        updateDate datetime,
        ownNumber varchar(255),
        gameId bigint,
        playerId bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    alter table PlayerResult
        add constraint FK_auybgmaeybs8l2v75u30bc077
        foreign key (gameId)
        references Game (id);

    alter table PlayerResult
        add constraint FK_21wjyol5hiq71b0xknm8nlap5
        foreign key (playerId)
        references Player (id);
