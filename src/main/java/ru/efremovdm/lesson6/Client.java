package ru.efremovdm.lesson6;

/**
 * Кдиент
 */
class Client {

    public static void main(String[] args) {

        final Connector conn = new Connector("client");
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
