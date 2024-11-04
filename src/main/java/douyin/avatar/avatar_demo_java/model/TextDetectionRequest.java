package douyin.avatar.avatar_demo_java.model;

import java.util.List;

public class TextDetectionRequest {

    private List<Task> tasks;

    // 定义内部类Task，对应JSON中的tasks数组内的对象

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}