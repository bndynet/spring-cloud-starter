# Resource Server

## For Keycloak 
 
Refer https://www.notion.so/bndynet/Keycloak-1153bd6b4b694ab4a03f33b6945277d1
to set up a Keycloak server.

1. Start your keycloak
1. Add a new client: foo
1. Get access token
1. Use the token to access APIs of Resource Server

Open http://127.0.0.1:8081/doc.html to view all APIs document.

## ELK

1. [How to install ELK?](https://elk-docker.readthedocs.io/#installation)
    - Kibana web interface: http://127.0.0.1:5601/
    - Elasticsearch JSON interface: http://127.0.0.1:9200/
    - 5044 (Logstash Beats interface, receives logs from Beats such as Filebeat – see the Forwarding logs with Filebeat section).
