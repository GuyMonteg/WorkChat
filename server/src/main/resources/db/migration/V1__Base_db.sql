CREATE TABLE users (
    user_name VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(40) NOT NULL,
    UNIQUE(user_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE messages (
    author VARCHAR(30) NOT NULL,
    mesage_text TEXT NOT NULL,
    message_time VARCHAR(27) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;