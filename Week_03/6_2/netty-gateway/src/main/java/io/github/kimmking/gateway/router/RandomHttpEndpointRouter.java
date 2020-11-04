package io.github.kimmking.gateway.router;

import java.util.List;
import java.util.Random;

public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    private Random random;

    public RandomHttpEndpointRouter() {
        this.random = new Random();
    }

    @Override
    public String route(List<String> endpoints) {
        if (endpoints == null || endpoints.isEmpty()) {
            return null;
        }

        int size = endpoints.size();
        int idx = random.nextInt(size);

        return endpoints.get(idx);
    }
}
