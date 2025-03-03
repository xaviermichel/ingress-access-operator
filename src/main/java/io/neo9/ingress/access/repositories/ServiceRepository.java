package io.neo9.ingress.access.repositories;

import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ServiceRepository {

	private final KubernetesClient kubernetesClient;

	public ServiceRepository(KubernetesClient kubernetesClient) {
		this.kubernetesClient = kubernetesClient;
	}

	public List<Service> listWithLabel(String labelKey, String labelValue) {
		return kubernetesClient.services().inAnyNamespace().withLabel(labelKey, labelValue).list().getItems();
	}

	public Service patchLoadBalancerSourceRanges(Service service, List<String> sources) {
		return kubernetesClient.services()
			.inNamespace(service.getMetadata().getNamespace())
			.withName(service.getMetadata().getName())
			.edit(svc -> new ServiceBuilder(svc).editSpec().withLoadBalancerSourceRanges(sources).and().build());
	}

	public Service patchWithAnnotations(Service service, Map<String, String> annotations) {
		return kubernetesClient.services()
			.inNamespace(service.getMetadata().getNamespace())
			.withName(service.getMetadata().getName())
			.edit(svc -> new ServiceBuilder(svc).editMetadata().addToAnnotations(annotations).and().build());
	}

	public Service removeAnnotation(Service service, String annotationKey) {
		return kubernetesClient.services()
			.inNamespace(service.getMetadata().getNamespace())
			.withName(service.getMetadata().getName())
			.edit(svc -> new ServiceBuilder(svc).editMetadata().removeFromAnnotations(annotationKey).and().build());
	}

}
