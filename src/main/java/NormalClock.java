/**
 * 一个正常的钟实现
 */
  class NormalClock extends Clock {

    @Override
    void checkState() {
        return;
    }

    @Override
    void delay() throws InterruptedException {
        Thread.sleep(1000L);
    }

}