# December 7th, 2023

file = open(f"{__file__}/../input.txt", "r")

n = file.readline().strip()
file.readline()

d = dict()
for line in file:
	[ a, b ] = line.strip().split(" = ")
	d[a] = b[1:-1].split(", ")

k = "AAA"
c = 0
while True:
	for x in n:
		c += 1
		k = d[k][0 if x == 'L' else 1]
		if k == "ZZZ":
			print(c)
			exit()
