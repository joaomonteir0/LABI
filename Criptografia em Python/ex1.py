import hashlib
import sys

f = open(sys.argv[1], "r")
text = f.read()
f.close()
print("Original text: ",text)
h = hashlib.sha1()
h.update(text.encode("utf-8"))
encryptedText = h.hexdigest()

print("Encrypted text: ",encryptedText)
f = open(sys.argv[2], "w")
f.write(encryptedText)
f.close()

print("Done!")