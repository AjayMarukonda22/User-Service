CREATE TABLE `role`
(
    id   BIGINT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE session
(
    id             BIGINT NOT NULL,
    token          VARCHAR(255) NULL,
    expiry_date    datetime NULL,
    user_id        BIGINT NULL,
    session_status SMALLINT NULL,
    CONSTRAINT pk_session PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       BIGINT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_role_set
(
    user_id     BIGINT NOT NULL,
    role_set_id BIGINT NOT NULL,
    CONSTRAINT pk_user_roleset PRIMARY KEY (user_id, role_set_id)
);

ALTER TABLE session
    ADD CONSTRAINT FK_SESSION_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_role_set
    ADD CONSTRAINT fk_userolset_on_role FOREIGN KEY (role_set_id) REFERENCES `role` (id);

ALTER TABLE user_role_set
    ADD CONSTRAINT fk_userolset_on_user FOREIGN KEY (user_id) REFERENCES user (id);