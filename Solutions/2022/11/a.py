# December 10th, 2022

file = open(f"{__file__}/../input.txt", "r")

items = []
op = []
test = []
true = []
false = []

while file.readline().strip():
	items.append([ int(x) for x in file.readline().strip().split(": ")[1].split(", ") ])
	
	o = file.readline().strip().split("= ")[1].split(" ")
	op.append((o[1], int(o[2]) if o[2] != "old" else o[2]))

	test.append(int(file.readline().strip().split(" ")[-1]))
	true.append(int(file.readline().strip().split(" ")[-1]))
	false.append(int(file.readline().strip().split(" ")[-1]))

	file.readline().strip()

c = [ 0 for _ in range(len(items)) ]
for round in range(20):
	for i in range(len(items)):
		for item in list(items[i]):
			items[i].remove(item)
			c[i] += 1
			v = op[i][1] if op[i][1] != "old" else item
			if op[i][0] == "+":
				item += v
			else:
				item *= v
			item //= 3
			if item % test[i] == 0:
				items[true[i]].append(item)
			else:
				items[false[i]].append(item)

c = sorted(c, reverse=True)
print(c[0] * c[1])
