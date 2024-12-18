package sa.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class OrderScheduler {

    private TaskScheduler scheduler = new ConcurrentTaskScheduler();
    private final ConcurrentHashMap<Long, ScheduledFuture> orderTask = new ConcurrentHashMap<>();

    public void reserve(Long orderId, Runnable runnable) {
        Date startTime = calculateStartTimeFor3Min();
        ScheduledFuture<?> future = scheduler.schedule(runnable, startTime);
        orderTask.put(orderId, future);
    }

    public void cancel(Long orderId){
        ScheduledFuture future = orderTask.get(orderId);
        if (future != null) {
            future.cancel(true);
            orderTask.remove(orderId);
        }
    }

    private Date calculateStartTimeFor3Min() {
        long delayInMillis = 1 * 60 * 1000;
        return new Date(System.currentTimeMillis() + delayInMillis);
    }
}
