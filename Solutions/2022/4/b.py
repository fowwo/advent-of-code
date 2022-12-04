# December 3rd, 2022

file = open(f"{__file__}/../input.txt", "r")

s = 0
for line in file.readlines():
	line = line.strip().split(',')
	a = [ int(x) for x in line[0].split('-') ]
	b = [ int(x) for x in line[1].split('-') ]
	x = sorted(( a, b ))
	if x[0][1] >= x[1][0]:
		s += 1
print(s)
