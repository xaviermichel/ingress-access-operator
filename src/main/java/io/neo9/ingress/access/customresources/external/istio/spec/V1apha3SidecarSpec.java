package io.neo9.ingress.access.customresources.external.istio.spec;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class V1apha3SidecarSpec {

	private List<IstioHostsListSpec> egress = new ArrayList<>();

}

