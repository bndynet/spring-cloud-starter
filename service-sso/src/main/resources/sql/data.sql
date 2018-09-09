-- Client Secret: 1  (if enable PasswordEncoder in java, this filed Must be encrypted into database)
DELETE FROM oauth_client_details 
WHERE
    client_id = 'foo';
INSERT INTO oauth_client_details(`client_id`, `owner`, `access_token_validity`, `authorized_grant_types`, `autoapprove`, `client_name`, `client_secret`, `client_secret_raw`, `redirect_uri`, `refresh_token_validity`, `resource_ids`, `scope`) 
	VALUES ('foo', 'BDNY-NET', 60, 'authorization_code;refresh_token', 0, 'OAuth Client', '$2a$10$amr84zeHdajBaaEyTei80ukj3kj.Tntq887pv9JXN.P.K5sAllx2K', '1', 'http://127.0.0.1:9111/login/oauth2/code/home;https://www.getpostman.com/oauth2/callback', 60, 'sso-resource', 'user_info');
