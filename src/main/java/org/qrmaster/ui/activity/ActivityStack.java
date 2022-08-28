package org.qrmaster.ui.activity;
import java.util.Stack;
public class ActivityStack {
    private final Stack<Activity> activityStackInterface;
    public ActivityStack(){
        activityStackInterface = new Stack<>();
    }
    public Stack<Activity> getStackInit() throws NoSuchFieldException, IllegalAccessException {
        Stack<Activity> stack = new Stack<>();
        return stack;
    }
    public Activity peekActivity(){
        return activityStackInterface.peek();
    }
    public Activity popActivity(){
        return activityStackInterface.pop();
    }
    public Activity pushActivity(Activity activity){
        activityStackInterface.push(activity);
        return activity;
    }
    public Stack<Activity> getActivityStackInterface() {
        return activityStackInterface;
    }
    public void clearActivityStack(){
        activityStackInterface.clear();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Activity activity:activityStackInterface){
            sb.append(activity.toString()).append("\n");
        }
        return sb.toString();
    }
}
