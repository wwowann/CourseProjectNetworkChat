package server;

import client.Client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args){
      new Server().start();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
            executorService.execute(new Runnable() {
            public void run() {
                new Client();
            }
        });
        executorService.shutdown();
    }
}
