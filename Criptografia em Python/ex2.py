import sys
import hashlib


if len(sys.argv) < 3:
    print("Invalid args!")

h = hashlib.sha1()

f = open(sys.argv[1], "rb")
buffer = f.read(512)
print("Original text: ", buffer)

while len(buffer) > 0:
    h.update(buffer)
    buffer = f.read(512)

print("Encrypted text: ", h.hexdigest())

f = open(sys.argv[2], "w")
f.write(h.hexdigest())
f.close()

