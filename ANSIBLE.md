# Setup

## Castle

### Setup

```sh
docker run --rm -v .:/project:ro -e ANSIBLE_CONFIG=/project/ansible/ansible.cfg -w /project/ansible gatomek_ansible ansible-playbook -i inventory.ini playbooks/setup.yml
```

### Stop

```sh
docker run --rm -v .:/project:ro -e ANSIBLE_CONFIG=/project/ansible/ansible.cfg -w /project/ansible gatomek_ansible ansible-playbook -i inventory.ini playbooks/stop.yml
```

### Start

```sh
docker run --rm -v .:/project:ro -e ANSIBLE_CONFIG=/project/ansible/ansible.cfg -w /project/ansible gatomek_ansible ansible-playbook -i inventory.ini playbooks/start.yml
```

### Restart

```sh
docker run --rm -v .:/project:ro -e ANSIBLE_CONFIG=/project/ansible/ansible.cfg -w /project/ansible gatomek_ansible ansible-playbook -i inventory.ini playbooks/restart.yml
```
