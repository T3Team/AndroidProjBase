package t3t.baseproj.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

//Lifecycle演示
public class TestLifecycleObserver implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void ON_CREATE() {
        System.out.println("@@@@@@@@MyObserver:ON_CREATE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void ON_START() {
        System.out.println("@@@@@@@@MyObserver:ON_START");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void ON_RESUME() {
        System.out.println("@@@@@@@@MyObserver:ON_RESUME");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void ON_PAUSE() {
        System.out.println("@@@@@@@@MyObserver:ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void ON_STOP() {
        System.out.println("@@@@@@@@MyObserver:ON_STOP");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void ON_DESTROY() {
        System.out.println("@@@@@@@@MyObserver:ON_DESTROY");
    }

}
