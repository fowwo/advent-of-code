# November 30th, 2024

from collections import Counter

file = open(f"{__file__}/../input.txt", "r")

a = []
b = Counter()

for line in file:
	x, y = map(int, line.strip().split("   "))
	a.append(x)
	b[y] += 1
file.close()

print(sum(x * b[x] for x in a))
