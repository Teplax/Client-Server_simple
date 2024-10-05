package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //Запускаем клиентский сокет и подключаем его к порту сервера
        try(Socket clientSocket = new Socket("localhost", Server.PORT);
            //Создаём поток для отправки сообщения на сервер
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            //Создаём поток для приёма отета сервера
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())))
        {
            //передаём сообщение клиента в поток
            writer.println("Hello from Alexander");
            //выводим в консоль ответ сервера
            System.out.println("Ответ сервера: "+reader.readLine());
        }
        catch (IOException e){
            throw new RuntimeException();
        }
    }
}
