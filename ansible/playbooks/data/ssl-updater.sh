#!/bin/bash

cp /etc/letsencrypt/live/{{ server_dns }}/fullchain.pem /home/{{ app_name }}/app/fullchain.pem
chown {{ app_name }}:{{ app_name }} /home/{{ app_name }}/app/fullchain.pem
chmod 600 /home/{{ app_name }}/app/fullchain.pem

cp /etc/letsencrypt/live/{{ server_dns }}/privkey.pem /home/{{ app_name }}/app/privkey.pem
chown {{ app_name }}:{{ app_name }} /home/{{ app_name }}/app/privkey.pem
chmod 600 /home/{{ app_name }}/app/privkey.pem

openssl pkcs12 -export -out /home/{{ app_name }}/app/keystore.p12 -inkey /home/{{ app_name }}/app/privkey.pem -in /home/{{ app_name }}/app/fullchain.pem -name {{app_name}} -passout pass:'{{keystore_password}}'
chown {{ app_name }}:{{ app_name }} /home/{{ app_name }}/app/keystore.p12
chmod 600 /home/{{ app_name }}/app/keystore.p12
