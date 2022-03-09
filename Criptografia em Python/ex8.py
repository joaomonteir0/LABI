from asyncore import read
from hashlib import sha256
import sys
import os
from Crypto.Cipher import AES
from Crypto.Hash import SHA256

if len(sys.argv) < 3:
    print("Invalid arguments!\n must be:\n>>> python3 ex8.py ficheiro.txt chave > criptograma")
    exit(1)

f = open(sys.argv[1], "r")
text = f.read()

chave = sys.argv[2]
print(text)
print(chave)
if len(chave) < 16:
    novaChave = SHA256.new()
    novaChave.update(chave.encode("utf-8"))
    novaChave = novaChave.hexdigest()
    novaChave= novaChave[0:16]
    chave = novaChave
elif len(chave) > 16 and len(chave) < 24:
    novaChave = SHA256.new()
    novaChave.update(chave.encode("utf-8"))
    novaChave = novaChave.hexdigest()
    novaChave= novaChave[0:24]
    chave = novaChave
elif len(chave) > 24 and len(chave) < 32:
    novaChave = SHA256.new()
    novaChave.update(chave.encode("utf-8"))
    novaChave = novaChave.hexdigest()
    novaChave= novaChave[0:32]
    chave = novaChave
elif len(chave) > 32:
    novaChave = SHA256.new()
    novaChave.update(chave.encode("utf-8"))
    novaChave = novaChave.hexdigest()
    novaChave= novaChave[0:32]
    chave = novaChave
print(chave)