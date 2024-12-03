package sa.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
@Component
public class DeliveryScheduler {
    private final TaskScheduler scheduler;
    private final ConcurrentHashMap<Long, ScheduledFuture> deliveryTask = new ConcurrentHashMap<>();

    public void reserve(Long deliveryId) {

    }

    public void cancel(Long deliveryId) {
        ScheduledFuture future = deliveryTask.get(deliveryId);
        if (future != null) {
            future.cancel(true);
            deliveryTask.remove(deliveryId);
        }
    }

    public Duration getDuration (LocalDateTime time) {
        return Duration.between(LocalDateTime.now(), time);
    }
}
