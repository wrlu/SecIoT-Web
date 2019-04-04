import socketserver


class SocketServer(socketserver.BaseRequestHandler):
    def handle(self):
        data = self.request.recv(2048)


if __name__ == '__main__':
    server = socketserver.ThreadingTCPServer(('localhost', 8888), SocketServer)
    server.serve_forever()
