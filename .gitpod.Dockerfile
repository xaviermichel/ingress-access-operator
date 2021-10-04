FROM gitpod/workspace-full

# kubectl
RUN curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add - && \
    echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list && \
    sudo apt-get update && \
    sudo apt-get install -y kubectl

# k3d
RUN curl -s https://raw.githubusercontent.com/rancher/k3d/main/install.sh | TAG=v4.4.8 bash

# disable native image during development by default
ENV NATIVE_IMAGE=false

