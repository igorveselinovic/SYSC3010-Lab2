# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

host = sys.argv[1]
textport = sys.argv[2]
n = int(sys.argv[3])

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

# passing 0 as the port will tell Python to pick any available port
s.bind(("localhost", 0))
port = s.getsockname()[-1]

server_port = int(textport)
server_address = (host, server_port)

for i in range(n):

    data = "Message" + str(i)
    s.sendto(data.encode('utf-8'), server_address)
    print ("Waiting to receive on port %d : press Ctrl-C or Ctrl-Break to stop " % port)
    buf, address = s.recvfrom(port)
    print ("Received %s bytes from %s %s: " % (len(buf), address, buf ))
