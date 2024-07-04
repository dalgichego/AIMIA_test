import time
from socket import *
from select import select
import sys
from _socket import AF_INET, socket, SOCK_STREAM

def sendmessage(msg):
    HOST = '127.0.0.1'
    PORT = 8112
    BUFSIZE = 1024
    clientSocket = socket(AF_INET, SOCK_STREAM)
    try:
        clientSocket.connect((HOST, PORT))

        clientSocket.sendall(msg.encode())
        print("Send: Hello, Java!")

        data = clientSocket.recv(BUFSIZE)
        print("Recived :", repr(data.decode()))

        clientSocket.close()
        print("client finish")
        return True;
    except Exception as e:
        print(e)

sendmessage("3\n")