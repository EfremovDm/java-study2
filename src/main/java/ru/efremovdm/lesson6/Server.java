package ru.efremovdm.lesson6;

/**
 * Сервер
 */
class Server {

    public static void main(String[] args) {

        final Connector conn = new Connector("server");
        conn.start();

        new Thread() {
            public void run() {
                conn.readMessage();
            }
        }.start();

        new Thread() {
            public void run() {
                conn.sendMessage();
            }
        }.start();
    }
}