# December 4th, 2022

file = open(f"{__file__}/../input.txt", "r")

lists = [
	[ x for x in "RNFVLJSM" ],
	[ x for x in "PNDZFJWH" ],
	[ x for x in "WRCDG" ],
	[ x for x in "NBS" ],
	[ x for x in "MZWPCBFN" ],
	[ x for x in "PRMW" ],
	[ x for x in "RTNGLSW" ],
	[ x for x in "QTHFNBV" ],
	[ x for x in "LMHZNF" ]
]

for line in file.readlines():
	line = line.strip().split(' ')
	a = int(line[1])
	b = int(line[3]) - 1
	c = int(line[5]) - 1
	for _ in range(a):
		x = lists[b].pop()
		lists[c].append(x)

for x in lists:
	print(x.pop(), end="")
print()
