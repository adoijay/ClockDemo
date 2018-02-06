/**
 * 一个损坏的钟实现
 */
  class BrokenClock extends Clock {

    @Override
    void checkState() {
        throw new IllegalStateException();
    }

    @Override
    void delay() throws InterruptedException {
        Thread.sleep(10000L);
    }

}