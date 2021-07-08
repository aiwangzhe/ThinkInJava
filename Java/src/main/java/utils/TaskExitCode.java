package utils;

public enum TaskExitCode {
        EXIT_ABNORMAL(-1), KILLED(-2), TIME_OUT(-5);

        public final int value;

        TaskExitCode(int value) {
            this.value = value;
        }
}