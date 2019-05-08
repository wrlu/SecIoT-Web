import socketserver
import json


class SocketServer(socketserver.BaseRequestHandler):
    def handle(self):
        r_data = self.request.recv(2048)
        data = json.loads(r_data.decode('utf8'))
        print("Receive data: "+str(data))


if __name__ == '__main__':
    server = socketserver.ThreadingTCPServer(('0.0.0.0', 8042), SocketServer)
    server.serve_forever()
