# August 9th, 2022

file = open(f"{__file__}/../input.txt", "r")
cardPublicKey = int(file.readline())
doorPublicKey = int(file.readline())
file.close()

x = 1
loopSize = 0
while x != cardPublicKey:
	x *= 7
	x %= 20201227
	loopSize += 1

x = 1
for _ in range(loopSize):
	x *= doorPublicKey
	x %= 20201227
print(x)
