# February 21st, 2022

file = open("input/2017/13.txt", "r")

severity = 0
for line in file:
	split = line.split(": ")
	d = int(split[0])
	r = int(split[1]) - 1
	if abs((d - r) % (2 * r) - r) == 0:
		severity += d * (r + 1)
print(severity)
