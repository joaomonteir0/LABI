from asyncore import read
from hashlib import sha256
import sys
import os
from Crypto.Cipher import AES
from Crypto.Hash import SHA256
import random
import string


if len(sys.argv) < 3:
    #print("Invalid arguments!\n must be:\n>>> python3 ex8.py ficheiro.txt chave > criptograma")
    exit(1)
chave = sys.argv[2]

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

f = open(sys.argv[1], "rb")
text = f.read()
f.close()

cryptogram = ""
cipher = AES.new(chave)

for i in range(0,int(len(text)/16)):
    cryptogram = cipher.encrypt(str(text[i*16:(i+1)*15]))
    print(cryptogram)