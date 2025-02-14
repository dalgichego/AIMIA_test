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

        for i in range(10):
            rdata = clientSocket.recv(BUFSIZE)
            rdata = json.loads(rdata)
            print("Recived :", rdata)

            sdata = rdata
            sdata = json.dumps(sdata)+'\n'
            clientSocket.sendall(sdata.encode('UTF-8'))
            print("Send: ", sdata)

        clientSocket.close()
        print("client finish")
        return rdata
    except Exception as e:
        print(e)

num=0
print("this is python")
sendrec("this is a content from Python")



import win32api, win32gui, win32con, win32ui
import time

hWnd = win32gui.FindWindow(None, "Minecraft 1.20.4 - 멀티플레이 (제삼자 서버)")
print(hWnd)

def click1(x, y):
    lParam = win32api.MAKELONG(x, y)

    win = win32ui.CreateWindowFromHandle(hWnd)
    win.PostMessage(win32con.WM_LBUTTONDOWN, win32con.MK_LBUTTON, lParam)
    win.PostMessage(win32con.WM_LBUTTONUP, None, lParam)

def move_mouse_relative(dx, dy):
    """ 현재 마우스 위치에서 (dx, dy) 만큼 상대 이동 (비활성 지원) """
    # 현재 마우스 위치 가져오기
    cursor_x, cursor_y = win32gui.GetCursorPos()

    # 새로운 좌표 계산
    new_x, new_y = cursor_x + dx, cursor_y + dy
    lParam = win32api.MAKELONG(960, 540)

    # 마우스 이동 메시지 전달
    win = win32ui.CreateWindowFromHandle(hWnd)
    win.PostMessage(win32con.WM_MOUSEMOVE, 0, lParam)


def move_mouse_velocity(dx_per_sec, dy_per_sec, duration):
    """
    마우스를 초당 dx_per_sec, dy_per_sec 픽셀 속도로 duration(초) 동안 이동.
    백그라운드에서도 실행 가능하도록 PostMessage 사용.
    """

    start_time = time.time()
    win = win32ui.CreateWindowFromHandle(hWnd)

    rect = win32gui.GetWindowRect(hWnd)
    win_x, win_y, win_width, win_height = rect  # (left, top, right, bottom)
    center_x = (win_x + win_width) // 2
    center_y = (win_y + win_height) // 2

    cursor_x, cursor_y = win32api.GetCursorPos()
    print(cursor_x, cursor_y)
    cursor_x, cursor_y = win32gui.ScreenToClient(hWnd, (cursor_x, cursor_y))
    # cursor_x -= win_x
    # cursor_y -= win_y
    print(cursor_x, cursor_y)

    while time.time() - start_time < duration:

        lParam = win32api.MAKELONG(cursor_x+dx_per_sec, cursor_y+dy_per_sec)

        # 백그라운드 마우스 이동 메시지 전달
        win.PostMessage(win32con.WM_MOUSEMOVE, 0, lParam)

        # 10ms (100FPS) 간격으로 반복 실행
        time.sleep(0.001)
# 테스트: 초당 (5px, -2px) 속도로 3초 동안 이동
move_mouse_velocity(-1, -2, 5)