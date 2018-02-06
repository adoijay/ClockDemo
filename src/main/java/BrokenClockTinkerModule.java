/**
 * 修复损坏的钟模块
 */

import javax.annotation.*;

import com.alibaba.jvm.sandbox.api.*;
import com.alibaba.jvm.sandbox.api.http.Http;
import com.alibaba.jvm.sandbox.api.ProcessControlException;
import com.alibaba.jvm.sandbox.api.event.Event;
import com.alibaba.jvm.sandbox.api.resource.*;
import com.alibaba.jvm.sandbox.api.filter.*;
import com.alibaba.jvm.sandbox.api.listener.EventListener;


@Information(id = "broken-clock-tinker")
public class BrokenClockTinkerModule implements Module {

    @Resource
    private ModuleEventWatcher moduleEventWatcher;

    @Http("/repairCheckState")
    public void repairCheckState() {

        moduleEventWatcher.watch(

                // 匹配到Clock$BrokenClock#checkState()
                new NameRegexFilter("Clock\\$BrokenClock", "checkState"),

                // 监听THROWS事件并且改变原有方法抛出异常为正常返回
                new EventListener() {
                    public void onEvent(Event event) throws Throwable {
                        // 立即返回
                        ProcessControlException.throwReturnImmediately(null);
                    }
                },

                // 指定监听的事件为抛出异常
                Event.Type.THROWS
        );

    }

}