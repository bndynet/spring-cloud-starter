USE oauth;

CREATE TABLE oauth_client_details (
    client_id VARCHAR(255) PRIMARY KEY,
    client_name VARCHAR(255),
    client_secret VARCHAR(255),
    resource_ids VARCHAR(255),
    scope VARCHAR(255),
    authorized_grant_types VARCHAR(255),
    web_server_redirect_uri VARCHAR(255),
    authorities VARCHAR(255),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove TINYINT
);


CREATE TABLE oauth_client_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication_id VARCHAR(255),
    user_name VARCHAR(255),
    client_id VARCHAR(255)
);

CREATE TABLE oauth_access_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication_id VARCHAR(255),
    user_name VARCHAR(255),
    client_id VARCHAR(255),
    authentication BLOB,
    refresh_token VARCHAR(255)
);

CREATE TABLE oauth_refresh_token (
    token_id VARCHAR(255),
    token BLOB,
    authentication BLOB
);

CREATE TABLE oauth_code (
    code VARCHAR(255),
    authentication BLOB
);

CREATE TABLE oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
);


