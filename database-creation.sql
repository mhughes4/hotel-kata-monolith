CREATE TABLE IF NOT EXISTS hotel(
    id INTEGER PRIMARY KEY,
    version INTEGER,
    name VARCHAR(50)
);

CREATE TABLE room(
    id INTEGER PRIMARY KEY,
    room_type INTEGER,
    room_number INTEGER,
    hotel_id INTEGER REFERENCES hotel(id)
);

