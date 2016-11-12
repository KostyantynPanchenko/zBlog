USE mmd5nrdccqehkora;

/*	table blog_users		*/
DROP TABLE IF EXISTS blog_users;
CREATE TABLE blog_users(
	id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    role VARCHAR(10),
    enabled boolean NOT NULL
	)
	AUTO_INCREMENT = 1000
    ENGINE = innoDB
    CHARSET = utf8
;

/*	table posts		*/
DROP TABLE IF EXISTS posts;
CREATE TABLE posts(
	id INT AUTO_INCREMENT PRIMARY KEY,    
    title VARCHAR(100) NOT NULL,
    content VARCHAR(4000) NOT NULL,
    author_id INT REFERENCES blog_user.id,
    added TIMESTAMP DEFAULT NOW(),
    last_modified TIMESTAMP,
    modified BOOLEAN DEFAULT FALSE
	)
	AUTO_INCREMENT = 1000
    ENGINE = innoDB
    CHARSET = utf8
;

/*	table comments		*/
DROP TABLE IF EXISTS comments;
CREATE TABLE comments(
	id INT AUTO_INCREMENT PRIMARY KEY, 
    content VARCHAR(4000) NOT NULL,
    author_id INT REFERENCES blog_user.id,
    post_id INT REFERENCES posts.id,
    added TIMESTAMP DEFAULT NOW(),
    last_modified TIMESTAMP,
    modified BOOLEAN DEFAULT FALSE
	)
	AUTO_INCREMENT = 1000
    ENGINE = innoDB
    CHARSET = utf8
;

INSERT INTO blog_users VALUES (1000, 'admin', 'getlost', 'admin@gov.ua', 'ROLE_ADMIN', true);
INSERT INTO blog_users VALUES (1001, 'user', 'user', 'user@gov.ua', 'ROLE_USER', true);
INSERT INTO blog_users VALUES (1002, 'Jordan', 'jordan', 'mj23@bulls.com', 'ROLE_USER', true);
INSERT INTO blog_users VALUES (1003, 'Pippen', 'pippen', 'pip@bulls.com', 'ROLE_USER', true);