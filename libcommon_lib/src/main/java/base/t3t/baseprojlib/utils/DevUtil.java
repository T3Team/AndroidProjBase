package base.t3t.baseprojlib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Log;

import java.util.HashMap;

/**
 * 开发工具类
 */
public class DevUtil {

    private static Context mContext;

    private static boolean isDebug = true;
    private static boolean isAllowedKey = false;                          // 是否合法签名
    private static HashMap<String, Long> oldTimeMap = new HashMap<String, Long>();

    private static final String NOT_INITIALIZE_ERROR_STRING = DevUtil.class.getSimpleName()
            + " not initialize. Please run "
            + DevUtil.class.getSimpleName()
            + ".initialize() first !";

    public static void initialize(Context context) {
        mContext = context;
        debugAccess();
    }

    /**
     * 设置debug 一般情况下不要调用 主要用于测试
     *
     * @param isDebug
     */
    public static void setDebug(boolean isDebug) {
        DevUtil.isDebug = isDebug;
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName 你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void v(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }


        if (isDebug) {
            if (message.length() > 4000) {
                for (int i = 0; i < message.length(); i += 4000) {
                    if (i + 4000 < message.length()) {
                        Log.v(yourName, message.substring(i, i + 4000));
                    } else {
                        Log.v(yourName, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.v(yourName, message);
            }
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName 你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void e(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            if (message.length() > 4000) {
                for (int i = 0; i < message.length(); i += 4000) {
                    if (i + 4000 < message.length()) {
                        Log.e(yourName, message.substring(i, i + 4000));
                    } else {
                        Log.e(yourName, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.e(yourName, message);
            }
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName 你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void w(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            if (message.length() > 4000) {
                for (int i = 0; i < message.length(); i += 4000) {
                    if (i + 4000 < message.length()) {
                        Log.w(yourName, message.substring(i, i + 4000));
                    } else {
                        Log.w(yourName, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.w(yourName, message);
            }
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName 你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void i(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            if (message.length() > 4000) {
                for (int i = 0; i < message.length(); i += 4000) {
                    if (i + 4000 < message.length()) {
                        Log.i(yourName, message.substring(i, i + 4000));
                    } else {
                        Log.i(yourName, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.i(yourName, message);
            }
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName 你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void d(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            if (message.length() > 4000) {
                for (int i = 0; i < message.length(); i += 4000) {
                    if (i + 4000 < message.length()) {
                        Log.d(yourName, message.substring(i, i + 4000));
                    } else {
                        Log.d(yourName, message.substring(i, message.length()));
                    }
                }
            } else {
                Log.d(yourName, message);
            }
        }
    }

    /**
     * 时间调试信息，以yourName为tag，日志输出每步花费的时间。<br>
     * 线下版本才会输入调试信息，线上版本会自动关闭
     *
     * @param yourName 你的名字，避免与其他人统计区分
     * @param message
     * @throws Exception
     */
    public static void timePoint(String yourName, String message) {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }

        if (isDebug) {
            Long currentTime = System.currentTimeMillis();
            Long oldTimeTemp = oldTimeMap.get(yourName);
            if (oldTimeTemp == null) {
                oldTimeTemp = System.currentTimeMillis();
            }
            Long durationTime = currentTime - oldTimeTemp;
            Log.v(yourName, message + " durationTime:" + durationTime + " - tag:" + yourName);
            oldTimeTemp = currentTime;
            oldTimeMap.put(yourName, oldTimeTemp);
        }
    }

    /**
     * 通过签名判断是否为开发版，开发版keystore见项目根目录的debug.keystore，用这个文件覆盖用户目录中.android目录的同名文件
     *
     * @return
     * @throws Exception
     */
    public static boolean isDebug() {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }
        return isDebug;
    }

    /**
     * 通过签名判断是否为合法的key签名: 线下debug keystore和线上 keystore
     *
     * @return
     */
    public static boolean isAllowedKey() {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }
        return isAllowedKey;
    }

    /**
     * 判断是否开发版本、是否合法的签名
     */
    private static void debugAccess() {

        final int DEBUG_SIGNATURE_HASH = -545290802;
        final int ONLINE_SIGNATURE_HASH = 111111111;//jackzhoutodo  更换为线上key

        // 判断是否为调试状态
        // http://stackoverflow.com/questions/3029819/android-automatically-choose-debug-release-maps-api-key
        PackageManager manager = mContext.getPackageManager();

        try {

            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), PackageManager.GET_SIGNATURES);

            for (Signature sig : info.signatures) {
                int hash = sig.hashCode();
                if (hash == DEBUG_SIGNATURE_HASH) {
                    isDebug = true;
                    isAllowedKey = true;
                }

                if (hash == ONLINE_SIGNATURE_HASH) {
                    isDebug = false;
                    isAllowedKey = true;
                }
            }

        } catch (NameNotFoundException e) {
            isDebug = false;
        }

    }

    /**
     * 获取一个全局的Context
     *
     * @return
     */
    public static Context getContext() {
        if (mContext == null) {
            throw new RuntimeException(NOT_INITIALIZE_ERROR_STRING);
        }
        return mContext;
    }


}
