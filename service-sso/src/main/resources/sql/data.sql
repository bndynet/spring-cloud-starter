DELETE FROM app_user 
WHERE
    username = 'bndy'
    AND email = 'zb@bndy.net';
    
INSERT INTO app_user (`account_expired`, `account_locked`, `credentials_expired`, `email`, `enabled`, `password`, `username`) VALUES (0, 0, 0, 'zb@bndy.net', 1, '123456', 'bndy');


DELETE FROM oauth_client_details 
WHERE
    client_id = 'foo';
INSERT INTO oauth_client_details(`client_id`, `authorized_grant_types`, `autoapprove`, `client_name`, `client_secret`, `redirect_uri`, `scope`) 
	VALUES ('foo', 'authorization_code;refresh_token', 0, 'test', 'bar', 'http://127.0.0.1:9111/login/oauth2/code/home;https://www.getpostman.com/oauth2/callback', 'user_info');
