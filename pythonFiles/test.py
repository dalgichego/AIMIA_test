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
        clientSocket.sendall((bytes(msg, 'UTF_8')))
        print("Send: Hello, Java!")
        clientSocket.close()
        print("client finish")
        return True;
    except Exception as e:
        print(e)

sendmessage("HELLO JAVA!")