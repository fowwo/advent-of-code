# November 30th, 2023

file = open(f"{__file__}/../input.txt", "r")

s = 0
for line in file.readlines():
	b = [ int(c) for c in line if c.isdigit() ]
	s += 10 * b[0] + b[-1]
print(s)
