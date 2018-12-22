# Service for OAuth

## Getting Started

### Workflow

1. Redirect to **/authorize?redirect_uri={your-site-callback}&target=github**  
1. Return to **{your-site-callback}?access_token={access-token}**  
1. Use **access_token** to visit resource APIs

### Config

1. Register client in your provider like **GitHub** or **bndy.net-sso**
1. Fill the client information in **application.yml** of this project
1. Add your app url as allowed-origin in **application.yml**
1. Now, your app can use this project endpoints to authorize and get token...

### Tests

- [http://localhost:9100/authorize?redirect_uri=http://localhost&target=github](http://localhost:9100/authorize?redirect_uri=http://localhost&target=github])
- [http://localhost:9100/authorize?redirect_uri=http://localhost&target=bndynet](http://localhost:9100/authorize?redirect_uri=http://localhost&target=bndynet)

Above urls should redirect to **http://localhost/?access_token=[token]**

### NOTES

- `callbackUri` option in **application.yml** should be your client redirect uri.
If empty, default is http://yourdomain/callback. If use proxy like Nginx, you should fill it using public address
