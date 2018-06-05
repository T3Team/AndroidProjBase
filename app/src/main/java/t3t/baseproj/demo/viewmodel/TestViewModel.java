package t3t.baseproj.demo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import t3t.baseproj.demo.User;

public class TestViewModel extends ViewModel {
    private MutableLiveData<User> mUser;

    public LiveData<User> getUser() {
        if (mUser == null) {
            mUser = new MutableLiveData<User>();
            loadUser();
        }
        return mUser;
    }

    private void loadUser() {
        User user = new User();
        user.setId("0");
        user.setUserName("Test");
        mUser.postValue(user);
    }

    public void setUser(User user) {
        mUser.postValue(user);
    }
}
