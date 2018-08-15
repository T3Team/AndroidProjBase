package base.t3t.baseprojlib.base;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * Created by dale on 2018/8/13.
 */
public abstract class BaseViewModel<T> extends AndroidViewModel {


    private ObservableEmitter<T> subscriber;

    private Observable<T> operation = Observable.create(new ObservableOnSubscribe<T>() {
        @Override
        public void subscribe(ObservableEmitter<T> e) throws Exception {
            subscriber = e;
        }
    });

    public BaseViewModel(@NonNull Application application) {
        super(application);

    }

    public Observable<T> getObservable() {
        return operation;
    }

    public void setUiObservableData(T t) {
        subscriber.onNext(t);
    }

    public abstract void loadData();

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
