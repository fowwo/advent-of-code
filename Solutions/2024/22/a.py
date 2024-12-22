# December 21st, 2024

file = open(f"{__file__}/../input.txt", "r")

n = 0
for line in file:
	x = int(line)
	for _ in range(2000):
		x ^= x * 64
		x %= 16777216
		x ^= x // 32
		x %= 16777216
		x ^= x * 2048 
		x %= 16777216
	n += x
file.close()

print(n)
