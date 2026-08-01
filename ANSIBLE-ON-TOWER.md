# Setup

## Tower

## Prerequisites

* docker context `docker-on-tower` prepared on local machine with docker agent

```
docker context create docker-on-tower --docker host=tcp://tower:2375
```

### Setup

```sh
docker --context docker-on-tower compose --file remote-ansible.yml run --rm --build collector-setup
```

### Stop

```sh
docker --context docker-on-tower compose --file remote-ansible.yml run --rm --build collector-stop
```

### Start

```sh
docker --context docker-on-tower compose --file remote-ansible.yml run --rm --build collector-start
```

### Restart

```sh
docker --context docker-on-tower compose --file remote-ansible.yml run --rm --build collector-restart
```
