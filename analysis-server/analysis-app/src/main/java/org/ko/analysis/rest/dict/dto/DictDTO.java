package org.ko.analysis.rest.dict.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ko.analysis.store.ads.domain.Dict;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
public class DictDTO extends Dict {

}