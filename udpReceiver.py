# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

BUFFER_SIZE = 100

textport = sys.argv[1]

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = ('localhost', port)
s.bind(server_address)

PREFIX = "ACK: ".encode("utf-8")

while True:

    print ("Waiting to receive on port %d : press Ctrl-C or Ctrl-Break to stop " % port)
    buf, address = s.recvfrom(BUFFER_SIZE)
    print ("Received %s bytes from %s %s: " % (len(buf), address, buf))
    data = PREFIX + buf
    print ("Sending $s\n", (data))
    s.sendto(data, address)
    print()
