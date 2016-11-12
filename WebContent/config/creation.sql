USE mmd5nrdccqehkora;
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

/*	table posts		*/
DROP TABLE IF EXISTS posts;
CREATE TABLE posts(
	id INT AUTO_INCREMENT PRIMARY KEY,    
    title VARCHAR(100) NOT NULL,
    content VARCHAR(4000) NOT NULL,
    author_id INT REFERENCES blog_user.id ON DELETE CASCADE,
    added TIMESTAMP DEFAULT NOW(),
    last_modified TIMESTAMP,
    modified BOOLEAN DEFAULT FALSE
	)
	AUTO_INCREMENT = 1000
    ENGINE = innoDB
    CHARSET = utf8
;

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

INSERT INTO blog_users VALUES (1000, 'admin', '5675a5c7a2a87e733f80e0e0a83c493641cc4993', 'admin@gov.ua', 'ROLE_ADMIN', true);
INSERT INTO blog_users VALUES (1001, 'user', '12dea96fec20593566ab75692c9949596833adc9', 'user@gov.ua', 'ROLE_USER', true);
INSERT INTO blog_users VALUES (1002, 'Jordan', '1cb5bd5a9e45420321f44c72da5d90d7f0432ffb', 'mj23@bulls.com', 'ROLE_USER', true);
INSERT INTO blog_users VALUES (1003, 'Pippen', '248d359fff9324beab8549bf6ecc040c10d1feff', 'pip@bulls.com', 'ROLE_USER', true);
SELECT * FROM blog_users;

INSERT INTO posts VALUES (
	1000, 
	'Meaner BMW X3 Caught On The Nurburgring, Sounds Like The Upcoming M Version', 
	'hile regular versions of the next generation BMW X3 continue to be spied in motion, the Germans are also working on a more potent member of the family, which will add the M designation. 
	Coming hot on the heels of the larger BMW X5 M, the smaller SUV seems to have been taken to the Nurburgring for some tire shredding action, spilling its additional horsepower on the famous track and growling like there''s no tomorrow. 
	A powerful inline-six engine is said to sit under the hood, probably taken from the M3 and M4, providing somewhere between 400 and 500 HP. The X3 M will also benefit from a tuned chassis with sports suspension, tweaked steering and larger brakes in order to improve its cornering abilities. 
	In order to match its power with its looks, the automaker will add a more aggressive body, with redesigned bumpers, a slightly modded grille, beefier side skirts and a rear diffuser to incorporate the quad tailpipes, while inside, expect sportier seats, M badging and some trim updates. 
	As for its reveal date, nothing is known yet but it should come after the regular X3 hits the market in the second half of next year. 
	',
    1002,
	now(), 
	null, 
	false);