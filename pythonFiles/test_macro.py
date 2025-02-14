import win32api, win32gui, win32con, win32ui
import time


hWnd = win32gui.FindWindow(None, "Minecraft 1.21.4 - 싱글플레이")
print(hWnd)

def click1(x, y):
    lParam = win32api.MAKELONG(x, y)

    win = win32ui.CreateWindowFromHandle(hWnd)
    win.PostMessage(win32con.WM_LBUTTONDOWN, win32con.MK_LBUTTON, lParam)
    win.PostMessage(win32con.WM_LBUTTONUP, None, lParam)

# def click2(x, y):
#     lParam = win32api.MAKELONG(x, y)
#
#     hWnd1 = win32gui.FindWindowEx(hWnd, None, None, None)
#     win32gui.PostMessage(hWnd1, win32con.WM_LBUTTONDOWN, win32con.MK_LBUTTON, lParam)
#     win32gui.PostMessage(hWnd1, win32con.WM_LBUTTONUP,  win32con.MK_LBUTTON, lParam)


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


time.sleep(2)
# 테스트: 초당 (5px, -2px) 속도로 3초 동안 이동
move_mouse_velocity(-1, -2, 5)

# while True:
#     move_mouse_relative(20, 20)
#     time.sleep(1)
# # import time
# #
# # import pyautogui
# #
# # print(pyautogui.size())
# # print(pyautogui.position())
# #
# # time.sleep(4)
# # print(pyautogui.position())
# # x = pyautogui.position()[0]
# # y = pyautogui.position()[1]
# #
# # pyautogui.moveTo(1117, 490)
# # pyautogui.click()
# # pyautogui.press('e')
# #
# # pyautogui.keyDown('w')
# # time.sleep(4)
# # for i in range(100):
# #     pyautogui.moveTo(x-0.1, y+0.01, 0.1)
# # pyautogui.keyUp('w')
# # # def move_smooth(xm, ym, t):
# # #     for i in range(t):
# # #         if i < t/2:
# # #             h = i
# # #         else:
# # #             h = t - i
# # #         pyautogui.moveTo(h*xm, h*ym)
# # #         time.sleep(1/60)
# #
# # # move_smooth(20, 1, 40)
# #
# # #
# # # for i in range(10):
# # #     time.sleep(2)
# # #     print(pyautogui.position())
# # #     pyautogui.moveTo(pyautogui.position()[0]-10, pyautogui.position()[1]+10, 1)
# import time
#
# import pynput
# mouse = pynput.mouse.Controller()
# mouse.move(10, 10)
#
# def move_smooth(xm, ym, t):
#     for i in range(t):
#         if i < t/2:
#             h = i
#         else:
#             h = t - i
#         mouse.move(h*xm, h*ym)
#         time.sleep(1/60)
#
# move_smooth(2, 2, 40)