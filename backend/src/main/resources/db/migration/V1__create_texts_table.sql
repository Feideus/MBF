CREATE TABLE texts (
    id           UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title        VARCHAR(512) NOT NULL,
    author       VARCHAR(256),
    language     VARCHAR(8)   NOT NULL,
    genre        VARCHAR(128),
    source_type  VARCHAR(32)  NOT NULL,
    status       VARCHAR(32)  NOT NULL,
    content      TEXT         NOT NULL,
    word_count   INTEGER      NOT NULL DEFAULT 0,
    scene_count  INTEGER,
    created_at   TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at   TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE INDEX idx_texts_status ON texts (status);
CREATE INDEX idx_texts_title ON texts (title);
