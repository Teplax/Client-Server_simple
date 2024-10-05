package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
public static final Integer PORT = 8080;

    public static void main(String[] args) throws IOException {
        //Запускаем сервер на сокете с портом 8080
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            System.out.println("Сервер запущен");
            while (true){
                try(Socket clientSocket = serverSocket.accept();//Принимаем запрос на подключение от клиента
                    //Создаём поток для ответа клиенту с данными о его сокете
                    PrintWriter printWriter= new PrintWriter(clientSocket.getOutputStream(), true);
                    //Создаём поток для чтения сообщения от клиента:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ){
                    //Присваиваем сообщение от клиента строке и выводим его на экран
                    String infoFromClient = bufferedReader.readLine();
                    System.out.printf("Новое подключение установлено. Данные клиента: %s, порт: %d%n", infoFromClient,clientSocket.getPort());
                    //передаём ответ клиенту:
                    printWriter.printf("Привет из СПб! Ваш порт: %d%n", clientSocket.getPort());
                }
            }

        }
        catch(IOException e){
            throw new RuntimeException();
        }
    }
}
