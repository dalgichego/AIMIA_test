import json
import time
from socket import *
from select import select
import sys
from _socket import AF_INET, socket, SOCK_STREAM


def sendrec(msg):
    HOST = '127.0.0.1'
    PORT = 8112
    BUFSIZE = 1024
    clientSocket = socket(AF_INET, SOCK_STREAM)
    try:
        clientSocket.connect((HOST, PORT))

        sdata = {'name': 'client', 'contents': msg, 'num': num}
        sdata = json.dumps(sdata)+'\n'
        clientSocket.sendall(sdata.encode('UTF-8'))
        print("Send: ", sdata)

        rdata = clientSocket.recv(BUFSIZE)
        rdata = json.loads(rdata)
        print("Recived :", rdata)

        clientSocket.close()
        print("client finish")
        return rdata
    except Exception as e:
        print(e)

num=0
sendrec("this is a content from Python")
