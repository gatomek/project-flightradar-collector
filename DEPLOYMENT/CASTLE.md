# Castle Deployment

* linux

## File system layout
```
- 📂 /home
    - 📂 /radar-collector
        - 📂 /app
            - 📄 radar-collector.jar
            - ⚙️ prod.env
            - 🔑 privkey.pem
            - 🔑 fullchain.pem
            - 🔑 keystore.p12
        - 📂 /scripts
            - 📜 ssl-updater.sh
- 📂 /etc
    - 📂 /systemd
        - 📂/system
            - ⚙️ radar-collector.service
            - ⚙️ devops-radar-collector-ssl-updater.timer
            - ⚙️ devops-radar-collector-ssl-updater.service
```
