FROM gitpod/workspace-full

USER root

# kubectl
RUN curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add - && \
    echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list && \
    sudo apt-get update && \
    sudo apt install -y kubectl

# k3d
RUN curl -s https://raw.githubusercontent.com/rancher/k3d/main/install.sh | TAG=v4.4.8 bash

# k3d support (https://github.com/gitpod-io/gitpod/issues/4889)
# 2
RUN touch /dev/kmsg
# 3
RUN apt install -y fuse3 uidmap
# 4
# 5
# 6
RUN sudo mkdir /workspace/kubelet
RUN mkdir -p /home/gitpod/.rancher/k3s/agent/kubelet
#RUN sudo mount --rbind /workspace/kubelet /home/gitpod/.rancher/k3s/agent/kubelet
# 7
ENV XDG_RUNTIME_DIR=/workspace/k3s/config
RUN mkdir -p -m 700 $XDG_RUNTIME_DIR

USER gitpod

# disable native image during development by default
ENV NATIVE_IMAGE=false

