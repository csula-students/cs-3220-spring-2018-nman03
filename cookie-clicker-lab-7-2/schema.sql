CREATE TABLE users (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE generators (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    rate INTEGER,
    base_cost INTEGER,
    unlock_at INTEGER,
    created_by INTEGER REFERENCES users(id), index(created_by)
);

CREATE TABLE events (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    trigger_at INTEGER,
    created_by INTEGER REFERENCES users(id), index(created_by)
);

CREATE TABLE quantities (
    generator_id INTEGER NOT NULL REFERENCES generators(id), index(generator_id),
    token VARCHAR(255),
    quantity INTEGER DEFAULT 0
);

INSERT INTO users (username, password) VALUES ("admin", "cs3220password"), 
                                              ("me", "notapassword");

INSERT INTO generators (name, description, rate, base_cost, unlock_at, created_by)  
VALUES ("Grandma", "Grandma likes to make cookies", 5, 10, 10, 1),
       ("Factory", "Factory to produce cookies", 10, 50, 50, 1),
       ("Mine", "Mining cookies", 20, 200, 200, 2);
       
INSERT INTO events (name, description, trigger_at, created_by)  
VALUES ("Grandma shows up", "You always know grandma likes to make cookies", 10, 1),
       ("You can construct factory now!", "Factory to produce cookies", 50, 1),
       ("We've found cookies in deep mountain ... in the mine?", "Mining cookies", 200, 2),
       ("sample event", "This is a sample event. Please delete me", 99999, 2);
       
INSERT INTO quantities (generator_id, token, quantity)
VALUES (1, "c7a69d44e0b9b415b2d9956cb26b944a", 2),
       (2, "c7a69d44e0b9b415b2d9956cb26b944a", 1),
       (1, "80516ce4663c3bd0c8385309a2fe226e", 20),
       (2, "80516ce4663c3bd0c8385309a2fe226e", 30);
      