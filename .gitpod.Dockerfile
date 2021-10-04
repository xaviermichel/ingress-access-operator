FROM gitpod/workspace-full

# kubectl
RUN curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add - && \
    echo "deb https://apt.kubernetes.io/ kubernetes-xenial main" | sudo tee -a /etc/apt/sources.list.d/kubernetes.list && \
    sudo apt-get update && \
    sudo apt-get install -y kubectl

# k3d
RUN curl -s https://raw.githubusercontent.com/rancher/k3d/main/install.sh | TAG=v4.4.8 bash

# k3d support (https://github.com/gitpod-io/gitpod/issues/4889)
RUN touch /dev/kmsg
RUN apt-get install fuse3 uidmap
RUN mkdir /workspace/kubelet && mount --rbind /workspace/kubelet /home/gitpod/.rancher/k3s/agent/kubelet
ENV XDG_RUNTIME_DIR=/workspace/k3s/config
RUN mkdir -p -m 700 $XDG_RUNTIME_DIR

# disable native image during development by default
ENV NATIVE_IMAGE=false

