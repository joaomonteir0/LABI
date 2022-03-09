from pydoc import cram
import sys
import os
from Crypto.Cipher import ARC4
from Crypto.Hash import SHA


if len(sys.argv) < 3:
    print("Invalid arguments!")
    exit(1)

ficheiro = sys.argv[1]
chave = sys.argv[2]

if len(chave) < 5:
    h = SHA.new()
    h.update(chave.encode("utf-8"))
    chave = h.hexdigest()
elif len(chave) > 256:
    chave = chave[0:256]

f = open(ficheiro, 'rb')
text = f.read()
f.close()

# cifrar
cipher = ARC4.new(chave)
cryptogram = cipher.encrypt(text)
os.write(1, cryptogram)


# decifrar == original se estiver com a mesma chave
decipher = ARC4.new(chave)
decrypted = decipher.decrypt(cryptogram)
os.write(1, decrypted)