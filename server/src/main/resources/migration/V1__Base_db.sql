CREATE TABLE users (
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(40) NOT NULL,
    PRIMARY KEY(user_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE messages (
    author VARCHAR(100) NOT NULL,
    mesage_text TEXT NOT NULL,
    message_time DATETIME DEFAULT NULL,
    PRIMARY KEY(author),
    FOREIGN KEY (author) REFERENCES users (user_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;