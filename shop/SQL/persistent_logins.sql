-- persistent_logins : 자동 로그인 테이블
create table persistent_logins (
    username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);