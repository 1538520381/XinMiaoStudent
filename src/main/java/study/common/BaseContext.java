package study.common;

/**
 * @author Persolute
 * @version 1.0
 * @description 存取当前使用者标识id
 * @email 1538520381@qq.com
 * @date 2024/1/28 19:23
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
