package io.neo9.gatekeeper.customresources;

import java.util.List;

import io.fabric8.kubernetes.client.CustomResource;
import io.fabric8.kubernetes.model.annotation.Group;
import io.fabric8.kubernetes.model.annotation.ShortNames;
import io.fabric8.kubernetes.model.annotation.Version;
import io.neo9.gatekeeper.customresources.spec.V1VisitorGroupSpec;
import io.neo9.gatekeeper.customresources.spec.V1VisitorGroupSpecSources;

@Group("mutable.neo9.io")
@Version("v1")
@ShortNames("vg")
public class VisitorGroup extends CustomResource<V1VisitorGroupSpec, Void> {

	public List<V1VisitorGroupSpecSources> extractSpecSources() {
		return getSpec().getSources();
	}

}


