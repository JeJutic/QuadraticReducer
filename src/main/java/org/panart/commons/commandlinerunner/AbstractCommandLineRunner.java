package org.panart.commons.commandlinerunner;

import java.util.Arrays;

public abstract class AbstractCommandLineRunner implements CommandLineRunner {

    private String flag;

    public AbstractCommandLineRunner(String flag) {
        setFlag(flag);
    }

    private void setFlag(String flag) {
        if (flag == null || !isFlag(flag)) {
            throw new IllegalArgumentException();
        }

        this.flag = flag;
    }

    boolean isFlag(String argument) {
        return argument.startsWith("-");
    }

    @Override
    public void run(String[] args) {
        int start = -1;
        int end = args.length;
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                if (start != -1) {
                    throw new IllegalArgumentException();
                }
            } else if (args[i].equals(flag)) {
                if (start == -1) {
                    start = i + 1;
                } else {
                    end = i;
                    break;
                }
            }
        }
        if (start == -1) {
            notFoundCase();
        } else {
            runImpl(Arrays.copyOfRange(args, start, end));
        }
    }

    protected abstract void runImpl(String[] args);

    protected void notFoundCase() { // can be overriden
    }
}
