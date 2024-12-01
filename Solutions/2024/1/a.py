# November 30th, 2024

file = open(f"{__file__}/../input.txt", "r")

a = []
b = []

for line in file:
	x, y = map(int, line.strip().split("   "))
	a.append(x)
	b.append(y)
file.close()

a.sort()
b.sort()

print(sum(abs(x - y) for x, y in zip(a, b)))
