FROM registry.gatomek.pl/projects/ansible
ARG CACHEBUST=1
COPY . /project
WORKDIR /project/ansible
ENV ANSIBLE_CONFIG=/project/ansible/ansible.cfg
