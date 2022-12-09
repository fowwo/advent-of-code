# December 8th, 2022

file = open(f"{__file__}/../input.txt", "r")

c = 0
for line in file:
	digits = line.strip().split(" | ")[1].split(" ")
	for d in digits:
		if len(d) in [ 2, 3, 4, 7 ]:
			c += 1
print(c)
