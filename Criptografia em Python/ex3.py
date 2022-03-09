from hashlib import sha256
import sys
from Crypto.Hash import SHA256


f = open(sys.argv[1], "r")
text = f.read()
f.close()




h = SHA256.new()
h.update(text.encode("utf-8"))

f = open(sys.argv[2], "w")
f.write(h.hexdigest())
f.close()

print(h.hexdigest())