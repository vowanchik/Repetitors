create table tutors
(
    id        integer primary key auto_increment,
    last_name varchar(200),
    name      varchar(200),
    login     varchar(200),
    password  varchar(200)
/*  id_student integer references students (id) on delete cascade on update cascade */
);

insert into tutors(last_name, name, login, password)
values ('Петров', 'И.Т.', 'gotov', '123QWER');
insert into tutors(last_name, name, login, password)
values ('Иванов', 'Х.Р.', 'nikov', '123QWERkov');
insert into tutors(last_name, name, login, password)
values ('Демидов', 'О.П.', 'hrenov', '123QWERty');

create table lessons
(
    id       integer primary key auto_increment,
    name     varchar(200),
    day     varchar(20),
    time    varchar(20),
    price    double,
    id_tutor integer references tutors (id) on delete cascade on update cascade
);

insert into lessons(name, day, time, price, id_tutor)
values ('Математика', 'Понедельник','18:00', 1000, 2);
insert into lessons(name, day,time, price, id_tutor)
values ('Математика', 'Вторник','18:00', 1000, 2);

