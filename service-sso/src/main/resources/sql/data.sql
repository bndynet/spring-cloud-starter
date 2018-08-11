DELETE FROM app_user 
WHERE
    username = 'admin'
    AND email = 'zb@bndy.net';
    
-- Password: 1
INSERT INTO app_user (`account_expired`, `account_locked`, `credentials_expired`, `email`, `enabled`, `password`, `username`) VALUES (0, 0, 0, 'zb@bndy.net', 1, '$2a$10$amr84zeHdajBaaEyTei80ukj3kj.Tntq887pv9JXN.P.K5sAllx2K', 'admin');


DELETE FROM oauth_client_details 
WHERE
    client_id = 'foo';
INSERT INTO oauth_client_details(`client_id`, `access_token_validity`, `authorized_grant_types`, `autoapprove`, `client_name`, `client_secret`, `redirect_uri`, `refresh_token_validity`, `resource_ids`, `scope`) 
	VALUES ('foo', 60, 'authorization_code;refresh_token', 0, 'test', 'bar', 'http://127.0.0.1:9111/login/oauth2/code/home;https://www.getpostman.com/oauth2/callback', 60, 'sso-resource', 'user_info');
