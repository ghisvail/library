package io.pillopl.library.lending.domain.patron;

import io.pillopl.library.lending.domain.patron.PatronResourcesEvent.ResourceCollected;
import io.pillopl.library.lending.domain.patron.PatronResourcesEvent.ResourcePlacedOnHold;
import io.pillopl.library.lending.domain.resource.Resource;
import lombok.Value;

import java.util.Set;

@Value
//TODO add not null
public class ResourcesOnHold {

    Set<ResourceOnHold> resourcesOnHold;

    ResourcePlacedOnHold hold(Resource resourceToHold, PatronInformation patronInformation) {
        ResourceOnHold resourceOnHold = new ResourceOnHold(resourceToHold);
        return ResourcePlacedOnHold.now(resourceOnHold.getResourceId(), resourceOnHold.getLibraryBranchId(), patronInformation);
    }

    ResourceCollected complete(ResourceOnHold resourceToCollect, PatronInformation patronInformation) {
        return ResourceCollected.now(resourceToCollect.getResourceId(), resourceToCollect.getLibraryBranchId(), patronInformation.getPatronId());
    }

    boolean doesNotContain(ResourceOnHold resourceOnHold) {
        return !resourcesOnHold.contains(resourceOnHold);
    }

    int count() {
        return resourcesOnHold.size();
    }

}