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
public class OrderScheduler {

    private final TaskScheduler scheduler;
    private final ConcurrentHashMap<Long, ScheduledFuture> orderTask = new ConcurrentHashMap<>();

    public void reserve(Long orderId, Runnable runnable){
        Duration duration = getDurationFor3Min();
        ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(runnable, duration);
        orderTask.put(orderId, future);
    }

    public void cancel(Long orderId){
        ScheduledFuture future = orderTask.get(orderId);
        if (future != null) {
            future.cancel(true);
            orderTask.remove(orderId);
        }
    }

    private Duration getDurationFor3Min(){
        return Duration.between(LocalDateTime.now(), LocalDateTime.now().plusMinutes(3));
    }
}
