package com.bigdata.hy_tools.utils;

public class ClassUtils {
    private String command;

    public String getCommand() {
        if (command == null) {
            this.command = this.getClass().getSimpleName();
        }
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public static void main(String[] args) {
        ClassUtils classUtils = new ClassUtils();
        System.out.println(classUtils.getCommand());
    }
}
