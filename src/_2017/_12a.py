# February 21st, 2022

file = open("input/2017/12.txt", "r")

arr = []
for line in file:
	pipes = set()
	for x in line.split(" <-> ")[1].strip().split(", "):
		pipes.add(int(x))
	arr.append(pipes)

group = set()
temp = { 0 }
while len(temp):
	copy = set(temp)
	for x in temp:
		group.add(x)
		for y in arr[x]:
			if y not in group:
				copy.add(y)
		copy.remove(x)
	temp = set(copy)
print(len(group))
