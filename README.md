# Classic_Seckill
 A classic seckill using SpringBoot, RabbitMQ and Redis.

- current method: Pessimistic Locking, use synchronized lock to control concurrency.
- running results:
![](.README_images/addb3741.png)
![](.README_images/8c281c1e.png)
![](.README_images/e92c3eb9.png)
![](.README_images/1e2bb50f.png)
![](.README_images/f96cf208.png)

It is proved that the system can handle 1000 requests in 1 second.

