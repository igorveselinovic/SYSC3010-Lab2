# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

BUFFER_SIZE = 100

host = sys.argv[1]
textport = sys.argv[2]
n = int(sys.argv[3])

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = s.getsockname()[-1]

server_port = int(textport)
server_address = (host, server_port)

for i in range(n):

    dataStr = "Message" + str(i)
    data = dataStr.encode('utf-8')
    print ("Sending", data)
    s.sendto(data, server_address)
    print ("Waiting to receive on port %d : press Ctrl-C or Ctrl-Break to stop " % port)
    buf, address = s.recvfrom(BUFFER_SIZE)
    print ("Received %s bytes from %s %s: \n" % (len(buf), address, buf ))
