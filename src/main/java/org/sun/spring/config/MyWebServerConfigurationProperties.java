package org.sun.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by tianfei on 17/2/27.
 */
@ConfigurationProperties(prefix = "my.webserver")
public class MyWebServerConfigurationProperties {
    private int port;
    private ThreadPool threadPool;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ThreadPool getThreadPool() {
        return threadPool;
    }

    public void setThreadPool(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public static class ThreadPool {
        private int maxThreads;
        private int minThreads;
        private int idleTimeout;

        public int getIdleTimeout() {
            return idleTimeout;
        }

        public void setIdleTimeout(int idleTimeout) {
            this.idleTimeout = idleTimeout;
        }

        public int getMaxThreads() {
            return maxThreads;
        }

        public void setMaxThreads(int maxThreads) {
            this.maxThreads = maxThreads;
        }

        public int getMinThreads() {
            return minThreads;
        }

        public void setMinThreads(int minThreads) {
            this.minThreads = minThreads;
        }
    }
}
